package agents.examples.sudoku.naive;

import agents.*;
import agents.examples.sudoku.*;
import agents.search.classic.*;
import agents.search.util.*;
import types.collection.vector.*;

/**
 * <p>Title: </p>
 * <p>
 * <p>Description: </p>
 * <p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gómez
 * @version 1.0
 */
public class NaiveSudokuAgentProgram implements AgentProgram {
    Vector<Action> cmd = new Vector<Action>();

    public NaiveSudokuAgentProgram() {
    }

    public void init() {
        cmd.clear();
    }

    public Action compute(Percept percept) {
        if (cmd.size() == 0) {
            NaiveSudokuBoardState initial_state = new NaiveSudokuBoardState((SudokuPercept) percept);
            int depth = initial_state.board.emptyPlaces();
            DepthFirstSearch<NaiveSudokuBoardState> search = new DepthFirstSearch<NaiveSudokuBoardState>(depth);
            cmd = search.apply(initial_state, new NaiveSudokuSearchSpace(),
                    new NaiveSudokuGoalTest(), new ConstantCost<NaiveSudokuBoardState>());
            if (cmd == null) {
                cmd = new Vector<Action>();
            }
        }
        if (cmd.size() > 0) {
            Action action = cmd.get(0);
            cmd.remove(0);
            return action;
        }
        return null;
    }
}
