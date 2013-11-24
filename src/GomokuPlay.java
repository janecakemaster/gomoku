import java.util.Scanner;

public class GomokuPlay {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = Integer.parseInt(args[0]); // size of board
		int m = Integer.parseInt(args[1]); // length of winning chain
		int g = Integer.parseInt(args[3]); // game mode
		if (g == 1) {
			System.out
					.println("Would you like to go first or second? Please enter 1 or 2");
			int turn = Integer.parseInt(scan.nextLine());
			if (turn == 1) {
				Human x = new Human(n, m, true);
				SmartAgent o = new SmartAgent(n, m, false);
				String xMove, oMove = "";
				xMove = x.takeTurn();
				System.out.println(xMove);
				boolean play = true;
				while (play) {
					o.receiveTurn(xMove);
					oMove = o.takeTurn();
					if (o.board.winner == 'x') {
						System.out.println("Winner: x");
						play = false;
						break;
					}
					if (o.board.winner == 'o') {
						System.out.println("Winner: o");
						play = false;
						break;
					}
					if (o.board.winner == 'd') {
						System.out.println("Winner: d");
						play = false;
						break;
					}
					x.receiveTurn(oMove);
					xMove = x.takeTurn();
					if (x.board.winner == 'x') {
						System.out.println("Winner: x");
						play = false;
						break;
					}
					if (x.board.winner == 'o') {
						System.out.println("Winner: o");
						play = false;
						break;
					}
					if (x.board.winner == 'd') {
						System.out.println("Winner: d");
						play = false;
						break;
					}
				}

			} else {
				SmartAgent x = new SmartAgent(n, m, false);
				Human o = new Human(n, m, true);
				String xMove, oMove = "";
				xMove = x.firstTurn();
				System.out.println(xMove);
				boolean play = true;
				while (play) {
					o.receiveTurn(xMove);
					oMove = o.takeTurn();
					if (o.board.winner == 'x') {
						System.out.println("Winner: x");
						play = false;
						break;
					}
					if (o.board.winner == 'o') {
						System.out.println("Winner: o");
						play = false;
						break;
					}
					if (o.board.winner == 'd') {
						System.out.println("Winner: d");
						play = false;
						break;
					}
					x.receiveTurn(oMove);
					xMove = x.takeTurn();
					if (x.board.winner == 'x') {
						System.out.println("Winner: x");
						play = false;
						break;
					}
					if (x.board.winner == 'o') {
						System.out.println("Winner: o");
						play = false;
						break;
					}
					if (x.board.winner == 'd') {
						System.out.println("Winner: d");
						play = false;
						break;
					}
				}
			}
		}
		if (g == 2) {
			System.out
					.println("Would you like the smart agent to go first or second? Please enter 1 or 2");
			int turn = Integer.parseInt(scan.nextLine());
			if (turn == 1) {
				SmartAgent x = new SmartAgent(n, m, true);
				RandomAgent o = new RandomAgent(n, m, false);
				String xMove, oMove = "";
				xMove = x.firstTurn();
				System.out.println(xMove);
				boolean play = true;
				while (play) {
					o.receiveTurn(xMove);
					oMove = o.takeTurn();
					if (o.board.winner == 'x') {
						System.out.println("Winner: x");
						play = false;
						break;
					}
					if (o.board.winner == 'o') {
						System.out.println("Winner: o");
						play = false;
						break;
					}
					if (o.board.winner == 'd') {
						System.out.println("Winner: d");
						play = false;
						break;
					}
					x.receiveTurn(oMove);
					xMove = x.takeTurn();
					if (x.board.winner == 'x') {
						System.out.println("Winner: x");
						play = false;
						break;
					}
					if (x.board.winner == 'o') {
						System.out.println("Winner: o");
						play = false;
						break;
					}
					if (x.board.winner == 'd') {
						System.out.println("Winner: d");
						play = false;
						break;
					}
				}
			} else {
				RandomAgent x = new RandomAgent(n, m, true);
				SmartAgent o = new SmartAgent(n, m, false);
				String xMove, oMove = "";
				xMove = x.takeTurn();
				System.out.println(xMove);
				boolean play = true;
				while (play) {
					o.receiveTurn(xMove);
					oMove = o.takeTurn();
					if (o.board.winner == 'x') {
						System.out.println("Winner: x");
						play = false;
						break;
					}
					if (o.board.winner == 'o') {
						System.out.println("Winner: o");
						play = false;
						break;
					}
					if (o.board.winner == 'd') {
						System.out.println("Winner: d");
						play = false;
						break;
					}
					x.receiveTurn(oMove);
					xMove = x.takeTurn();
					if (x.board.winner == 'x') {
						System.out.println("Winner: x");
						play = false;
						break;
					}
					if (x.board.winner == 'o') {
						System.out.println("Winner: o");
						play = false;
						break;
					}
					if (x.board.winner == 'd') {
						System.out.println("Winner: d");
						play = false;
						break;
					}
				}
			}
		}
		if (g == 3) {
			SmartAgent x = new SmartAgent(n, m, true);
			SmartAgent o = new SmartAgent(n, m, false);
			String xMove, oMove = "";
			xMove = x.firstTurn();
			boolean play = true;
			while (play) {
				o.receiveTurn(xMove);
				oMove = o.takeTurn();
				if (o.board.winner == 'x') {
					System.out.println("Winner: x");
					play = false;
					break;
				}
				if (o.board.winner == 'o') {
					System.out.println("Winner: o");
					play = false;
					break;
				}
				if (o.board.winner == 'd') {
					System.out.println("Winner: d");
					play = false;
					break;
				}
				x.receiveTurn(oMove);
				xMove = x.takeTurn();
				if (x.board.winner == 'x') {
					System.out.println("Winner: x");
					play = false;
					break;
				}
				if (x.board.winner == 'o') {
					System.out.println("Winner: o");
					play = false;
					break;
				}
				if (x.board.winner == 'd') {
					System.out.println("Winner: d");
					play = false;
					break;
				}
			}
		}
	}
}