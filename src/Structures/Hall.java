package Structures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
/**
 * A hall with an id, rows and seats
 */
public class Hall {
    private int id;
    private int numberOfRows;
    private int numberOfSeatsPerRow;
    /**
     * @param id                   hall ide
     * @param numberOfRows         total rows
     * @param numberOfSeatsPerRow  seats per row
     */
    public Hall(int id, int numberOfRows, int numberOfSeatsPerRow) {
        this.id = id;
        this.numberOfRows = numberOfRows;
        this.numberOfSeatsPerRow = numberOfSeatsPerRow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfSeatsPerRow() {
        return numberOfSeatsPerRow;
    }

    public void setNumberOfSeatsPerRow(int numberOfSeatsPerRow) {
        this.numberOfSeatsPerRow = numberOfSeatsPerRow;
    }

    @Override
    public String toString() {
        return "Hall," + id + "," + numberOfRows +"," + numberOfSeatsPerRow;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return id == hall.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfRows, numberOfSeatsPerRow);
    }
}
