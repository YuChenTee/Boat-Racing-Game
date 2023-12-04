import java.util.Random;

public class Current extends RiverObject{
	
	public Current() {
		this.setPiece("| C |");
		this.setSteps(getrandom());
		
	}
	
	@Override
	public void outputEffect(Boat boat) {
		System.out.println(boat+" have encountered a current!! Move forward "+getSteps()+" steps");
	}
	
	// to get odd numbers 2n+1
	@Override
	public int getrandom() {
		Random random = new Random();
		int ranNum = (1+random.nextInt(2))*2 + 1;
		return ranNum;
	}
}
