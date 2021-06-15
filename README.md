## Aubay DN Java Project

Back-end test assignment for the position of Java developer

## Technologies/Tools used:

* JDK 1.8.0_291
* Eclipse Version: 2020-12 (4.18.0)
* Apache Maven 3.6.3
* JUnit 4.13.2
* SonarLint 5.9
* EclEmma Java Code Coverage 3.1.3
* Checkstyle 8.42.0
* Windows 10 OS

## Building Instructions:

Run these commands:
```sh
 git clone https://git@github.com:americanolinhares/aubay-dn-project.git
 cd aubay-dn-project
 mvn clean package
```

## Running Instructions:

Execute this command from the root folder of the project:
```sh
 java -jar project-1.0.0.jar -input validInputlogfile -output outputJsonFileName
```
Example:
```sh
 java -jar target/project-1.0.0.jar -input server.log -output Report.json
```