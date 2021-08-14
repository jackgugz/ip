import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static boolean isDoneOps(String input) {
        if (input == null) {
            return false;
        }
        int length = input.length();
        if (length < 6) {
            return false;
        }
        if (input.substring(0, 5).equals("done ")) {
            try {
                Integer.parseInt(input.substring(5));
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    };

    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n"
        + "Heyllo! Jackie here\n"
        + "What can I do for you?\n"
        + "____________________________________________________________\n");

        boolean bye = false;
        Scanner myObj = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();


        while (!bye) {
            String temp = myObj.nextLine();
            if (temp.equals("bye")) {
                System.out.println("____________________________________________________________\n"
                        + "Bye bye. Love you\n"
                        + "____________________________________________________________\n");
                bye = true;
            } else if (temp.equals("list")) {
                System.out.println("____________________________________________________________\n"
                        + "Darling, here are the tasks in your list:\n");
                for (int i = 0; i < taskList.size(); i++) {
                    Task task = taskList.get(i);
                    String entry = String.format("%d. [%s] %s",
                            i+1, task.getStatus() ? "X" : " ", task.getContent());
                    System.out.println(entry);
                }
                System.out.println("____________________________________________________________\n");
            } else if (isDoneOps(temp)) {
                int index = Integer.parseInt(temp.substring(5));
                taskList.get(index - 1).doneTask();
                System.out.println("____________________________________________________________\n"
                        + "Noice! I've marked this task as done: \n");
                String entry = String.format("[X] %s",
                        taskList.get(index - 1).getContent());
                System.out.println(entry);
                System.out.println("____________________________________________________________\n");
            } else {
                System.out.println("____________________________________________________________\n"
                        + "added: " + temp
                        + "\n"
                        + "____________________________________________________________\n");
                taskList.add(new Task(temp));
            }

        }
    }
}
