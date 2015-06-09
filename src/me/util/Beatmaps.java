package me.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Beatmaps {
	private HashMap<String, String> hashes;

	public Beatmaps() {
		hashes = new HashMap<String, String>();
	}

	public void processBeatmapHashes(File directory) throws IOException {
		for (File folder : directory.listFiles())
			if (folder.isDirectory())
				for (File file : folder.listFiles())
					if (Utilities.getExtension(file).equals("osu"))
						hashes.put(file.getName(), Utilities.md5(file));
	}

	public String nameForHash(String hash) {
		return (String) Utilities.getKeyFromValue(hashes, hash);
	}

	public HashMap<String, String> getHashes() {
		return hashes;
	}
}