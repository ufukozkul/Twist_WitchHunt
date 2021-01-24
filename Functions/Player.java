import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Player {
	static String myNotes = "";
	static int noteCount = 1;
	int money = 0;
	static ArrayList <Items> allFounded = new ArrayList <Items>();
	
	
	
	public void printAnimation(String inputString) {
		
		if(inputString == null) {
			System.out.println("Input string cannot be null!");
			return;
		}
		Driver.music.playMusicLoop(2);
		Driver.music.setVolume(100);
		int speed = 0;
		for(int a = 0; a < inputString.length(); a++){
			System.out.print(inputString.charAt(a));
			try {
				TimeUnit.MILLISECONDS.sleep(60-speed);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			speed += 1;
		}
		Driver.music.stopMusic();
		
	}
	
	public static String help() {
		
		String output = "";
		
		output += "You can type \"ShowBackgrounds\" to learn more about the suspects.\r\n";
		output +="\"EnterRoom\" <int roomNuber>\r\n" ; 
		output +="You can type EnterRoom to enter the suspect's room.\r\n";
		output +="You can type Investigate to see all the items in the room that you are in.\r\n";
		output +="\"MoveTo\" <int x> <int y>\r\n" ; 

		
		output +="You can type MoveTo to go near to the items, also you can inspect items.\r\n";
		
		
		output +="You can type volume to lower/raise the music volume in the rooms.\r\n";
		output +="\"volume\" <int input>\r\n" ; 
		output +="You can type \"ExitToRoom\" to get out of the rooms.\r\n";
		output +="You can type \"OpenNoteBook\" to mention the items that you found in the rooms.\r\n";
		output +="You can type \"askToMonk\" to ask for hints(questions) to monk accordingly the notes you take through the game.\r\n";
		output +="takeNote <String Note>\r\n" ;
		output += "After the type \"takeNote\", you can clear your all notes by typing \"clear\"\r\n";
		
		output +="You can type \"takeNote\" to take notes or type important things through the game.\r\n";
		output +="You can type \"ShowAlivePeople\" to see/list alive suspects.\r\n";
		output +="You can type \"ShowDeadPeople\" to see/list dead suspects.\r\n";
		output +="\"Kill\" <String type>\r\n" ; 
		
		output +="You can type Kill to kill the suspect that you choose.\r\n";
		
		return output;
	
	}
	
	public String enterRoom(int roomIndex) {
		
		String output = "";
		
		output += "Now you are in the room " + roomIndex + "\r\n";
		output += "You can investigate  room " + roomIndex + " or you can move.\r\n";
		
		return output;
		
	}
	
	public String takeNote(String inputString) {
		
		
		
		myNotes += noteCount + ") " + inputString + "\r\n";
		noteCount++;
		
		if(inputString.equalsIgnoreCase("clear")) {
			myNotes = "";
			noteCount = 0;
		}
		
		return myNotes;
		
	}
	
	public String investigate(int inputIndex) {
		
		String output = "";
		
		for(int a = 0; a < Driver.People.get(inputIndex-1).charactersRoom.squares.length; a++) {
			for(int b = 0; b < Driver.People.get(inputIndex-1).charactersRoom.squares[a].length; b++) {
				if(Driver.People.get(inputIndex-1).charactersRoom.squares[a][b].item.itemName.equals("")) {
					
				}else {
					output +=	"I see " + Driver.People.get(inputIndex-1).charactersRoom.squares[a][b].item.itemName + " in [" + a + "," + b + "]\r\n" ;
				}
			}
		}
		int m=(14*Driver.People.get(inputIndex-1).charactersRoom.squares[0].length);
		 String roomFrame = "";
		for(int g=0;g<m;g++) {
			roomFrame+="-";
		}
		
		 
		output += "\r\n";
		output += roomFrame+"\r\n";
		
		
		for(int d = 0; d < Driver.People.get(inputIndex-1).charactersRoom.squares.length; d++) {
				for(int b = 0; b < Driver.People.get(inputIndex-1).charactersRoom.squares[d].length; b++) {
					int t=(13-Driver.People.get(inputIndex-1).charactersRoom.squares[d][b].item.itemName.length());
					String spaces = String.format("%"+t+"s", "");
					if(Driver.People.get(inputIndex-1).charactersRoom.squares[d][b].item.itemName.equals("")) {
						output +="|             |";
					}else{
						output +="|"+ Driver.People.get(inputIndex-1).charactersRoom.squares[d][b].item.itemName+spaces+"|";
					}
					
					

				}
				output += "\r\n";
			}
		
		
		output += "|"+roomFrame+"|\r\n";
		output += "\r\n";
		
		return output;
	}

	public static String xyChecker(int person,int x, int y) {
		boolean status=false;
		String s = "";
		String output = "";
		if(s != Driver.People.get(person-1).charactersRoom.squares[x][y].item.itemName) {
			status = true;
		}
		if(status == true) {
			//to update notebook first I checked any duplication.

			boolean founded=false;
			for(int k=0; k < Driver.People.get(person-1).charactersRoom.foundItems.size(); k++) {
				if(Driver.People.get(person-1).charactersRoom.squares[x][y].item.itemName.equals(Driver.People.get(person-1).charactersRoom.foundItems.get(k).itemName)) {
					founded = true;
				}
			}
			if(founded == false) {
				Driver.People.get(person-1).charactersRoom.foundItems.add(Driver.People.get(person-1).charactersRoom.squares[x][y].item);
				output += "You found " + Driver.People.get(person-1).charactersRoom.squares[x][y].item + "\r\n";
				allFounded.add(Driver.People.get(person-1).charactersRoom.squares[x][y].item);
			}else {
				output += "I don't need to examine this item again and again, already checked it.\r\n";
			}
			
			
			boolean check=false;
			for(int i=0; i < Driver.Notes.size(); i++) {
				if(Driver.People.get(person-1).charactersRoom.squares[x][y].item.itemName.equals(Driver.Notes.get(i).itemName)) {
					check=true;
					
				}
			}
			if(check == false) {
				Driver.Notes.add(Driver.People.get(person-1).charactersRoom.squares[x][y].item);
				output += "Notes updated.\r\n";
			}else {
				output += "I already find this item in another room. I don't need to take note for it.\r\n";
			}
			
		}else {
			output += "You found nothing, this square is empty.\r\n";
		}
		
		return output;
	}
	
	public static String collectAll(int person) {
		
		String output = "";
		for(int d = 0; d < Driver.People.get(person-1).charactersRoom.squares.length; d++) {
			for(int b = 0; b < Driver.People.get(person-1).charactersRoom.squares[d].length; b++) {
				System.out.println(xyChecker(person,d,b));
				}
			}
		output +="You collected all items in this room but it costed you 15 time.\r\n";
		Driver.time+=15;
		return output;
	}
	public static String Check() {
		String output = "";
		int remaningTime=301-Driver.time;
		int remaningTension=601-Driver.tension;
		output +="Sir you have only "+remaningTime+" remaining time and "+remaningTension+" tension until rebelion.\r\n";
		return output;
	}

	public static String noteBook() {

		String output = "";
		
		if(Driver.Notes.size() == 0) {
			output += "You did not add anything to notebook so far.\r\n";
		}else {
		        for (int i =0; i < 6; i++) {

		            if(Driver.People.get(i).charactersRoom.foundItems.size() == 0) {
		            	output += "You found nothing in room " + (i+1) + " room.\r\n";

		            }
		            else {
		            	output += "In room " + (i+1) + " you found:\r\n";
		                for(int k=0; k < Driver.People.get(i).charactersRoom.foundItems.size(); k++) {
		                	output += (k+1) + "-) " + Driver.People.get(i).charactersRoom.foundItems.get(k).itemName + "\r\n";
		                }
		            }
		        }
		    }
		
		return output;
		}
	public static boolean aliveCheck(String type) {
		for(int i=0;i<Driver.People.size();i++) {
			if(Driver.People.get(i).CharacterType.equalsIgnoreCase(type)) {
				if(Driver.People.get(i).isAlive==true) {
					return true;
				}else {
					System.out.println("She is already dead.");
					return false;
				}
			}
		}
		return false;
	}


	public int moneyCalculator() {

		
		for(int i=0;i<allFounded.size();i++) {
			if(allFounded.get(i).coin!=0) {
				if(allFounded.get(i).isUsed==false) {
					money+=allFounded.get(i).coin;
					allFounded.get(i).isUsed=true;
					
				}
			}
		}
		return money;
	}
	
	public String shop(int index) {
		
		String output = "";
		
		if(index == 1) {
			if(moneyCalculator() >= 40) {
				Driver.tension -= 50;

				money -= 40;
				output += "After purchase "+money+" your money.\r\n";
				output += "After purchase "+Driver.tension+" your tension.\r\n";

			}else {
				output += "Sir, you dont have enough money for this service\r\n";
			}	
		}else if(index == 2) {
			if(moneyCalculator() >= 35) {
				Driver.time-=40;
				money -= 35;
				output += "After purchase "+money+" your money.\r\n";
				output += "After purchase "+Driver.time+" your time.\r\n";

			}else {
				output += "Sir, you dont have enough money for this service\r\n";
			}
		}else if(index == 3) {
			if(moneyCalculator()>=70) {
				money -= 70;
				int indexOfNonWitch = randomWitchHunt();
				output += "Sir I can give you a clue about non-witch suspect. I am sure "+(Driver.People.get(indexOfNonWitch).CharacterType) +" is not a Witch.\r\n";

			}else {
				output += "Sir, you dont have enough money for this service\r\n";
			}
		}else if(index==4) {
			if(moneyCalculator() <= 400) {
				output += "Only the King has that kind of money in these lands. Sorry Sir.\r\n";

			}
		}else {
			output += "Sir, I don't have that option you want.\r\n";
		}

		
		return output;
	}

	public void work(int inputDuration) {
		Driver.music.playMusicLoop(1);
		Driver.music.setVolume(100);
		
		for(int a = 1; a <= inputDuration; a++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			System.out.println(a);
		}
		Driver.music.stopMusic();
		printAnimation("You work " + inputDuration + " seconds and earn " + (inputDuration*2) + " coin but it cost you " + (inputDuration*3) + " time\r\n");
		money += inputDuration*2;
		Driver.time += inputDuration*3;
		
	}
	
	public static String kill(String charType) {
		//music calsin loop
		String output = "";

		for(int i=0; i < 6; i++) {
			if(Driver.People.get(i).CharacterType.equalsIgnoreCase(charType) && Driver.People.get(i).isAlive == true ) {

				Driver.People.get(i).isAlive = false;
				output += "She is dead. Everyone hopes that she is the witch.\r\n";

				if(Driver.People.get(i).isWitch == true) {
					
					output += "You found the real witch.\r\n";
					Driver.isWin = 1;
					Driver.isRunning = false;

				}else {
					output += "You picked the wrong person. People gets really mad about this selection.\r\n";

					output += "Tension increased by :" + Driver.People.get(i).Tension + "\r\n";
					Driver.tension+=Driver.People.get(i).Tension;

					output += "After your wrong selection Witch hunted another suspect.\r\n";
					int indexOfWitchsTarget = randomWitchHunt();
					Driver.People.get(indexOfWitchsTarget).isAlive = false;
					
					output += Driver.People.get(indexOfWitchsTarget).CharacterType + "\r\n";
					output += "Tension increased by :" + Driver.People.get(indexOfWitchsTarget).Tension + "\r\n";
					
					Driver.tension += Driver.People.get(indexOfWitchsTarget).Tension;
					
					output += "Total tension is " + Driver.tension + "\r\n";
					
					if(Driver.tension > 601) {
						Driver.isWin = -1;
						Driver.isRunning = false;
					}

				}


			}else if(Driver.People.get(i).CharacterType.equalsIgnoreCase(charType) && Driver.People.get(i).isAlive == false){
				output += "She is already dead\r\n";	
			}
		}
		
		return output;
	}


	public static int randomWitchHunt() {
		ArrayList <Integer> alives = new ArrayList <Integer>();
		for(int i=0;i<Driver.People.size();i++) {
			if(Driver.People.get(i).isAlive == true && Driver.People.get(i).isWitch == false) {
				alives.add(i);
			}
		}
		Collections.shuffle(alives);
		return alives.get(0);
	}

	public static String showAlive() {
		
		String output = "";
		for(int i=0; i < 6; i++) {
			if(Driver.People.get(i).isAlive == true) {
				output += Driver.People.get(i).CharacterType + " "+Driver.People.get(i).Name + "\r\n";
			}
		}
		
		return output;
	}
	public static String showDead() {
		
		String output = "";
		int counter=0;
		for(int i=0; i < 6; i++) {
			if(Driver.People.get(i).isAlive == true) {
				counter++;
			}
		}
		if(counter == 6) {
			output += "You did not kill anyone. You should hurry.\r\n";
		}else {
			for(int i=0; i < 6; i++) {
				if(Driver.People.get(i).isAlive == false) {
					output += Driver.People.get(i).CharacterType+" "+Driver.People.get(i).Name + "\r\n";
				}
			}
		}
		
		return output;
	}
	
	public static String showBackground() {
		ArrayList <Person> shuffle = new ArrayList <Person>();
		
		
		shuffle = Driver.People;
		
		String output = "";
		
		for(int i=5; i >= 0; i--) {
			output += shuffle.get(i).CharacterType + " ";
			output += shuffle.get(i).backGroundTeller() + "\r\n\n";	
		}
		
		return output;
	}
	
	public static String askToMonk() {
		
		String output = "";
		
		for (int i=0; i < Driver.Notes.size(); i++) {
			output += Driver.Notes.get(i).itemName + ": " + Driver.Notes.get(i).usedFor + "\r\n";
		}
		
		return output;
	}
	
	


}
