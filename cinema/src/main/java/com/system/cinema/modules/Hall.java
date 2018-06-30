package com.system.cinema.modules;

import com.google.common.base.Strings;
import com.system.cinema.dao.SeatDao;
import com.system.cinema.exceptions.DaoException;

import java.util.*;
import java.util.stream.Collectors;

public class Hall {

    private int id;
    private int columnLength;
    private int rowLength;
    private int capacity;
    private List<Seat> seats;
    private SeatDao seatDao = SeatDao.getDao();

    public Hall(int id, int columnLength, int rowLength) {
        this.id = id;
        this.columnLength = columnLength;
        this.rowLength = rowLength;
        this.capacity = columnLength * rowLength;
        this.seats = new ArrayList<>();
        this.createSeats();
    }

    public Hall(int columnLength, int rowLength) {
        this.columnLength = columnLength;
        this.rowLength = rowLength;
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

                /// save seatDao
                return true;
            }
        }
        return false;
    }

    public boolean unBookSeat(String column, int row) {   //rec deals amount of desired seats -> controller
        for (Seat s : seats) {
            if (s.getRow() == row && s.getColumn().equalsIgnoreCase(column) && !s.isAvailable()) {
                s.setSeatAvailable();

                // remove seatDao later
                return true;
            }
        }
        return false;
    }

    public void addBookedSeats(int movieID) throws DaoException {
        HashMap<String , List<Integer>> bookedSeats = seatDao.getBookedSeats(movieID);   //// ???
        for (Seat s : seats) {
            for (String c : bookedSeats.keySet()) {
                if (s.getColumn().equalsIgnoreCase(c) && bookedSeats.get(c).contains(s.getRow())) {
                    s.setSeatNotAvailable();
                }
            }
        }
    }

    public void showHall() {
        int rowL = this.rowLength;
        while (rowL-- > 0) {
            System.out.printf("[%d ]", rowL + 1);
        }
        System.out.println("\t");
        System.out.println(Strings.repeat("====", this.rowLength+1));

        for (int i = 0; i < seats.size(); ++i) {
            System.out.printf("%s", seats.get(i));
            if ((i + 1) < seats.size() && !seats.get(i).getColumn().equals(seats.get(i + 1).getColumn())) {
                System.out.printf(" | | [%s]\n", seats.get(i).getColumn());
            }
        }
        System.out.printf(" | | [%s]\n\n", seats.get(seats.size() - 1).getColumn());

        rowL = this.rowLength;
        while (rowL-- > 0) {
            System.out.printf("%s\n", Strings.repeat("████",this.rowLength));
        }
    }

//    public static void main(String[] args) throws DaoException {
//        Hall hall = new Hall(1, 5, 3);
//        hall.bookSeat("b", 2);
//        hall.showHall();
//    }
}

