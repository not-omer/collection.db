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
public class BeatmapProcessor {
  private HashMap<String, String> hashes;

  public BeatmapProcessor() {
    hashes = new HashMap<String, String>();
  }

  /**
   * This needs to be invoked before calling {@link #getNameByHash(String)}.
   * 
   * @param directory
   * @throws IOException
   */
  public void processBeatmapHashes(File directory) throws IOException {
    for (File folder : directory.listFiles())
      if (folder.isDirectory())
        for (File file : folder.listFiles())
          if (Utilities.getExtension(file).equals("osu")) {
            String name = file.getName();
            hashes.put(name.substring(0, name.length() - 4), Utilities.md5(file));
          }
  }

  /**
   * Get the name of a beatmap by its MD5 hash. Must invoke
   * {@link #processBeatmapHashes(File)} before calling this method.
   * 
   * @param hash
   * @return
   */
  public String getNameByHash(String hash) {
    return (String) Utilities.getKeyFromValue(hashes, hash);
  }

  public HashMap<String, String> getHashes() {
    return hashes;
  }
}