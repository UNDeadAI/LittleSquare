package agents.examples.sudoku.naive;

import agents.search.*;
import reflect.tag.TaggedObject;

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
public class NaiveSudokuGoalTest implements GoalTest<NaiveSudokuBoardState> {
    public NaiveSudokuGoalTest() {
    }

    @Override
    public Boolean apply(TaggedObject<NaiveSudokuBoardState> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean apply(NaiveSudokuBoardState state) {
        return state.board.solved();
    }
}
