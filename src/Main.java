import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Main {
    static int machineCount = 3;

    public static void main(String[] args) {

        List<Task> tasksList = new ArrayList<>();
        List<Machine> machinesList = new ArrayList<>();

        if(args[0] == null) {
            System.out.println("Musisz podać liczbe maszyn!");
        } else {
            machineCount = parseInt(args[0]);
        }

        for (int i = 1; i < machineCount + 1; ++i)
            machinesList.add(new Machine(i));

        tasksList.add(new Task(1, Arrays.asList(7,10,12),Arrays.asList()));
        tasksList.add(new Task(2, Arrays.asList(7,10,12),Arrays.asList()));
        tasksList.add(new Task(3, Arrays.asList(8,11,12),Arrays.asList()));
        tasksList.add(new Task(4, Arrays.asList(9,11,12),Arrays.asList()));
        tasksList.add(new Task(5, Arrays.asList(9,11,12),Arrays.asList()));
        tasksList.add(new Task(6, Arrays.asList(10,12),Arrays.asList()));
        tasksList.add(new Task(7, Arrays.asList(10,12),Arrays.asList(1,2)));
        tasksList.add(new Task(8, Arrays.asList(11,12),Arrays.asList(3)));
        tasksList.add(new Task(9, Arrays.asList(11,12),Arrays.asList(4,5)));
        tasksList.add(new Task(10, Arrays.asList(12),Arrays.asList(6,7,1,2)));
        tasksList.add(new Task(11, Arrays.asList(12),Arrays.asList(3,4,5,6,7,8,9)));
        tasksList.add(new Task(12, Arrays.asList(),Arrays.asList(10,11,6,7,8,9,1,2,3,4,5)));
        tasksList.stream().forEach(t -> System.out.println(t.isFree()));

        HuAlgorithm hu = new HuAlgorithm(tasksList, machinesList);
        hu.eval();
    }


}
