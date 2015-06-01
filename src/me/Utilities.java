package me;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class Utilities {
	public static String getExtension(File file) {
		String fileName = file.getName();
		int i = fileName.lastIndexOf('.');
		return i > 0 ? fileName.substring(i + 1) : "";
	}

	public static String md5(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);
		fis.close();
		return md5;
	}

	public static Object getKeyFromValue(Map<?, ?> hm, Object value) {
		for (Object o : hm.keySet())
			if (hm.get(o).equals(value))
				return o;
		return null;
	}
}