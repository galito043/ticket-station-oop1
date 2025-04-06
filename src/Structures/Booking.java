package Structures;

import java.time.LocalDate;
import java.util.Objects;

public class Booking {
    private String row;
    private String seat;
    private LocalDate localDate;
    private String name;
    private String note;

    public Booking(String row, String seat, LocalDate localDate, String name, String note) {
        this.row = row;
        this.seat = seat;
        this.localDate = localDate;
        this.name = name;
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return row == booking.row && seat == booking.seat && Objects.equals(localDate, booking.localDate) && Objects.equals(name, booking.name) && Objects.equals(note, booking.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, seat, localDate, name, note);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "row=" + row +
                ", seat=" + seat +
                ", localDate=" + localDate +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public String getRow() {
        return row;
    }

    public String getSeat() {
        return seat;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }
}
