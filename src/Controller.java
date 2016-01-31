import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class Controller implements PropertyChangeListener {
	private View view;
	private Model model;

	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		
		view.getDesiredWorkedHours().setText(model.getDesiredWorkHours().toString());
		
		view.getCheckIn().addPropertyChangeListener("value", this);
		view.getLunchBegin().addPropertyChangeListener("value", this);
		view.getLunchEnd().addPropertyChangeListener("value", this);
		view.getDesiredWorkedHours().addPropertyChangeListener("value", this);
		view.getConfirm().addActionListener((e) -> update());
	}

	private void update() {
		view.getGoHome().setText(model.getGoHomeTime().toString());
		view.getActualWorkedHours().setText(model.getActualWorkHours().toString());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getNewValue() == null)
			return;
		Date date = (Date) evt.getNewValue();
		Instant instant = Instant.ofEpochMilli(date.getTime());
		LocalTime localTime = LocalDateTime.ofInstant(instant,
				ZoneId.systemDefault()).toLocalTime();
		Object source = evt.getSource();

		if (source == view.getCheckIn()) {
			model.setCheckInTime(localTime);
		} else if (source == view.getLunchBegin()) {
			model.setLunchBeginTime(localTime);
		} else if (source == view.getLunchEnd()) {
			model.setLunchEndTime(localTime);
		} else if (source == view.getDesiredWorkedHours()) {
			model.setDesiredWorkHours(localTime);
		} else if (source == view.getLunchEnd()) {
			model.setLunchEndTime(localTime);
		} 
	}

}
