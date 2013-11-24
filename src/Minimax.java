import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Minimax class has evaluation function and minimax algorithm with alpha-beta pruning to determine best moves.
 */
public class Minimax {

	/**
	 * Evaluation function
	 * @param  board node to evaluate
	 * @return score of board
	 */
	double evaluate(Board board) {
		int oneAway = board.nearWins(board.prevPlayer, 1);
		int twoAway = board.nearWins(board.prevPlayer, 2);
		int threeAway = board.nearWins(board.prevPlayer, 3);
		double score = oneAway * 100.0 + twoAway * 5.0 + threeAway * 1.0;
		return score;
	}

	/**
	 * Minimax algorithm with alpha-beta pruning.
	 * @param  depth lookahead
	 * @param  myBest alpha
	 * @param  theirBest beta
	 * @return Object[0] contains (Double) score and Object[1] contains (String) move
	 */
	Object[] mmab(Board board, int d, double myBest, double theirBest) {
		ArrayList<String> moveList;
		Set<String> moves = new HashSet<String>();
		ArrayList<String> places = board.getPlayerPlaces(board.nextPlayer);
		for (int i = 0; i < places.size(); i++) {
			moves.addAll(board.lookAround(places.get(i)));
		}
		moves.retainAll(board.getEmpties());
		// make sure that moves is not empty
		// otherwise, pick from list of empty locations
		if (moves.isEmpty())
			moveList = new ArrayList<String>(board.getEmpties());
		else
			moveList = new ArrayList<String>(moves);

		Double bestScore;
		Object[] temp;
		Double tempScore;
		String bestMove = "";

		// evaluate at leaf
		if (d == 0) {
			Object[] x = { evaluate(board), moveList.get(0) };
			return x;
		}
		bestScore = myBest;
		while (moveList.size() > 0) {
			Board newBoard = new Board(board);
			String newMove = moveList.get(0);
			newBoard.placeMove(newBoard.nextPlayer, newMove, false);
			temp = mmab(newBoard, d - 1, -theirBest, -bestScore);
			tempScore = -(Double) temp[0];
			if (tempScore > bestScore) {
				bestScore = tempScore;
				bestMove = newMove;
			}
			if (bestScore > theirBest) {
				Object[] x = { bestScore, bestMove };
				return x;
			}
			moveList.remove(0);
		}
		Object[] x = { bestScore, bestMove };
		return x;
	}
}