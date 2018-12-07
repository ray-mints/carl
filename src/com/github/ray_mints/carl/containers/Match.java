package com.github.ray_mints.carl.containers;

public class Match {

  private int fullMatches;
  private int partialMatches;

  void incrementFullMatches(){
    fullMatches++;
  }

  void incrementPartialMatches() {
    partialMatches++;
  }

  int getFullMatches() {
    return fullMatches;
  }

  int getPartialMatches() {
    return partialMatches;
  }

  @Override
  public String toString() {
    return "полных совпадений=" + fullMatches +
        ", частичных совпадений=" + partialMatches;
  }
}
