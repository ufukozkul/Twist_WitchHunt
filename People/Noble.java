
public class Noble extends Person{

	
	
	public Noble() {
		
		Name = "Georgiana Cowper";
		CharacterType = "Noble";
	    Tension = 500;
	    charactersRoom = new Large_Room();
	 
	    firstSpecificCharacterItem = new Items("Painting","Brush strokes shows that the artist is someone reputed, but \n why is that covered with drape? It looks so expensive.");
	    secondSpecificCharacterItem = new Items("Notebook","Looks ordinary for this woman's room, inside of it there is some old handwriting with Latin alphabet. It is a handwriting of a noble.");	
	    specificItemAdder();
	    witchChooser(whoIsWitch);
	    fillTheRoomwithItems(4, 4, specificItemList, 2);
	    fillTheRoomwithItems(4, 4, ImportedItems, 4);
	    fillTheRoomwithItems(4, 4, ValuableItemList, 4);
	}
	
	@Override
	public String backGroundTeller() {
		
		String result = "Name: Georgiana Cowper. Daugther of the reputable \nBritish family and a herbalist.";

        result += "\nHer interest for the herbal cures led her to learn different \nlangauges to understand ancient herbal knowledge."; 
		return result;
	}
	public void witchChooser(int index) {
		if(index == 3) {
			isWitch = true;
			fillTheRoomwithItems(4, 4, WitchItemList, 2);
		}
	}
	
}
