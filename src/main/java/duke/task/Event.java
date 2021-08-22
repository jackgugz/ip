package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A Class that extends the Task class.
 * It is specifically designed for a Task with time of occurrence. 
 *
 * @author Gu Geng
 */
public class Event extends Task {
    private String time;
    private LocalDate localDate;

    /**
     * Returns a Event object using a String. 
     *
     * @param content A String containing information that is possibly enough to create a Event object.
     * @throws duke.DukeException Will be thrown if the information in content is insufficient/incorrect. 
     */
    public Event(String content) throws duke.DukeException {
        super(content.substring(6, content.indexOf("/")).trim());
        time = content.substring(content.indexOf("/") + 1).trim();
        try {
            this.localDate = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new duke.DukeException(" ☹ SORZ but I only understand date in yyyy-MM-dd format!");
        }
    }
    
    public String getTime() {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Overrides the toString method.
     *
     * @return A String representation of the Event object in specified format. 
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                getStatus() ? "X" : " ", getContent(), time);
    }

    /**
     * Implements the record abstract method from Task.
     *
     * @return A String of the Event object in specified format for starage purpose.
     */
    public String record() {
        return String.format("E | %s | %s | %s",
                getStatus() ? "1" : "0", getContent(), time);
    }

    /**
     * Implements the getType method from Task.
     *
     * @return A String indicating the type of Event as a Task.
     */
    public String getType() {
        return "E";
    }

    /**
     * Implements the hasSchedule method from Task.
     *
     * @return A boolean indicating if Event has a schedule.
     */
    public boolean hasSchedule() {
        return true;
    }
}
