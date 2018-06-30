package com.system.cinema.modules;

import java.util.*;
import java.util.stream.Collectors;

public class Hall {

    private int id;
    private int columnLength;
    private int rowLength;
    private int capacity;
    private List<Seat> seats;

    public Hall(int id, int columnLength, int rowLength) {
        this.id = id;
        this.columnLength = columnLength;
        this.rowLength = rowLength;
        this.capacity = columnLength * rowLength;
        this.seats = new ArrayList<>();
        this.createSeats();
    }

    public int getId() {
        return id;
    }

    public int getColumnLength() {
        return columnLength;
    }

    public int getRowLength() {
        return rowLength;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    private void createSeats() {
        int charInteger = 64;
        for (int i = columnLength; i > 0; i--) {
            charInteger++;

            for (int j = rowLength; j > 0; j--) {
                String column = String.valueOf((char) charInteger);
                seats.add(new Seat(j, column));
            }
        }
    }

    public int calculateAvailableSeats() {
        return seats.stream().filter(Seat::isAvailable).collect(Collectors.toList()).size();
    }

    public boolean bookSeat(String column, int row) {   //rec deals amount of desired seats -> controller
        for (Seat s : seats) {
            if (s.getRow() == row && s.getColumn().equalsIgnoreCase(column) && s.isAvailable()) {
                s.setSeatNotAvailable();
                return true;
            }
        }
        return false;
    }

    public boolean unBookSeat(String column, int row) {   //rec deals amount of desired seats -> controller
        for (Seat s : seats) {
            if (s.getRow() == row && s.getColumn().equalsIgnoreCase(column) && !s.isAvailable()) {
                s.setSeatAvailable();
                return true;
            }
        }
        return false;
    }

    public void showHall() {
        int rowL = this.rowLength;
        while (rowL-- > 0) {
            System.out.printf("[%d ]", rowL + 1);
        }
        System.out.println("\n");

        for (int i = 0; i < seats.size(); ++i) {
            System.out.printf("%s", seats.get(i));
            if ((i + 1) < seats.size() && !seats.get(i).getColumn().equals(seats.get(i + 1).getColumn())) {
                System.out.printf("\t[%s ]", seats.get(i).getColumn());
                System.out.println("\t");
            }
        }
        System.out.printf("\t[%s ]", seats.get(seats.size() - 1).getColumn());

        rowL = this.rowLength;
        System.out.println("\n");
        while (rowL-- > 0) {
            System.out.printf("%s", "████");
        }
        System.out.println("\n");
    }


    private class Seat {
        private boolean isAvailable;
        private int row;
        private String column;

        public Seat(int row, String column) {
            this.isAvailable = true;
            this.row = row;
            this.column = column;
        }

        private boolean isAvailable() {
            return isAvailable;
        }

        private int getRow() {
            return row;
        }

        private String getColumn() {
            return column;
        }

        private void setSeatAvailable() {
            isAvailable = true;
        }

        private void setSeatNotAvailable() {
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

    public static void main(String[] args) {
        Hall hall = new Hall(1, 5, 3);
        hall.bookSeat("b", 2);
        hall.showHall();
    }
}

