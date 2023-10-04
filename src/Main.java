import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
//import javafx.util.Pair;

public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<ArrayList> pairs = loadPairs("elephants and people.txt");
        searchForDoubleHumanForElephant(pairs);
       // groupByHuman(pairs);
        groupByHumanAndElephant(pairs);
    }

    private static void groupByHuman(ArrayList<ArrayList> pairs) {
        HashMap<Human, ArrayList<Elephant>> dict = new HashMap<>();
        for (ArrayList pair: pairs) {
            Elephant el = (Elephant) pair.get(0);
            Human chel = (Human)pair.get(1);
            if(! dict.containsKey(chel))
                dict.put(chel, new ArrayList<>());

            dict.get(chel).add(el);
        }

        ArrayList<Human> people = new ArrayList<>(dict.keySet());
        people.sort(Comparator.comparing(Human::getName));
        for (Human hu: people ) {
            System.out.println(hu);
            List<String> elNames = dict.get(hu).stream()
                                               .map(elephant -> elephant.getName())
                                               .sorted().toList();
            System.out.println(elNames);
        }
    }

    private static void groupByHumanAndElephant(ArrayList<ArrayList> pairs) {
        HashMap<Human, HashSet<Elephant>> dict = new HashMap<>();
        for (ArrayList pair: pairs) {
            Elephant el = (Elephant) pair.get(0);
            Human chel = (Human)pair.get(1);
            if(! dict.containsKey(chel))
                dict.put(chel, new HashSet<>());

            dict.get(chel).add(el);
        }

        ArrayList<Human> people = new ArrayList<>(dict.keySet());
        people.sort(Comparator.comparing(Human::getName));
        for (Human hu: people ) {
            System.out.println(hu);
            List<String> elNames = dict.get(hu).stream().map(elephant -> elephant.getName()).toList();
            System.out.println(elNames);
        }
    }

    private static void searchForDoubleHumanForElephant(ArrayList<ArrayList> pairs) {
        HashMap<Elephant, Human> elHuMap = new HashMap<>();
        for (ArrayList pair: pairs) {
            Elephant el = (Elephant) pair.get(0);
            Human chel = (Human)pair.get(1);
            if(elHuMap.containsKey(el) && !elHuMap.get(el).equals(chel) )
                System.out.println("кажется, у слона "+el+" не один владелец");
            else
                elHuMap.put(el, chel);
        }
    }

    public static ArrayList<ArrayList> loadPairs(String fname) throws IOException {
        List<String> list = Files.readAllLines(new File(fname).toPath());
        list.remove(0);
        ArrayList<ArrayList> listOfPairs = new ArrayList<>();
        for (String s: list     ) {
            String[] words = s.split(", ");
            Elephant el = new Elephant(words[0], Integer.parseInt(words[1]),
                    words[2], Integer.parseInt(words[3]));
            Human chel = new Human(words[4], words[5], Integer.parseInt(words[6]));
            ArrayList pair = new ArrayList();
            pair.add(el);
            pair.add(chel);
            listOfPairs.add(pair);
            //System.out.println(el+" : " +chel);
        }
        return listOfPairs;
    }
}