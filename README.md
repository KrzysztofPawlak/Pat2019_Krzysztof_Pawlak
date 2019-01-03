# Pat2019_Krzysztof_Pawlak

requirements: Java 10

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

# Add new boardroom by using cURL
```javascript
curl -H "Content-Type: application/json" -X POST -d '{"name":"salka example","id":"1.33","organizationName":"foo","level":0,"available":true,"seats":10,"standingPlaces":10,"sunbeds":10,"hammocks":10,"equipment":{"projectorName":"foo","phoneAvailable":true,"phone":{"internalNumber":99,"externalNumber":"+12 123456789","phoneInterface":"USB"}}}' http://localhost:8080/boardrooms
```
