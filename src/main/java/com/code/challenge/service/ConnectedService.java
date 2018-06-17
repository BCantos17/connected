package com.code.challenge.service;

import com.code.challenge.util.CityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
@Slf4j
public class ConnectedService {

    private Map<String, Set<String>> connectedCities;
    private ResourceLoader resourceLoader;
    private String filePath;

    @Autowired
    public ConnectedService(ResourceLoader resourceLoader,
                            @Value("${cities.file}") String filePath) {
        this.resourceLoader = resourceLoader;
        this.filePath = filePath;
    }

    @PostConstruct
    public void init(){
        Resource resource = resourceLoader.getResource(filePath);
        log.info("Loading cities from cities.text file");
        try {
            InputStream stream = resource.getInputStream();
            connectedCities = CityMapper.map(stream);
        } catch (IOException e) {
            log.error("File not found", e);
            e.printStackTrace();
        }
    }


    public Boolean isConnected(String firstCity, String secondCity){
        Boolean isConnected = false;

        if(connectedCities.containsKey(firstCity) && connectedCities.containsKey(secondCity)){
            Queue<String> citiesToVisit = new LinkedList<>();
            Set<String> citiesAlreadyVisited = new HashSet<>();
            citiesToVisit.add(firstCity);

            while (!citiesToVisit.isEmpty() && !isConnected) {
                String city = citiesToVisit.poll();
                isConnected = city.equals(secondCity);
                Set<String> possibleConnections = connectedCities.get(city);

                for (String possibleCity : possibleConnections) {
                    if (!citiesAlreadyVisited.contains(possibleCity)) {
                        citiesToVisit.add(possibleCity);
                        citiesAlreadyVisited.add(possibleCity);
                    }
                }
            }
        }
        return isConnected;
    }

}
