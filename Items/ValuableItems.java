public class ValuableItems extends Items{
	

	public ValuableItems(String name1, String usedFor1,int coin1 ) {
		super(name1,usedFor1);
		super.coin=coin1;
		super.isUsed=false;
	}
}