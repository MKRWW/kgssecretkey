package com.voidmain.jobinterview.kgs.secretkey;

import com.voidmain.jobinterview.kgs.secretkey.exception.ParameterValidationException;
import com.voidmain.jobinterview.kgs.secretkey.service.IParameterValidatorService;
import com.voidmain.jobinterview.kgs.secretkey.service.impl.CommandLineParameterValidatorService;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    static final Logger LOG = Logger.getLogger(App.class);

    static final IParameterValidatorService parameterValidatorService = new CommandLineParameterValidatorService();

    public static void main(String[] args) {
        LOG.info("KGS-Keystore crypto tool.....");
        final List<String> parameters = Collections.unmodifiableList(Arrays.asList(args));
        try {
            parameterValidatorService.validateParameters(parameters);
        } catch (ParameterValidationException parameterValidationException) {
            LOG.error("OOPS: wrong parameters. Root Cause is: " + parameterValidationException.getMessage());
        } /*catch (MalformedURLException malformedURLException) {
            LOG.error("OOPS: WTF did you provide as input and/or output path. Are you kidding me?");
        } catch (IOException ioException) {
            LOG.error("OOPS: input and/or output file cannot be read. Check if file is present and you provided the correct path");
        } catch (NoSuchAlgorithmException cryptoEx) {
            LOG.error("OOPS: Something went wrong with crypto support. We dont have an SHA256 implementation onboard.");
        }*/
    }
}