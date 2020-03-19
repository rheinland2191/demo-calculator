# Demo Calculator
Backend using springboot, Frontend using Angular

### PREREQ BACKEND ###

* Install Java 1.8
* Install Maven 3
* Install MySQL

### PREREQ FRONTEND ###

* Install [Node](https://nodejs.org/en/download/)
* Install [Angular-cli](https://www.npmjs.com/package/@angular/cli)

### HOW TO ###
* For Backend after install maven, running go to path calculator, then running mvn spring-boot:run
* For Frontend after install node & angular cli, go to path calculator-frontend, then running npm install. After finish run ng serve.

### Notes ###
1. Springboot will automatically create the database on run. (If not existed previously)
2. Please import 'car.sql' file to 'calculator' database to add some cars data.
3. Do Note that in [calculator-frontend](https://github.com/rheinland2191/demo-calculator/tree/master/calculator-frontend) node modules are not included therefore please run 'npm install' before 'ng serve'
4. For Backend Documentation you can check at [localhost:8080/swagger-ui.html](localhost:8080/swagger-ui.html) after finish running backend server.
