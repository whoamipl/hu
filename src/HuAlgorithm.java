import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HuAlgorithm {
    private List<Task> tasks;
    private List<Machine> machines;

    public HuAlgorithm(List<Task> tasksList, List<Machine> machinesList) {
        this.tasks = tasksList;
        this.machines = machinesList;
    }

    private void setTasksLevel(List<Task> taskList) {
        taskList.stream()
                .forEach(task -> task.setLevel(task.getPredecesors().size()));
    }

    private void algorithm() {
        int time = 1;
        setTasksLevel(tasks);
        do {
            List<Task> sortedTasksList = getFreeTasksList(tasks).stream()
                    .sorted(Comparator.comparing(t -> t.getLevel()))
                    .collect(Collectors.toList());

            while (sortedTasksList.size() != 0) {
                 for (int i = 0; i < Main.machineCount; ++i) {
                    if (sortedTasksList.size() != 0 ) {
                            int taskId = sortedTasksList.get(0).getId();
                            machines.get(i).insertTask(taskId);
                            tasks.remove(sortedTasksList.get(0));
                            sortedTasksList.remove(0);
                            updateGraph(taskId);
                    }
                    else
                        break;
                }
            }
            time++;
            System.out.println("Time: " + time);
        } while (getFreeTasksList(tasks).size() != 0);
        for (Machine m : machines) {
            System.out.println(m.getTasksIds().toString());
        }

    }

    private void updateGraph(int taskId) {
        for (Task t : tasks) {
            System.out.println(t.getId());
            if(t.hasPredecesors()) {
                System.out.println("Task " + taskId + " deleted!");
                t.getPredecesors().remove(new Integer(taskId));
            }
            else
                t.setFree();

            if(t.hasSuccesors())
                t.getSuccesors().remove(new Integer(taskId));
        }
    }

    private List<Task> getFreeTasksList(List<Task> tasks) {
        return tasks.stream()
            .filter(t -> t.isFree())
            .collect(Collectors.toList());
    }

    public void eval() {
        algorithm();
    }
}
