package minesweeper;

import java.util.Arrays;
import java.util.Random;

public class MinesweeperGame {
	public static final int BOARD_SIZE = 10;
	public static final int NUM_MINES = 10;
	public static final char MINE_CHAR = '*';
	public static final String UNREVEALED_CELL = "[ ]";

	//declare two-dimensional array
	public String[][] board; 
	public boolean[][] revealed;
	public int remainingCells;

    public MinesweeperGame() {
    	
        board = new String[BOARD_SIZE][BOARD_SIZE]; 
        revealed = new boolean[BOARD_SIZE][BOARD_SIZE];
        remainingCells = BOARD_SIZE * BOARD_SIZE - NUM_MINES;
        initializeBoard();
        placeMines();
    }

    public void initializeBoard() {
        for (String[] row : board) {
            Arrays.fill(row, UNREVEALED_CELL);
        }
    }

    public void placeMines() {
        Random random = new Random();
        int minesPlaced = 0;

        while (minesPlaced < NUM_MINES) {
            int x = random.nextInt(BOARD_SIZE);
            int y = random.nextInt(BOARD_SIZE);

            if (!board[x][y].equals(Character.toString(MINE_CHAR))) {
                board[x][y] = Character.toString(MINE_CHAR);
                minesPlaced++;
            }
        }
    }

    public int countSurroundingMines(int x, int y) {
        int count = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < BOARD_SIZE && j >= 0 && j < BOARD_SIZE && board[i][j].equals(Character.toString(MINE_CHAR))) {
                    count++;
                }
            }
        }

        return count;
    }

    public void revealCell(int x, int y) {
        if (!revealed[x][y]) {
            revealed[x][y] = true;
            remainingCells--;

            if (board[x][y].equals("*")) {
            	board[x][y] = "[*]";
                printBoard();
                System.out.println("\nBOOM! Game Over. Better luck next time");
                printFullBoard();
                System.exit(0);
            }

            if (remainingCells == 0) {
                printBoard();
                System.out.println("\nCongratulations! You win!");
                printFullBoard();
                System.exit(0);
            }

            if (board[x][y].equals(UNREVEALED_CELL)) {
                int surroundingMines = countSurroundingMines(x, y);
                if (surroundingMines == 0) {
                    board[x][y] = "[0]";
                    revealSurroundingEmptyCells(x, y);
                } else {
                    board[x][y] = "[" + surroundingMines + "]";
                }
            }
        }
    }

    public void revealSurroundingEmptyCells(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < BOARD_SIZE && j >= 0 && j < BOARD_SIZE && !revealed[i][j]) {
                    revealCell(i, j);
                }
            }
        }
    }

    public void printBoard() {
        System.out.println("\n  0  1  2  3  4  5  6  7  8  9");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i);
            for (int j = 0; j < BOARD_SIZE; j++) {
                String cell = revealed[i][j] ? board[i][j] : UNREVEALED_CELL;
                System.out.print(cell);
            }
            System.out.println();
        }
    }
    
    public void printFullBoard() {
        System.out.println("\nPrinting Full Board with Mines:");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                String cell = board[i][j];
                if (cell.equals(Character.toString(MINE_CHAR))) {
                    cell = "[" + MINE_CHAR + "]";
                }
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}
    