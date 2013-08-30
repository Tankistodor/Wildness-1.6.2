package wildness.mod;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class WildnessSettings {

	public static boolean disableSleepInBed = false;
	
	
    public static int dayLength;
    public static int yearLength;


	public static int playerFailHeight;

	public static boolean getBooleanFor(Configuration config, String heading,
			String item, boolean value) {
		if (config == null) {
			return value;
		} else {
			try {
				Property e = config.get(heading, item, value);
				return e.getBoolean(value);
			} catch (Exception var5) {
				System.out
						.println("[Wildness] Error while trying to add Integer, config wasn\'t loaded properly!");
				return value;
			}
		}
	}

	public static boolean getBooleanFor(Configuration config, String heading,
			String item, boolean value, String comment) {
		if (config == null) {
			return value;
		} else {
			try {
				Property e = config.get(heading, item, value);
				e.comment = comment;
				return e.getBoolean(value);
			} catch (Exception var6) {
				System.out
						.println("[Wildness] Error while trying to add Integer, config wasn\'t loaded properly!");
				return value;
			}
		}
	}

	public static int getIntFor(Configuration config, String heading,
			String item, int value) {
		if (config == null) {
			return value;
		} else {
			try {
				Property e = config.get(heading, item, value);
				return e.getInt(value);
			} catch (Exception var5) {
				System.out
						.println("[Wildness] Error while trying to add Integer, config wasn\'t loaded properly!");
				return value;
			}
		}
	}

	public static int getIntFor(Configuration config, String heading,
			String item, int value, String comment) {
		if (config == null) {
			return value;
		} else {
			try {
				Property e = config.get(heading, item, value);
				e.comment = comment;
				return e.getInt(value);
			} catch (Exception var6) {
				System.out
						.println("[Wildness] Error while trying to add Integer, config wasn\'t loaded properly!");
				return value;
			}
		}
	}

}
