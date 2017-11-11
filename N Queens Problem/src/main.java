import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("N Queen Puzzle");
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter No of Queen's: ");
		int n = reader.nextInt(); // Scans the next token of the input as an int.
		System.out.println("Enter 1 for Hill CLimbing and 2 for Min-Conflict");
		int value = reader.nextInt();
		reader.close(); 		
		switch(value)
		{
		case 1: ChessBoard Queen = new ChessBoard(n);
				break;
		case 2: MinConflict Q = new MinConflict(15);
				break;
		default : System.out.println("You should either provide 1 or 2");
		}
		
	}

}
