import java.awt.Container;
import java.awt.GridBagLayout;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.horstmann.corejava.GBC;

public class Main {
	static Scanner sc = new Scanner(System.in);
	final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter
			.ofPattern("H:m"); 
	private static final int TEXT_FIELD_WIDTH = 10;
	private static JFrame frame = new JFrame(Messages.getString("Main.0")); 

	public static void main(String[] args) {
		frame.setLayout(new GridBagLayout());
		JPanel west = new JPanel(new GridBagLayout());
		frame.add(west, new GBC(0, 0).setInsets(10));
		JPanel east = new JPanel(new GridBagLayout());
		frame.add(east, new GBC(1, 0).setInsets(10));
		west.add(new JLabel(Messages.getString("Main.1")), new GBC(0, 0));
		west.add(new JLabel(Messages.getString("Main.2")), new GBC(0, 1));
		west.add(new JLabel(Messages.getString("Main.3")), new GBC(0, 2)); 
		east.add(new JLabel(Messages.getString("Main.4")), new GBC(0, 0));
		east.add(new JLabel(Messages.getString("Main.5")), new GBC(0, 1)); 
		JTextField checkIn = addTextField(west, 1, 0);
		JTextField lunchBegin = addTextField(west, 1, 1);
		JTextField lunchEnd = addTextField(west, 1, 2);
		JTextField goHome = addTextField(east, 1, 0);
		JTextField workedHours = addTextField(east, 1, 1);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		LocalTime checkInTime = inputTime("Började kl:"); //$NON-NLS-1$
		LocalTime lunchBeginTime = inputTime("Lunch började kl:"); //$NON-NLS-1$
		LocalTime lunchEndTime = inputTime("Lunch slutade kl:"); //$NON-NLS-1$
		long lunchDuration = ChronoUnit.MINUTES.between(lunchBeginTime,
				lunchEndTime);
		LocalTime goHomeTime = checkInTime.plusHours(8).plusMinutes(
				lunchDuration);
		System.out.println("Go home kl: " + goHomeTime); //$NON-NLS-1$
	}

	private static JTextField addTextField(Container container, int gridx,
			int gridy) {
		final JTextField textField = new JTextField(TEXT_FIELD_WIDTH);
		container.add(textField, new GBC(gridx, gridy));
		return textField;
	}

	private static LocalTime inputTime(String prompt) {
		System.out.println(prompt);
		return LocalTime.parse(sc.nextLine(), dateTimeFormatter);
	}

}
