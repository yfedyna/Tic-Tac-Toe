import java.util.Scanner;

public class Main {
    public static final String EMPTY = " ";
    public static final String CROSS = "X";
    public static final String ZERO = "O";
    public static final int LINE = 3;
    public static final int COLUMN = 3;
    static String[][] mas = new String[LINE][COLUMN];
    public static int gameStatus;
    public static final int GAME_NOT_FINISH = 0;
    public static final int DRAW = 1;
    public static final int X_WIN = 2;
    public static final int O_WIN = 3;
    public static String activePlayers;

    public static void main(String[] args) {
        startGame();
        gameResult();
    }

    public static void gameResult() {
        do {
            String winner = getWinner();
            gameStatus = analiseBoard(winner);
            board();
            if (gameStatus == X_WIN) {
                System.out.println("X wins");
            } else if (gameStatus == O_WIN) {
                System.out.println("O wins");
            } else if (gameStatus == DRAW) {
                System.out.println("Game end. Draw");
            }

            activePlayers = activePlayers.equals(CROSS) ? ZERO : CROSS;
        } while (gameStatus == GAME_NOT_FINISH);
    }

    public static void board() {
        System.out.println("---------");
        for (int i = 0; i < LINE; i++) {
            System.out.print("| ");
            for (int j = 0; j < COLUMN; j++) {
                System.out.print(mas[i][j] + " ");
            }
            System.out.println("| ");
        }
        System.out.println("---------");
    }

    public static void startGame() {
        for (int i = 0; i < LINE; i++) {
            for (int j = 0; j < COLUMN; j++) {
                mas[i][j] = EMPTY;
            }
        }
        activePlayers = CROSS;
        board();
    }

    public static int analiseBoard(String winner) {
        if (winner.equals(CROSS)) {
            return X_WIN;
        } else if (winner.equals(ZERO)) {
            return O_WIN;
        } else {
            boolean emptyBoard = isFullBoard();
            if (emptyBoard) {
                return DRAW;
            }
        }
        return GAME_NOT_FINISH;
    }

    public static String getWinner() {
        boolean takeCoordinates;
        String line = null;
        do {
            takeCoordinates = getCoordinates();
        } while (!takeCoordinates);

        for (int i = 0; i < 8; i++) {
            switch (i) {
                case (0) -> line = mas[0][0] + mas[0][1] + mas[0][2];
                case (1) -> line = mas[1][0] + mas[1][1] + mas[1][2];
                case (2) -> line = mas[2][0] + mas[2][1] + mas[2][2];
                case (3) -> line = mas[0][0] + mas[1][0] + mas[2][0];
                case (4) -> line = mas[0][1] + mas[1][1] + mas[2][1];
                case (5) -> line = mas[0][2] + mas[1][2] + mas[2][2];
                case (6) -> line = mas[0][0] + mas[1][1] + mas[2][2];
                case (7) -> line = mas[0][2] + mas[1][1] + mas[2][0];
                default -> {
                }
            }

            if (line.equals("XXX")) {
                return CROSS;
            } else if (line.equals("OOO")) {
                return ZERO;
            }
        }
        return EMPTY;
    }

    public static boolean isFullBoard() {
        for (int i = 0; i < LINE; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (mas[i][j].equals(EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean getCoordinates() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(activePlayers + " enter the coordinates: ");
        try {
            String str = scanner.nextLine();
            String[] s = str.split("\\s+");
            int line = Integer.parseInt(s[0]) - 1;
            int column = Integer.parseInt(s[1]) - 1;

            if (!(line >= 0 && line < LINE && column >= 0 && column < COLUMN)) {
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            } else if (!mas[line][column].equals(EMPTY)) {
                System.out.println("This cell is occupied! Choose another one!");
                return false;
            } else {
                mas[line][column] = activePlayers;
                return true;
            }
        } catch (RuntimeException e) {
            System.out.println("You should enter numbers!");
            return false;
        }
    }
}

