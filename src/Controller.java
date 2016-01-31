import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Controller implements PropertyChangeListener {
	private View view;
	private Model model;

	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		
		view.desiredWorkedHours.setText(model.getDesiredWorkHours().toString());
		
		view.checkIn.addPropertyChangeListener("value", this);
		view.lunchBegin.addPropertyChangeListener("value", this);
		view.lunchEnd.addPropertyChangeListener("value", this);
		view.desiredWorkedHours.addPropertyChangeListener("value", this);
		view.confirm.addActionListener((e) -> update());
	}

	private void update() {
		view.goHome.setText(model.getGoHomeTime().toString());
		view.actualWorkedHours.setText(model.getActualWorkHours().toString());
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

		if (source == view.checkIn) {
			model.setCheckInTime(localTime);
		} else if (source == view.lunchBegin) {
			model.setLunchBeginTime(localTime);
		} else if (source == view.lunchEnd) {
			model.setLunchEndTime(localTime);
		} else if (source == view.desiredWorkedHours) {
			model.setDesiredWorkHours(localTime);
		} else if (source == view.lunchEnd) {
			model.setLunchEndTime(localTime);
		} 
	}

}
