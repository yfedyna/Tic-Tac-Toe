import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void gameResultXWin() {
        String input = "1 3\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.activePlayers = Main.CROSS;
        Main.mas[0][0] = "X";
        Main.mas[0][1] = "X";
        Main.mas[0][2] = " ";
        Main.mas[1][0] = "O";
        Main.mas[1][1] = "O";
        Main.mas[1][2] = " ";
        Main.mas[2][0] = " ";
        Main.mas[2][1] = " ";
        Main.mas[2][2] = " ";

        Main.gameResult();

        StringBuilder result = new StringBuilder();
        result.append("X enter the coordinates: ");
        result.append("---------\n");
        for (int i = 0; i < Main.LINE; i++) {
            result.append("| ");
            for (int j = 0; j < Main.COLUMN; j++) {
                result.append(Main.mas[i][j]).append(" ");
            }
            result.append("| \n");
        }
        result.append("---------\n");
        result.append("X wins\n");

        assertEquals(result.toString(), out.toString());
    }

    @Test
    void gameResultOWin() {
        String input = "2 3\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.activePlayers = Main.ZERO;
        Main.mas[0][0] = "X";
        Main.mas[0][1] = "X";
        Main.mas[0][2] = " ";
        Main.mas[1][0] = "O";
        Main.mas[1][1] = "O";
        Main.mas[1][2] = " ";
        Main.mas[2][0] = " ";
        Main.mas[2][1] = " ";
        Main.mas[2][2] = "X";

        Main.gameResult();

        // result
        StringBuilder result = new StringBuilder();
        result.append("O enter the coordinates: ");
        result.append("---------\n");
        for (int i = 0; i < Main.LINE; i++) {
            result.append("| ");
            for (int j = 0; j < Main.COLUMN; j++) {
                result.append(Main.mas[i][j]).append(" ");
            }
            result.append("| \n");
        }
        result.append("---------\n");
        result.append("O wins\n");

        assertEquals(result.toString(), out.toString());
    }

    @Test
    void gameResultDraw() {
        String input = "3 2\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.activePlayers = Main.ZERO;
        Main.mas[0][0] = "X";
        Main.mas[0][1] = "X";
        Main.mas[0][2] = "O";
        Main.mas[1][0] = "O";
        Main.mas[1][1] = "O";
        Main.mas[1][2] = "X";
        Main.mas[2][0] = "X";
        Main.mas[2][1] = " ";
        Main.mas[2][2] = "X";

        Main.gameResult();

        // result
        StringBuilder result = new StringBuilder();
        result.append("O enter the coordinates: ");
        result.append("---------\n");
        for (int i = 0; i < Main.LINE; i++) {
            result.append("| ");
            for (int j = 0; j < Main.COLUMN; j++) {
                result.append(Main.mas[i][j]).append(" ");
            }
            result.append("| \n");
        }
        result.append("---------\n");
        result.append("Game end. Draw\n");

        assertEquals(result.toString(), out.toString());
    }

    @Test
    void startGame() {
        String activePlayers = Main.CROSS;
        String[][] mas = new String[Main.LINE][Main.COLUMN];
        for (int i = 0; i < Main.LINE; i++) {
            for (int j = 0; j < Main.COLUMN; j++) {
                mas[i][j] = " ";
            }
        }
        Main.startGame();

        assertEquals(activePlayers, Main.activePlayers);
    }

    @Test
    void analiseBoardWinnerCross() {
        int result = Main.analiseBoard(Main.CROSS);
        assertEquals(Main.X_WIN, result);
    }

    @Test
    void analiseBoardWinnerZero() {
        int result = Main.analiseBoard(Main.ZERO);
        assertEquals(Main.O_WIN, result);
    }

    @Test
    void analiseBoardNotDrawGame() {
        String[][] mas = Main.mas;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mas[i][j] = "O";
            }
        }
        int result = Main.analiseBoard(Main.EMPTY);

        assertTrue(Main.isFullBoard());
        assertEquals(Main.DRAW, result);
    }

    @Test
    void analiseBoardNotFinishGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Main.mas[i][j] = "O";
            }
        }
        Main.mas[1][1] = " ";
        int result = Main.analiseBoard(Main.EMPTY);

        assertFalse(Main.isFullBoard());
        assertEquals(Main.GAME_NOT_FINISH, result);
    }

    @Test
    void getWinner() {
        String input = "3 1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.activePlayers = Main.ZERO;
        Main.mas[0][0] = "X";
        Main.mas[0][1] = "X";
        Main.mas[0][2] = "X";
        Main.mas[1][0] = "O";
        Main.mas[1][1] = "O";
        Main.mas[1][2] = " ";
        Main.mas[2][0] = " ";
        Main.mas[2][1] = " ";
        Main.mas[2][2] = " ";

        assertEquals("X", Main.getWinner());

    }

    @Test
    void emptyBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Main.mas[i][j] = "O";
            }
        }
        assertTrue(Main.isFullBoard());
    }

    @Test
    void emptyBoardFalse() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Main.mas[i][j] = "O";
            }
        }
        Main.mas[1][1] = " ";

        assertFalse(Main.isFullBoard());
    }


    @Test
    void getCoordinatesFailOneToThree() {
        String input = "4 4\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.activePlayers = Main.ZERO;
        Main.getCoordinates();

        String consoleOutput = "O enter the coordinates: " +
                "Coordinates should be from 1 to 3!\n";

        assertEquals(consoleOutput, out.toString());
    }

    @Test
    void getCoordinatesFailOccupiedCeil() {
        String input = "1 1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.activePlayers = Main.ZERO;
        Main.mas[0][0] = "X";
        Main.mas[0][1] = "X";
        Main.mas[0][2] = "X";
        Main.mas[1][0] = "O";
        Main.mas[1][1] = "O";
        Main.mas[1][2] = " ";
        Main.mas[2][0] = " ";
        Main.mas[2][1] = " ";
        Main.mas[2][2] = " ";

        Main.getCoordinates();

        String consoleOutput = "O enter the coordinates: " +
                "This cell is occupied! Choose another one!\n";

        assertEquals(consoleOutput, out.toString());

    }

    @Test
    void getCoordinatesFailEnterNumbers() {
        String input = "a a\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.activePlayers = Main.ZERO;
        Main.getCoordinates();

        String consoleOutput = "O enter the coordinates: " +
                "You should enter numbers!\n";

        assertEquals(consoleOutput, out.toString());
    }

    @Test
    void getCoordinates() {
        String input = "3 1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.mas[0][0] = "X";
        Main.mas[0][1] = "X";
        Main.mas[0][2] = "X";
        Main.mas[1][0] = "O";
        Main.mas[1][1] = "O";
        Main.mas[1][2] = " ";
        Main.mas[2][0] = " ";
        Main.mas[2][1] = " ";
        Main.mas[2][2] = " ";

        assertTrue(Main.getCoordinates());
    }
}