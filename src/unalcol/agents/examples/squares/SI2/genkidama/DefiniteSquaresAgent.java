package unalcol.agents.examples.squares.SI2.genkidama;


import unalcol.agents.Action;

public class DefiniteSquaresAgent extends SquaresFather{

    private MiniMaxPlayer miniMaxPlayer;
    private QuickSquaresPlayer quickSquaresPlayer;
    private MeroBob meroBob;
    private int twoDepthLimit = 2 * 12 * 12 - (12*2), threeDepthLimit = 2 * 5 * 5 - (5*2), quickLimit = 2 * 24 * 24 - (24*2);

    public DefiniteSquaresAgent(String color) {
        super( color );
        miniMaxPlayer = new MiniMaxPlayer( this, 2 );
        quickSquaresPlayer = new QuickSquaresPlayer( this );
        meroBob = new MeroBob( this );
    }

    @Override
    Action play() {
        int totalLines = 2 * size * size - ( size * 2 );
        updateBoard();
        int linesUnassigned = totalLines - linesAssigned;
        if ( linesUnassigned <= threeDepthLimit){
            miniMaxPlayer.setMaxDepth( 3 );
            return miniMaxPlayer.play();
        }
        if ( linesUnassigned <= twoDepthLimit){
            miniMaxPlayer.setMaxDepth( 2 );
            return miniMaxPlayer.play();
        }
        if ( linesUnassigned <= quickLimit )
            return quickSquaresPlayer.play();

        return meroBob.play();
    }

    @Override
    public void init() { super.init(); }
}
