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
	final DateTimeFormatter hourMinuteFormatter = DateTimeFormatter
			.ofPattern("H:m");
	final DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("H");
	private final int TEXT_FIELD_WIDTH = 5;
	private JFrame frame = new JFrame(Messages.getString("Main.0"));
	private DateFormat format = DateFormat.getTimeInstance(DateFormat.SHORT);
	public JFormattedTextField checkIn = new JFormattedTextField(format);
	public JFormattedTextField lunchBegin = new JFormattedTextField(format);
	public JFormattedTextField lunchEnd = new JFormattedTextField(format);
	public JFormattedTextField goHome = new JFormattedTextField(format);
	public JTextField desiredWorkedHours = new JTextField(TEXT_FIELD_WIDTH);
	public JTextField actualWorkedHours = new JTextField(TEXT_FIELD_WIDTH);
	public JButton confirm = new JButton("Confirm");
	private JPanel west = new JPanel(new GridBagLayout());
	private JPanel east = new JPanel(new GridBagLayout());

	public void init() {
		frame.setLayout(new GridBagLayout());
		
		checkIn.setColumns(TEXT_FIELD_WIDTH);
		lunchBegin.setColumns(TEXT_FIELD_WIDTH);
		lunchEnd.setColumns(TEXT_FIELD_WIDTH);
		goHome.setColumns(TEXT_FIELD_WIDTH);
		
		west.add(new JLabel(Messages.getString("Main.1")), new GBC(0, 0));
		west.add(new JLabel(Messages.getString("Main.2")), new GBC(0, 1));
		west.add(new JLabel(Messages.getString("Main.3")), new GBC(0, 2));
		west.add(checkIn, new GBC(1, 0));
		west.add(lunchBegin, new GBC(1, 1));
		west.add(lunchEnd, new GBC(1, 2));

		east.add(new JLabel(Messages.getString("Main.4")), new GBC(0, 0));
		east.add(new JLabel(Messages.getString("Main.5")), new GBC(0, 1));
		east.add(new JLabel(Messages.getString("Main.6")), new GBC(0, 2));
		east.add(goHome, new GBC(1, 0));
		east.add(actualWorkedHours, new GBC(1, 1));
		east.add(desiredWorkedHours, new GBC(1, 2));
		east.add(confirm, new GBC(1, 3));
		
		frame.add(west, new GBC(0, 0).setInsets(10));
		frame.add(east, new GBC(1, 0).setInsets(10));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public LocalTime getCheckInTime(KeyEvent e) {
		return getTime(e, checkIn);
	}
	
	private LocalTime getTime(KeyEvent e, JTextField field) {
		String text = field.getText() + e.getKeyChar();
		if (text.charAt(text.length() - 1) == ':')
			return null;
		if (text.equals(""))
			return null;
		if (text.length() < 3)
			return LocalTime.parse(text, hourFormatter);
		return LocalTime.parse(text, hourMinuteFormatter);
	}

	public LocalTime getLunchBeginTime(KeyEvent e) {
		return getTime(e, lunchBegin);
	}

	public LocalTime getLunchEndTime(KeyEvent e) {
		return getTime(e, lunchEnd);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void addActionListeners(KeyAdapter fieldListener) {
		checkIn.addKeyListener(fieldListener);
		lunchBegin.addKeyListener(fieldListener);
		lunchEnd.addKeyListener(fieldListener);
	}

}
