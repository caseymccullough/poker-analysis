
public class DivisionAndModulusDemo {

	public static void main(String[] args) {
		
		for (int i = 0; i < 52; i++)
		{
			int suitNum = i / 13;
			int rankNum = i % 13;
			System.out.println("suit: " + suitNum + "\trank: "  + rankNum);
		}

	}

}
