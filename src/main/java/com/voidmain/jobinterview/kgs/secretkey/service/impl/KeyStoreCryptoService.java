package com.voidmain.jobinterview.kgs.secretkey.service.impl;

import com.voidmain.jobinterview.kgs.secretkey.repositories.IDataRepository;
import com.voidmain.jobinterview.kgs.secretkey.service.ICryptoService;

import javax.annotation.Nonnull;

public class KeyStoreCryptoService implements ICryptoService {

    @Override
    public void decrypt(@Nonnull String password, @Nonnull IDataRepository keyStoreRepository, @Nonnull IDataRepository inputDataRepository, @Nonnull IDataRepository outputDataRepository) {

    }

    @Override
    public void encryptData(@Nonnull String password, @Nonnull IDataRepository keyStoreRepository, @Nonnull IDataRepository inputDataRepository, @Nonnull IDataRepository outputDataRepository) {

    }
}
