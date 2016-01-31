import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import javax.swing.text.Document;

public class Model {
	private LocalTime desiredWorkHours = LocalTime.parse("08:00");
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
		if (lunchBeginTime == null || lunchEndTime == null
				|| checkInTime == null)
			return null;
		long lunchDuration = ChronoUnit.MINUTES.between(lunchBeginTime,
				lunchEndTime);
		return checkInTime.plusNanos(desiredWorkHours.toNanoOfDay())
				.plusMinutes(lunchDuration);
	}

	public void setDesiredWorkHours(LocalTime desiredWorkHours) {
		this.desiredWorkHours = desiredWorkHours;
	}

	public LocalTime getDesiredWorkHours() {
		return desiredWorkHours;
	}

	public LocalTime getActualWorkHours() {
		return LocalTime.now().minus(Duration.between(LocalTime.MIN, checkInTime));
//		return LocalTime.ofNanoOfDay(Duration.between(checkInTime,
//				LocalTime.now()).toNanos());
	}
}
