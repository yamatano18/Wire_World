public class Pair {
    private Address address;
    private Cell cell;

    public Pair(Address address, Cell cell) {
        this.address = address;
        this.cell = cell;
    }

    public Address getAddress() {
        return this.address;
    }

    public Cell getCell() {
        return this.cell;
    }
}