package bianchang.net.com.mylibrary;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 */
public class EnvironmentStateUtils {

	/**
	 *
	 * @return
	 */
	public static boolean ExternalStorageIsAvailable() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *
	 * @return
	 */
	public static File getExternalStorageDirectory() {
		return Environment.getExternalStorageDirectory();
	}

	/**
	 *
	 * @return
	 */
	public static long getAvailableExternalMemorySize() {
		if ((ExternalStorageIsAvailable())) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return availableBlocks * blockSize;
		} else {
			return -1;
		}
	}

	/**
	 * @return
	 */
	public long getTotalExternalMemorySize() {
		if (ExternalStorageIsAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long totalBlocks = stat.getBlockCount();
			return totalBlocks * blockSize;
		} else {
			return -1;
		}
	}


	
}