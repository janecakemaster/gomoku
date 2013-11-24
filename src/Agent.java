/**
 * Agent class that is extended by other players
 */
public class Agent {
	Board board;
	char me;
	char them;
	boolean isFirst;

	/**
	 * Agent constructor
	 * Each agent has an instance of Board
	 * @param  n       board dimension
	 * @param  m       winning chain length
	 * @param  isFirst player's turn
	 */
	public Agent(int n, int m, boolean isFirst) {
		this.board = new Board(n, m);
		this.me = isFirst ? 'x' : 'o';
		this.them = isFirst ? 'o' : 'x';
		this.isFirst = isFirst;
	}

	/**
	 * Get opponent's turn and update board
	 * @param  move opponent's move
	 * @return opponent's move
	 */
	String receiveTurn(String move) {
		board.placeMove(them, move, false);
		return move;
	}

	/**
	 * Execute a turn
	 * @param  move to execute
	 * @return agent's move
	 */
	String takeTurn(String move) {
		return null;
	}

	/**
	 * Get the board's winner
	 * @return player's char representation
	 */
	char getWinner() {
		return board.winner;
	}
}