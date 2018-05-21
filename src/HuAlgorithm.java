import java.util.ArrayList;
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
        List<Task> sortedTasksList;
        List<Integer> taskToDelete;
        setTasksLevel(tasks);
        do {
            sortedTasksList = getFreeTasksList(tasks).stream()
                    .sorted(Comparator.comparing(t -> t.getLevel()))
                    .collect(Collectors.toList());
            taskToDelete = new ArrayList<>();
            while (sortedTasksList.size() != 0) {
                for (int i = 0; i < Main.machineCount; ++i) {
                    if (sortedTasksList.size() != 0) {
                        int taskId = sortedTasksList.get(0).getId();
                        machines.get(i).insertTask(taskId);
                        taskToDelete.add(taskId);
                        tasks.remove(sortedTasksList.get(0));
                        sortedTasksList.remove(0);
                    } else
                        break;
                }
            }
            updateGraph(taskToDelete);
            time++;
        } while (getFreeTasksList(tasks).size() != 0);

        for (Machine m : machines) {
            System.out.println(m.getTasksIds().toString());
        }
        System.out.println("Time: " + time);
    }

    private void updateGraph(List<Integer> taskToDelete) {
        for (Task t : tasks) {
            if (!t.isFree()) {
                if (t.hasSuccesors())
                    t.getSuccesors().removeAll(taskToDelete);

                if (t.hasPredecesors()) {
                    t.getPredecesors().removeAll(taskToDelete);
                }
                if (!t.hasPredecesors())
                    t.setFree();
            }
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
