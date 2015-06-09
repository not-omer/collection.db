package me.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Use this class to get the .osu file MD5 hashes and to get beatmap names by
 * their hashes.
 * 
 * @author Omer
 */
public class Beatmaps {
	private HashMap<String, String> hashes;

	public Beatmaps() {
		hashes = new HashMap<String, String>();
	}

	/**
	 * This needs to be invoked before calling {@link #nameForHash(String)}.
	 * 
	 * @param directory
	 * @throws IOException
	 */
	public void processBeatmapHashes(File directory) throws IOException {
		for (File folder : directory.listFiles())
			if (folder.isDirectory())
				for (File file : folder.listFiles())
					if (Utilities.getExtension(file).equals("osu"))
						hashes.put(file.getName(), Utilities.md5(file));
	}

	/**
	 * Get the name of a beatmap by its MD5 hash. Must invoke
	 * {@link #processBeatmapHashes(File)} before calling this method.
	 * 
	 * @param hash
	 * @return
	 */
	public String nameForHash(String hash) {
		return (String) Utilities.getKeyFromValue(hashes, hash);
	}

	public HashMap<String, String> getHashes() {
		return hashes;
	}
}