import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CarlStarter {

  /**
   * Gives all possible options to create Carl.
   *
   * @param professions all possible professions.
   * @param influences  all obtained influences.
   * @return list of options to choose from.
   */
  private static List<Option> bestMatcher(Profession[] professions, List<TraitContainer> influences) {

    List<Option> optionList = new ArrayList<>();

    for (Profession profession : professions) {
      for (int i = 0; i < influences.size(); i++) {
        optionList.add(new Option(profession.getName(),
            profession.matchIndex(influences.get(i)),
            i));

        for (int j = i + 1; j < influences.size(); j++) {
          optionList.add(new Option(profession.getName(),
              profession.matchIndex(influences.get(i).add(influences.get(j))),
              i, j));

          for (int k = j + 1; k < influences.size(); k++) {
            optionList.add(new Option(profession.getName(),
                profession.matchIndex(influences.get(i).add(influences.get(j)).add(influences.get(k))),
                i, j, k));


            for (int l = k + 1; l < influences.size(); l++) {
              optionList.add(new Option(profession.getName(),
                  profession.matchIndex(influences.get(i).add(influences.get(j)).add(influences.get(k)).add(influences.get(l))),
                  i, j, k, l));

              for (int m = l + 1; m < influences.size(); m++) {
                optionList.add(new Option(profession.getName(),
                    profession.matchIndex(influences.get(i).add(influences.get(j)).add(influences.get(k)).add(influences.get(l)).add(influences.get(m))),
                    i, j, k, l, m));

              }
            }
          }
        }
      }
    }
    return optionList;
  }


  private static void removeTooSmallResults(List<Option> resultList) {
    removeTooSmallResults(resultList, 0, 0);
  }

  //todo: update - operate on lamps lighted instead for full and partial
  private static void removeTooSmallResults(List<Option> resultList, int maxFull, int maxPartial) {
    boolean removedSome = false;

    for (int i = 0; i < resultList.size(); i++) {
      int currentFull = resultList.get(i).getFullMatches();
      int currentPartial = resultList.get(i).getPartialMatches();
      if ((currentFull == maxFull && currentPartial < maxPartial) ||
          (currentFull < maxFull && currentPartial == maxPartial) ||
          (currentFull < maxFull && currentPartial < maxPartial)) {
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

  /**
   * True if string contains only digits.
   */
  private static boolean isUnsignedInteger(String toCheck) {
    return toCheck != null && Pattern.matches("\\d+", toCheck);
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

    String choice1 = "";
    Console console = System.console();


    while (!choice1.equals("q")) {

      if (console == null) {
        System.out.print("Ошибка!. Консоль недоступна.");
        break;
      } else {

        if (!influences.isEmpty()) {
          System.out.println();
          System.out.println("Добавленные влияния: ");
          for (int i = 0; i < influences.size(); i++) {
            System.out.println(i + ": " + influences.get(i));
          }
          System.out.println();
        }


        String choice1Descr;
        if (influences.isEmpty()) {
          choice1Descr = "Сделайте выбор (a - добавить !!!ВЛИЯНИЕ, q - закончить работу):";
        } else {
          choice1Descr = "Сделайте выбор (a - добавить !!!ВЛИЯНИЕ, " +
              "rmX - удалить влияние X (нужно посдставить вместо X число), " +
              "p - рассчитать результат, " +
              "q - закончить работу):";
        }
        choice1 = console.readLine(choice1Descr);
        if (choice1.equals("a") || choice1.equals("а")) {
          influences.add(new TraitContainer(
              Integer.parseInt(console.readLine("Сила: ")),
              Integer.parseInt(console.readLine("Интеллект: ")),
              Integer.parseInt(console.readLine("Творчество: ")),
              Integer.parseInt(console.readLine("Патриотизм: ")),
              Integer.parseInt(console.readLine("Выносливость: "))
          ));
        }
        if (choice1.startsWith("rm")) {
          influences.remove(Integer.parseInt(choice1.substring(2)));
        }
        if (choice1.equals("p") || choice1.equals("р")) {
          List<Option> resultList = bestMatcher(professions, influences);

          removeTooSmallResults(resultList);

          for (int i = 0; i < resultList.size(); i++) {
            System.out.println("вариант " + i + " :" + resultList.get(i));
          }

          String choice2 = console.readLine("Сделайте выбор (число - выбрать вариант, любой другой ввод - вернутся к !!!ВЛИЯНИЯМ):");
          if (isUnsignedInteger(choice2)) {
            System.out.println("\nВаш выбор: ");
            Option chosenOption = resultList.get(Integer.parseInt(choice2));
            System.out.println("Профессия: " + chosenOption.getProfession());
            System.out.println("!!!ВЛИЯНИЯ:");
            int[] indexes = chosenOption.getInfluenceIndexes();
            for (int index : indexes) {
              System.out.println(influences.get(index).toString());
            }

            for (int i = indexes.length - 1; i >= 0; i--) {
              influences.remove(indexes[i]);
            }

            System.out.println("Нажмите Enter");

            console.readLine();
          }

        }
      }
    }

  }
}
