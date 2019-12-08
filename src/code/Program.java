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

			// in case there's a laser being shot, we wait 1sec then stop trying to render
			// it.
			// it is nevertheless important to have a gif file that only runs once (that's
			// one parameter of gif files). If not the frame will keep refreshing even
			// though the gif's removed
			if (GameSettings.drawLaser) {
				Thread.sleep(1000);
				GameSettings.drawLaser = false;
				GBoard.updateGI();
			}

		}

		// GestionJeu.updateDirectionTortue(this.Tortue.getType(), direction);

	}

}
