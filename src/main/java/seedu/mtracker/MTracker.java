package seedu.mtracker;

import seedu.mtracker.commands.Command;
import seedu.mtracker.console.InputParser;
import seedu.mtracker.instrument.InstrumentManager;
import seedu.mtracker.ui.TextUi;

public class MTracker {

    private InstrumentManager instrumentManager;
    private InputParser parser;

    public MTracker() {
        instrumentManager = InstrumentManager.getInstance();
        parser = new InputParser();
    }

    public void run() {
        Command command;
        TextUi.greetAtStartUp();
        String userInput = parser.getUserInput();
        String[] commandComponents = parser.getCommandComponents(userInput);
        try {
            command = parser.filterByCommandType(commandComponents);
            command.execute();
        } catch (Exception e) {
            TextUi.showErrorMessage(e);
        }
    }

    /**
     * Main entry-point for the mTracker application.
     */
    public static void main(String[] args) {
        new MTracker().run();
        //command.setData(instrumentManager, ui); uncomment after initialising ui and command
    }

}
