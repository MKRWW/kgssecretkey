package com.voidmain.jobinterview.kgs.secretkey.service.impl;

import com.voidmain.jobinterview.kgs.secretkey.repositories.IDataRepository;
import com.voidmain.jobinterview.kgs.secretkey.service.ICryptoService;

import javax.annotation.Nonnull;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class KeyStoreCryptoService implements ICryptoService {

    public static final String KEY_ALIAS = "keytool";
    public static final String CRYPTO_ALGORITHM = "RSA/CBC/PKCS5Padding";

    @Override
    public void decrypt(@Nonnull String password, @Nonnull IDataRepository keyStoreRepository, @Nonnull IDataRepository inputDataRepository, @Nonnull IDataRepository outputDataRepository) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, NoSuchPaddingException, InvalidKeyException {
        final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(new ByteArrayInputStream(keyStoreRepository.read()), password.toCharArray());
        final Key secretKey = keyStore.getKey(KEY_ALIAS, password.toCharArray());
        Cipher crypto = Cipher.getInstance(CRYPTO_ALGORITHM);
        crypto.init(Cipher.DECRYPT_MODE, secretKey);

        try (CipherInputStream inputStream = new CipherInputStream(inputDataRepository.getInputStream(), crypto)) {
            try (OutputStream outputStream = outputDataRepository.getOutPutStream()) {
                while (inputStream.available() != 0) {
                    outputStream.write(inputStream.read());
                }
                outputStream.flush();
            }
        }
    }

    @Override
    public void encryptData(@Nonnull String password, @Nonnull IDataRepository keyStoreRepository, @Nonnull IDataRepository inputDataRepository, @Nonnull IDataRepository outputDataRepository) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, NoSuchPaddingException, InvalidKeyException {
        final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(new ByteArrayInputStream(keyStoreRepository.read()), password.toCharArray());
        final Key secretKey = keyStore.getKey(KEY_ALIAS, password.toCharArray());
        Cipher crypto = Cipher.getInstance(CRYPTO_ALGORITHM);
        crypto.init(Cipher.ENCRYPT_MODE, secretKey);

        try (CipherOutputStream outputStream = new CipherOutputStream(outputDataRepository.getOutPutStream(), crypto)) {
            final byte[] sourceData = inputDataRepository.read();
            outputStream.write(sourceData);
            outputStream.flush();
        }
    }
}
