package minesweeper;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);
	        MinesweeperGame game = new MinesweeperGame();
	        System.out.println("Welcome to Minesweeper!");
	        System.out.println("Make sure to avoid the mines! Goodluck!");

	        while (true) {
	            game.printBoard();
	            System.out.print("\nEnter coordinates from 0 to 9 (x y): ");

	            int x = scanner.nextInt();
	            int y = scanner.nextInt();

				if (x < 0 || x >= game.BOARD_SIZE  || y < 0 || y >= game.BOARD_SIZE) {
	                System.out.println("Invalid coordinates. Try again.");
	                continue;
	            }

	            game.revealCell(x, y);
        }
	        
	        
	}

}
