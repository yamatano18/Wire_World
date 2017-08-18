import javax.swing.*;
import java.awt.*;

public class WorldCell extends JLabel {
    private int x, y;
    private Cell.State state;

    public WorldCell(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        setState(Cell.State.EMPTY);
    }

    public int getRow() {
        return x;
    }

    public int getCol() {
        return y;
    }

    public void setState(Cell.State state) {
        this.state = state;
        switch (this.state) {
            case ELECTRON_HEAD:
                setBackground(new Color(109, 143, 255));
                break;
            case ELECTRON_TAIL:
                setBackground(new Color(255, 82, 81));
                break;
            case CONDUCTOR:
                setBackground(new Color(255, 227, 68));
                break;
            default:
                setBackground(new Color(38, 28, 26));
        }
    }

    public Cell.State getState() {
        return state;
    }

}
