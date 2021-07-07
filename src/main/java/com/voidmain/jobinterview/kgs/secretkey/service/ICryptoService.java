package com.voidmain.jobinterview.kgs.secretkey.service;

import com.voidmain.jobinterview.kgs.secretkey.repositories.IDataRepository;

import javax.annotation.Nonnull;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public interface ICryptoService {
    void decrypt(@Nonnull final String password, @Nonnull final IDataRepository keyStoreRepository, @Nonnull final IDataRepository inputDataRepository, @Nonnull final IDataRepository outputDataRepository) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, NoSuchPaddingException, InvalidKeyException;

    void encryptData(@Nonnull final String password, @Nonnull final IDataRepository keyStoreRepository, @Nonnull final IDataRepository inputDataRepository, @Nonnull final IDataRepository outputDataRepository) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, NoSuchPaddingException, InvalidKeyException;
}
