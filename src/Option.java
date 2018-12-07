import java.util.Arrays;

/**
 * Possible choice - profession with influences and how they match each other.
 */
public class Option {

  private final String profession;
  private final Match match;
  private final int[] influenceIndexes;


  public Option(String profession, Match match, int ... influenceIndexes) {
    this.match = match;
    this.profession = profession;
    this.influenceIndexes = influenceIndexes;
  }

  public int getFullMatches() {
    return match.getFullMatches();
  }

  public int getPartialMatches() {
    return match.getPartialMatches();
  }

  public int[] getInfluenceIndexes() {
    return influenceIndexes;
  }

  public String getProfession() {
    return profession;
  }

  @Override
  public String toString() {
    return "{" +
         match.toString() +
        ", профессия='" + profession + '\'' +
        ", влияния=" + Arrays.toString(influenceIndexes) +
        '}';
  }
}
