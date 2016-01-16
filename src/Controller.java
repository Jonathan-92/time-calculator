import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public class Controller {
	private View view;
	private Model model;
	
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		
		view.addActionListeners(new FieldListener());
	}
	
	private static class FieldListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LocalTime checkInTime = LocalTime.parse(checkIn.getText(), dateTimeFormatter);
			LocalTime lunchBeginTime = LocalTime.parse(lunchBegin.getText(), dateTimeFormatter);
			LocalTime lunchEndTime = LocalTime.parse(lunchEnd.getText(), dateTimeFormatter);
			long lunchDuration = ChronoUnit.MINUTES.between(lunchBeginTime,
					lunchEndTime);
			LocalTime goHomeTime = checkInTime.plusHours(8).plusMinutes(
					lunchDuration);
			goHome.setText(goHomeTime.toString());
		}
		
	}
}
