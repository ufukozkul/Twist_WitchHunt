
public class Chef extends Person{
   
		
	public Chef() {
			
			Name = "Marie Antoine";
			CharacterType = "Chef";
			Tension = 200;
			 charactersRoom = new Small_Room();
			
			firstSpecificCharacterItem = new Items("Spices","It is a spice collection, shelves of jars with spices from all over the world.");
			secondSpecificCharacterItem = new Items("Chef's Apron","A dirty apron with some unusual red stains on it.");
			specificItemAdder();
			witchChooser(whoIsWitch);
			fillTheRoomwithItems(3, 2, specificItemList, 2);
			fillTheRoomwithItems(3, 2, ImportedItems, 4);
			fillTheRoomwithItems(3, 2, ValuableItemList, 4);
		}
	
	@Override
	public String backGroundTeller() {
		
		String result = "Name: Marie Antoine. A famous chef who is the exponent \nof the elaborate style of cooking known as grande cuisine.";

        result += " She \nhas a restaurant which has a mystic spice smell and addictive foods.";
		
		return result;
	}
	
	public void witchChooser(int index) {
		if(index == 1) {
			isWitch = true;
			fillTheRoomwithItems(3, 2, WitchItemList, 2);
		}
	}
   
}
