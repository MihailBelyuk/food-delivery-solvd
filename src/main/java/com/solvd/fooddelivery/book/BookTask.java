package com.solvd.fooddelivery.book;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class BookTask {

    private static final Logger LOGGER = LogManager.getLogger(BookTask.class);

    private static final String BOOK_PATH = "src/main/resources/20000 leagues under the sea.txt";
    private static final String SORTED_WORDS_PATH = "sorted words.txt";
    private static final String[] SIGNS = {",", ".", "\"", "!", "?", ";", ":", "\n", "\r", "\t", "(", ")", "]", "[", "{", "}", "- ", " -", " '", "' "};
    private static final String SPACE = " ";

    public static void main(String[] args) {
        try {
            String book = readFile();
            book = replacePunctuationAndSpecialSigns(book);
            String[] wordArray = StringUtils.split(book, SPACE);
            Map<String, Integer> wordMap = countWordMatches(book, wordArray);
            Map<String, Integer> sortedMap = sortWordMap(wordMap);
            createSortedWordsFile(sortedMap);
        } catch (IOException e) {
            LOGGER.info("Failed to read/write information from/to file.");
        }
    }

    private static String readFile() throws IOException {
        File file = new File(BOOK_PATH);
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8).toLowerCase();
    }

    private static void createSortedWordsFile(Map<String, Integer> sortedMap) throws IOException {
        File sortedWords = new File(SORTED_WORDS_PATH);
        FileUtils.writeStringToFile(sortedWords, String.valueOf(sortedMap), StandardCharsets.UTF_8, true);
    }

    private static Map<String, Integer> countWordMatches(String book, String[] wordArray) {
        Set<String> setOfWords = new HashSet<>(Arrays.asList(wordArray));
        Map<String, Integer> wordMap = new HashMap<>();
        LOGGER.info(setOfWords.stream()
                .peek(LOGGER::info)
                .map(word -> {
                    String wordWithCount = word + SPACE + StringUtils.countMatches(book, word);
                    wordMap.put(word, StringUtils.countMatches(book, word));
                    return wordWithCount;
                })
                .collect(Collectors.toList()));
        return wordMap;
    }

    private static Map<String, Integer> sortWordMap(Map<String, Integer> wordMap) {
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        return entryList.stream()
                .collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }

    private static String replacePunctuationAndSpecialSigns(String book) {
        for (String sign : SIGNS) {
            book = StringUtils.replace(book, sign, SPACE);
        }
        return book;
    }
}
