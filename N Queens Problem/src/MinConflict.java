import java.util.Random;

public class MinConflict extends ChessBoard{
	private static final Random RAND = new Random();


	public MinConflict(int n)
	{
		super(n);
	}

	@Override
	public boolean solveNQueen(int [][] board, int n)
	{
		int col;
		int stateCount = 0;
		int [][] temp = new int [n][n];
		temp = copyBoard(board, temp);
		for(int i = 0; i < n*n; i++)
		{
			col = RAND.nextInt(n-1);
			stateCount++;
			getLeastHValueIncolumn(col, temp, n);
			if (isSolved(temp, n))
			{
				System.out.println("Solution:");
				printBoard(temp, n);
				System.out.println("");
				System.out.println("No of steps:  "+stateCount);
				System.out.println("");
				return true;
			}			
		}
		return false;
	}
}