public class Hall {
    private int id;
    private int numberOfRows;
    private int numberOfSeatsPerRow;

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
}
