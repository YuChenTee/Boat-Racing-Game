
public class Game {
	private static int gameSize = 100;
	private static int startingPoint = -1;
	private static int endingPoint = gameSize-1;
	private Player playerA;
	private Player playerB;
	private River river;
	private boolean turn = true;
	private boolean gamePlaying = true;
	private Scorelist scores;
	
	public Game() {
		
	}
	public Game(Player playerA, Player playerB, River river, boolean turn, boolean gamePlaying) {
		this.playerA = playerA;
		this.playerB = playerB;
		this.river = river;
		this.turn = turn;
		this.gamePlaying = gamePlaying;
	}
	
	
	public Player getPlayerA() {
		return playerA;
	}
	public void setPlayerA(Player playerA) {
		this.playerA = playerA;
	}
	public Player getPlayerB() {
		return playerB;
	}
	public void setPlayerB(Player playerB) {
		this.playerB = playerB;
	}
	public static int getGameSize() {
		return gameSize;
	}
	public static void setGameSize(int gameSize) {
		Game.gameSize = gameSize;
	}
	public River getRiver() {
		return river;
	}
	public void setRiver(River river) {
		this.river = river;
	}

	public boolean isTurn() {
		return turn;
	}
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	public boolean isGamePlaying() {
		return gamePlaying;
	}
	
	public void setGamePlaying(boolean gamePlaying) {
		this.gamePlaying = gamePlaying;
	}
	
	
	public static int getStartingPoint() {
		return startingPoint;
	}
	public static void setStartingPoint(int startingPoint) {
		Game.startingPoint = startingPoint;
	}
	public static int getEndingPoint() {
		return endingPoint;
	}
	public static void setEndingPoint(int endingPoint) {
		Game.endingPoint = endingPoint;
	}
	
	public Scorelist getScores() {
		return scores;
	}
	public void setScores(Scorelist scores) {
		this.scores = scores;
	}
	
	public void checkGameState(Player player, Scorelist scorelist) {
		if (player.getPosition()>=endingPoint) {
			setGamePlaying(false);
			System.out.println(player.getName()+" wins the game!!!");
			scorelist.checkScore(player);
		}
	}
	
	public void printGameDescription() {
		System.out.println("--- << BOAT RACING GAME >> ---");
		System.out.println();
		System.out.println("--- Game Rules ---");
		System.out.println("This game is a two player game. Users are required to enter their names, "
				+ "and choose a boat to begin the game. During the game, each player takes turn to throw a " );
		System.out.println("dice. The boat will move according to the "
				+ "number of the dice. There are traps(#) and currents(C) scattered around the river. Some currents are stronger than ");		
		System.out.println("others, so as traps. If two boats crashed, the boat behind will return to the starting position !!!");
		System.out.println("---------------------------------------------------------------------------------------------------------"
				+ "--------------------------------------------------------");
		System.out.println();	
	}
	public void play() {
		printGameDescription();
		scores = new Scorelist();
		scores.displayScores();
		System.out.print("[Player 1] ");
		playerA = new Player();
		System.out.print("[Player 2] ");
		playerB = new Player();
		river = new River(playerA, playerB);
		river.displayRiver();
		
		while (true) {
			if (turn == true) {
				playerA.move();
				playerA.addScore();
				river.handleRiverObjectEffect(playerA);
				checkGameState(playerA, scores);
				turn = false;
			}
			else {
				playerB.move();
				playerB.addScore();
				river.handleRiverObjectEffect(playerB);
				checkGameState(playerB, scores);
				turn = true;
			}
			
			if (gamePlaying == false) {
				river.displayRiver();
				scores.displayScores();
				break;
			}
			
		}
		
		
		
	}
	
	@Override
	public String toString() {
		return String.format("Game [playerA=%s, playerB=%s, river=%s, turn=%s, gamePlaying=%s]", playerA, playerB,
				river, turn, gamePlaying);
	}
	
	
	
}
