package me.collections;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Collections {
	private File database;
	private HashMap<String, ArrayList<String>> collections;

	public Collections(File database) {
		if (Utilities.getExtension(database).equals("db"))
			this.database = database;
		collections = new HashMap<String, ArrayList<String>>();
	}

	public void parse() throws IOException {
		OsuBinaryReader in = new OsuBinaryReader(new FileInputStream(database));

		in.getInt();
		int num = in.getInt();
		for (int i = 0; i < num; i++) {
			ArrayList<String> beatmaps = new ArrayList<String>();
			String key = in.getLine();
			int num2 = in.getInt();
			for (int j = 0; j < num2; j++)
				beatmaps.add(in.getLine());
			collections.put(key, beatmaps);
		}

		in.close();
	}

	/**
	 * returns a hashmap
	 * key: name of the collection
	 * value: an arraylist of beatmap hashes
	 */
	public HashMap<String, ArrayList<String>> getCollections() {
		return collections;
	}
}