/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agents.examples.squares;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import agents.Agent;
import agents.Clock;
import agents.simulate.gui.EnvironmentView;
import agents.simulate.gui.SimpleView;
import agents.simulate.gui.WorkingPanel;

/**
 * @author Jonatan
 */
public class SquaresMainFrame extends JFrame implements EnvironmentView {

    private static final long serialVersionUID = -6958542352671181413L;
    private Agent white_agent;
    private Agent black_agent;
    private Squares squares = null;
    private Thread thread = null;
    private SimpleView view;

    // Graphic components
    private JPanel jPanel2 = new JPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private GridLayout gridLayout2 = new GridLayout();
    private WorkingPanel drawArea = new WorkingPanel(new SquaresDrawer());
    private BorderLayout borderLayout2 = new BorderLayout();
    private JPanel jPanel1 = new JPanel();
    private JLabel jLabel1 = new JLabel();
    private JTextField jTextField1 = new JTextField();
    private JLabel jLabel2 = new JLabel();
    private JTextField jTextField2 = new JTextField();
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    private JPanel jPanel3 = new JPanel();
    private GridLayout gridLayout3 = new GridLayout();
    private JLabel jLabelw = new JLabel();
    private JLabel jLabelb = new JLabel();

    public SquaresMainFrame(Agent w_agent, Agent b_agent) {
        view = new SimpleView(drawArea);
        white_agent = w_agent;
        black_agent = b_agent;
        squares = new Squares(white_agent, black_agent);
        squares.setDelay(100);
        squares.registerView(view);
        squares.registerView(this);

        drawArea.getDrawer().setEnvironment(squares);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void envChanged(String command) {
        if (command.indexOf(DONE) == 0) {
            drawArea.setText(command);
            squares.stop();
            thread = null;
            jButton2.setText("Simulate");
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(430, 540));
        this.setTitle("Squares");
        this.getContentPane().setLayout(borderLayout2);
        jPanel2.setLayout(borderLayout1);

        gridLayout2.setColumns(2);
        gridLayout2.setRows(3);
        jLabel1.setText("Dimension:");
        jTextField1.setPreferredSize(new Dimension(37, 20));
        jTextField1.setText("8");
        jLabel2.setText("Time (sec):");
        jTextField2.setPreferredSize(new Dimension(37, 20));
        jTextField2.setText("10");
        jButton1.setText("Init");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });
        jButton2.setToolTipText("");
        jButton2.setText("Simulate");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }
        });
        this.getContentPane().add(jPanel2, BorderLayout.CENTER);
        jPanel2.add(drawArea, BorderLayout.CENTER);
        this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
        jPanel1.add(jLabel1, null);
        jPanel1.add(jTextField1, null);
        jPanel1.add(jLabel2, null);
        jPanel1.add(jTextField2, null);
        jPanel1.add(jButton1, null);
        jPanel1.add(jButton2, null);

        gridLayout3.setColumns(1);
        gridLayout3.setRows(2);
        jPanel3.setLayout(gridLayout3);
        jLabelw.setText("Blue time:");
        jLabelb.setText("Red time:");
        jPanel3.add(jLabelw);
        jPanel3.add(jLabelb);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                white_agent.die();
                black_agent.die();
                thread = null;
                System.exit(0);
            }
        });
    }

    private void jButton1_actionPerformed(ActionEvent e) {
        int dim = Integer.parseInt(jTextField1.getText());
        long time = 1000 * Long.parseLong(jTextField2.getText());
        Clock clock = new Clock(time, time);
        squares.init(new Board(dim), clock);
        drawArea.update();
        jLabelw.setText("Blue time:" + clock.white_time_string());
        jLabelb.setText("Red time:" + clock.black_time_string());
    }

    private void jButton2_actionPerformed(ActionEvent e) {
        if (thread == null) {
            thread = new Thread(squares);
            squares.clock.init(true);
            squares.run();
            jButton2.setText("Stop");
        } else {
            squares.stop();
            thread = null;
            jButton2.setText("Simulate");
        }
    }
}