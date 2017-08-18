import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class Grid implements Serializable {
    private HashMap<Address, Cell> grid;

    public Grid() {
        this.grid = new HashMap<Address, Cell>();
    }

    public HashMap<Address, Cell> getGrid() {
        return grid;
    }

    public void setCellState(int x, int y, Cell.State state) {
        grid.put(new Address(x, y), new Cell(state));
    }

    public Cell.State getCellState(int x, int y) {
        Cell cell = grid.get(new Address(x, y));
        return cell != null ? cell.getState() : Cell.State.EMPTY;
    }

    public void countNeighbours() {
        Collection<Address> addresses = grid.keySet();
        for (Address a : addresses) {
            Cell cell = grid.get(a); // resets counter
            cell.setNeighbours(0);
            // since only a conductor can become an electron head,
            // counting only neighbours for conductor
            if (cell.getState() == Cell.State.CONDUCTOR) {
                int n = 0;
                if (getCellState(a.getX() - 1, a.getY() - 1) == Cell.State.ELECTRON_HEAD) n++;
                if (getCellState(a.getX() - 1, a.getY()) == Cell.State.ELECTRON_HEAD) n++;
                if (getCellState(a.getX() - 1, a.getY() + 1) == Cell.State.ELECTRON_HEAD) n++;
                if (getCellState(a.getX(), a.getY() + 1) == Cell.State.ELECTRON_HEAD) n++;
                if (getCellState(a.getX() + 1, a.getY() + 1) == Cell.State.ELECTRON_HEAD) n++;
                if (getCellState(a.getX() + 1, a.getY()) == Cell.State.ELECTRON_HEAD) n++;
                if (getCellState(a.getX() + 1, a.getY() - 1) == Cell.State.ELECTRON_HEAD) n++;
                if (getCellState(a.getX(), a.getY() - 1) == Cell.State.ELECTRON_HEAD) n++;
                cell.setNeighbours(n);
                grid.put(a, cell);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grid)) return false;

        Grid grid1 = (Grid) o;

        if (!grid.equals(grid1.grid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return grid.hashCode();
    }

    @Override
    public String toString() {
        return "Grid{" +
                "grid=" + grid +
                '}';
    }
}