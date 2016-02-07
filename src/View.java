import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.horstmann.corejava.GBC;

public class View {
	private final int TEXT_FIELD_WIDTH = 5;
	private JFrame frame = new JFrame(Messages.getString("Main.0"));
	private DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
	private JFormattedTextField checkIn = new JFormattedTextField(dateFormat);
	private JFormattedTextField lunchBegin = new JFormattedTextField(dateFormat);
	private JFormattedTextField lunchEnd = new JFormattedTextField(dateFormat);
	private JFormattedTextField goHome = new JFormattedTextField(dateFormat);
	private JFormattedTextField desiredWorkedHours = new JFormattedTextField(dateFormat);
	private JFormattedTextField actualWorkedHours = new JFormattedTextField(dateFormat);
	private JButton confirm = new JButton("Confirm");
	private JButton now = new JButton("Now");
	private JPanel west = new JPanel(new GridBagLayout());
	private JPanel east = new JPanel(new GridBagLayout());

	public void init() {
		frame.setLayout(new GridBagLayout());
		
		getCheckIn().setColumns(TEXT_FIELD_WIDTH);
		getLunchBegin().setColumns(TEXT_FIELD_WIDTH);
		getLunchEnd().setColumns(TEXT_FIELD_WIDTH);
		getGoHome().setColumns(TEXT_FIELD_WIDTH);
		getDesiredWorkedHours().setColumns(TEXT_FIELD_WIDTH);
		getActualWorkedHours().setColumns(TEXT_FIELD_WIDTH);
		
		getActualWorkedHours().setEditable(false);
		
		west.add(new JLabel(Messages.getString("Main.1")), new GBC(0, 0));
		west.add(new JLabel(Messages.getString("Main.2")), new GBC(0, 1));
		west.add(new JLabel(Messages.getString("Main.3")), new GBC(0, 2));
		west.add(getCheckIn(), new GBC(1, 0));
		west.add(getLunchBegin(), new GBC(1, 1));
		west.add(getLunchEnd(), new GBC(1, 2));
		west.add(now, new GBC(1, 3));

		east.add(new JLabel(Messages.getString("Main.4")), new GBC(0, 0));
		east.add(new JLabel(Messages.getString("Main.5")), new GBC(0, 1));
		east.add(new JLabel(Messages.getString("Main.6")), new GBC(0, 2));
		east.add(getGoHome(), new GBC(1, 0));
		east.add(getActualWorkedHours(), new GBC(1, 1));
		east.add(getDesiredWorkedHours(), new GBC(1, 2));
		east.add(getConfirm(), new GBC(1, 3));
		
		frame.add(west, new GBC(0, 0).setInsets(10));
		frame.add(east, new GBC(1, 0).setInsets(10));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public JFormattedTextField getDesiredWorkedHours() {
		return desiredWorkedHours;
	}

	public JFormattedTextField getCheckIn() {
		return checkIn;
	}

	public JFormattedTextField getLunchBegin() {
		return lunchBegin;
	}

	public JFormattedTextField getLunchEnd() {
		return lunchEnd;
	}

	public JButton getConfirm() {
		return confirm;
	}

	public JFormattedTextField getGoHome() {
		return goHome;
	}

	public JFormattedTextField getActualWorkedHours() {
		return actualWorkedHours;
	}

	public JButton getNow() {
		return now;
	}

	public JFrame getFrame() {
		return frame;
	}

}
