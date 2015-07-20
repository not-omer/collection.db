package me.collections.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import me.collections.Beatmap;
import me.collections.Collection;

public class CollectionWriter {
  private ArrayList<Collection> collections;

  public CollectionWriter(ArrayList<Collection> collections) {
    if (!collections.isEmpty() && collections != null)
      this.collections = collections;
  }

  public void write(File file) throws IOException {
    if (collections == null)
      throw new IOException("empty or null collections object");

    OsuBinaryWriter out = new OsuBinaryWriter(new FileOutputStream(file));

    out.writeInt32(0);
    out.writeInt32(collections.size());
    for (Collection collection : collections) {
      out.writeLine(collection.getName());
      out.writeInt32(collection.getBeatmaps().size());
      for (Beatmap beatmap : collection.getBeatmaps())
        out.writeLine(beatmap.getHash());
    }

    out.close();
  }
}