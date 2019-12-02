package code;

import java.util.ArrayList;
import java.util.HashMap;

public class GameSettings {

	public static boolean gameWon = false; // TODO methode qui verifie si quelqu'un a gagne
	public static int numberPlayers;
	public static HashMap<String, TurtleTile> turtles;
	public static HashMap<String, Jewel> jewels;
	public static ArrayList<StoneWall> stoneWalls;
	public static ArrayList<IceWall> iceWalls;

	public static HashMap<String, int[]> turtlesPositions;
	public static HashMap<String, Character> turtlesOrientations;

	public static HashMap<String, int[]> turtlesStartingPositions;

	public static int playerChoice; // 1 commpleter prog, 2 placer un mur, 3 exectuer prog

	public static boolean victory;

	public static int getNumberOfPlayers() {
		return numberPlayers;
	}

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

		turtlesStartingPositions.put("BlueTurtle", posBleue2);
		turtlesStartingPositions.put("RedTurtle", posRouge2);
		turtlesStartingPositions.put("GreenTurtle", posVerte2);
		turtlesStartingPositions.put("PurpleTurtle", posViolette2);

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
}
