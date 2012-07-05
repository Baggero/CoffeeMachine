package se.coffeemachine.utils;

public class VolumeUtils {

	public static final int SEEKBAR_MAX = 100;

	public static int weightVolume(double volume, double max) {
		return (int) ((volume / max) * SEEKBAR_MAX);
	}

}
