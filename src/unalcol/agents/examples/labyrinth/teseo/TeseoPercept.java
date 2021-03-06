package unalcol.agents.examples.labyrinth.teseo;

import unalcol.agents.examples.labyrinth.LabyrinthPercept;
import unalcol.agents.examples.labyrinth.LabyrinthUtil;

public class TeseoPercept extends LabyrinthPercept {
    public TeseoPercept(int value) {
        super(value);
        int flag = (1 << 4);
        setAttribute(LabyrinthUtil.TREASURE, (value & flag) == flag);
    }
}
