package com.musinsa.core.runner;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ResourceReader {
    public static List<String> read(String csvFile) {

        ClassPathResource resource = new ClassPathResource(csvFile);

        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"))){
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException("file error",e);
        }
        return lines;
    }
}
