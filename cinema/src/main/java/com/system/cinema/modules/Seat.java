package com.system.cinema.modules;

public class Seat {

    private int id;
    private boolean isAvailable;
    private int row;
    private String column;

    public Seat(int row, String column, int id) {
        this.id = id;
        this.row = row;
        this.column = column;
    }

    public Seat(int row, String column) {
        this.isAvailable = true;
        this.row = row;
        this.column = column;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getRow() {
        return row;
    }

    public String getColumn() {
        return column;
    }

    public void setSeatAvailable() {
        isAvailable = true;
    }

    public void setSeatNotAvailable() {
        isAvailable = false;
    }

    @Override
    public String toString() {
        if (this.isAvailable()) {
            return "[" + this.getColumn()+ this.getRow() +"]";
        }
        return "[--]";
    }
}
