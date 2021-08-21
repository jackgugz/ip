public abstract class Task {
    private String content;
    private boolean status;

    Task(String content) {
        this.content = content;
        this.status = false;
    }

    public String getContent() {
        return this.content;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void doneTask() {
        this.status = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.status ? "x" : " ", this.content);
    }
    
    abstract public String record();
    abstract public boolean hasSchedule();

}
