import java.util.Scanner;
import java.util.concurrent.TimeUnit;


import java.util.ArrayList;
import java.util.Collections;

public class Driver {
	static ArrayList<Person> People = new ArrayList<Person>();
	static ArrayList<Items> Notes = new ArrayList<Items>();
	static int tension = 0;
	static int time =0 ;
	static boolean isRunning = true;
	protected static Person newPerson = new Person();
	protected static final int witchIndex = newPerson.generateRandomIntBetween0_input(6);
	static Music music = new Music();
	static String allCommands;
	static int isWin  = 0;
		public static void main(String [] args) throws InterruptedException {


		Scanner keyboard = new Scanner(System.in);
		Music music= new Music();
		Player player = new Player();

		Person newAlchemist= new Alchemist();
		Person newNoble=new Noble();
		Person newChef= new Chef();
		Person newJudge= new Judge();
		Person newTailor= new Tailor();
		Person newThief= new Thief();

		People.add(newThief);
		People.add(newTailor);
		People.add(newChef);
		People.add(newAlchemist);
		People.add(newJudge);
		People.add(newNoble);
		Collections.shuffle(People);
		System.out.println("Welcome to the Witch HUNT");

		allCommands = " commands";
		String inputString;
		
		String[] command;

		
		while (isRunning) {

			System.out.print("Enter a command sir: ");
			inputString = keyboard.nextLine();
			//Split for the whitespace
			command = inputString.split("\\s");


			switch (command[0]) {

			case "ShowBackgrounds":
				
				player.printAnimation("\r\n" + Player.showBackground());

				break;
				
			
			case "enterroom":
			case "Enterroom":	
			case "EnterRoom":
				//room number
				String inputStringRoomIndex;
				int roomIndex;
				try {
					inputStringRoomIndex = command[1];
				}catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("Sir, you must write something! " );
					break;
				}catch(Exception e) {
					System.out.println("Sir something is wrong but i don't know what is!"  );
					break;
				}

				try {

					roomIndex = Integer.parseInt(inputStringRoomIndex);
				}catch(NumberFormatException e){
					System.out.println("Sir, room index must be integer!" );
					break;
				}catch(Exception e) {
					System.out.println("Sir something is wrong but i don't know what is!"  );
					break;
				}


				try {
					People.get(roomIndex-1);		

				}catch(IndexOutOfBoundsException e) {
					System.out.println("Sir, you can investigate only 6 people's rooms.");
					break;
				}catch(Exception e) {
					System.out.println("Sir something is wrong but i don't know what is!" );
					break;
				}

				
				music.playMusicLoop(0);
				
				player.printAnimation(player.enterRoom(roomIndex));
				
				

				while (!command[0].equalsIgnoreCase("ExitRoom")) {
					player.printAnimation("Now, what do you want to do in this room? ");
					inputString = keyboard.nextLine();
					//Split for the whitespace
					command = inputString.split("\\s");

					if(command[0].equalsIgnoreCase("Investigate")) {
						try {
						player.printAnimation(player.investigate(roomIndex));
						}catch(Exception e) {
							System.out.println("Sir there is a smoke inside, you cannot investigate it right now.");
							continue;
							
						}


					}else if(command[0].equalsIgnoreCase("MoveTo")) {
						int coordinateX = 0;
						int coordinateY = 0;
						try {
							coordinateX = Integer.parseInt(command[1]);
							coordinateY = Integer.parseInt(command[2]);
						}catch(NumberFormatException e){
							System.out.println("Sir, coordinates must be integer!" );
							continue;
						}catch(Exception e) {
							System.out.println("Sir something is wrong but i don't know what is!" );
							continue;
						}


						try {
							player.printAnimation(Player.xyChecker(roomIndex,coordinateX,coordinateY));
							
							time+=10;
						}catch(ArrayIndexOutOfBoundsException e) {
							System.out.println("Sir, coordinates must be between 0 to X: \"" + People.get(roomIndex-1).charactersRoom.squares.length + "\" and 0 to Y: \"" + People.get(roomIndex-1).charactersRoom.squares[0].length + "\".");
							continue;
						}catch(Exception e) {
							System.out.println("Sir something is wrong but i don't know what is!");
							continue;
						}

					}else if(command[0].equalsIgnoreCase("volume")) {
						int volume;

						try {
							volume = Integer.valueOf(command[1]);
						}catch(NumberFormatException e){
							System.out.println("Sir, volume must be integer!" );
							continue;
						}catch(Exception e) {
							System.out.println("Sir something is wrong but i don't know what is!");
							continue;
						}

						music.setVolume(volume);

					}else if(command[0].equalsIgnoreCase("collectAll")) {
						player.printAnimation(Player.collectAll(roomIndex));
						
						
					}else if(command[0].equalsIgnoreCase("exitroom")) {
						
					}

					else {
						player.printAnimation("Sir, you should first leave the room for doing \"" + command[0] + "\"\r\n");
					}

				}

				player.printAnimation("Now you are out of the room " + roomIndex + "\r\n");
				
				music.stopMusic();
				break;

			case "OpenNoteBook":
				player.printAnimation(Player.noteBook());
				

				break;

				
			case "AskToMonk":	
			case "asktoMonk":	
			case "asktomonk":	
			case "askToMonk":
				
				
				player.printAnimation(Player.askToMonk());

				break;	
			case"Check":
				player.printAnimation(Player.Check());
				
				break;
			case "Shop":
				
				String outputmessage = "";
				
				time+=10;
				if(player.moneyCalculator()==0) {
					
					player.printAnimation("Sir, you don't have any money or valuable item.\r\n");
					
				}else {
					outputmessage += "You have " + player.moneyCalculator() + " coin.\r\n";
					outputmessage += "What do you want to buy with this money. You know you can buy everthing with money.\r\n";
					outputmessage += "1-) You can decrease tension by money aid. It will cost you 40 coin.\r\n";
					outputmessage += "2-) You can gain time with public speach. Organizing this event will cost 35 coin.\r\n";
					outputmessage += "3-) You can give bribe to Monk for hint. But you have to give him plenty, 70 coin.\r\n";
					outputmessage += "4-) Or you can learn the real witch from a old wizard but she expects 400 coin, I believe its not possible.\r\n";
					outputmessage += "5-) Exit from shop.\r\n";
					outputmessage += "Now, what do you want to do Sir?\r\n";
					
					player.printAnimation(outputmessage);
					
					try {
						inputString = keyboard.nextLine();
					}catch(Exception e) {
						System.out.println("Please enter something, Sir.");
						break;
					}
					int inputInt;
					try {
						inputInt=Integer.parseInt(inputString);
					}catch(Exception e) {
						System.out.println("Sir, you should select integers between 1-5 for Shop selections.");
						break;
					}
					
					
					if(inputInt==5) {
						player.printAnimation("Have a nice day Sir, and also good luck for the WITCH HUNT!!!!\r\n");

					}else {
						
						player.printAnimation(player.shop(inputInt));
					}
					
					
				}
				break;
				
			case "Work":
				
				player.printAnimation("Working too long can tire you. Are you sure sir(yes/no)\r\n");
				
				try {
					inputString = keyboard.nextLine();
				}catch(Exception e) {
					System.out.println("Sir please enter something");
					continue;
				}
				if(inputString.equalsIgnoreCase("yes")) {

					player.printAnimation("How many real seconds you want to work?\r\n");
					int duration;
					inputString = keyboard.nextLine();
					try {
						duration = Integer.valueOf(inputString);
					}catch(Exception e) {
						System.out.println("Sir please enter a integer");
						continue;
					}
					if(duration >= 100) {
						player.printAnimation("Sir if work more than 100 seconds you will lose the game\r\n");
					}
					else {
					player.work(duration);
					}
				}
				else {
					player.printAnimation("As you wish sir\r\n");
					break;
				}

				break;

			case "takeNote":
				//inside of method we have clear command that deletes all notes
				player.printAnimation(Player.myNotes);
				player.printAnimation("Enter your notes sir: \r\n");
				
				inputString = keyboard.nextLine();

				player.takeNote(inputString);

				player.printAnimation(Player.myNotes);

				break;	

			case "ShowAlivePeople":
				
				player.printAnimation(Player.showAlive());
				
				break;

			case "ShowDeadPeople":
				
				player.printAnimation(Player.showDead());
				
				break;

			case "help":
				
				player.printAnimation(Player.help());
				
				

				break;
				
			case "kill":	
			case "Kill":

				String type;
				String output = "";

				try {
					type = command[1];
				}catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("You must write something!");
					break;
				}catch(Exception e) {
					System.out.println("Unknown exception found!");
					break;
				}

				if(type.equalsIgnoreCase("Thief") || type.equalsIgnoreCase("Judge") || type.equalsIgnoreCase("Chef") ||type.equalsIgnoreCase("Noble") ||type.equalsIgnoreCase("Tailor") ||type.equalsIgnoreCase("Alchemist")) {


					if(Player.aliveCheck(type) == true) {


						if(type.equalsIgnoreCase("noble")) {
							output = "An executioner come close to you and ask:\r\n";
							output += "Are you sure sir? She is a noble women, it may cause a rebelion. You should select wisely(yes/no)\r\n";
							player.printAnimation(output);
							inputString = keyboard.nextLine();
							if(inputString.equalsIgnoreCase("yes")) {
								
								player.printAnimation(Player.kill("Noble"));
								if(!newNoble.isWitch) {
									time +=20;
								}
								
							}
							else {
								
								output = "As you wish sir.\r\n";
								player.printAnimation(output);
								break;
							}
							
						}else if(type.equalsIgnoreCase("Thief")) {
							output = "An executioner come close to you and ask:\r\n";
							output += "She is really no one, you can execute her without doubt.(yes/no)\r\n";
							player.printAnimation(output);
							inputString = keyboard.nextLine();
							if(inputString.equalsIgnoreCase("yes")) {
								
								player.printAnimation(Player.kill("Thief"));
								
								if(!newThief.isWitch) {
									time += 20;
								}
								
							}
							else {
								
								output ="As you wish sir.\r\n";
								player.printAnimation(output);
								break;
							}
							
						}else if(type.equalsIgnoreCase("Judge")) {
							output = "An executioner come close to you and ask:\r\n";
							output += "Are you sure sir? She is the first judge of this city. This selection might be the end of you.(yes/no)\r\n";
							player.printAnimation(output);
							inputString = keyboard.nextLine();
							if(inputString.equalsIgnoreCase("yes")) {
								
								player.printAnimation(Player.kill("Judge"));
								
								if(!newJudge.isWitch) {
									time += 20;
								}
								
								
							}
							else {
								output = "As you wish sir.\r\n";
								player.printAnimation(output);
								break;
							}
						}else if(type.equalsIgnoreCase("Chef")) {
							output = "An executioner come close to you and ask:\r\n";
							output += "She feeds so many homeless people sir, it might be critical.(yes/no)\r\n";
							player.printAnimation(output);
							inputString = keyboard.nextLine();
							if(inputString.equalsIgnoreCase("yes")) {
								
								player.printAnimation(Player.kill("Chef"));
								
								if(!newChef.isWitch) {
									time += 20;
								}
								
								
							}
							else {
								output ="As you wish sir.\r\n";
								player.printAnimation(output);
								break;
							}
						}else if(type.equalsIgnoreCase("Tailor")) {
							output ="An executioner come close to you and ask:\r\n";
							output +="She is creates nice dresses to Noble women, you should consider it again .(yes/no)\r\n";
							player.printAnimation(output);
							inputString = keyboard.nextLine();
							if(inputString.equalsIgnoreCase("yes")) {
								
								player.printAnimation(Player.kill("Tailor"));
								
								if(!newTailor.isWitch) {
									time += 20;
								}
								
								
							}
							else {
								output = "As you wish sir.\r\n";
								player.printAnimation(output);
								break;
							}
						}else if(type.equalsIgnoreCase("Alchemist")) {
							output = "An executioner come close to you and ask:\r\n";
							output += "She is makes some pills for town, It may cause unhappiness.(yes/no)\r\n";
							player.printAnimation(output);
							inputString = keyboard.nextLine();
							if(inputString.equalsIgnoreCase("yes")) {
								
								player.printAnimation(Player.kill("Alchemist"));
								
								if(!newAlchemist.isWitch) {
									time += 20;
								}
								
								
							}
							else {
								output = "As you wish sir.\r\n";
								player.printAnimation(output);
								break;
							}
						}
					}

				}else {
					output = "Sir, please enter a valid character type.\r\n";
					player.printAnimation(output);
				}



				break;

				//zaman kalırsa random event

			default:
				output = "The game does not respond to such a command: \"" + command[0] + "\" \nPlease enter a valid command sir.\r\n";
				player.printAnimation(output);
			}
			
			if(time>=300) {
				isWin=-2;
				isRunning = false;
			}

		}

		String output = "";
		if(isWin == 1) {
			music.playMusicSingle(0);
			output = "\r\nNow the village can take a fresh breath without any fear. They thank you sir.\r\nYou win\r\n";
			player.printAnimation(output);
			
		}else if(isWin == -1) {
			music.playMusicSingle(1);
			output = "\r\nCause of your wrong decisions, the rest of the village start a rebelion and they hang you sir\r\nYou lost\r\n";
			player.printAnimation(output);
			
		}else {
			music.playMusicSingle(1);
			output = "\r\nSir your decison making speed is too slow for this village. So they kick you out of town.\r\nYou lost\r\n";
			player.printAnimation(output);
			
		}

		
		TimeUnit.SECONDS.sleep(music.DurationOfMusic);
		music.clip.close();

		System.err.println("WITCH HUNT");
	}
}