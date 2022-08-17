package com.solvd.fooddelivery;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class BookTask {
    public static void main(String[] args) {
        File file = new File("src/main/resources/20000 leagues under the sea.txt");
        try {
            String book = FileUtils.readFileToString(file, StandardCharsets.UTF_8).toLowerCase();
            String[] signs = {",", ".", "\"", "!", "?", ";", ":", "\n", "\r", "\t", "(", ")", "[", "]", "{", "}", "- ", " -", " '", "' "};
            for (String sign : signs) {
                book = StringUtils.replace(book, sign, " ");
            }
            String[] wordArray = StringUtils.split(book, " ");
            Set<String> setOfWords = new HashSet<>(Arrays.asList(wordArray));
            Map<String, Integer> wordMap = new HashMap<>();
            for (String word : setOfWords) {
                System.out.println(word + " " + StringUtils.countMatches(book, word));
                wordMap.put(word, StringUtils.countMatches(book, word));
            }
            List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordMap.entrySet());
            entryList.sort(Map.Entry.comparingByValue());
            Map<String, Integer> sortedMap = new LinkedHashMap<>();
            for (Map.Entry<String, Integer> entry : entryList) {
                sortedMap.put(entry.getKey(), entry.getValue());
            }
            File sortedWords = new File("src/main/resources/sorted words.txt");
            FileUtils.touch(sortedWords);
            FileUtils.writeStringToFile(sortedWords, String.valueOf(sortedMap), StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
