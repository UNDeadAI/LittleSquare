package agents.examples.sudoku;

import agents.*;

import agents.simulate.util.*;
import agents.examples.sudoku.naive.*;

public class SudokuMain {
    private static Language getLanguage() {
        return new SudokuLanguage();
    }

    public static void main(String[] argv) {
        //    Agent agent = new Agent( new InteractiveAgentProgram( getLanguage() ) );
        Agent agent = new Agent(new NaiveSudokuAgentProgram());
        SudokuMainFrame frame = new SudokuMainFrame(agent, getLanguage());
        frame.setVisible(true);
    }
}
