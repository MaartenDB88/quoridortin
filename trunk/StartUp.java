
import gui.ConsoleInterface;
import gui.grafisch.BordPaneel;
import domein.Domeincontroller;
import gui.MainGui;
import gui.grafisch.TaalKeuze;

class StartUp {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
//		Domeincontroller dc = new Domeincontroller();
//		ConsoleInterface console = new ConsoleInterface(dc);
//		console.run();

      TaalKeuze taal =  new TaalKeuze();
      taal.setVisible(true);
    }

    ;
}
