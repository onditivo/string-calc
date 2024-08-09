package com.esg.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringCalculator {
    private static final String DEFAULT_DELIMITER = "[,\\n]";
    public static int add(String stringInput) {
        if ("".equals(stringInput)) {
            return 0;
        }

        var delimiter = DEFAULT_DELIMITER;
        var stringNumber = stringInput;

        // if delimiter is specified, extract both delimiter and number from input string

        if (stringInput.indexOf("//") == 0) {
            String[] components = stringInput.split("\\n");
            delimiter = extractDelimiter(components[0].substring(2).trim());
            stringNumber = components[1].trim();
        }

        // Tokenise the number string
        String[] numbers = stringNumber.split(delimiter);

        // gather negative numbers into a collection
        List<Integer> negative = Stream.of(numbers)
                .filter(s -> !s.trim().isEmpty())
                .mapToInt(Integer::parseInt)
                .filter(p -> p < 0)
                .boxed()
                .toList();

        // exit if unhappy path condition is met
        if (!negative.isEmpty()) {
            var negativeString = negative.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));

            throw new IllegalArgumentException("Negatives not allowed: " + negativeString);
        }

        // filter out non-numbers and negative numbers then find the total
        return Arrays.stream(numbers)
                .filter(s -> !s.trim().isEmpty())
                .map(Integer::parseInt)
                .filter(n -> n > 0 && n <= 1000)
                .reduce(0,Integer::sum);
    }

    private static String extractDelimiter(String component) {
        // A non-greedy pattern: look for enclosing character -i.e. square bracket, start capture, 
        // look for at least one non-], end capture, look for closing square bracket.
        Pattern pattern = Pattern.compile("\\[([^]]+)");
        Matcher matcher = pattern.matcher(component);

        StringBuilder delimiter = new StringBuilder();

        delimiter.append("[");
        while (matcher.find()) {
            delimiter.append(matcher.group(1));
        }
        delimiter.append("]");

        return delimiter.length() == 2 ? component : delimiter.toString();
    }
}
