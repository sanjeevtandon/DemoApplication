Requirement:
As per the understanding from the requirement a web gui is required where a user can input any random number and the following set of results are excepted:
1) Gui should display the numbers entered in sorted order(ascending)
2) Gui should display the time taken to sort the numbers
3) Gui should display the number of positions changed to re-arrange the numbers in sorting order(ascending).
4) If the user restart the application the last result should be displayed on the screen.

Technologies used to complete this requirement:
1) Java 1.8
2) Angular JS
3) CSS
4) JSP
5) Spring Boot 1.4.2
6) Maven

How to make the application up:
1) After running maven build install, spring-boot-web-jsp-1.0.jar will be created in the target folder, uploading the jar also, but it should not be the part of maven build and install.
2) Execute command java -jar ./spring-boot-web-jsp-1.0.jar, if port is already in use then run command 'netstat -ano' and after that to terminate process at 8080 port 'taskkill -pid <pid> /f', or change port of application by overriding the property 'server.port=<new port number>' in application.properties
3) Open the browser and type url localhost:8080 and press enter
4) In the text box provided enter the number user want to sort, and press 'Add Number' button.
5) Following result will be displayed:
	a) Time taken to sort the numbers in milliseconds.
	b) Number of re-arrangements happened, if new number is added which is assumed to be at the end of the sorted number list then it should return 0, because this number is new and no re-arragmenents are done as compared to previous result, if new number is assumed to be in between existing numbers list or at the top then result should be greater than 0.
6) After desired set of entries restart the application.
7) Open the browser and type url localhost:8080 and press enter
8) The last result i.e. the sorted number list, time taken to sort and number of re-arrangements will be shown on gui. Here to make it light i am storing the result data in a property file i.e. data.properties, we can store it in other storage mediums also like DB etc.

