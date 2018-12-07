package com.github.ray_mints.carl.containers;

public class Profession extends TraitContainer {

  private final String name;

  public Profession(String name, int strength, int intelligence, int creativity, int patriotism, int stamina) {
    super(strength, intelligence, creativity, patriotism, stamina);

    this.name = name;
  }

  public String getName() {
    return name;
  }

  //todo: get rid of match and return light bulbs
  public Match matchInfluences(TraitContainer influences) {
    Match match = new Match();

    int [] deviations = new int[5];
    deviations[0] = Math.abs(getStrength() - influences.getStrength());
    deviations[1] = Math.abs(getIntelligence() - influences.getIntelligence());
    deviations[2] = Math.abs(getCreativity() - influences.getCreativity());
    deviations[3] = Math.abs(getPatriotism() - influences.getPatriotism());
    deviations[4] = Math.abs(getStamina() - influences.getStamina());

    for (int deviation : deviations) {
      if(deviation == 0) {
        match.incrementFullMatches();
      } else if (deviation <= 5) {
        match.incrementPartialMatches();
      }
    }
    return match;
  }
}
