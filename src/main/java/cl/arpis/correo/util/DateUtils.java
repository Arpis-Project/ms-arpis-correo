package cl.arpis.correo.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

	public static final String SYSTEM_TIMEZONE = "America/Santiago";
	public static final String SYSTEM_DATE_FORMAT = "yyyy-MM-dd";
	public static final String SYSTEM_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static TimeZone getTimeZone() {
		return TimeZone.getTimeZone(SYSTEM_TIMEZONE);
	}

	public static Calendar getCalendar() {
		return Calendar.getInstance(DateUtils.getTimeZone());
	}

	public static Date getCurrentDate() {
		return DateUtils.getCalendar().getTime();
	}

}
