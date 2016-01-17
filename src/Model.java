import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.swing.text.Document;

public class Model {
	private int workDayLength = 8;
	private LocalTime checkInTime;
	private LocalTime lunchBeginTime;
	private LocalTime lunchEndTime;
	private LocalTime goHomeTime;

	public LocalTime getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(LocalTime checkInTime) {
		this.checkInTime = checkInTime;
	}

	public LocalTime getLunchBeginTime() {
		return lunchBeginTime;
	}

	public void setLunchBeginTime(LocalTime lunchBeginTime) {
		this.lunchBeginTime = lunchBeginTime;
	}

	public LocalTime getLunchEndTime() {
		return lunchEndTime;
	}

	public void setLunchEndTime(LocalTime lunchEndTime) {
		this.lunchEndTime = lunchEndTime;
	}

	public LocalTime getGoHomeTime() {
		if (lunchBeginTime == null || lunchEndTime == null || checkInTime == null)
			return null;
		long lunchDuration = ChronoUnit.MINUTES.between(lunchBeginTime,
				lunchEndTime);
		return checkInTime.plusHours(workDayLength).plusMinutes(
				lunchDuration);
	}

	public int getWorkDayLength() {
		return workDayLength;
	}

	public void setWorkDayLength(int workDayLength) {
		this.workDayLength = workDayLength;
	}
}
