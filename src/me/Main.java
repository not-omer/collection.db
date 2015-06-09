package me;

import java.io.File;
import java.io.IOException;

import me.collections.Beatmap;
import me.collections.Collection;
import me.collections.Collections;
import me.util.Beatmaps;

public class Main {
	private static File OSU_DIRECTORY = null;

	static {
		OSU_DIRECTORY = new File(System.getenv("ProgramFiles") + " (x86)"
				+ "\\osu!\\Songs\\");
	}

	public static void main(String[] args) throws IOException {
		Collections collections = new Collections(new File("collection.db"));
		collections.parse();
		Beatmaps maps = new Beatmaps();
		maps.processBeatmapHashes(OSU_DIRECTORY);

		for (Collection collection : collections.getCollections()) {
			System.out.println(collection.getName());
			for (Beatmap map : collection.getBeatmaps()) {
				String hash = map.getHash();
				System.out.println("\t" + maps.nameForHash(hash));
				System.out.println("\t\tHash: " + hash);
			}
		}
	}
}