package me.collections;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import me.util.Utilities;

public class Collections {
	private File database;
	private ArrayList<Collection> collections;

	public Collections(File database) {
		if (Utilities.getExtension(database).equals("db"))
			this.database = database;
		collections = new ArrayList<Collection>();
	}

	public void parse() throws IOException {
		if (database == null)
			throw new IOException("not a valid .db file!");

		OsuBinaryReader in = new OsuBinaryReader(new FileInputStream(database));

		in.getInt();
		int num = in.getInt();
		for (int i = 0; i < num; i++) {
			ArrayList<Beatmap> beatmaps = new ArrayList<Beatmap>();
			String key = in.getLine();
			int num2 = in.getInt();
			for (int j = 0; j < num2; j++)
				beatmaps.add(new Beatmap(in.getLine()));
			collections.add(new Collection(key, beatmaps));
		}

		in.close();
	}

	public ArrayList<Collection> getCollections() {
		return collections;
	}
}