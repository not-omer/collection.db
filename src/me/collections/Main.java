package me.collections;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Main {
	public static void main(String[] args) throws IOException {
		/**
		 * store the hashes of every beatmap
		 */
		HashMap<String, String> hashes = new HashMap<String, String>();
		File directory = new File(System.getenv("ProgramFiles") + " (x86)"
				+ "\\osu!\\Songs\\");
		for (File folder : directory.listFiles())
			if (folder.isDirectory())
				for (File file : folder.listFiles())
					if (Utilities.getExtension(file).equals("osu"))
						hashes.put(file.getName(), Utilities.md5(file));

		/**
		 * now get all the beatmap hashes from the collections and compare them
		 */
		Collections collections = new Collections(new File("collection.db"));
		collections.parse();
		HashMap<String, ArrayList<String>> map = collections.getCollections();
		for (Entry<String, ArrayList<String>> entry : map.entrySet()) {
			System.out.println("\nCollection: " + entry.getKey());
			for (String hash : entry.getValue())
				if (hashes.containsValue(hash))
					System.out.println("\t" + Utilities.getKeyFromValue(hashes, hash));
		}
	}
}