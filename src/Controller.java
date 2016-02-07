import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.text.TextAction;

public class Controller implements PropertyChangeListener {
	private View view;
	private Model model;

	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;

		view.getDesiredWorkedHours().setText(
				model.getDesiredWorkHours().toString());

		view.getCheckIn().addPropertyChangeListener("value", this);
		view.getLunchBegin().addPropertyChangeListener("value", this);
		view.getLunchEnd().addPropertyChangeListener("value", this);
		view.getDesiredWorkedHours().addPropertyChangeListener("value", this);
		view.getConfirm().addActionListener((e) -> update());
		view.getNow().addActionListener(new TextAction(null) {
			@Override
			public void actionPerformed(ActionEvent e) {
				Component focusOwner = getFocusedComponent();
				if (focusOwner instanceof JFormattedTextField) {

					JFormattedTextField textField = (JFormattedTextField) focusOwner;
					LocalTime now = LocalTime.now();
					Date time = getDate(now);
					setValueAndCommitEdit(textField, time);
					findNextFocus(focusOwner).requestFocus();
				}
			}

		});
	}

	private static void setValueAndCommitEdit(JFormattedTextField textField,
			Date time) {
		textField.setValue(time);
		try {
			textField.commitEdit();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

	private static Date getDate(LocalTime now) {
		Instant instant = now.atDate(LocalDate.of(2016, 1, 1))
				.atZone(ZoneId.systemDefault()).toInstant();
		Date time = Date.from(instant);
		return time;
	}

	public Component findNextFocus(Component focusOwner) {
		Container root = focusOwner.getFocusCycleRootAncestor();

		FocusTraversalPolicy policy = root.getFocusTraversalPolicy();
		Component nextFocus = policy.getComponentAfter(root, focusOwner);
		if (nextFocus == null) {
			nextFocus = policy.getDefaultComponent(root);
		}
		if (focusOwner == view.getLunchEnd()) {
			nextFocus = view.getConfirm();
		}
		return nextFocus;
	}

	private void update() {
		setValueAndCommitEdit(view.getGoHome(), getDate(model.getGoHomeTime()));
		setValueAndCommitEdit(view.getActualWorkedHours(),
				getDate(model.getActualWorkHours()));
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
