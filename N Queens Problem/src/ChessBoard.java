import java.util.Random;

/**
 * ChessBoard.java
 * Purpose: Solve N-Queen Problem using hill climbing
 *
 * @author Eshwar
 * @version 1.0 10/28/2017
 */
public class ChessBoard {
	
	private static final Random RAND = new Random();
	//private static int stateCount = 0;
	public ChessBoard(int n)
	{
		int h =0;
		int [][] board;		
		for(int i = 0 ; i<1000000; i++)
		{
			board = generateRandomBoard(n);
			if (solveNQueen(board, n))
			{
				System.out.print("No of random restart are "+i);
				break;
			}
		}		
	}
	
	/**
	 * Generate Random Chess Board
	 *
	 * @param n no of queens
	 * @return Board with all queens placed
	 */	
	private static int [][] generateRandomBoard(int n) 
	{
		int r;
		int [][] queenBoard = new int [n][n];
		for (int col = 0; col < n; col++) 
		{
			r = RAND.nextInt(n-1);
			for(int row = 0; row < n; row++)
			{
				if(r==row)
				{  
					queenBoard[r][col] = 1;
				}
				else{
					queenBoard[row][col] = 0;
				}
			}
		}
		return 	queenBoard;	
	}

	/**
	 * Get Heuristic Value of the board
	 *
	 * @param board two dimensional board array
	 * @param n  no of queens
	 * @return Heuristic Value
	 */		
	public int getHeuristicValue(int [][] board, int n)
	{
		int hValue = 0;
		for(int col = 0; col < n; col++ ){
			for(int row = 0; row < n; row++){
				if(this.getBoardValue(board, row, col, n) == 1)
				{
					int k =1;
					for(int i=col+1 ; i<n ; i++)
					{
						int j = col+1+i;
						
						if(this.getBoardValue(board, row, i, n)==1)
						{						
							hValue+=1;
						}
						if(this.getBoardValue(board, row-k, i, n)==1)
						{   
							hValue+=1;
						}
						if(this.getBoardValue(board, row+k, i, n)==1)
						{   						
							hValue+=1;
						}	
						k++;					
					}
				}
				
			}
			
		}
		return hValue;
	}

	/**
	 * Get the value of given indexes
	 *
	 * @param i row value
	 * @param j column value
	 * @return value of the position
	 */	
	public int getBoardValue(int[][] board, int i, int j, int n)
	{
		if(i<0 || j<0 || i>n-1 || j>n-1)
		{
			return -1;
		}
		return board[i][j];
	}

	/**
	 * Solve N queen problem
	 *
	 * @param board 
	 * @param n  queen's size
	 * @return boolean value if success or failure
	 */	
	public boolean solveNQueen(int [][] board, int n)
	{
		int stateCount = 0;
		int [][] temp = new int [n][n];
		temp = copyBoard(board, temp);
		for(int col = 0; col < n; col++)
		{
			stateCount++;
			getLeastHValueIncolumn(col, temp, n);
			if (isSolved(temp, n))
			{
				System.out.println("Solution:");
				printBoard(temp, n);
				System.out.println("");
				System.out.println("No of states:  "+stateCount);
				System.out.println("");
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if the Heuristic is zero
	 *
	 * @param board 
	 * @param n  queen's size
	 * @return boolean value if success or failure
	 */		
	public boolean isSolved(int [][] board, int n)
	{
		if (getHeuristicValue(board, n) == 0)
		{
			return true;
		}
		return false;
	}

	/**
	 * Calculates least Heuristic value in the column of board
	 *@param  col given column
	 * @param board 
	 * @param n  queen's size
	 * @return updates the board with the queen with least heuristic in the column
	 */	
	public int getLeastHValueIncolumn(int col, int[][] board, int n)
	{
		int actualIndex = 0;
		int resultantIndex = 0;
		int hVal, min = 0 ;
		for(int row = 0; row < n; row++)
		{
			if(this.getBoardValue(board, row, col, n) == 1)
			{
				actualIndex = row;
				min = getHeuristicValue(board, n);				
			}
		}
		board[actualIndex][col] = 0;
		for(int row = 0; row < n; row++)
		{
			board[row][col] = 1;
			hVal = getHeuristicValue(board, n);
			if(hVal<=min)
			{
				resultantIndex = row;
				min = hVal;
			}
			board[row][col] = 0;
		}
		board[resultantIndex][col] = 1;
		return -1;
	}

	/**
	 * Duplicate the Board
	 *
	 * @param scr Source board
	 * @param dest Destination board
	 * @return returns duplicated board
	 */		
	public int [][] copyBoard(int [] [] src, int [][] dest)
	{
		for (int i = 0; i < src.length; i++) 
		{
	        System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
		}	
		return dest;
	}

	/**
	 * Print the Board
	 *
	 * @param board 
	 * @param n  queen's size
	 */	
	public void printBoard(int[][] board, int n)
	{
		for(int i = 0; i<n; i++)
		{
			for(int j = 0; j<n; j++)
			{
				System.out.print(this.getBoardValue(board, i, j, n) + " ");
			}
			System.out.println(" ");
		}		
	}

}
