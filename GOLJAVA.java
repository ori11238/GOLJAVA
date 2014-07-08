/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package goljava;

import java.util.Random;

/**
 * @author ozigindere@ptc.com
 */
public class GOLJAVA {

	/**
	 * @param args the command line arguments
	 */

	public static void main(String[] args) {
		/**
		 * args[0]: Number of rows
		 * args[1]: Number of cols
		 * args[2]: Number of Steps to iterate
		 */

		try {
			int rows = Integer.parseInt(args[0]);
			int cols = Integer.parseInt(args[1]);
			int steps = (args.length < 3) ? 1 : Integer.parseInt(args[2]);

			int[][] grid = initializeGrid(rows, cols);
			int[][] board = setupGrid(grid, rows, cols);
			System.out.println("\nOriginal grid: ");
			displayGrid(board);
			
			for (int jj = 0; jj < steps; jj++) {
				board = tick(board);
				System.out.println("\nStep " + (jj+1) + ": ");
				displayGrid(board);
			}
			
		} catch (Exception e){ 
			//Catch stuff
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Initializes the grid with dead cells
	 * @param rows
	 * @param cols
	 * @return int[][] arr
	 */
	public static int[][] initializeGrid(int rows, int cols) {
		int[][] arr = new int[rows][cols];

		for (int ii = 0; ii < arr.length; ii++) {
			for(int jj = 0; jj < arr[0].length; jj++ ) {
				arr[ii][jj] = 0;
			}
		}
		return arr;
	}
	
	public static int[][] setupGrid(int[][] arr, int rows, int cols) {
		long x = Math.round(rows * cols * .2);
		System.out.print("Initializing grid with " + x + " alive cells... \n");
		//randomly assign values 0 || 1 to each cell
		for (int ii = 0; ii < x; ii++) {
			int row_x =  randNumber(0, rows);
			int col_y =  randNumber(0, cols);
			//System.out.println(ii + " row_x: " + row_x + " col_y: " + col_y);
			if( arr[row_x][col_y] != 1 ) {
				arr[row_x][col_y] = 1;
			} else {
				ii--;
			}
		}
		return arr;
	}
	
	public static int randNumber(int min, int max) {
		Random randGen = new Random();
		return randGen.nextInt(max - min) + min;
	}
	
	public static int[][] tick(int[][] arr) {
		int rows = arr.length;
		int cols = arr[0].length;
	  
		for (int ii = 0; ii < rows; ii++) {
			for (int jj = 0; jj < cols; jj++) {
				int aliveNeighbors = checkNeighbors(arr, ii, jj);
				//System.out.println("# of alive neighbors for [" + ii + ", " + jj + "] is: " + aliveNeighbors);
				if(arr[ii][jj] == 1) {
					if(aliveNeighbors < 2){
						arr[ii][jj] = 0;
					}
					if (aliveNeighbors > 3) {
						arr[ii][jj] = 0;
					}
				}	else {
					// Cell is dead so check to see if it has 3 live neighbors if 
					// true then bring it back to life
					if (aliveNeighbors == 3) {
						arr[ii][jj] = 1;
					}
				}			
			}
		}
		return arr;
	}
	
	public static void displayGrid(int[][] grid) {
		for(int ii = 0; ii < grid.length; ii++) {
			for(int jj = 0; jj< grid[ii].length; jj++) {
				if(grid[ii][jj] == 1) {
					System.out.print("+ ");
				}	else {
					System.out.print("- ");
				}
			}
			System.out.print("\n");
		}
	}
	
	public static int checkNeighbors(int[][] board, int x, int y) {
		int rows = board.length;
		int cols = board[0].length;
		
		int aliveNeighbors = 0;

		// [TL] [T] [TR]
		if( x - 1 >= 0) {
			if( y - 1 >= 0) {
				if(board[x-1][y-1] == 1) {
					//System.out.print("\tAlive neighbor [" + (x-1) +", " + (y-1) + "] \n");
					aliveNeighbors++;
				}
			}
			if(board[x-1][y] == 1) {
				//System.out.print("\tAlive neighbor [" + (x-1) +", " + y + "] \n");
				aliveNeighbors++;
			}
			if( y + 1 < cols) {
				if(board[x-1][y+1] == 1) {
					//System.out.print("\tAlive neighbor [" + (x-1) +", " + (y+1) + "] \n");
					aliveNeighbors++;
				}
			}
		}
		// [L] [R]
		if( y - 1 >= 0) {
			if(board[x][y-1] == 1) {
				//System.out.print("\tAlive neighbor [" + x +", " + (y-1) + "] \n");
				aliveNeighbors++;
			}
		}
		if( y + 1 < cols) {
			if(board[x][y+1] == 1) {
				//System.out.print("\tAlive neighbor [" + x +", " + (y+1) + "] \n");
				aliveNeighbors++;
			}
		}
		// [BL] [B] [BR] 
		if( x + 1 < rows ) {
			if( y - 1 >= 0) {
				if(board[x+1][y-1] == 1) {
					//System.out.print("\tAlive neighbor [" + (x+1) +", " + (y-1) + "] \n");
					aliveNeighbors++;
				}
			}
			if(board[x+1][y] == 1) {
				//System.out.print("\tAlive neighbor [" + (x+1) +", " + y + "] \n");
				aliveNeighbors++;
			}
			if( y + 1 < cols) {
				if(board[x+1][y+1] == 1) {
					//System.out.print("\tAlive neighbor [" + (x+1) +", " + (y+1) + "] \n");
					aliveNeighbors++;
				}
			}
		}

		return aliveNeighbors;
	}
	
}
