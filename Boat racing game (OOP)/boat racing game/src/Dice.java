import java.util.Random;
import java.util.Scanner;

public class Dice {
    private Random random;

    public Dice() {
        random = new Random();
    }

    public Dice(Random random) {
		this.random = random;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public int roll(Boat boat) {
        // Generate a random number between 1 and 6 (inclusive)
    	Scanner scanner = new Scanner(System.in);
    	System.out.println(boat+" Please roll the dice [Enter any key]: ");
    	int ranNum = random.nextInt(5) + 1;
    	scanner.next();
    	System.out.println("Dice number: "+ranNum);
        return ranNum;
    }

	@Override
	public String toString() {
		return String.format("Dice [random=%s]", random);
	}
    
}


