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

  public int getStrength() {
    return strength;
  }

  public int getIntelligence() {
    return intelligence;
  }

  public int getCreativity() {
    return creativity;
  }

  public int getPatriotism() {
    return patriotism;
  }

  public int getStamina() {
    return stamina;
  }

  public TraitContainer add(TraitContainer that) {
    return new TraitContainer(
        this.strength + that.strength,
        this.intelligence + that.intelligence,
        this.creativity + that.creativity,
        this.patriotism + that.patriotism,
        this.stamina + that.stamina);
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
