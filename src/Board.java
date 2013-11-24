import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Board {
	char[][] board;
	int n;
	int m;
	char nextPlayer;
	char prevPlayer;
	char winner;
	String lastMove;

	public Board(int n, int m) {
		this.n = n;
		this.m = m;
		this.board = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.board[i][j] = '.';
			}
		}
		this.winner = '.';
	}

	public Board(Board other) {
		this.n = other.n;
		this.m = other.m;
		this.board = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.board[i][j] = other.board[i][j];
			}
		}
		this.nextPlayer = other.nextPlayer;
		this.prevPlayer = other.prevPlayer;
	}

	Set<String> getEmpties() {
		Set<String> ems = new HashSet<String>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == '.') {
					ems.add(i + " " + j);
				}

			}
		}
		return ems;
	}

	String placeMove(char p, String move, boolean out) {
		int[] ij = parseMove(move);
		board[ij[0]][ij[1]] = p;
		if (out)
			System.out.println(move);
		prevPlayer = p;
		if (p == 'x')
			nextPlayer = 'o';
		else
			nextPlayer = 'x';
		winner = setWinner(p, ij);
		return move;
	}

	ArrayList<String> getPlayerPlaces(char p) {
		ArrayList<String> places = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == p) {
					places.add(i + " " + j);
				}
			}
		}
		return places;
	}

	ArrayList<String> lookAround(String pos) {
		ArrayList<String> adjacent = new ArrayList<String>();
		int[] coords = parseMove(pos);
		int i = coords[0];
		int j = coords[1];
		String x;
		if (i - 1 >= 0) {
			if (board[i - 1][j] == '.') {
				x = strMove(i - 1, j);
				adjacent.add(x);
			}
			if (j - 1 >= 0) {
				if (board[i - 1][j - 1] == '.') {
					x = strMove(i - 1, j - 1);
					adjacent.add(x);
				}
			}
		}
		if (j + 1 < n) {
			if (board[i][j + 1] == '.') {
				x = strMove(i, j + 1);
				adjacent.add(x);
			}
			if (i - 1 >= 0) {
				if (board[i - 1][j + 1] == '.') {
					x = strMove(i - 1, j + 1);
					adjacent.add(x);
				}
			}
		}
		if (i + 1 < n) {
			if (board[i + 1][j] == '.') {
				x = strMove(i + 1, j);
				adjacent.add(x);
			}
			if (j + 1 < n) {
				if (board[i + 1][j + 1] == '.') {
					x = strMove(i + 1, j - 1);
					adjacent.add(x);
				}
			}
		}
		return adjacent;
	}

	char setWinner(char p, int[] ij) {
		if (isRowWin(p, ij) || isColWin(p, ij) || isLtrWin(p, ij)
				|| isRtlWin(p, ij)) {
			return p;
		}
		if (getEmpties().isEmpty()) {
			return 'd';
		}
		return '.';

	}

	boolean isRowWin(char p, int[] ij) {
		String row = new String(board[ij[0]]);
		if (row.contains(strMatch(p)))
			return true;
		return false;
	}

	boolean isColWin(char p, int[] ij) {
		String column = "";
		for (int i = 0; i < n; i++) {
			column += board[i][ij[1]];
		}
		if (column.contains(strMatch(p)))
			return true;
		return false;
	}

	boolean isLtrWin(char p, int[] ij) {
		int i = ij[0];
		int j = ij[1];
		String match = strMatch(p, m);
		String diag = "";
		// going up and left
		while (i >= 0 && j >= 0) {
			diag = String.valueOf(board[i][j]) + diag;
			i--;
			j--;
		}
		i = ij[0] + 1;
		j = ij[1] + 1;
		// going down and right
		while (i < n && j < n) {
			diag += String.valueOf(board[i][j]);
			i++;
			j++;
		}
		if (diag.contains(match))
			return true;
		return false;
	}

	boolean isRtlWin(char p, int[] ij) {
		int i = ij[0];
		int j = ij[1];
		String match = strMatch(p, m);
		String diag = "";
		// going up and right
		while (i >= 0 && j < n) {
			diag = String.valueOf(board[i][j]) + diag;
			i--;
			j++;
		}
		i = ij[0] + 1;
		j = ij[1] - 1;
		// going down and left
		while (i < n && j >= 0) {
			diag += String.valueOf(board[i][j]);
			i++;
			j--;
		}
		if (diag.contains(match))
			return true;
		return false;
	}

	int nearWins(char p, int away) {
		return nearWinRows(p, away) + nearWinCols(p, away);
	}

	int nearWinRows(char p, int away) {
		int count = 0;
		int length = m - away;
		String match1 = strMatch(p, length);
		String match2 = '.' + match1;
		match1 += '.';
		for (int i = 0; i < n; i++) {
			String row = new String(board[i]);
			if (row.contains(match1)) {
				int x = row.indexOf(match1);
				while (x >= 0) {
					count++;
					x = row.indexOf(match1, match1.length() + x);
				}
			}
			if (row.contains(match2)) {
				int x = row.indexOf(match2);
				while (x >= 0) {
					count++;
					x = row.indexOf(match2, match2.length() + x);
				}
			}
		}
		return count;
	}

	int nearWinCols(char p, int away) {
		int count = 0;
		int length = m - away;
		String match1 = strMatch(p, length);
		String match2 = '.' + match1;
		match1 += '.';
		for (int j = 0; j < n; j++) {
			String column = "";
			for (int i = 0; i < n; i++) {
				column += board[i][j];
			}
			if (column.contains(match1)) {
				int x = column.indexOf(match1);
				while (x >= 0) {
					count++;
					x = column.indexOf(match1, match1.length() + x);
				}
			}
			if (column.contains(match2)) {
				int x = column.indexOf(match2);
				while (x >= 0) {
					count++;
					x = column.indexOf(match2, match2.length() + x);
				}
			}
		}

		return count;
	}

	String strMatch(char p) {
		String match = "";
		for (int i = 0; i < m; i++) {
			match += Character.toString(p);
		}
		return match;
	}

	String strMatch(char p, int length) {
		String match = "";
		for (int i = 0; i < length; i++) {
			match += Character.toString(p);
		}
		return match;
	}

	int[] parseMove(String s) {
		String[] ss = s.split(" ");
		int[] ij = { Integer.parseInt(ss[0]), Integer.parseInt(ss[1]) };
		return ij;
	}

	String strMove(int i, int j) {
		return i + " " + j;
	}

	String strMove(int[] ij) {
		return ij[0] + " " + ij[1];
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				str += board[i][j] + " ";
			}
			str += "\n";
		}
		return str;
	}

}