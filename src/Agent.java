public class Agent {
	Board board;
	char me;
	char them;
	boolean isFirst;

	public Agent(int n, int m, boolean isFirst) {
		this.board = new Board(n, m);
		this.me = isFirst ? 'x' : 'o';
		this.them = isFirst ? 'o' : 'x';
		this.isFirst = isFirst;
	}

	String receiveTurn(String move) {
		board.placeMove(them, move, false);
		return move;
	}

	String takeTurn(String move) {
		return null;
	}

	char getWinner() {
		return board.winner;
	}
}