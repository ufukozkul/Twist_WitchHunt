

public class Thief extends Person{


	public Thief() {
		
		Name = "Beatriz Manzanares";
		CharacterType = "Thief";
	    Tension = 100; 
	    charactersRoom = new Small_Room();
	  
	    
	    firstSpecificCharacterItem = new Items("Chest","It contains a lot of stuff like a hand mirror, a pouch and silver goblet. Interesting...");
	    secondSpecificCharacterItem = new Items("Documents","They are court documents, I found one of them in front of the door and few at the dinner table");
	    specificItemAdder();                     
	    witchChooser(whoIsWitch);
	    fillTheRoomwithItems(3, 2, specificItemList, 2);
	    fillTheRoomwithItems(3, 2, ImportedItems, 4);
	    fillTheRoomwithItems(3, 2, ValuableItemList, 4);
	    
	}
	
	@Override
	public String backGroundTeller() {
		
		String result = "Name: Beatriz Manzanares. Since she moved this \nroom, most of the neighbours bickbites about her beauty and \nlifestyle.";
        result += "There is something suspcious about her.";
		
		return result;
	}
	
	public void witchChooser(int index) {
		if(index == 5) {
			isWitch = true;
			fillTheRoomwithItems(3, 2, WitchItemList, 2);
		}
	}
	
	

}
