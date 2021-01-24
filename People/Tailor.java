
public class Tailor extends Person{

	
	
	
	public Tailor() {
		
		Name = "Sylvia Vidmar";
		CharacterType = "Tailor";
	    Tension = 150;
	    charactersRoom = new Medium_Room();
	    
	    firstSpecificCharacterItem = new Items("Weaving Loom","She is with this simple machine 24/7 and weaving without break. \nWhat can she weave all the time?");
	    secondSpecificCharacterItem= new Items("Ruby"," This rare, medium size necklace, bright gemstone is always on her neck.");
	    specificItemAdder();
	    witchChooser(whoIsWitch);
	    fillTheRoomwithItems(4, 3, specificItemList, 2);
	    fillTheRoomwithItems(4, 3, ImportedItems, 4);
	    fillTheRoomwithItems(4, 3, ValuableItemList, 4);
	}
	
	@Override
	public String backGroundTeller() {
		
		
		String result = "Name: Slyvia Vidmar. An old immigrant lady who sits \nall the day with her";

        result += "clenched lips and weaves some product \nand not talks to much";
		
		return result;
	}
	public void witchChooser(int index) {
		if(index == 4) {
			isWitch = true;
			fillTheRoomwithItems(4, 3, WitchItemList, 2);
		}
	}
}
