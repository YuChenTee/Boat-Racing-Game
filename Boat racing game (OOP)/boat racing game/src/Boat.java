import java.util.Scanner;

public class Boat extends RiverObject{
	private String[] boatList = {"|AAA|","|BBB|","|XXX|","|DDD|","|EEE|"};
	
	public Boat() {
		getBoatOption();
		
		// if two boat crashes, get the boat back to starting point 
		this.setSteps(-99);
	}

	public Boat(String[] boatList) {
		this.boatList = boatList;
	}

	public String[] getBoatList() {
		return boatList;
	}

	public void setBoatList(String[] boatList) {
		this.boatList = boatList;
	}
	
	public void getBoatOption() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Which boat would you like to choose (1-5):");
	    for (int i = 0; i < boatList.length; i++) {
	        System.out.println((i + 1) + ") " + boatList[i]);
	    }
	    String selectedBoatIndexStr = scanner.nextLine();

	    while (!selectedBoatIndexStr.matches("[1-5]")) {
	        System.out.println("Please enter a number between (1-5)! ");
	        System.out.println("Which boat would you like to choose (1-5):");
	        for (int i = 0; i < boatList.length; i++) {
	            System.out.println((i + 1) + ") " + boatList[i]);
	        }
	        selectedBoatIndexStr = scanner.nextLine();
	    }

	    int selectedBoatIndex = Integer.parseInt(selectedBoatIndexStr);
	    this.setPiece(boatList[selectedBoatIndex - 1]);
	}
	@Override
	public void outputEffect(Boat boat) {
		System.out.println(boat+" crashed with other boat!!! back to starting point!");
	}
	
}
