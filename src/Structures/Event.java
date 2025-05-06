package Structures;

import Commands.AddEvent;

import java.time.LocalDate;

public class Event {
    private LocalDate localDate;
    private String nameOfEvent;
    private String hallId;

    public Event(LocalDate localDate, String nameOfEvent, String hallId) {
        this.localDate = localDate;
        this.nameOfEvent = nameOfEvent;
        this.hallId = hallId;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getNameOfEvent() {
        return nameOfEvent;
    }

    public void setNameOfEvent(String nameOfEvent) {
        this.nameOfEvent = nameOfEvent;
    }

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    @Override
    public String toString() {
        return "Event{" +
                "date=" + localDate +
                ", nameOfEvent=" + nameOfEvent  +
                ", hallId=" + hallId  +
                "}";
    }
}
