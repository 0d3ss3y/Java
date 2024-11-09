package org.side.generatePassword;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PasswordGenerator {
    private static final List<String> SPECIAL_CHARACTERS = Collections.unmodifiableList(Arrays.asList(
            "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=",
            "{", "}", "[", "]", "|", "\\", ":", ";", "\"", "'", "<", ">", ",", ".",
            "?", "/", "`", "~"
    ));

    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARS = LOWERCASE_CHARS.toUpperCase();
    private static final String NUMBERS = "0123456789";

    private final SecureRandom secureRandom = new SecureRandom();

    public String generatePassword(int length, boolean useSpecialChars,
                                   boolean useCaseSensitive, boolean useNumbers) {
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be positive");
        }

        // Build character pool based on requirements
        StringBuilder charPool = new StringBuilder(LOWERCASE_CHARS);
        if (useCaseSensitive) {
            charPool.append(UPPERCASE_CHARS);
        }
        if (useSpecialChars) {
            SPECIAL_CHARACTERS.forEach(charPool::append);
        }
        if (useNumbers) {
            charPool.append(NUMBERS);
        }

        // Ensure at least one character of each required type
        List<Character> password = new ArrayList<>();
        if (useCaseSensitive) {
            password.add(LOWERCASE_CHARS.charAt(secureRandom.nextInt(LOWERCASE_CHARS.length())));
            password.add(UPPERCASE_CHARS.charAt(secureRandom.nextInt(UPPERCASE_CHARS.length())));
        }
        if (useSpecialChars) {
            password.add(SPECIAL_CHARACTERS.get(secureRandom.nextInt(SPECIAL_CHARACTERS.size())).charAt(0));
        }
        if (useNumbers) {
            password.add(NUMBERS.charAt(secureRandom.nextInt(NUMBERS.length())));
        }

        // Fill remaining length with random characters from pool
        while (password.size() < length) {
            password.add(charPool.charAt(secureRandom.nextInt(charPool.length())));
        }

        // Shuffle the password characters
        Collections.shuffle(password, secureRandom);

        return password.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}