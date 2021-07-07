package com.voidmain.jobinterview.kgs.secretkey.exception;

import javax.annotation.Nonnull;

public class IllegalAppModeException extends Exception {
    public IllegalAppModeException(@Nonnull final String message) {
        super(message);
    }
}
