public class Match {

  private int fullMatches;
  private int partialMatches;

  public void incrementFullMatches(){
    fullMatches++;
  }

  public void incrementPartialMatches() {
    partialMatches++;
  }

  public int getFullMatches() {
    return fullMatches;
  }

  public int getPartialMatches() {
    return partialMatches;
  }

  @Override
  public String toString() {
    return "полных совпадений=" + fullMatches +
        ", частичных совпадений=" + partialMatches;
  }
}
