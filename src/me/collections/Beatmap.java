package me.collections;

public class Beatmap {
	private String name, hash;

	public Beatmap(String hash) {
		this.hash = hash;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getHash() {
		return hash;
	}
}