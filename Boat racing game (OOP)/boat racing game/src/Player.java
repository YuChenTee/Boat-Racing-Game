import java.util.*;

public class Player {
	private String name = "";
	private int position = Game.getStartingPoint();
	private int score = 0;
	private Boat boat;
	private Dice dice;
	
	public Player() {
		inputInfo();
		boat = new Boat();
		dice = new Dice();
	}

	
	public Player(String name, int position, int score, Boat boat, Dice dice) {
		this.name = name;
		this.position = position;
		this.score = score;
		this.boat = boat;
		this.dice = dice;
	}

	public int getPosition() {
		// avoid boat go beyond the river starting and ending point
		if (this.position<=Game.getStartingPoint()) {
			this.position=Game.getStartingPoint();
		}
		else if (this.position >= Game.getEndingPoint()) {
			this.position=Game.getEndingPoint();
		}
		return position;
	}

	public void setPosition(int position) {
		this.position = position;

		if (this.position<=Game.getStartingPoint()) {
			this.position=Game.getStartingPoint();
		}
		else if (this.position >= Game.getEndingPoint()) {
			this.position=Game.getEndingPoint();
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		this.dice = dice;
	}

	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public void move() {
		int steps = dice.roll(boat);
		this.setPosition(this.getPosition()+steps);
	}

	public void inputInfo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your name (one word):");
		name = scanner.nextLine();	
		while (name.contains(" ")) {
            System.out.println("Please enter your name with one word only!");
            System.out.println("Please enter your name (one word):");
            name = scanner.nextLine();	
        }
	}
	
	public void addScore() {
		score += 1;
	}
	@Override
	public String toString() {
		return String.format("Player [position=%s, score=%s]", position, score);
	}
	
	
}
