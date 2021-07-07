package com.voidmain.jobinterview.kgs.secretkey.service;

import com.voidmain.jobinterview.kgs.secretkey.repositories.IDataRepository;

import javax.annotation.Nonnull;

public interface ICryptoService {
    void decrypt(@Nonnull final String password, @Nonnull final IDataRepository keyStoreRepository, @Nonnull final IDataRepository inputDataRepository, @Nonnull final IDataRepository outputDataRepository);

    void encryptData(@Nonnull final String password, @Nonnull final IDataRepository keyStoreRepository, @Nonnull final IDataRepository inputDataRepository, @Nonnull final IDataRepository outputDataRepository);
}
