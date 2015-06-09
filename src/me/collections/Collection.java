package me.collections;

import java.util.ArrayList;

public class Collection {
  private String name;
  private ArrayList<Beatmap> beatmaps;

  public Collection(String name, ArrayList<Beatmap> beatmaps) {
    this.name = name;
    this.beatmaps = beatmaps;
  }

  public String getName() {
    return name;
  }

  public ArrayList<Beatmap> getBeatmaps() {
    return beatmaps;
  }
}