package unalcol.agents.examples.squares.SI2.genkidama;

public class SimulatedBoard {
    public static final int LEFT = 1;
    public static final int TOP = 2;
    public static final int RIGHT = 4;
    public static final int BOTTOM = 8;
    public static final int WHITE = 16;

    public int[][] values;

    public int n;

    public SimulatedBoard(SimulatedBoard board){
        values = new int[n][n];
        for (int i = 0; i < n; i++)
            System.arraycopy(board.values[i], 0, values[i], 0, n);
    }

    public static int[][] init(int n, int m) {
        int[][] values = new int[n][m];
        for (int i = 0; i < n; i++) {
            values[i][0] = LEFT;
            values[i][m - 1] = RIGHT;
        }
        for (int i = 0; i < m; i++) {
            values[0][i] |= TOP;
            values[n - 1][i] |= BOTTOM;
        }
        return values;
    }

    public SimulatedBoard(int n) {
        this(n, n);
        this.n = n;
    }

    public SimulatedBoard(int n, int m) {
        values = init(n, m);
    }

    public boolean invalid(int i, int j, int val) {
        return i < 0 || values.length <= i || j < 0 || values[0].length <= j ||
                val <= 0 || val > BOTTOM || (values[i][j] & val) == val;
    }

    public int lines(int i, int j) {
        int c = (values[i][j] & LEFT) == LEFT ? 1 : 0;
        c += (values[i][j] & TOP) == TOP ? 1 : 0;
        c += (values[i][j] & RIGHT) == RIGHT ? 1 : 0;
        c += (values[i][j] & BOTTOM) == BOTTOM ? 1 : 0;
        return c;
    }

    public boolean closable(int i, int j) {
        return lines(i, j) == 3;
    }

    public void check(int color, int i, int j) {
        if (closable(i, j)) {
            if ((values[i][j] & LEFT) == 0) {
                values[i][j] |= LEFT;
                values[i][j - 1] |= RIGHT;
                values[i][j] |= color;
                check(color, i, j - 1);
            }

            if ((values[i][j] & TOP) == 0) {
                values[i][j] |= TOP;
                values[i - 1][j] |= BOTTOM;
                values[i][j] |= color;
                check(color, i - 1, j);
            }

            if ((values[i][j] & RIGHT) == 0) {
                values[i][j] |= RIGHT;
                values[i][j + 1] |= LEFT;
                values[i][j] |= color;
                check(color, i, j + 1);
            }

            if ((values[i][j] & BOTTOM) == 0) {
                values[i][j] |= BOTTOM;
                values[i + 1][j] |= TOP;
                values[i][j] |= color;
                check(color, i + 1, j);
            }
        }
    }

    public boolean play(boolean white, int i, int j, int val) {
        if (invalid(i, j, val)) {
            return false;
        }
        values[i][j] |= val;
        switch (val) {
            case LEFT:
                values[i][j - 1] |= RIGHT;
                check(white ? 0 : WHITE, i, j - 1);
                break;
            case TOP:
                values[i - 1][j] |= BOTTOM;
                check(white ? 0 : WHITE, i - 1, j);
                break;
            case RIGHT:
                values[i][j + 1] |= LEFT;
                check(white ? 0 : WHITE, i, j + 1);
                break;
            case BOTTOM:
                values[i + 1][j] |= TOP;
                check(white ? 0 : WHITE, i + 1, j);
                break;
        }
        check(white ? 0 : WHITE, i, j);
        return true;
    }
    
    public int white_count() {
        int c = 0;
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                if (lines(i, j) == 4 && (values[i][j] & WHITE) == WHITE) {
                    c++;
                }
            }
        }
        return c;
    }

    public int black_count() {
        int c = 0;
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                if (lines(i, j) == 4 && (values[i][j] & WHITE) != WHITE) {
                    c++;
                }
            }
        }
        return c;
    }

    public boolean full() {
        boolean flag = true;
        for (int i = 0; i < values.length && flag; i++) {
            for (int j = 0; j < values[0].length && flag; j++) {
                flag = (lines(i, j) == 4);
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        SimulatedBoard b = new SimulatedBoard(10);
        System.out.println(b);
        System.out.println("************************************");
        b.play(true, 4, 6, LEFT);
        System.out.println(b);
        System.out.println("************************************");
        b.play(false, 2, 5, LEFT);
        System.out.println(b);
        System.out.println("************************************");
        b.play(false, 8, 3, LEFT);
        System.out.println(b);
        System.out.println("************************************");
        b.play(false, 9, 7, LEFT);
        System.out.println(b);
        System.out.println("************************************");
        b.play(false, 1, 0, BOTTOM);
        System.out.println(b);
        System.out.println("************************************");
        b.play(true, 0, 1, LEFT);
        System.out.println(b);
        System.out.println("************************************");
    }
}


