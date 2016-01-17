import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;


public class Controller {
	final DateTimeFormatter hourMinuteFormatter = DateTimeFormatter
			.ofPattern("H:m");
	final DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("H");
	private View view;
	private Model model;
	
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		
//		view.addActionListeners(new FieldListener());
		view.checkIn.getDocument().addDocumentListener(new MyDocumentListener());
		view.lunchBegin.getDocument().addDocumentListener(new MyDocumentListener());
		view.lunchEnd.getDocument().addDocumentListener(new MyDocumentListener());
//		view.workedHours.getDocument().addDocumentListener(new MyDocumentListener());
//		KeyAdapter fieldListener = new FieldListener();
//		view.checkIn.addKeyListener(fieldListener);
//		view.lunchBegin.addKeyListener(fieldListener);
//		view.lunchEnd.addKeyListener(fieldListener);
	}
	
	private class MyDocumentListener implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
//			Document document = e.getDocument();
//			String text = document.getText(0, document.getLength());
			model.setCheckInTime(parseTime(view.checkIn.getText()));
			model.setLunchBeginTime(parseTime(view.lunchBegin.getText()));
			model.setLunchEndTime(parseTime(view.lunchEnd.getText()));
			model.setWorkDayLength(Integer.parseInt(view.workedHours.getText()));
			LocalTime goHomeTime = model.getGoHomeTime();
			if (goHomeTime != null)
				view.goHome.setText(goHomeTime.toString());
			view.workedHours.setText(Integer.toString(model.getWorkDayLength()));
		}
		
		private LocalTime parseTime(String text) {
			if (text.equals("") || text.charAt(text.length() - 1) == ':')
				return null;
			if (text.length() < 3)
				return LocalTime.parse(text, hourFormatter);
			return LocalTime.parse(text, hourMinuteFormatter);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub

		}

	}
	
	private class FieldListener extends KeyAdapter {

		@Override
		public void keyTyped(KeyEvent e) {
			model.setCheckInTime(view.getCheckInTime(e));
			model.setLunchBeginTime(view.getLunchBeginTime(e));
			model.setLunchEndTime(view.getLunchEndTime(e));
			LocalTime goHomeTime = model.getGoHomeTime();
			if (goHomeTime != null)
				view.goHome.setText(goHomeTime.toString());
		}
	}
}
