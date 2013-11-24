public class SmartAgent extends Agent {

	Minimax minimax;

	public SmartAgent(int n, int m, boolean isFirst) {
		super(n, m, isFirst);
		minimax = new Minimax();
	}

	String firstTurn() {
		String move = "7 7";
		board.placeMove(me, move, true);
		System.out.println(board);
		return move;
	}

	String takeTurn() {
		String move = pickMove();
		board.placeMove(me, move, true);
		System.out.println(board);
		return move;
	}

	String pickMove() {
		Object[] move = minimax.mmab(board, 1, Double.NEGATIVE_INFINITY,
				Double.POSITIVE_INFINITY);
		return (String) move[1];
	}
}