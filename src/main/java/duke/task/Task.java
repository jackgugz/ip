package duke.task;

/**
 * Abstract class that is extended by Deadline, Event and Todo.
 * It contains methods to obtain/alter information about the Task object.
 * 
 * @author Gu Geng
 */
public abstract class Task {
    private String content;
    private boolean status;

    /**
     * Initialises the Task with the given information.
     * 
     * @param content A String that contains information that can possibly create a Task Object.
     */
    Task(String content) {
        this.content = content;
        status = false;
    }
    
    public String getContent() {
        return content;
    }

    public boolean getStatus() {
        return status;
    }

    /**
     * Sets the Task as done.
     */
    public void doneTask() {
        status = true;
    }

    /**
     * Overrides the toString method.
     *
     * @return A String representation of the Task object in specified format. 
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                status ? "x" : " ", content);
    }
    
    abstract public String record();
    abstract public boolean hasSchedule();
    abstract public String getType();
    abstract public String getTime();

}
