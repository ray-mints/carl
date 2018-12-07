import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class CarlStarter {

  private static List<MatchInfo> bestMatcher(Profession[] professions, List<TraitContainer> influences) {

    List<MatchInfo> matchInfoList = new ArrayList<>();

    for (Profession profession : professions) {
      for (int i = 0; i < influences.size(); i++) {
        matchInfoList.add(new MatchInfo(profession.matchIndex(influences.get(i)), profession.getName(), i));

        for (int j = i + 1; j < influences.size(); j++) {
          matchInfoList.add(new MatchInfo(profession.matchIndex(influences.get(i).add(influences.get(j))), profession.getName(), i, j));

          for (int k = j + 1; k < influences.size(); k++) {
            matchInfoList.add(new MatchInfo(profession.matchIndex(influences.get(i).add(influences.get(j)).add(influences.get(k))), profession.getName(), i, j, k));


            for (int l = k + 1; l < influences.size(); l++) {
              matchInfoList.add(new MatchInfo(profession.matchIndex(influences.get(i).add(influences.get(j)).add(influences.get(k)).add(influences.get(l))), profession.getName(), i, j, k, l));

              for (int m = l + 1; m < influences.size(); m++) {
                matchInfoList.add(new MatchInfo(profession.matchIndex(influences.get(i).add(influences.get(j)).add(influences.get(k)).add(influences.get(l)).add(influences.get(m))), profession.getName(), i, j, k, l, m));

              }
            }
          }
        }
      }
    }
    return matchInfoList;
  }

  public static void removeTooSmallResults(List<MatchInfo> resultList, int maxFull, int maxPartial) {
//    int maxFull = 0;
//    int maxPartial = 0;
    boolean removedSome = false;

    for (int i = 0; i < resultList.size(); i++) {
      int currentFull = resultList.get(i).getFullMatches();
      int currentPartial = resultList.get(i).getPartialMatches();
      if (currentFull < maxFull && currentPartial < maxPartial) {
        removedSome = true;
        resultList.remove(i);
        i--;
      }
      if (currentFull > maxFull) {
        maxFull = currentFull;
      }
      if (currentPartial > maxPartial) {
        maxPartial = currentPartial;
      }
    }

    if (removedSome) {
      removeTooSmallResults(resultList, maxFull, maxPartial);
    }
  }

  public static void main(String[] args) {

    Profession[] professions = new Profession[5];

    professions[0] = new Profession("Инженер-сантехник", 15, 5, 15, 10, 20);
    professions[1] = new Profession("Управляющий дома", 10, 10, 10, 10, 10);
    professions[2] = new Profession("Шеф-повар", 10, 10, 25, 15, 20);
    professions[3] = new Profession("Коллектор", 30, 5, 10, 15, 30);
    professions[4] = new Profession("Учитель биологии", 10, 25, 10, 15, 15);

    List<TraitContainer> influences = new ArrayList<>();
//    influences.add(new TraitContainer(15, 15, 0, -10, 10);
//    influences.add(new TraitContainer(20, 0, 20, 5, -10));

    while (true) {

      Console console = System.console();
      if (console == null) {
        System.out.print("Ошибка!. Консоль недоступна.");
        break;
      } else {

        if(!influences.isEmpty()) {
          System.out.println();
          System.out.println("Добавленные влияния: ");
          for (int i = 0; i < influences.size(); i++) {
            System.out.println(i + ": " + influences.get(i));
          }
          System.out.println();
        }


        String choice1;
        if(influences.isEmpty()) {
          choice1 = "Сделайте выбор (a - добавить !!!ВЛИЯНИЕ, q - закончить работу):";
        } else {
          choice1 = "Сделайте выбор (a - добавить !!!ВЛИЯНИЕ, rmX - удалить влияние X (нужно посдставить вместо X число), p - рассчитать результат, q - закончить работу):";
        }
        String line = console.readLine(choice1);
        if (line.equals("a") || line.equals("а")) {
          influences.add(new TraitContainer(
              Integer.parseInt(console.readLine("Cила: ")),
              Integer.parseInt(console.readLine("Интеллект: ")),
              Integer.parseInt(console.readLine("Творчество: ")),
              Integer.parseInt(console.readLine("Патриотизм: ")),
              Integer.parseInt(console.readLine("Выносливость: "))
          ));
        }
        if(line.startsWith("rm")) {
          influences.remove(Integer.parseInt(line.substring(2)));
        }
        if(line.equals("p")|| line.equals("р")){
          List<MatchInfo> resultList = bestMatcher(professions, influences);

          removeTooSmallResults(resultList, 0, 0);

          for (int i = 0; i < resultList.size(); i++) {
            System.out.println(i + " :" + resultList.get(i));
          }

          String line2 = console.readLine("Сделайте выбор (число - выбрать результат, c - вернутся к !!!ВЛИЯНИЯМ):");
          if(line2.equals("c")) {
            continue;
          }else {
            System.out.println("Ваш выбор: ");
            MatchInfo chosen = resultList.get(Integer.parseInt(line2));
            int [] indexes = chosen.getInfluenceIndexes();
            for (int index : indexes) {
              System.out.println(influences.get(index).toString());
            }

            for (int i = indexes.length - 1; i>=0; i--) {
              influences.remove(indexes[i]);
            }

            System.out.println("Нажмите Enter");

            console.readLine();
          }

        }
        if(line.equals("q")){
          break;
        }
      }


    }


  }
}
