import java.util.ArrayList;
import java.util.Random;

public class Person implements Person_Interface {

	protected boolean isAlive = true;
	protected boolean isWitch = false;
	protected String CharacterType;
	protected int Tension;
	protected String Name;
	Random rand = new Random();
	
	
	final int whoIsWitch = 5;
	
	Room charactersRoom;

	ArrayList<Items> ImportedItems = new ArrayList<Items>();
	ArrayList<Items> ValuableItemList = new ArrayList<Items>();
	ArrayList<Items> WitchItemList = new ArrayList<Items>();
	ArrayList<Items> specificItemList;

	// Define it in person sub classes
	protected Items firstSpecificCharacterItem;
	protected Items secondSpecificCharacterItem;

	
	
	public Person() {
		charactersRoom = new Room();

		itemAdder();
		specificItemList = new ArrayList<Items>();
		
		
	}
	
	

	Items fork=new Items("Fork","It looks like a normal fork.");
	Items knife = new Items("Knife","Looks like there's a blood on it, or is it?");
	Items apple = new Items("Apple","It is a Wormy Apple, it is suspicous and disgusting.");
	Items deadMouse = new Items("Mouse", "It is a Dead Mouse maybe householder purposely put it there for rituel, but cannot be sure");
	Items herbs =new Items("Herbs","It is common to have medicinal herbs in these epidemic years.");
	Items bible =new Items("Bible","Holy Bible so, this houseowner has fear of God, GOOOD!!!");
	Items perfume =new Items("Perfume","Nice to smell perfume but I am not really sure about to usage.");
	Items boots= new Items("Boots","It has muds on it. Maybe host of this room likes going to empty countryside.");
	Items insects= new Items("Insects","These type of insects loves lifeless animal bodies.");
	Items mirror= new Items("Mirror","Some peoples say that Witches loves looking themselves.");
	Items smudgeStick= new Items("Smudge","It is a smudge stick smells so diffrent, is it sulfur ?, I don't know actually.");
	Items mushroom= new Items("Mushroom","Some people use them for herbal reasons, but there is anothoter unholy usage of course.");
	Items feather= new Items("Feather","Raven feather means a secret knowledge about everthing, but it looks not good.");
	Items bone= new Items("Bone","It is sign of bad luck, until you break it.");
	Items tooth= new Items("Tooth","It looks like a Devils tooth because it is black");

	
	Items coin= new ValuableItems("Coin Purse","Its full with coin.",17);
	Items bread= new ValuableItems("Bread","Fresh Bread, it's sellable",5);
	Items candle= new ValuableItems("Candle","Nice looking candle, its sellable.",7);
	Items amethyst= new ValuableItems("Amethyst","Nice looking stone and its sellable",12);
	Items doll= new ValuableItems("Doll","Kids like these dolls nowadays. I can sell this.",10);
	Items paper= new ValuableItems("Paper","It's hard to find good quality paper in these years.",8);
	
	
	Items broom= new WitchItems("Broom","It looks like new and not used, maybe this is the reason of this dirty room. Also it is so light.");
	Items Scroll= new WitchItems("Scroll","It is a good quality scroll but I dont know the meaning of (5+1,4+2,3+3)");
	Items Cat = new WitchItems("Cat","It is just good looking regular black cat but it looks so sinfull. It's frightening");
	
	
	public void specificItemAdder() {
		specificItemList.add(firstSpecificCharacterItem);
		specificItemList.add(secondSpecificCharacterItem);
		
	}
	
	
	public void itemAdder() {
		ImportedItems.add(fork);
		ImportedItems.add(knife);
		ImportedItems.add(apple);
		ImportedItems.add(deadMouse);
		ImportedItems.add(herbs);
		ImportedItems.add(bible);
		ImportedItems.add(perfume);
		ImportedItems.add(boots);
		ImportedItems.add(insects);
		ImportedItems.add(mirror);
		ImportedItems.add(smudgeStick);
		ImportedItems.add(mushroom);
		ImportedItems.add(feather);
		ImportedItems.add(bone);
		ImportedItems.add(tooth);
		
		
		ValuableItemList.add(coin);
		ValuableItemList.add(bread);
		ValuableItemList.add(candle);
		ValuableItemList.add(amethyst);
		ValuableItemList.add(doll);
		ValuableItemList.add(paper);
		
		WitchItemList.add(broom);
		WitchItemList.add(Scroll);
		WitchItemList.add(Cat);

	}

	public Items returnsGivenItemArray(int inputIndex, ArrayList<Items> inputItemList) {

		////Check the parameters
		if (inputItemList.get(0) == null) {
			System.out.println("given item list can not be null!");
			return null;
		}

		if (inputIndex > inputItemList.size() - 1) {
			System.out.println("index can not be bigger than list size");
			return null;
		}

		return inputItemList.get(inputIndex);

	}

	public int fillTheRoomwithItems(int inputRow, int inputColumn, ArrayList<Items> inputItemList, int itemCountYouWant) {

		int rowNumber;
		int columnNumber;
		
		//Check the parameters
		if (itemCountYouWant <= 0) {
			System.out.println("itenCountYouWant can not be smaller or equal the 0!");
			return -1;
		}
		if (inputItemList.get(0) == null) {
			System.out.println("given item list can not be null!");
			return -1;
		}
		if (inputRow <= 0) {
			System.out.println("inputRow can not be smaller or equal the 0!, it must be equal to" + charactersRoom.squares.length );
			return -1;
		}
		if (inputColumn <= 0) {
			System.out.println("inputRow can not be smaller or equal the 0!, it must be equal to" + charactersRoom.squares[0].length);
			return -1;
		}

		rowNumber = generateRandomIntBetween0_input(inputRow);
		columnNumber = generateRandomIntBetween0_input(inputColumn);

		int randomInt = 0;

		//Check the parameters
		if (rowNumber >= inputRow || rowNumber < 0) {
			System.out.println("Row number of Small room should be between 0 to 2");
			return -1;
		}
		if (columnNumber >= inputColumn || columnNumber < 0) {
			System.out.println("Column number of Small room should be between 0 to 2");
			return -1;
		}

		for (int a = 0; a < itemCountYouWant; a++) {

			// if square does not have item, assign it a item
			if (charactersRoom.squares[rowNumber][columnNumber].isEmpty == true) {

				randomInt = generateRandomIntBetween0_input(inputItemList.size());
				charactersRoom.squares[rowNumber][columnNumber].item = returnsGivenItemArray(
						randomInt, inputItemList);
				charactersRoom.squares[rowNumber][columnNumber].isEmpty = false;
				inputItemList.remove(charactersRoom.squares[rowNumber][columnNumber].item);
			}

			rowNumber = generateRandomIntBetween0_input(inputRow);
			columnNumber = generateRandomIntBetween0_input(inputColumn);

		}

		return 1;

	}

	public int generateRandomIntBetween0_input(int inputRange) {
	
		//Check the parameters
		if(inputRange <= 0) {
			System.out.println("Range of the random generator can not be negative!");
			return -1;
		}
		
		int randomNumber = rand.nextInt(inputRange);
		return randomNumber;
	}

	public String backGroundTeller() {
		return "";
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}

}
