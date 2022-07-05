package com.mjc.stage0;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.readAllLines;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstApplicationTest {
    private static Stream<?> getFileLines(Path sourcePath) {
        try {
            return readAllLines(sourcePath, StandardCharsets.UTF_8).stream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Stream.empty();
    }

    @Test
    public void dummyTest() throws IOException {
        String result = Files.walk(Paths.get("src/main/java/com/mjc/stage0"))
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".java"))
                .flatMap(FirstApplicationTest::getFileLines)
                .map(Object::toString)
                .collect(Collectors.joining("\n"));

        List<String> requiredElements = List.of("System.out.println", "Hello, World!");
        requiredElements.forEach(el ->
                assertTrue(result.contains(el), String.format("'%s' should be used", el))
        );
    }
}
