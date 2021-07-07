package com.voidmain.jobinterview.kgs.secretkey.service;

import com.voidmain.jobinterview.kgs.secretkey.exception.ParameterValidationException;

import javax.annotation.Nonnull;
import java.util.List;

public interface IParameterValidatorService {
    /**
     * Validates application input parameters
     *
     * @param parameters A List of parameters
     * @throws ParameterValidationException Is thrown when the parameters are not sufficient.
     */
    void validateParameters(@Nonnull final List<String> parameters) throws ParameterValidationException;
}
