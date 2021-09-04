package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that communicates and delivers the data between the running programme and database.
 * The database is specified through a Path object indicating its file directory.
 *
 * @author Gu Geng
 */
public class Storage {
    /**
     * A collection of done statuses, 1 indicates done and 0 not done.
     *
     * @author Gu Geng
     */
    private enum DoneStatus {
        DONE("1"), NOT_DONE("0");
        private final String value;
        DoneStatus(String s) {
            this.value = s;
        }
    }

    /**
     * A collection of indexes of the content array and what they mean respectively.
     *
     * @author Gu Geng
     */
    private enum Index {
        TYPE(0), DONE_STATUS(1), CONTENT(2), TIME(3);
        private final int value;
        Index(int i) {
            this.value = i;
        }
    }
    private final Path filePath;


    /**
     * Returns a Storage instance with the file directory in the form of Path.
     *
     * @param filePath A Path object specifying the file directory to the data storage.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns an ArrayList of Task objects created from data stored in the directory specified by the file path.
     *
     * @return An ArrayList of Task objects created from data stored in the directory specified by the file path.
     * @throws DukeException Will be thrown if directory specified cannot be located or Task objects failed to create.
     */
    public ArrayList<duke.task.Task> load() throws DukeException {
        try {
            ArrayList<duke.task.Task> result = new ArrayList<>();
            createPath();
            File fileHolder = new File(filePath.toString());
            Scanner scanner = new Scanner(fileHolder);
            while (scanner.hasNext()) {
                String holder = scanner.nextLine();
                String[] content = holder.split(" \\| ");

                switch (content[Index.TYPE.value]) {
                case "T":
                    result.add(createTodo(content));
                    break;
                case "D":
                    result.add(createDeadline(content));
                    break;
                case "E":
                    result.add(createEvent(content));
                    break;
                default:
                    assert false;
                }
            }
            scanner.close();
            return result;
        } catch (IOException e) {
            throw new DukeException("D: OH NOOOOO! I cannot locate the file!!" + e.getMessage());
        }
    }

    /**
     * Updates the storage file specified by the this.filePath against the TaskList provided.
     *
     * @param taskList A TaskList object containing information to update the storage.
     * @throws DukeException Will be thrown if the storage file cannot be located.
     */
    public void updateStorage(TaskList taskList) throws DukeException {
        try {
            Path temp = Files.createTempFile(Paths.get(filePath.toString(), ".."),
                    "temp", ".txt");
            FileWriter fw = new FileWriter(temp.toString());
            File tempFile = new File(temp.toString());
            for (int i = 0; i < taskList.size(); i++) {
                duke.task.Task task = taskList.getTask(i);
                fw.write(task.record() + System.lineSeparator());
            }
            fw.close();
            Files.delete(filePath);
            Files.copy(temp, filePath);
            tempFile.delete();
        } catch (IOException e) {
            throw new DukeException("D: OH NOOOOO! Something wrong with the file!!" + e.getMessage());
        }
    }

    private void createPath() throws IOException {
        Path folderPath = Paths.get(filePath.toString(), "..");
        if (!Files.exists(folderPath)) {
            // create filepath if not existed
            Files.createDirectories(folderPath);
        }
        assert !Files.exists(folderPath);
        if (!Files.exists(filePath)) {
            // create file if not existed
            Files.createFile(filePath);
        }
        assert !Files.exists(filePath);
    }

    private duke.task.Todo createTodo(String[] content) {
        duke.task.Todo todo = new duke.task.Todo("todo ", content[Index.CONTENT.value].trim());
        if (content[Index.DONE_STATUS.value].equals(DoneStatus.DONE.value)) {
            todo.doneTask();
        }
        assert content[Index.DONE_STATUS.value].equals(DoneStatus.NOT_DONE.value);
        return todo;
    }

    private duke.task.Deadline createDeadline(String[] content) throws DukeException {
        duke.task.Deadline deadline = new duke.task.Deadline("deadline ",
                content[Index.CONTENT.value].trim(), "/", content[Index.TIME.value].trim());
        if (content[Index.DONE_STATUS.value].equals(DoneStatus.DONE.value)) {
            deadline.doneTask();
        }
        assert content[Index.DONE_STATUS.value].equals(DoneStatus.NOT_DONE.value);
        return deadline;
    }

    private duke.task.Event createEvent(String[] content) throws DukeException {
        duke.task.Event event = new duke.task.Event(
                "event ", content[Index.CONTENT.value].trim(), "/", content[Index.TIME.value].trim());
        if (content[Index.DONE_STATUS.value].equals(DoneStatus.DONE.value)) {
            event.doneTask();
        }
        assert content[Index.DONE_STATUS.value].equals(DoneStatus.NOT_DONE.value);
        return event;
    }
}
