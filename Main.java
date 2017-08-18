import java.awt.*;

import static javax.swing.SwingUtilities.*;

public class Main {
    public static void main(String[] args) {
        invokeLater(new Runnable() {
            @Override
            public void run() {
                //All sizes may be modified - adjust it to screen
                int rows = 60;
                int cols = 60;
                int cellSize = 10;

                WorldGrid worldGrid = new WorldGrid(rows, cols, cellSize);
                Wireworld wireworld = Wireworld.getInstance();

                wireworld.addObserver(worldGrid);
                worldGrid.getChangeGridObserver().addObserver(wireworld);

                SimulationThread simulationThread = new SimulationThread();
                simulationThread.getGameTickSubject().addObserver(wireworld);
                //WorldKeyListener keyListener = new WorldKeyListener(simulationThread);

                WireworldGUI wireworldGUI = new WireworldGUI(simulationThread);
                wireworldGUI.getContentPane().add(worldGrid, BorderLayout.CENTER);
                //            wireworldGUI.addKeyListener(keyListener);
                wireworldGUI.pack();

                wireworldGUI.setResizable(false);
                wireworldGUI.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - wireworldGUI.getWidth()) / 2,
                        (Toolkit.getDefaultToolkit().getScreenSize().height - wireworldGUI.getHeight()) / 2);
                wireworldGUI.setVisible(true);
            }
        });
    }
}
