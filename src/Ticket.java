public class Ticket {
    private int row;
    private int seat;
    private String date;
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
        private int row;
        private int seat;
        private String date;
        private String name;
        private String note;
        private String code;

        public Builder(int row, int seat, String date, String name){
            this.row = row;
            this.seat = seat;
            this.date = date;
            this.name = name;
        }
        public Builder row(int row){
            this.row = row;
            return this;
        }
        public Builder seat(int seat){
            this.seat = seat;
            return this;
        }
        public Builder date(String date){
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
        public Builder code(){
            this.code = CodeGenerator.generateCode(new Ticket(this));
            return this;
        }
        public Ticket build(){
            return new Ticket(this);
        }

    }

    @Override
    public String toString() {
        return "Ticket{" +
                "row=" + row +
                ", seat=" + seat +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public String getDate() {
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
