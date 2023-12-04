import java.util.Random;

public class Chance extends RiverObject{
	
	public Chance() {
		this.setPiece("| ? |");
		this.setSteps(getrandom());
		
	}
	
	@Override
	public int getrandom() {
		Random random = new Random();
		Boolean ranBool = random.nextBoolean();
		int ranNum = 1+random.nextInt(3);
		if (ranBool == false) {
			ranNum *= -1;
		}
		return ranNum;
	}
	@Override
	public void outputEffect(Boat boat) {
		System.out.println(boat+" have encountered a chance!! Move "+getSteps()+" steps");

	}
}
