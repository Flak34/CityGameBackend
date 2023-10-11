package com.example.citygamebackend;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
@Log4j2
public class CitiesService {
    private final HashSet<String> cities = new HashSet<>();

    public CitiesService(@Value("${cities.file-name}") String citiesFileName){
        log.info(System.getProperty("user.dir") + "/" + citiesFileName);
        try(FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/" + citiesFileName)){
            String[] readCities = new String(file.readAllBytes()).split("\n");
            for(String city: readCities){
                cities.add(city.substring(1, city.length() - 2).toLowerCase().trim());
            }
        }catch(IOException e){
            log.info(e.getMessage());
        }
    }

    private static boolean isLettersEqualRU(char l1, char l2){
        return false;
    }

    public boolean isCityPairValid(String c1, String c2){
        return false;
    }

    public boolean isCityExist(String city){
        return false;
    }
}
