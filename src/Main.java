import domein.PrikToGo;
import domein.Vestiging;
// import presentatie.View;



public class Main {
	public static void main(String[] args) {
		try {
			PrikToGo prikToGo = new PrikToGo();

			// View view = new View(prikToGo);
			
			String[] ves = prikToGo.getOverzichtVestigingen();
			for (String string : ves) {
				System.out.println(string);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}