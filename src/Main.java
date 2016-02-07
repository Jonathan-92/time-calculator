import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {

		View view = new View();
		Model model = new Model();
		
		SwingUtilities.invokeLater(() -> {
			new Controller(view, model);
			view.init();
			view.setVisible(true);
		});
	}

}
