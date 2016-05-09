import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TreeMap<String, TreeMap<String, TreeSet<String>>> classes = new TreeMap<>();
        HashMap<String, Integer> classUnits = new HashMap<>();

        Pattern patt = Pattern.compile("^([A-Z][a-zA-Z0-9]+) \\| ([A-Z][a-zA-Z0-9]+) \\| ([A-Z][a-zA-Z0-9]+)$");
        String input = sc.nextLine();

        while (!input.equals("It's testing time!")) {
            Matcher matcher = patt.matcher(input);

            if (matcher.find()) {
                String clas = matcher.group(1);
                String method = matcher.group(2);
                String test = matcher.group(3);

                if (!classes.containsKey(clas)) {
                    classes.put(clas, new TreeMap<>());
                    classUnits.put(clas, 1);
                }

                if (!classes.get(clas).containsKey(method)) {
                    classes.get(clas).put(method, new TreeSet<>());
                }

                if (!classes.get(clas).get(method).contains(test)) {
                    classes.get(clas).get(method).add(test);
                    classUnits.put(clas, classUnits.get(clas) + 1);
                }
            }

            input = sc.nextLine();
        }


        classes.entrySet().stream().//сортира класовете по бр на методите възходящо
                sorted((r1, r2) -> Integer.compare(classUnits.get(r2.getKey()), classUnits.get(r1.getKey()))).forEach(entrys -> {//сортира низходящо по броя на методите

            System.out.println(entrys.getKey() + ":");
            entrys.getValue().entrySet().stream().sorted((m1, m2) -> Integer.compare(m2.getValue().size(), m1.getValue().size())).forEach(k -> {//взема класа, после сортира метода по унитите низходящо

                System.out.println("##" + k.getKey());
                k.getValue().stream().sorted().sorted((o1, o2) -> Integer.compare(o1.length(), o2.length())).forEach(i -> {//сортира юнитите по дължината на името

                    System.out.println("####" + i);
                });
            });
        });
    }
}