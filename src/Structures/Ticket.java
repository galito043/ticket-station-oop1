package Structures;

import java.time.LocalDate;
import java.util.Date;

public class Ticket {
    private String row;
    private String seat;
    private LocalDate date;
    private String name;
    private String note;
    private String code;

    private Ticket(Builder builder) {
        this.row = builder.row;
        this.seat = builder.seat;
        this.date = builder.date;
        this.name = builder.name;
        this.note = builder.note;
        this.code = builder.code;
    }


    public static class Builder{
        private String row;
        private String seat;
        private LocalDate date;
        private String name;
        private String note;
        private String code;

        public Builder(String row, String seat, LocalDate date, String name){
            this.row = row;
            this.seat = seat;
            this.date = date;
            this.name = name;
            this.code = CodeGenerator.generateCode(row, seat, date.toString(), name);
        }
        public Builder row(String row){
            this.row = row;
            return this;
        }
        public Builder seat(String seat){
            this.seat = seat;
            return this;
        }
        public Builder date(LocalDate date){
            this.date = date;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder note(String note){
            this.note = note;
            return this;
        }
//        public Builder code(){
//            this.code = CodeGenerator.generateCode();
//            return this;
//        }
        public Ticket build(){
            return new Ticket(this);
        }

    }

    @Override
    public String toString() {
        return "Ticket{" +
                "row=" + row +
                ", seat=" + seat +
                ", date='" + date.toString()  +
                ", name=" + name  +
                ", note=" + note  +
                ", code=" + code  +
                '}';
    }

    public String getRow() {
        return row;
    }

    public String getSeat() {
        return seat;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public String getCode() {
        return code;
    }
}
