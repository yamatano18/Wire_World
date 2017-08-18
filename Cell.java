import java.io.Serializable;

public class Cell implements Serializable {
    public enum State {
        EMPTY,
        ELECTRON_HEAD,
        ELECTRON_TAIL,
        CONDUCTOR
    }

    private State state;
    private int neighbours;

    public Cell(State initialState) {
        this.state = initialState;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public void setNeighbours(int neighbours) {
        this.neighbours = neighbours;
    }

    public int getNeighbours() {
        return this.neighbours;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Cell)) return false;
        if (this == obj) return true;
        Cell cell = (Cell) obj;
        return this.state.equals(cell.state);
    }

    public int hashCode() {
        return this.state.hashCode();
    }

    public String toString() {
        return this.state.toString();
    }
}
