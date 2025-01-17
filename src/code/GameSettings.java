package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class GameSettings {

	public static int numberPlayers;

	public static ArrayList<Player> players;

	public static HashMap<String, TurtleTile> turtles;
	public static HashMap<String, Jewel> jewels;
	public static ArrayList<StoneWall> stoneWalls;
	public static ArrayList<IceWall> iceWalls;

	// is used for the BFS function that checks if everything is reachable when you
	// place a new wall in the Board class
	public static int jewelsAmount;

	public static HashMap<String, int[]> turtlesPositions;
	public static HashMap<String, Character> turtlesOrientations;

	public static HashMap<String, int[]> turtlesStartingPositions;

	public static int playerChoice; // 1 complete program, 2 place a wall, 3 run program, 4 bug a player

	public static ArrayList<String> playersNames;

	public static LinkedHashMap<Jewel, int[]> jewelsPositions;
	public static boolean gameEnd;

	public static ArrayList<Player> ranking = new ArrayList<Player>();
	public static ArrayList<Player> playersOutOfTheGame = new ArrayList<Player>();

	// *** variables for laser in graphic interface ***
	public static boolean drawLaser = false;
	public static int[] laserGIPosition = { 0, 0 };
	public static char laserOrientation = 'S';
	// *** ***

	public static int getNumberOfPlayers() {
		return numberPlayers;
	}

	// we initialize all the tiles
	public static void setTiles() {
		turtles = new HashMap<String, TurtleTile>();
		jewels = new HashMap<String, Jewel>();
		stoneWalls = new ArrayList<StoneWall>();
		iceWalls = new ArrayList<IceWall>();

		TurtleTile BlueTurtle = new TurtleTile("BlueTurtle");
		turtles.put("BlueTurtle", BlueTurtle);

		TurtleTile RedTurtle = new TurtleTile("RedTurtle");
		turtles.put("RedTurtle", RedTurtle);

		TurtleTile GreenTurtle = new TurtleTile("GreenTurtle");
		turtles.put("GreenTurtle", GreenTurtle);

		TurtleTile PurpleTurtle = new TurtleTile("PurpleTurtle");
		turtles.put("PurpleTurtle", PurpleTurtle);

		Jewel BlueJewel = new Jewel("BlueJewel");
		jewels.put("BlueJewel", BlueJewel);

		Jewel RedJewel = new Jewel("RedJewel");
		jewels.put("RedJewel", RedJewel);

		Jewel GreenJewel = new Jewel("GreenJewel");
		jewels.put("GreenJewel", GreenJewel);

		Jewel PurpleJewel = new Jewel("PurpleJewel");
		jewels.put("PurpleJewel", PurpleJewel);

		for (int i = 0; i < 20; i++) {
			StoneWall StoneWall = new StoneWall();
			stoneWalls.add(StoneWall);
		}

		for (int i = 0; i < 12; i++) {
			IceWall IceWall = new IceWall();
			iceWalls.add(IceWall);
		}

	}

	// we initialize the variables for turtles positions management
	public static void setTurtlesPositions() {
		turtlesPositions = new HashMap<String, int[]>();
		int[] posBleue = { 0, 0 };
		int[] posRouge = { 0, 0 };
		int[] posVerte = { 0, 0 };
		int[] posViolette = { 0, 0 };

		turtlesPositions.put("BlueTurtle", posBleue);
		turtlesPositions.put("RedTurtle", posRouge);
		turtlesPositions.put("GreenTurtle", posVerte);
		turtlesPositions.put("PurpleTurtle", posViolette);

		int[] posBleue2 = { 0, 0 };
		int[] posRouge2 = { 0, 0 };
		int[] posVerte2 = { 0, 0 };
		int[] posViolette2 = { 0, 0 };

		turtlesStartingPositions = new HashMap<String, int[]>();

		// to avoid a bug with the BFS (in the board class) and the blocking the
		// starting position we need to only put in the turtlesStartingPositions
		// ArrayList the turtles that will be on the board

		switch (numberPlayers) {
		case 2:
			turtlesStartingPositions.put("BlueTurtle", posBleue2);
			turtlesStartingPositions.put("RedTurtle", posRouge2);
			break;

		case 3:
			turtlesStartingPositions.put("BlueTurtle", posBleue2);
			turtlesStartingPositions.put("RedTurtle", posRouge2);
			turtlesStartingPositions.put("GreenTurtle", posVerte2);
			break;

		case 4:
			turtlesStartingPositions.put("BlueTurtle", posBleue2);
			turtlesStartingPositions.put("RedTurtle", posRouge2);
			turtlesStartingPositions.put("GreenTurtle", posVerte2);
			turtlesStartingPositions.put("PurpleTurtle", posViolette2);

			break;
		}
	}

	public static void updateTurtleStartingPosition(String Tortue, int x, int y) {
		turtlesStartingPositions.get(Tortue)[0] = x;
		turtlesStartingPositions.get(Tortue)[1] = y;
	}

	public static void updateTurtlePosition(String Tortue, int x, int y) {

		turtlesPositions.get(Tortue)[0] = x;
		turtlesPositions.get(Tortue)[1] = y;

	}

	public static void setTurtlesOrientations() {
		turtlesOrientations = new HashMap<String, Character>();

		turtlesOrientations.put("BlueTurtle", 'S');
		turtlesOrientations.put("RedTurtle", 'S');
		turtlesOrientations.put("GreenTurtle", 'S');
		turtlesOrientations.put("PurpleTurtle", 'S');

	}

	public static void updateTurtleOrientation(String Tortue, char direction) {
		turtlesOrientations.put(Tortue, direction);
	}

	// if two turtles hit themselves they have to go back to their staring position
	public static void turtleCollisionBackToStart(TurtleTile t1, TurtleTile t2, Object[][] board) {
		updateTurtlePosition(t1.getType(), turtlesStartingPositions.get(t1.getType())[0],
				turtlesStartingPositions.get(t1.getType())[1]);
		updateTurtleOrientation(t1.getType(), 'S');

		updateTurtlePosition(t2.getType(), turtlesStartingPositions.get(t2.getType())[0],
				turtlesStartingPositions.get(t2.getType())[1]);
		updateTurtleOrientation(t2.getType(), 'S');

		board[turtlesStartingPositions.get(t1.getType())[0]][turtlesStartingPositions.get(t1.getType())[1]] = t1;
		board[turtlesStartingPositions.get(t2.getType())[0]][turtlesStartingPositions.get(t2.getType())[1]] = t2;
	}

	public static void checkVictory(Player p, Object[][] board) {

		for (int i = 0; i < jewelsPositions.size(); i++) {

			// a bit of a filthy code, we want to get the jewelsPositions elements by index
			// not keys so we use
			// jewelsPositions.get(jewelsPositions.keySet().toArray()[i])[0] to access them

			if (jewelsPositions.get(jewelsPositions.keySet().toArray()[i])[0] == turtlesPositions
					.get(p.getTurtle().getType())[0]
					&& jewelsPositions.get(jewelsPositions.keySet().toArray()[i])[1] == turtlesPositions
							.get(p.getTurtle().getType())[1]) {

				board[turtlesPositions.get(p.getTurtle().getType())[0]][turtlesPositions
						.get(p.getTurtle().getType())[1]] = jewelsPositions.keySet().toArray()[i];
				
				// we have to remove all the following cards in the player's program

				p.program.getProgram().clear();

				// we add the player to the ranking
				ranking.add(p);

				// At first we removed the player from the playing pool but it could create bugs
				// because of the for loop in main so we keep it in the player ArrayList and we
				// use the PlayerOutOfTheGame trick to check if the player's still playing

				playersOutOfTheGame.add(p);

			}
		}
	}

}
