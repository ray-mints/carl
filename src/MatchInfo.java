import java.util.Arrays;

public class MatchInfo {

  private Match match;
  private String profession;
  private int[] influenceIndexes;

  public MatchInfo(Match match, String profession, int ... influenceIndexes) {
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

  @Override
  public String toString() {
    return "вариант{" +
         match.toString() +
        ", профессия='" + profession + '\'' +
        ", влияния=" + Arrays.toString(influenceIndexes) +
        '}';
  }
}
