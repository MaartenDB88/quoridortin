import gui.ConsoleInterface;
import gui.grafisch.BordPaneel;
import domein.Domeincontroller;


class StartUp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Domeincontroller dc = new Domeincontroller();
		ConsoleInterface console = new ConsoleInterface(dc);
		console.run();
	

	}
;
}
