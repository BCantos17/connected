package com.code.challenge.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CityMapper {
    private static final String DELIMITER = ",";

    public static Map<String, Set<String>> map(InputStream stream) throws IOException {
        BufferedReader reader =  new BufferedReader(new InputStreamReader(stream));
        Map<String, Set<String>> connectedCities = new HashMap<>();
        String line;

        while((line = reader.readLine()) != null){
            String[] cities = line.split(DELIMITER);
            String firstCity = cities[0].trim();
            String secondCity = cities[1].trim();

            if(!connectedCities.containsKey(firstCity))
                connectedCities.put(firstCity, new HashSet<>());

            if(!connectedCities.containsKey(secondCity))
                connectedCities.put(secondCity, new HashSet<>());

            connectedCities.get(firstCity).add(secondCity);
            connectedCities.get(secondCity).add(firstCity);
        }

        return connectedCities;
    }
}
