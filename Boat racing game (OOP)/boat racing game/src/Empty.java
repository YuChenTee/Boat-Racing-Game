public class Empty extends RiverObject{
	public Empty() {
		this.setPiece("|   |");
	}

	@Override
	public void outputEffect(Boat boat) {
		System.out.println(boat+" is slowing down.....");
	}

}
