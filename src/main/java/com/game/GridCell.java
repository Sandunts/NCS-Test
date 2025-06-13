package com.game;

public class GridCell {
    private boolean isBomb;
    private String value;
    private boolean isVisible;

    GridCell(boolean isBomb, String value, boolean isVisible){
        this.isBomb = isBomb;
        this.value = value;
        this.isVisible = isVisible;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}

