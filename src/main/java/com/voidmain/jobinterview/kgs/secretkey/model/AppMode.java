package com.voidmain.jobinterview.kgs.secretkey.model;

import com.voidmain.jobinterview.kgs.secretkey.exception.IllegalAppModeException;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.Locale;

public enum AppMode {

    ENCRYPT("e"),
    DECRYPT("d");

    private final String modeTag;

    AppMode(@Nonnull final String pModeTag) {
        this.modeTag = pModeTag;
    }

    @Nonnull
    public static AppMode mapToAppMode(@CheckForNull final String appMode) throws IllegalAppModeException {
        if (null == appMode) {
            throw new IllegalAppModeException("Providing empty appmode  is not supported.");
        }
        switch (appMode.toLowerCase(Locale.ROOT)) {
            case "e":
                return ENCRYPT;
            case "d":
                return DECRYPT;
            default:
                throw new IllegalAppModeException("The appmode " + appMode + " is not supported.");
        }
    }

    @Nonnull
    private String getModeTag() {
        return this.modeTag;
    }
}
