# Pat2019_Krzysztof_Pawlak

requirements: Java 10

# Build
in repository directory
```javascript
mvn clean install
```
# Run
```javascript
java -jar target/Pat2019_Krzysztof_Pawlak-0.0.1-SNAPSHOT.jar
```

# Add new boardroom by using cURL
```javascript
curl -H "Content-Type: application/json" -X POST -d '{"name":"salka example","id":"1.33","organizationName":"foo","level":0,"available":true,"seats":10,"standingPlaces":10,"sunbeds":10,"hammocks":10,"equipment":{"projectorName":"foo","phoneAvailable":true,"phone":{"internalNumber":99,"externalNumber":"+12 123456789","phoneInterface":"USB"}}}' http://localhost:8080/boardrooms
```
