import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.Map.Entry;
// import java.util.stream.Collectors;

public class main {
    public static void main(String[] args) {
        String PathToFile = "C:\\Users\\Tommy\\IdeaProjects\\TextTask\\src\\text.txt";  // INSERT FILE PATH
        Map<String, Integer> map;

        String st = parseFile(PathToFile);
        st = st.replaceAll("[,\"\n\r]", "").toLowerCase();

        map = countFrequencyInString(st);

        map = sortMapByValue(map);

        printMap(map);
    }

    public static String parseFile(String PathToFile) {
        String tmpString = "";

        try {
            Path file = Path.of(PathToFile);
            tmpString = Files.readString(file);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return tmpString;
    }

    public static Map<String, Integer> countFrequencyInString(String str) {
        Map<String, Integer> map = new HashMap<>();

        String[] arr = str.split(" ");

        for (String s : arr) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }

        return map;
    }

    public static void printMap(Map<String, Integer> map) {
        map.forEach((key, value) -> System.out.println(key + " " + value));
    }

    public static Map<String, Integer> sortMapByValue(Map<String, Integer> map) {

        Map<String, Integer> tmpMap = new LinkedHashMap<>();
        List<Entry<String, Integer>> tmpList = new ArrayList<>(map.entrySet());
        tmpList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        tmpList.forEach(stringIntegerEntry -> tmpMap.put(stringIntegerEntry.getKey(), stringIntegerEntry.getValue()));

        return tmpMap;

//Streams//
//        return map.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                        (e1, e2) -> e1, LinkedHashMap::new));

    }
}