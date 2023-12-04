import java.util.Random;

public class RiverObject {
	private String piece;
	private int steps;
	
	public RiverObject() {

	}
	public RiverObject(String piece) {
		this.piece = piece;
	}
	
	public RiverObject(String piece, int steps) {
		this.piece = piece;
		this.steps = steps;
	}
	public String getPiece() {
		return piece;
	}
	public void setPiece(String piece) {
		this.piece = piece;
	}
	
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	public int getrandom() {
		Random random = new Random();
		int ranNum = 1+random.nextInt(5);
		return ranNum;
	}
	
	public void outputEffect(Boat boat) {
		System.out.println(boat+" have encountered an object!! ");
	}
	
	@Override
	public String toString() {
		return piece;
	}
	
	
}
