import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class WireworldGUI extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem imp, exp;
    private static JButton btnStart, btnStop, btnStep;
    private static JTextField text;
    private JTextField TFnumSteps;
    private SimulationThread simulation;
    private int numSteps;

    public WireworldGUI(SimulationThread simulation) {
        this.simulation = simulation;
        setTitle("Wireworld");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        add(menuBar, BorderLayout.NORTH);
        add(this.myContainer(), BorderLayout.EAST);

        file = new JMenu("File");
        menuBar.add(file);

        imp = new JMenuItem("Import");
        imp.addActionListener(this);
        file.add(imp);

        exp = new JMenuItem("Export");
        exp.addActionListener(this);
        file.add(exp);
    }

    public JPanel myContainer() {
        JPanel p = new JPanel();

        p.setBackground(new Color(10, 150, 150));
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;

        TFnumSteps = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        p.add(TFnumSteps, c);

        btnStart = new JButton("START");
        btnStart.setActionCommand("start");
        btnStart.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        p.add(btnStart, c);

        btnStop = new JButton("STOP");
        btnStop.setActionCommand("stop");
        btnStop.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        p.add(btnStop, c);

        btnStep = new JButton("ONE STEP");
        btnStep.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.ipady = 20;
        c.gridx = 0;
        c.gridy = 4;
        p.add(btnStep, c);

        text = new JTextField("ALERT BOX");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 100;
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 6;
        p.add(text, c);

        return p;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {

            public String getDescription() {
                return "Wireworld file (*.w)";
            }

            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".w");
                }
            }
        });

        if (source == imp) {
            int status = fileChooser.showOpenDialog(this);
            if (status == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                GridImporter importer = GridImporter.getInstance();
                Wireworld wireworld = Wireworld.getInstance();
                try {
                    wireworld.setGrid(importer.gridImport(file));
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Wrong extention of input file ", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (source == exp) {
            int status = fileChooser.showSaveDialog(this);
            if (status == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                GridExporter exporter = GridExporter.getInstance();
                try {
                    exporter.gridExport(Wireworld.getInstance(), file);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Wrong extention of output file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (source == btnStart) {
            try {
                numSteps = Integer.parseInt(TFnumSteps.getText().toString());
                if (numSteps < 0)
                    text.setText("Wrong data, input non-negative integer value ");

            } catch (Exception e) {
                text.setText("Wrong data, input non-negative integer value");
            }

            if (numSteps == 0)
                simulation.resumeThread();
            else {
                for (int i = 0; i < numSteps; i++) {
                    try {
                        simulation.getGameTickSubject().setChanged();
                        simulation.getGameTickSubject().notifyObservers();
                        simulation.sleep(200);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

        } else if (source == btnStop) {
            try {
                simulation.pauseThread();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        } else if (source == btnStep) {
            simulation.getGameTickSubject().setChanged();
            simulation.getGameTickSubject().notifyObservers();
        }
    }
}