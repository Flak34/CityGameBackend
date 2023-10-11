package com.example.citygamebackend;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@Log4j2
public class CitiesService {
    private final HashSet<String> cities = new HashSet<>();

    public CitiesService(@Value("${cities.file-name}") String citiesFileName){
        try(FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/" + citiesFileName)){
            String[] readCities = new String(file.readAllBytes(), StandardCharsets.UTF_8).split("\n");
            for(String city: readCities){
                cities.add(city.substring(1, city.length() - 2).toLowerCase().trim());
            }
        }catch(IOException e){
            log.info(e.getMessage());
        }
    }

    private static boolean isLettersEqualRU(char l1, char l2){
        return l1 == l2 || (l1 == 'и' || l1 == 'й') && (l2 == 'и' || l2 == 'й') ||
                (l1 == 'е' || l1 == 'ё') && (l2 == 'е' || l2 == 'ё');
    }

    public boolean isCityPairValid(String c1, String c2){
        c1 = c1.toLowerCase();
        c2 = c2.toLowerCase();
        log.info(c1 + " " + c2);
        char c2Last = c2.charAt(c2.length() - 1), c1First = c1.charAt(0);
        log.info("c2Last " + c2Last + " c1First " + c1First);
        if((c2Last == 'ъ' || c2Last == 'ь') && c2.length() > 1)
            c2Last = c2.charAt(c2.length() - 2);
        return isLettersEqualRU(c1First, c2Last);
    }

    public boolean isCityExist(String city){
        city = city.toLowerCase();
        return cities.contains(city);
    }
}
