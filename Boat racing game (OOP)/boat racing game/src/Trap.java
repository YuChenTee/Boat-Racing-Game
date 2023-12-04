import java.util.Random;

public class Trap extends RiverObject{
	
	public Trap() {
		this.setPiece("| # |");
		this.setSteps(getrandom());
		
	}

	@Override
	public void outputEffect(Boat boat) {
		System.out.println(boat+" have encountered a trap!! Move backwards "+getSteps()*-1+" steps");
	}
	
	// to get even numbers 2n
	@Override
	public int getrandom() {
		Random random = new Random();
		int ranNum = (1+random.nextInt(2))*-2;
		return ranNum;
	}
	
}
