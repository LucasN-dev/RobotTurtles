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

	public void completeProgram(Player p) throws InterruptedException {

		new GCompleteProgram(p.hand, this.program, p.turtle);

		while (!GCompleteProgram.closed) {
			Thread.sleep(300);
		}
	}

	public void runProgram(Board board, Player p) throws InterruptedException {

		int size = this.program.size();
		for (int i = 0; i < size; i++) {
			this.program.element().runCard(board.getBoard(), p);

			Thread.sleep(500);
			GBoard.updateGI();

		}

		// GestionJeu.updateDirectionTortue(this.Tortue.getType(), direction);

	}

}
