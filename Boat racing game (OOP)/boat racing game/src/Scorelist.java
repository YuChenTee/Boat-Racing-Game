import java.util.*;
import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.io.File;


public class Scorelist {
	ArrayList<Integer> topScores = new ArrayList<Integer>();
	ArrayList<String> topNames = new ArrayList<String>();
	
	public Scorelist() {
		
	}

	public Scorelist(ArrayList<Integer> topScores, ArrayList<String> topNames) {
		this.topScores = topScores;
		this.topNames = topNames;
	}
	
	
	public ArrayList<Integer> getTopScores() {
		return topScores;
	}

	public void setTopScores(ArrayList<Integer> topScores) {
		this.topScores = topScores;
	}

	public ArrayList<String> getTopNames() {
		return topNames;
	}

	public void setTopNames(ArrayList<String> topNames) {
		this.topNames = topNames;
	}

	public void readTextFile() {
		try{
			Scanner input = new Scanner(new File("TopScore.txt"));
			while (input.hasNext()){
				System.out.printf("%1d %-10s %d \n", input.nextInt(),input.next(), input.nextInt());
			}
			if (input != null){
				input.close();
			}
		} 
		catch (FileNotFoundException fe){
			System.out.println("Error opening file.");
		}
		catch (NoSuchElementException ex){
			System.out.println("File improperly formed.");
		}
	}
	public void displayScores() {
	
		System.out.println("SCOREBOARD(Ranking, Name, Steps)");
		System.out.println("--------------------------------");
		readTextFile();
		System.out.println("--------------------------------");
		System.out.println();
	} 
	
	public void ReadThirdIntFromFile() {
        try {
            // Replace "TopScore.txt" with the actual file path if it's located in a different directory
            File file = new File("TopScore.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Split the line by spaces
                String[] parts = line.split("\\s+");

                if (parts.length >= 3) {
                    // Get the third part (integer) from the split line
                	String secondString = parts[1];
                    String thirdIntString = parts[2];

                    try {
                        // Parse the third integer and use it as needed
                        int thirdInt = Integer.parseInt(thirdIntString);
                        topScores.add(thirdInt);
                        topNames.add(secondString);
                    } catch (NumberFormatException e) {
                        // Handle any parsing errors if the third part is not an integer
                        System.out.println("Error parsing integer: " + thirdIntString);
                    }
                } 
            }

            // Close the scanner after use
            scanner.close();
        } catch (FileNotFoundException e) {
            // Handle file not found errors
            System.out.println("File not found: " + e.getMessage());
        }
	   
	}
	
	public void checkScore(Player player) {	
		ReadThirdIntFromFile();
		for (int i = 0 ; i < topScores.size(); i++) {
			if (player.getScore()<topScores.get(i)) {
				topScores.set(topScores.size()-1, player.getScore());
				break;
			}
		}
		Collections.sort(topScores);
		replaceNameArray(player);
		writeTextFile();

	}
	
	public void replaceNameArray(Player player) {
		int index;
		for (int i = 0 ; i < topScores.size(); i++) {
			if (player.getScore() == topScores.get(i)) {
				index = i;
				topNames.remove(topNames.size()-1);
				topNames.add(index, player.getName());
				break;
			}
		}
		
	}
	
	public void writeTextFile() {
		
		Formatter output;
		try{
			output = new Formatter("TopScore.txt");
			for (int i = 0; i < topScores.size();i++) {
				output.format("%d %s %d\n", i+1, topNames.get(i), topScores.get(i));
			}
			if (output!= null){
				output.close();
			}
		} 
		catch (SecurityException se){
			System.out.println("You do not have write access");
			System.exit(1);
		} 
		catch (FileNotFoundException fe){
			System.out.println("Error opening/creating file.");
			System.exit(1);
		}
	}

	@Override
	public String toString() {
		return String.format("Scorelist [topScores=%s, topNames=%s]", topScores, topNames);
	}
	

	
}

