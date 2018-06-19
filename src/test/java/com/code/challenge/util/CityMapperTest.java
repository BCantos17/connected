package com.code.challenge.util;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CityMapperTest {

    private static Map<String, Set<String>> actualValue;
    private static Map<String, Set<String>> expectedValue;

    @BeforeClass
    public static void setup() throws IOException {
        File initialFile = new File("src/test/resources/assets/cities.text");
        InputStream stream = new FileInputStream(initialFile);
        actualValue = CityMapper.map(stream);
        expectedValue = new HashMap<>();

        Set<String> firstSet = new HashSet<>();
        Set<String> secondSet = new HashSet<>();
        Set<String> thirdSet = new HashSet<>();
        Set<String> fourthSet = new HashSet<>();
        Set<String> fifthSet = new HashSet<>();
        Set<String> sixthSet = new HashSet<>();

        firstSet.add("Boston");
        secondSet.add("Philadelphia");
        secondSet.add("Boston");
        thirdSet.add("Albany");
        fourthSet.add("Newark");
        fifthSet.add("New York");
        fifthSet.add("Newark");
        sixthSet.add("Trenton");

        expectedValue.put("New York", firstSet);
        expectedValue.put("Newark", secondSet);
        expectedValue.put("Trenton", thirdSet);
        expectedValue.put("Philadelphia", fourthSet);
        expectedValue.put("Boston", fifthSet);
        expectedValue.put("Albany", sixthSet);
    }

    @Test
    public void mapTest(){
        assertThat(actualValue).isNotNull();
        assertThat(actualValue).isNotEmpty();
        assertThat(expectedValue).isEqualTo(actualValue);
    }
}
