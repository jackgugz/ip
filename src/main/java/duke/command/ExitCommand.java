package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A Class that extends the Command class.
 * It is specifically designed for a Command for exiting.
 *
 * @author Gu Geng
 */
public class ExitCommand extends Command {

    /**
     * Implements the execute method from Command superclass.
     * Executes the given exiting command accordingly by updating taskList and storage, interacting with ui.
     * Returns a String of system reply when given certain input under execution.
     *
     * @param taskList A duke.TaskList object that contains an ArrayList of duke.task.task object to be updated.
     * @param ui A duke.Ui object that helps to perform interaction when the command is executed.
     * @param storage A duke.Storage object that helps to update the storage after the execution is done.
     * @return a String of system reply when given certain input under execution.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println(ui.showFarewell());
        return ui.showFarewell();
    }

    /**
     * Implements the isExit method from Command superclass.
     * Returns a boolean indicating if the programme terminates after the exit execution.
     *
     * @return A boolean indicating if the programme terminates after the exit execution.
     */
    public boolean isExit() {
        return true;
    }

}
