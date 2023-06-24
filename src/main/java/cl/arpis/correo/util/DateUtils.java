package cl.arpis.correo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.springframework.util.ObjectUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

	public static final String SYSTEM_TIMEZONE = "America/Santiago";
	public static final String SYSTEM_DATE_FORMAT = "yyyy-MM-dd";
	public static final String SYSTEM_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final long MULTIPLO_MILISEC = 1000;

	public static TimeZone getTimeZone() {
		return TimeZone.getTimeZone(SYSTEM_TIMEZONE);
	}

	public static Calendar getCalendar() {
		return Calendar.getInstance(DateUtils.getTimeZone());
	}

	public static Date getCurrentDate() {
		return DateUtils.getCalendar().getTime();
	}

	public static Date stringToDate(final String dateString, final String pattern) {
		final SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String dateToString(final Date date, final String pattern) {
		if(ObjectUtils.isEmpty(date)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	public static Date sumarMinutos(final Date date, final int minutos) {
		Calendar cal = DateUtils.getCalendar();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutos);
		return cal.getTime();
	}

	public static Date sumarDia(final Date fecha, final int cantidad) {
		Calendar cal = getCalendar();
		cal.setTime(fecha);
		cal.add(Calendar.DAY_OF_MONTH, cantidad);
		return cal.getTime();
	}

	public static Short diferencia(final Date inicio, final Date termino) {
		return (short) TimeUnit.DAYS.convert(Math.abs(termino.getTime() - inicio.getTime()),
				TimeUnit.MILLISECONDS);
	}

	public static Date removerHora(final Date fecha) {
		Calendar cal = getCalendar();
		cal.setTime(fecha);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Integer obtenerAgno() {
		Calendar cal = getCalendar();
		return cal.get(Calendar.YEAR);
	}

}
