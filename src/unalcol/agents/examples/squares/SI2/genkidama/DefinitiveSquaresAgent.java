package unalcol.agents.examples.squares.SI2.genkidama;


import unalcol.agents.Action;

public class DefinitiveSquaresAgent extends SquaresFather{

    private MiniMaxPlayer miniMaxPlayer;
    private QuickSquaresPlayer quickSquaresPlayer;

    private int twoDepthLimit = 2 * 14 * 14 - (14*2), threeDepthLimit = 2 * 8 * 8 - (8*2),
            fourDepthLimit = 2 * 4 * 4 - (4*2);

    public DefinitiveSquaresAgent(String color) {
        super( color );
        miniMaxPlayer = new MiniMaxPlayer( this, 2 );
        quickSquaresPlayer = new QuickSquaresPlayer( this );
    }

    @Override
    Action play() {
        int totalLines = 2 * size * size - ( size * 2 );
        updateBoard();
        if ( activateMiniMax ) {
            int linesUnassigned = totalLines - linesAssigned;
            if (linesUnassigned <= fourDepthLimit) {
                miniMaxPlayer.setMaxDepth(4);
                return miniMaxPlayer.play();
            }
            if (linesUnassigned <= threeDepthLimit) {
                miniMaxPlayer.setMaxDepth(3);
                return miniMaxPlayer.play();
            }
            if (linesUnassigned <= twoDepthLimit) {
                miniMaxPlayer.setMaxDepth(2);
                return miniMaxPlayer.play();
            }
            miniMaxPlayer.setMaxDepth(1);
            return miniMaxPlayer.play();
        }
        return quickSquaresPlayer.play();
    }

    @Override
    public void init() { super.init(); }
}
