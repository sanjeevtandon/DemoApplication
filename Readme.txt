Requirement:
As per the understanding from the requirement a web gui is required where a user can input any random number and the following set of results are excepted:
1) gui should display the numbers entered in sorted order(ascending)
2) gui should display the time taken to sort the numbers
3) gui should display the number of positions changed to re-arrange the numbers in sorting order(ascending).
4) if the user restart the application the last result should be displayed on the screen.

Technologies used to complete this requirement:
1) Java 1.8
2) Angular JS
3) CSS
4) JSP
5) Spring Boot 1.4.2
6) Maven

How to make the application up:
After running maven build install, spring-boot-web-jsp-1.0.jar will be created in the target folder, uploading the jar also, but it should not be the part maven build and install.
1) java -jar ./spring-boot-web-jsp-1.0.jar, if port is already in use then run command 'netstat -ano' and after that to terminate process at 8080 port 'taskkill -pid <pid> /f', or change port of application by overriding the property 'server.port=<new port number>' in application.properties
2) open the browser and type url localhost:8080 and press enter
3) in the text box provided enter the number user want to sort, and press 'Add Number' button.
4) following result will be displayed:
	a) Time taken to sort the numbers in milliseconds.
	b) Number of re-arrangements happened, if new number is added which is assumed to be at the end then it should return 0, because this number is new and no re-arragmenents are done as compared to previous result, if new number is assumed to be in between existing numbers then result should be greater than 0.
5) after desired set of entries restart the application.
6) open the browser and type url localhost:8080 and press enter
7) the last result i.e. the sorted number list, time taken to sort and number of re-arrangements will be shown on gui. Here to make it light i am storing the result data in a property file i.e. data.properties, we can store it in DB also.

