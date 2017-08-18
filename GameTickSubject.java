import java.util.Observable;

public class GameTickSubject extends Observable {
    //Informs observators about movement
    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }

    @Override
    protected synchronized void clearChanged() {
        super.clearChanged();
    }
}
