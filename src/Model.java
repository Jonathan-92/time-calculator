import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Model {
	private LocalTime desiredWorkHours = LocalTime.parse("08:00");
	private LocalTime checkInTime;
	private LocalTime lunchBeginTime;
	private LocalTime lunchEndTime;

	public void setCheckInTime(LocalTime checkInTime) {
		this.checkInTime = checkInTime;
	}

	public void setLunchBeginTime(LocalTime lunchBeginTime) {
		this.lunchBeginTime = lunchBeginTime;
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
		// TODO: This doesn't return the correct value
		return LocalTime.now().minus(
				Duration.between(LocalTime.MIN, checkInTime));
	}
}
