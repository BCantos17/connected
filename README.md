# Connected

By [Bryan Cantos](bcantos17@gmail.com)

## Instructions

1. Navigate to [repo](https://github.com/bcantos17/connected)
2. Clone locally using
   `git clone git@github.com:bcantos17/connected.git`
3. Build a jar file using `mvn clean install`
4. Start your server using `java -jar target/challenge-0.0.1-SNAPSHOT.jar`
    (Note: if using an IDE such as eclipse or intellij, running the application as is
    should work.)
5. Either go to your browser or your choice of application to test API endpoints
    and use this URL localhost:8080/connected?origin=city1&destination=city2
6. Change city 1 and city 2 to the cities of your choice.
    List of cities can be found and modified in src/main/resources/assets
    (Note: if you want to modify the list make sure to only have two cities per line and must be
    separated by a comma.)
7. See if your cities are connected!


## Discussion

I used the following technologies: Java, Spring MVC, Spring Boot.
I built this application using Java 10 but will work on Java 7 and above.

## Requirements

#### Write a program in Java which determines if two cities are connected.
#### Two cities are considered connected if there’s a series of roads that
#### can be traveled from one city to another.

Call the endpoint /connected with the parameters origin and destination.
Will return a response value of true or false.