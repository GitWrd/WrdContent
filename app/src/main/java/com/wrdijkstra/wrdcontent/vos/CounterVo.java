package com.wrdijkstra.wrdcontent.vos;

/**
 * Created by WDijkstra on 30-Jun-17.
 */

public class CounterVo {

    private int id = -1;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private int count = -1;
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    private String label = "";
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    private boolean locked = false;
    public boolean isLocked() {
        return locked;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
