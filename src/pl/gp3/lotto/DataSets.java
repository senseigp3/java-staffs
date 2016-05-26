/**
 * Created by yegomosc on 15.05.16.
 */

package pl.gp3.lotto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataSets {
    public static void main(String[] args) {
        int twos[][] = new int[2][49];

        Map<String, Integer> counts = new HashMap<>();
        //twos
        int cnt2 = 0;
        for (int i = 0; i < 49; i++) {
            for (int j = i + 1; j < 49; j++) {
                //System.out.println(i + 1 + "," + (j + 1));
                String two = i + 1 + "," + (j + 1);
                counts.put(two, 0);
                cnt2++;
            }
        }

        System.out.println(cnt2);

        //threes
        int cnt3 = 0;
        for (int i = 0; i < 49; i++) {
            for (int j = i + 1; j < 49; j++) {
                for (int k = j + 1; k < 49; k++) {
                    //System.out.println(i + 1 + "," + (j + 1) + "," + (k + 1));
                    String three = i + 1 + "," + (j + 1) + "," + (k + 1);
                    counts.put(three, 0);
                    cnt3++;
                }
            }
        }

        System.out.println(cnt3);

        //fours
        int cnt4 = 0;
        for (int i = 0; i < 49; i++) {
            for (int j = i + 1; j < 49; j++) {
                for (int k = j + 1; k < 49; k++) {
                    for (int l = k + 1; l < 49; l++) {
                        //System.out.println(i + 1 + "," + (j + 1) + "," + (k + 1));
                        String four = i + 1 + "," + (j + 1) + "," + (k + 1) + "," + (l + 1);
                        counts.put(four, 0);
                        cnt4++;
                    }
                }
            }
        }

        System.out.println(cnt4);

        String fileName = "./data/dl.txt";
        List<String> results = new ArrayList<>();

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            results = stream.map(line -> line.split(" ")[2])
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String test:
             counts.keySet()) {

            for (String line : results) {

                if(concludeAllNumbers(test, line)) {
                    counts.put(test, counts.get(test) + 1);
//                    System.out.println("Taka linia: " + line);
                }
            }
        }

        //sort map
        counts = sortByValue(counts);

        // print map
        for (String key : counts.keySet()){
            Integer value = counts.get(key);
            System.out.println(key + " " + value);


        }

    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {

        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();

        st.sorted(Map.Entry.comparingByValue())
                .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }

    private static boolean concludeAllNumbers(String str1, String str2) {
        boolean result = false;

        String[] str1Split = str1.split(",");
        //String[] str2Split = str2.split(",");

        for (String s: str1Split) {
            if (str2.contains(s)) {
                result = true;
            } else {
                result = false;
                break;
            }
        }

        return result;
    }
}
