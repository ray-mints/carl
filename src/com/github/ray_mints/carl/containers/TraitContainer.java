package com.github.ray_mints.carl.containers;

public class TraitContainer {


  private final int strength;
  private final int intelligence;
  private final int creativity;
  private final int patriotism;
  private final int stamina;


  public TraitContainer(int strength, int intelligence, int creativity, int patriotism, int stamina) {
    this.strength = strength;
    this.intelligence = intelligence;
    this.creativity = creativity;
    this.patriotism = patriotism;
    this.stamina = stamina;
  }

  int getStrength() {
    return strength;
  }

  int getIntelligence() {
    return intelligence;
  }

  int getCreativity() {
    return creativity;
  }

  int getPatriotism() {
    return patriotism;
  }

  int getStamina() {
    return stamina;
  }

  /**
   * Returns an instance of {@code com.github.ray_mints.carl.containers.TraitContainer} whose parameters are the result of addition of
   * parameters of {@code this} and parameters of {@code addend}.
   *
   * @param addend instance of {@code com.github.ray_mints.carl.containers.TraitContainer} to add {@code this} parameters.
   * @return new instance of {@code com.github.ray_mints.carl.containers.TraitContainer} with parameters summed.
   */
  public TraitContainer add(TraitContainer addend) {
    return new TraitContainer(
        this.strength + addend.strength,
        this.intelligence + addend.intelligence,
        this.creativity + addend.creativity,
        this.patriotism + addend.patriotism,
        this.stamina + addend.stamina);
  }

  @Override
  public String toString() {
    return "{" +
        "Сила=" + strength +
        ", Интеллект=" + intelligence +
        ", Творчество=" + creativity +
        ", Патриотизм=" + patriotism +
        ", Выносливость=" + stamina +
        '}';
  }
}
