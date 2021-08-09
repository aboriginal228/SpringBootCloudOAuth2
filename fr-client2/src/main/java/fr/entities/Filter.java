package fr.entities;

public class Filter {

    private String min;
    private String max;
    private String partOfName;

    public Filter(String min, String max, String partOfName) {
        this.min = min;
        this.max = max;
        this.partOfName = partOfName;
    }

    public Filter() {
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getPartOfName() {
        return partOfName;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }
}
