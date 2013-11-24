import java.util.Scanner;

public class Human extends Agent {

	Scanner scan;

	public Human(int n, int m, boolean isFirst) {
		super(n, m, isFirst);
		scan = new Scanner(System.in);
	}

	String takeTurn() {
		String move = pickMove();
		board.placeMove(me, move, true);
		System.out.println(board);
		return move;
	}

	String pickMove() {
		System.out.println("Please enter your first move. e.g. "
				+ "1 2; 10 6; 2 5");
		String move = scan.nextLine();
		if (board.getEmpties().contains(move))
			return move;
		return "-1 -1";
	}

}