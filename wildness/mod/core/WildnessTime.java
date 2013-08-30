package wildness.mod.core;

import net.minecraft.world.World;
import wildness.mod.WildnessSettings;

public class WildnessTime {

	private static World worldObj;
	public static String[] seasons = new String[] { "EarlySpring", "Spring",
			"LateSpring", "EarlySummer", "Summer", "LateSummer", "EarlyAutumn",
			"Autumn", "LateAutumn", "EarlyWinter", "Winter", "LateWinter" };
	public static String[] months = new String[] { "March", "April", "May",
			"June", "July", "August", "September", "October", "November",
			"December", "January", "February" };
	public static String[] Days = new String[] { "Sunday", "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday", "Saturday" };
	public static int currentDay = 0;
	public static int lastMonth = 11;
	public static int currentMonth = 0;
	public static int currentYear = 0;
	private static long time = 0L;
	public static final int January = 10;
	public static final int February = 11;
	public static final int March = 0;
	public static final int April = 1;
	public static final int May = 2;
	public static final int June = 3;
	public static final int July = 4;
	public static final int August = 5;
	public static final int September = 6;
	public static final int October = 7;
	public static final int November = 8;
	public static final int December = 9;
	public static final long hourLength = (long) (WildnessSettings.dayLength / 24);
	public static int dayLength = WildnessSettings.dayLength;
	public static float timeRatio = (float) WildnessSettings.yearLength / 360.0F;
	public static int daysInYear = WildnessSettings.yearLength;
	public static int daysInMonth = daysInYear / 12;
	public static long ticksInYear = (long) (daysInYear * dayLength);
	public static long ticksInMonth = (long) (daysInMonth * dayLength);

	public static void UpdateTime(World world) {
		time = world.getWorldInfo().getWorldTime();
		int m = getMonth();
		int m1 = m - 1;

		if (m1 < 0) {
			m1 = 11;
		}

		lastMonth = m1;
		currentDay = getDayOfYear();
		currentMonth = m;
		currentYear = getYear();
	}

	public static String getSeason() {
		return seasons[getMonth()];
	}

	public static long getTotalTicks() {
		return time;
	}

	public static int getDayOfWeek() {
		long day = getTotalDays() + 1L;
		long days = day / 7L;
		long days2 = day - days * 7L;
		return (int) days2;
	}

	public static int getDayOfMonth() {
		long month = getTotalMonths();
		long days = (long) daysInMonth * month;
		long days2 = getTotalDays() - days;
		return 1 + (int) days2;
	}

	public static int getDayOfYear() {
		long year = (long) getYear();
		long years = ticksInYear * year;
		long years2 = time - years;
		return (int) (years2 / (long) dayLength);
	}

	public static int getDayOfYearFromTick(long tick) {
		long years = tick / ticksInYear;
		long years2 = tick - ticksInYear * years;
		return (int) (years2 / (long) dayLength);
	}

	public static int getDayOfYearFromDays(long days) {
		long years = days / (long) daysInYear;
		long years2 = days - (long) daysInYear * years;
		return (int) years2;
	}

	public static int getMonth() {
		long totalmonths = getTotalMonths();
		long totalmonths2 = totalmonths / 12L;
		long totalmonths3 = totalmonths - totalmonths2 * 12L;
		return (int) totalmonths3;
	}

	public static int getYear() {
		long totalmonths = getTotalMonths();
		long totalmonths2 = totalmonths / 12L;
		return (int) totalmonths2;
	}

	public static long getTotalDays() {
		return time / (long) dayLength;
	}

	public static long getTotalHours() {
		return time / hourLength;
	}

	public static long getTotalMonths() {
		return getTotalDays() / (long) daysInMonth;
	}

	public static long getTotalYears() {
		return getTotalMonths() / 12L;
	}

	public static long getHour() {
		long h = (time - time / (long) dayLength * (long) dayLength)
				/ hourLength;
		h -= 6L;

		if (h < 0L) {
			h += 23L;
		}

		h -= 12L;

		if (h < 0L) {
			h += 23L;
		}

		return h;
	}

	public static boolean isSpring() {
		return getDayOfYear() >= 20 && getDayOfYear() <= 111;
	}

	public static boolean isSummer() {
		return getDayOfYear() >= 112 && getDayOfYear() <= 202;
	}

	public static boolean isFall() {
		return getDayOfYear() >= 203 && getDayOfYear() <= 293;
	}

	public static boolean isWinter() {
		return getDayOfYear() >= 294 || getDayOfYear() < 20;
	}

	public static int getMonthFromDayOfYear(int day) {
		if (day < 0) {
			day += daysInYear;
		}

		return day / daysInMonth;
	}

	public static int getDayOfMonthFromDayOfYear(int day) {
		if (day < 0) {
			day += daysInYear;
		}

		return day - (int) Math.floor((double) (day / daysInMonth))
				* daysInMonth;
	}

	public static int getPrevMonth() {
		return lastMonth;
	}

	public static int getPrevMonth(int month) {
		return month == 0 ? 11 : month - 1;
	}

	public static float getYearRatio() {
		return (float) daysInYear / 360.0F;
	}

}
