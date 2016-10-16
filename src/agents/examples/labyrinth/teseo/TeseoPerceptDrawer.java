package agents.examples.labyrinth.teseo;

import java.awt.Graphics;

import agents.Percept;
import agents.examples.labyrinth.LabyrinthPerceptDrawer;
import agents.examples.labyrinth.LabyrinthUtil;

public class TeseoPerceptDrawer extends LabyrinthPerceptDrawer {
    public void draw(Graphics g, int x, int y, int CELL_SIZE, Percept p) {
        super.draw(g, x, y, CELL_SIZE, p);
        if (((Boolean) p.getAttribute(LabyrinthUtil.TREASURE)).booleanValue()) {
            g.drawLine(x, y, x + CELL_SIZE, y + CELL_SIZE);
            g.drawLine(x, y + CELL_SIZE, x + CELL_SIZE, y);
        }
    }
}
