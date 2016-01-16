import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Model {
	private static final int WORK_DAY_LENGTH = 8;
	private LocalTime checkInTime;
	private LocalTime lunchBeginTime;
	private LocalTime lunchEndTime;
	private LocalTime goHomeTime;
	private LocalTime workedHours;

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
		long lunchDuration = ChronoUnit.MINUTES.between(lunchBeginTime,
				lunchEndTime);
		return checkInTime.plusHours(WORK_DAY_LENGTH).plusMinutes(
				lunchDuration);
	}

	public LocalTime getWorkedHours() {
		return workedHours;
	}

	public void setWorkedHours(LocalTime workedHours) {
		this.workedHours = workedHours;
	}
}
