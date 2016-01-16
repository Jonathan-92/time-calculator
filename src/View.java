import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.horstmann.corejava.GBC;

public class View {
	final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
			.ofPattern("H:m");
	private final int TEXT_FIELD_WIDTH = 10;
	private JFrame frame = new JFrame(Messages.getString("Main.0"));
	private JTextField checkIn = new JTextField(TEXT_FIELD_WIDTH);
	private JTextField lunchBegin = new JTextField(TEXT_FIELD_WIDTH);
	private JTextField lunchEnd = new JTextField(TEXT_FIELD_WIDTH);
	private JTextField goHome = new JTextField(TEXT_FIELD_WIDTH);
	private JTextField workedHours = new JTextField(TEXT_FIELD_WIDTH);
	private JPanel west = new JPanel(new GridBagLayout());
	private JPanel east = new JPanel(new GridBagLayout());

	public void init() {
		frame.setLayout(new GridBagLayout());

		west.add(new JLabel(Messages.getString("Main.1")), new GBC(0, 0));
		west.add(new JLabel(Messages.getString("Main.2")), new GBC(0, 1));
		west.add(new JLabel(Messages.getString("Main.3")), new GBC(0, 2));
		west.add(checkIn, new GBC(1, 0));
		west.add(lunchBegin, new GBC(1, 1));
		west.add(lunchEnd, new GBC(1, 2));

		east.add(new JLabel(Messages.getString("Main.4")), new GBC(0, 0));
		east.add(new JLabel(Messages.getString("Main.5")), new GBC(0, 1));
		east.add(goHome, new GBC(1, 0));
		east.add(workedHours, new GBC(1, 1));

		frame.add(west, new GBC(0, 0).setInsets(10));
		frame.add(east, new GBC(1, 0).setInsets(10));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public LocalTime getCheckInTime() {
		return LocalTime.parse(checkIn.getText(), dateTimeFormatter);
	}

	public LocalTime getLunchBeginTime() {
		return LocalTime.parse(lunchBegin.getText(), dateTimeFormatter);
	}
	
	public LocalTime getLunchEndTime() {
		return LocalTime.parse(lunchEnd.getText(), dateTimeFormatter);
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void addActionListeners(ActionListener fieldListener) {
		
	}

}
