import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> list = Files.readAllLines(new File("elephants and people.txt").toPath());
        list.remove(0);
        for (String s: list     ) {
            String[] words = s.split(", ");
            Elephant el = new Elephant(words[0], Integer.parseInt(words[1]),
                                       words[2], Integer.parseInt(words[3]));
            Human chel = new Human(words[4], words[5], Integer.parseInt(words[6]));
            System.out.println(el+" : " +chel);
        }
    }
}