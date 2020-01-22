package code;

import java.util.ArrayDeque;

import ginterface.GBoard;
import ginterface.GCompleteProgram;

public class Program {

	public ArrayDeque<Card> program;

	public Program() {
		this.program = new ArrayDeque<Card>();
	}

	public ArrayDeque<Card> getProgram() {
		return program;
	}

	// complete program, calls the graphic interface
	public void completeProgram(Player p) throws InterruptedException {

		new GCompleteProgram(p.hand, this.program, p.turtle);

		while (!GCompleteProgram.closed) {
			Thread.sleep(300);
		}
	}

	public void runProgram(Board board, Player p) throws InterruptedException {

		int size = this.program.size();
		for (int i = 0; i < size; i++) {
			// we added the check for the program not to be empty, because if someone wins,
			// his program is emptied inside the for loop
			if (!program.isEmpty()) {
				this.program.element().runCard(board.getBoard(), p);

				// we have to check if the player reached a jewel
				GameSettings.checkVictory(p, board.getBoard());

				// we delay the board update to have a moving animation
				Thread.sleep(500);
				GBoard.updateGI();

			}
		}

		// if someone was bugged then it gets debugged from running his program
		p.isBugged = false;

	}

	// reverse program when bugged
	public void reverseProgram() {
		ArrayDeque<Card> tempProgram = new ArrayDeque<Card>();

		int size = this.program.size();
		for (int i = 0; i < size; i++) {
			tempProgram.addFirst(this.program.element());
			this.program.remove();
		}

		this.program = tempProgram;

	}

}
