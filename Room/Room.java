import java.util.ArrayList;

public class Room {
	RoomSquare[][] squares;
	ArrayList<Items> foundItems = new ArrayList<Items>();

	public void ArrayInitialize(int inputRow, int inputColumn) {

		if (inputRow <= 0) {
			System.out.println("inputRow can not be smaller or equal the 0!, it must be equal to 3");
			return;
		}
		if (inputColumn <= 0) {
			System.out.println("inputRow can not be smaller or equal the 0!, it must be equal to 2");
			return;
		}

		for (int a = 0; a < inputRow; a++) {
			for (int b = 0; b < inputColumn; b++) {
				squares[a][b] = new RoomSquare();
			}
		}
	}

}
