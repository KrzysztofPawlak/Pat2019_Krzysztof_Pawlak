# Pat2019_Krzysztof_Pawlak

requirements: Java 10

# Build
in repository directory

```javascript
put in {$your_java_path} your path to dir with java 10
example: gradle run -Dorg.gradle.java.home=/opt/jdk-10.0.1
```
```javascript
gradle tasks -Dorg.gradle.java.home={$your_java_path}
gradle build -Dorg.gradle.java.home={$your_java_path}
```
# Run
```javascript
gradle run -Dorg.gradle.java.home={$your_java_path}
```

# Add new boardroom by using cURL
```javascript
curl -H "Content-Type: application/json" -X POST -d '{"name":"salka example","id":"1.33","organizationName":"foo","level":0,"available":true,"seats":10,"standingPlaces":10,"sunbeds":10,"hammocks":10,"equipment":{"projectorName":"foo","phoneAvailable":true,"phone":{"internalNumber":99,"externalNumber":"+12 123456789","phoneInterface":"USB"}}}' http://localhost:8080/boardrooms
```
