
public class Alchemist extends Person{

	
	
	
	public Alchemist() {
		
		Name = "Alessandro Cagliostro";
		CharacterType = "Alchemist";
	    Tension = 250;
	    charactersRoom = new Large_Room();
	  
	    firstSpecificCharacterItem = new Items("Stone","Ancients fight against their brothers and sisters for owning the power of this magnificent Philospher's stone.");
	    secondSpecificCharacterItem = new Items("Show Globe","These ostentatious swinging vases have different 'fluids' in them. ");
	    specificItemAdder();
	    witchChooser(whoIsWitch);
	    fillTheRoomwithItems(4, 4, specificItemList, 2);
	    fillTheRoomwithItems(4, 4, ImportedItems, 4);
	    fillTheRoomwithItems(4, 4, ValuableItemList, 4);
	}
	
	@Override
	public String backGroundTeller() {
		
		String result = "Name: Alessandro Cagliostro. A chatty, \nextraordinary character who has a unique way of thinking about";

        result += " \nthe usage of sources in the world and also she is very cautios about \nher setting which contains glasses.";
	
        return result;		
	}
	
	public void witchChooser(int index) {
		if(index == 0) {
			isWitch = true;
			fillTheRoomwithItems(4, 4, WitchItemList, 2);
		}
	}
	
	
}
