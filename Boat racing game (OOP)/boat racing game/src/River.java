import java.util.ArrayList;
import java.util.Random;

public class River {
    private ArrayList<RiverObject> river;
    private Player playerA;
    private Player playerB;

    public River() {
    	
    }
    
    public River(Player A, Player B) {
        river = new ArrayList<>();
        generateRiver();
        playerA = A;
        playerB = B;
    
    }

	public River(ArrayList<RiverObject> river, Player playerA, Player playerB) {
		this.river = river;
		this.playerA = playerA;
		this.playerB = playerB;
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

	private void generateRiver() {
        Random random = new Random();
        
        // To set 10 traps and 20 currents 
        for (int i = 0; i <Game.getGameSize(); i ++) {
        	river.add(new Empty());
        }
        for (int i = 0; i <10; i ++) {
        	int randomNum = 10 + random.nextInt(Game.getGameSize()-13);
        	if (!(river.get(randomNum) instanceof Trap)) {
        		river.set(randomNum, new Trap());
        	}
        	else {
        		i--;
        	}
        }
        for (int i = 0; i <20; i ++) {
        	int randomNum = 1 + random.nextInt(Game.getGameSize()-3);
        	if (!(river.get(randomNum) instanceof Trap) && !(river.get(randomNum) instanceof Current)) {
        		river.set(randomNum, new Current());
        	}
        	else {
        		i--;
        	}
        }
    }

    public ArrayList<RiverObject> getRiver() {
        return river;
    }
    
    public void setRiver(ArrayList<RiverObject> river) {
		this.river = river;
	}

	// remove previous player position on the river to avoid duplicated 
    public void checkPlayerPosition(Player player, RiverObject riverobject) {
    	for(int i = 0; i < Game.getGameSize(); i ++) {
    		if(river.get(i).equals(player.getBoat())) {
    			river.set(i, riverobject);
    		}
    	}
    }
    
    public void updatePlayerPosition(Player player) {
    	if (player.getPosition()>Game.getStartingPoint()) {
    		river.set(player.getPosition(), player.getBoat());
    	}
    }
    
    // how will the player piece move when meet different river objects
    public void handleRiverObjectEffect(Player player) {
    	// to remove the boat at the previous position
    	dynamicMovingEffect(player, new Empty());

    	
    	while (true) {
    		if (player.getPosition()>Game.getStartingPoint()) {
    			RiverObject objectEncountered = river.get(player.getPosition());
    			updatePlayerPosition(player);
 			
    	    	if (!(objectEncountered instanceof Empty)){
    	    		System.out.printf("%s is moving.....\n",player.getBoat());
    	    		player.setPosition(player.getPosition()+objectEncountered.getSteps());	
    	    		objectEncountered.outputEffect(player.getBoat());
    	    		try {
    	    			  Thread.sleep(700);
    	    			} catch (InterruptedException e) {
    	    			  Thread.currentThread().interrupt();
    	    			}
    	    		dynamicMovingEffect(player,objectEncountered);
    	    		
    	    	}
    	    	// if meet empty, break
    	    	else {
    	    		objectEncountered.outputEffect(player.getBoat());
    	    		try {
  	    			  Thread.sleep(500);
  	    			  System.out.printf("%s stopped.\n",player.getBoat());
  	    			} catch (InterruptedException e) {
  	    			  Thread.currentThread().interrupt();
  	    			}
    	    		
    	    		break;
    	    	}
    	    	
    		}
    		// if back to starting point, break
    		else {
    			System.out.printf("%s stopped.\n",player.getBoat());

    			displayRiver();
    			break;
    		}
    		
    	}
    }
    
    public void dynamicMovingEffect(Player player, RiverObject objectEncountered) {
    	int previousPosition = -1;
    	for(int i = 0; i < Game.getGameSize(); i ++) {
    		if(river.get(i).equals(player.getBoat())) {
    			previousPosition = i;
    		}
    	}
    	int steps = player.getPosition()-previousPosition;
    	checkPlayerPosition(player, objectEncountered);
    	River dynamicRiver = new River(playerA, playerB);
    	
    	if (steps>0) {
    		for (int i = previousPosition+1;i <= player.getPosition();i++) {
    			dynamicRiver.setRiver((ArrayList<RiverObject>)river.clone());
    			if (player.getPosition()>Game.getStartingPoint()) {
        			dynamicRiver.getRiver().set(i, player.getBoat());
        			dynamicRiver.displayRiver();
        			try {
    	    			  Thread.sleep(500);
    	    			 
    	    			} catch (InterruptedException e) {
    	    			  Thread.currentThread().interrupt();
    	    			}
        			
    			}
    			else {
    				break;
    			}
    			
    		}
    	}
    	else {
    		for (int i = previousPosition-1;i >= player.getPosition();i--) {
    			dynamicRiver.setRiver((ArrayList<RiverObject>)river.clone());
    			if (player.getPosition()>Game.getStartingPoint()) {
        			dynamicRiver.getRiver().set(i, player.getBoat());
        			dynamicRiver.displayRiver();
        			try {
    	    			  Thread.sleep(500);
    	    			  System.out.printf("%s is moving....\n",player.getBoat());
    	    			} catch (InterruptedException e) {
    	    			  Thread.currentThread().interrupt();
    	    			}
        			
    			}
    			else {
    				break;
    			}

    		}
    	
    	}
    }
    
    public void displayRiver() {  	
    	// show the player at the original position when player position is -1
    	if (playerA.getPosition() == Game.getStartingPoint() && playerB.getPosition() == Game.getStartingPoint()) {
        	System.out.println("<<Start>> "+playerA.getBoat()+" "+playerB.getBoat());
    	}
    	else if (playerA.getPosition() == Game.getStartingPoint() && playerB.getPosition() != Game.getStartingPoint()) {
        	System.out.println("<<Start>> "+playerA.getBoat());
    	}
    	else if (playerA.getPosition() != Game.getStartingPoint() && playerB.getPosition() == Game.getStartingPoint()) {
        	System.out.println("<<Start>> "+playerB.getBoat());
    	}
    	else {
        	System.out.println("<<Start>>");
    	}
    	
        for (int i = 0; i <Game.getGameSize(); i++) {
        	if (i == Game.getGameSize()-1) {
        		System.out.println(river.get(i)+"<<End>>");
        	}
        	else {
        		System.out.print(river.get(i)+" ~~ ");
        	}
   
        	if ((i+1)%10 == 0) {
        		System.out.print("\n");
        	}

        }
	
    }

	@Override
	public String toString() {
		return river.toString();
	}
    
}