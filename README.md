# Pat2019_Krzysztof_Pawlak

requirements: Java 11

checked on: IntelliJ 2018.3.2, gradle 5.1

# Build
in repository directory

```javascript
gradle tasks
gradle build
```
# Run
```javascript
gradle run
```

```javascript
you can specify your gradle jvm version by puting in {$your_java_path} path to dir with java 10
gradle run -Dorg.gradle.java.home={$your_java_path}
example: gradle run -Dorg.gradle.java.home=/opt/jdk-10.0.1
```

## cURL

### Important: You should make organization and boardroom before reservation!!! 
- Boardroom require existing organization.
- Reservation require existing boardroom.

Add new organization
```javascript
curl -H "Content-Type: application/json" -X POST -d "{\"name\":\"foo\"}" http://localhost:8080/organizations	
```

Add new boardroom
```javascript
curl -H "Content-Type: application/json" -X POST -d "{\"name\":\"salka example\",\"id\":\"1.33\",\"organizationName\":\"foo\",\"level\":0,\"available\":true,\"seats\":10,\"standingPlaces\":10,\"sunbeds\":10,\"hammocks\":10,\"equipment\":{\"id\":\"88089ae1-7a58-4145-b072-742cdb56b20a\", \"projectorName\":\"foo\",\"phoneAvailable\":true,\"phone\":{\"id\":\"858a7536-3d00-4ff4-803f-7e1cc98b2ed7\", \"internalNumber\":99,\"externalNumber\":\"+12 123456789\",\"phoneInterface\":\"USB\"}}}" http://localhost:8080/boardrooms
```

Add new reservation
```javascript
curl -H "Content-Type: application/json" -X POST -d "{\"id\":\"foo\",\"boardroomName\":\"salka example\",\"reservationFrom\":\"2019-01-19T17:14:39\",\"reservationTo\":\"2019-01-19T18:14:39\"}" http://localhost:8080/reservations
```
