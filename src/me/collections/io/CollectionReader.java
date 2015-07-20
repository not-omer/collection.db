package me.collections.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import me.collections.Beatmap;
import me.collections.Collection;
import me.util.Utilities;

public class CollectionReader {
  private File database;
  private ArrayList<Collection> collections;

  public CollectionReader(File database) {
    if (Utilities.getExtension(database).equals("db"))
      this.database = database;
    collections = new ArrayList<Collection>();
  }

  public void parse() throws IOException {
    if (database == null)
      throw new IOException("not a valid .db file!");

    OsuBinaryReader in = new OsuBinaryReader(new FileInputStream(database));

    in.getInt();
    int totalCollections = in.getInt();
    for (int i = 0; i < totalCollections; i++) {
      ArrayList<Beatmap> collection = new ArrayList<Beatmap>();
      String collectionName = in.getLine();
      int collectionCapacity = in.getInt();
      for (int j = 0; j < collectionCapacity; j++)
        collection.add(new Beatmap(in.getLine()));
      collections.add(new Collection(collectionName, collection));
    }

    in.close();
  }

  public Collection getCollectionByName(String name) {
    if (!collections.isEmpty())
      for (Collection collection : collections)
        if (collection.getName().equals(name))
          return collection;
    return null;
  }

  public ArrayList<Collection> getCollections() {
    return collections;
  }
}