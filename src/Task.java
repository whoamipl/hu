import java.util.ArrayList;
import java.util.List;

public class Task {
    private boolean isFree;
    private int id;
    private int processingTime;
    private int level;
    private List<Integer> succesors;
    private List<Integer> predecesors;

    public Task(int id, List<Integer> succesors, List<Integer> predecesors) {
        this.id = id;
        this.processingTime = 1;
        this.level = -1;
        this.succesors = new ArrayList<>(succesors);
        this.predecesors = new ArrayList<>(predecesors);
        this.isFree = !this.hasPredecesors() ? true : false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Integer> getSuccesors() {
        return succesors;
    }

    public void setSuccesors(List<Integer> succesors) {
        this.succesors = succesors;
    }

    public List<Integer> getPredecesors() {
        return predecesors;
    }

    public void setPredecesors(List<Integer> predecesors) {
        this.predecesors = predecesors;
    }

    public boolean isFree() {
        return isFree;
    }

    public boolean hasPredecesors() {
        return this.predecesors.size() != 0;
    }

    public boolean hasSuccesors() {
        return this.succesors.size() != 0;
    }

    public void setFree() {
        this.isFree = true;
    }
}
