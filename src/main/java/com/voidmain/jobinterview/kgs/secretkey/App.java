package com.voidmain.jobinterview.kgs.secretkey;

import com.voidmain.jobinterview.kgs.secretkey.exception.IllegalAppModeException;
import com.voidmain.jobinterview.kgs.secretkey.exception.ParameterValidationException;
import com.voidmain.jobinterview.kgs.secretkey.model.AppMode;
import com.voidmain.jobinterview.kgs.secretkey.repositories.IDataRepository;
import com.voidmain.jobinterview.kgs.secretkey.repositories.impl.FileRepository;
import com.voidmain.jobinterview.kgs.secretkey.service.ICryptoService;
import com.voidmain.jobinterview.kgs.secretkey.service.IParameterValidatorService;
import com.voidmain.jobinterview.kgs.secretkey.service.impl.CommandLineParameterValidatorService;
import com.voidmain.jobinterview.kgs.secretkey.service.impl.KeyStoreCryptoService;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    static final Logger LOG = Logger.getLogger(App.class);

    static final IParameterValidatorService parameterValidatorService = new CommandLineParameterValidatorService();

    static final IDataRepository inputDataRepository = new FileRepository();

    static final IDataRepository keyStoreRepository = new FileRepository();

    static final IDataRepository outputDataRepository = new FileRepository();

    static final ICryptoService cryptoService = new KeyStoreCryptoService();

    static AppMode currentAppMode = AppMode.ENCRYPT;

    public static void main(String[] args) {
        LOG.info("KGS-Keystore crypto tool.....");
        final List<String> parameters = Collections.unmodifiableList(Arrays.asList(args));
        try {
            parameterValidatorService.validateParameters(parameters);
            currentAppMode = AppMode.mapToAppMode(args[0]);
            final String password = args[1];
            final File keyStore = new File(args[2]);
            final File inputFile = new File(args[3]);
            final File outputFile = new File(args[4]);
            keyStoreRepository.attach(keyStore.toURI(), true);
            inputDataRepository.attach(inputFile.toURI(), true);
            outputDataRepository.attach(outputFile.toURI(), false);
            switch (currentAppMode) {
                case DECRYPT:
                    cryptoService.decrypt(password, keyStoreRepository, inputDataRepository, outputDataRepository);
                    break;
                case ENCRYPT:
                    cryptoService.encryptData(password, keyStoreRepository, inputDataRepository, outputDataRepository);
                    break;
            }
        } catch (ParameterValidationException parameterValidationException) {
            LOG.error("OOPS: wrong parameters. Root Cause is: " + parameterValidationException.getMessage());
        } catch (MalformedURLException malformedURLException) {
            LOG.error("OOPS: WTF did you provide as input and/or output path. Are you kidding me?");
        } catch (IOException ioException) {
            LOG.error("OOPS: input and/or output and/or keystore file cannot be read. Check if files are present and you provided the correct paths");
        } /*catch (NoSuchAlgorithmException cryptoEx) {
            LOG.error("OOPS: Something went wrong with crypto support. We dont have an SHA256 implementation onboard.");
        }*/ catch (IllegalAppModeException e) {
            LOG.error("OOPS: Illegal appmode: " + e.getMessage());
            LOG.info("Try \"d\" for decryption od \"e\" for encryption as first parameter");
        }
    }
}