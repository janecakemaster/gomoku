import java.util.ArrayList;
import java.util.Random;

public class RandomAgent extends Agent {

	Random r;

	public RandomAgent(int n, int m, boolean isFirst) {
		super(n, m, isFirst);
		r = new Random();
	}

	String takeTurn() {
		String move = pickMove();
		board.placeMove(me, move, true);
		System.out.println(board);
		return move;
	}

	String pickMove() {
		ArrayList<String> ems = new ArrayList<String>(board.getEmpties());
		int size = ems.size();
		if (size > 0)
			return ems.get(r.nextInt(size));
		return "-1 -1";
	}

}