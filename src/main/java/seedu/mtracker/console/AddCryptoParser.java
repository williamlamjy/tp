package seedu.mtracker.console;

import seedu.mtracker.asserthelpers.AssertParserHelper;
import seedu.mtracker.commands.AddCryptoCommand;
import seedu.mtracker.commons.Validate;
import seedu.mtracker.commons.error.OperationAbortedError;
import seedu.mtracker.ui.TextUi;

/**
 * A class responsible for parsing inputs when user is adding a new crypto instrument.
 */
public class AddCryptoParser extends AddInstrumentParser {

    public static String INSTRUMENT_TYPE = "crypto";

    /**
     * Queries and gets crypto remarks from the user.
     *
     * @return User remarks input.
     */
    public String getCryptoRemarksFromUser() {
        TextUi.displayAddRemarksInstruction();
        return getUserInput(WORKSPACE);
    }

    /**
     * Queries and gets crypto expiry date from the user.
     *
     * @return User expiry date input.
     */
    public String getCryptoExpiryFromUser() {
        TextUi.displayAddExpiryInstruction();
        return getUserInput(WORKSPACE);
    }

    /**
     * Gets the user crypto expiry input and adds it into the parameters list.
     *
     * @throws OperationAbortedError If the user wants to abort the add crypto process.
     */
    public void addCryptoExpiryToParameters() throws OperationAbortedError {
        String expiry;
        do {
            expiry = getCryptoExpiryFromUser();
            checkIfAbort(expiry, WORKSPACE);
        } while (!Validate.isValidExpiry(expiry));
        parameters.add(expiry);
        AssertParserHelper.assertExpiryInTheFuture(expiry);
    }

    /**
     * Gets the user crypto remarks input and adds it into the parameters list.
     *
     * @throws OperationAbortedError If the user wants to abort the add crypto process.
     */
    public void addCryptoRemarksToParameters() throws OperationAbortedError {
        String remarks = getCryptoRemarksFromUser();
        checkIfAbort(remarks, WORKSPACE);
        parameters.add(remarks);
    }

    /**
     * Gets crypto specific parameters from the user when adding a new crypto instrument.
     *
     * @throws OperationAbortedError If the user wants to abort the add crypto process.
     */
    public void getCryptoSpecificParameters() throws OperationAbortedError {
        addCryptoExpiryToParameters();
        addCryptoRemarksToParameters();
    }

    /**
     * Gets from the user all parameters needed to create a new crypto instrument.
     *
     * @return A command for adding a new crypto.
     * @throws OperationAbortedError If the user wants to abort the add crypto process.
     */
    @Override
    public AddCryptoCommand getInstrumentParameters() throws OperationAbortedError {
        getGeneralParameters(INSTRUMENT_TYPE);
        getCryptoSpecificParameters();
        AssertParserHelper.assertNoMissingCryptoParameters(parameters);
        return new AddCryptoCommand();
    }
}
