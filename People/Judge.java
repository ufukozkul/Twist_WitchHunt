
public class Judge extends Person{

	
	public Judge() {
		
		Name = "Eliza Orme";
		CharacterType = "Judge";
	    Tension = 350;
	    charactersRoom = new Medium_Room();
		
		firstSpecificCharacterItem = new Items("Gown","A usual judge gown from black, straigth fabric. But why do I smell burning smell from it.");
		secondSpecificCharacterItem = new Items("Huge Library","These books, encyclopedias are covering the whole wall. Wait, a hidden room or not?");
		specificItemAdder();
		witchChooser(whoIsWitch);
		fillTheRoomwithItems(4, 3, specificItemList, 2);
		fillTheRoomwithItems(4, 3, ImportedItems, 4);
		fillTheRoomwithItems(4, 3, ValuableItemList, 4);
		
	}

	@Override
	public String backGroundTeller() {
		
		String result = "Eliza Orme, a stalwart woman in the law community.";

        result += "\nShe is quick-witted, diligent and known for her ambition \ntowards matters related to her job.";
        result += " Her colleagues say that \nshe is not very warm and not really sharer about her past life.";
		
		return result;
	}
	
	public void witchChooser(int index) {
		if(index == 2) {
			isWitch = true;
			fillTheRoomwithItems(4, 3, WitchItemList, 2);
		}
	}
}
