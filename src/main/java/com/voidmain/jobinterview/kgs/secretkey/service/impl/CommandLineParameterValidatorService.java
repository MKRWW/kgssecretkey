package com.voidmain.jobinterview.kgs.secretkey.service.impl;

import com.voidmain.jobinterview.kgs.secretkey.exception.ParameterValidationException;
import com.voidmain.jobinterview.kgs.secretkey.service.IParameterValidatorService;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Validates the command line parameters
 *
 * @author Markus Kremer
 */
public class CommandLineParameterValidatorService implements IParameterValidatorService {

    public static final int MINIMUM_PARAMETERS_COUNT = 4;

    /**
     * Validates parameters coming in from the commandline
     *
     * @param parameters A List of parameters
     * @throws ParameterValidationException Is thrown when there are no parameters at all or the amount is not correct (2 parameters required).
     */
    @Override
    public void validateParameters(@Nonnull List<String> parameters) throws ParameterValidationException {
        if (MINIMUM_PARAMETERS_COUNT != parameters.size()) {
            throw new ParameterValidationException("You should provide password, keystore filepath, input file, output file as parameter.");
        }
    }

}
