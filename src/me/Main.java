package me;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import me.collections.Beatmap;
import me.collections.Collection;
import me.collections.io.CollectionReader;
import me.collections.io.CollectionWriter;
import me.util.BeatmapProcessor;

/**
 * Demonstrate creating and modifying a collection, writing it to a file, and
 * then reading it.
 */
public class Main {
  private static File OSU_DIRECTORY = new File(System.getenv("ProgramFiles") + " (x86)\\osu!\\Songs\\");

  public static void main(String[] args) throws IOException {
    // create a collection called 'songs' consisting of one beatmap by providing
    // the hash of the .osu file
    Collection songs = new Collection("songs", asArrayList(new Beatmap("b0ad13a33b141198971c0ae3f15b3e04")));
    // create a new collection writer consisting of the 'songs' collection
    CollectionWriter writer = new CollectionWriter(asArrayList(songs));
    // generate the collections file and write it to test.db
    writer.write(new File("test.db"));

    // create a new collection reader to read test.db
    CollectionReader collections = new CollectionReader(new File("test.db"));
    // parse the collections from the file
    collections.parse();
    // use this to process the beatmap hashes in the osu directory in order to
    // get beatmap names
    BeatmapProcessor maps = new BeatmapProcessor();
    maps.processBeatmapHashes(OSU_DIRECTORY);

    collections.getCollections().forEach(coll -> {
      System.out.println(coll.getName());
      coll.getBeatmaps().forEach(map -> {
        String hash = map.getHash();
        System.out.println("\t" + maps.getNameByHash(hash));
        System.out.println("\t\tHash: " + hash);
      });
    });
  }

  @SafeVarargs
  private static <T> ArrayList<T> asArrayList(T... t) {
    return new ArrayList<>(Arrays.asList(t));
  }
}