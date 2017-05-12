Hej Alla,

Requirement Understanding:
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

How to setup the application and test:
1) After running maven build install, spring-boot-web-jsp-1.0.war will be created in the target folder, here I am uploading the generated war also, but it should not be the part of maven build and install.
2) Execute command java -jar target/spring-boot-web-jsp-1.0.war, if port is already in use then run command 'netstat -ano' and after that to terminate process at 8080 port 'taskkill -pid <pid> /f', or change port of application by overriding the property 'server.port=<new port number>' in application.properties
3) Open the browser and type url localhost:8080 and press enter
4) In the text box provided enter the number user want to sort, and press 'Add Number' button.
5) Following result will be displayed:
	a) Time taken to sort the numbers in nanoseconds: 
	Logic behind the result: The raw data is stored in an arraylist, to sort the data here I have used Treeset, this time is the time taken by treeset to sort the data.
	b) Number of re-arrangements happened, if new number is added which is assumed to be at the end of the sorted number list then it should return 0, because this number is new and no re-arragmenents are done as compared to previous result, if new number is assumed to be in between existing numbers list or at the top then result should be greater than 0.
	Logic behind the result: Here first the existing data is stored in an arraylist and after that new arraylist(old data + new added number) gets sorted with treeset, now the old and new arraylist(sorted) is compared for data at each position, if data doesn't match at that position it means re-arrangement happened, and the count of re-arrangement is calculated.
	c) Sorted Number List: 
	Logic behind the result: Treeset has been used to sort the data.
	d) If there is no exception then success message i.e. 'Added Successfully...' is shown on the screenby applying green color css.
6) After desired set of entries restart the application.
7) Open the browser and type url localhost:8080 and press enter
8) The last result i.e. the sorted number list, time taken to sort and number of re-arrangements will be shown on gui. Here to make it light weight i am storing the result data in a property file i.e. data.properties, we can store it in other storage mediums also like DB etc. Once the user type the url in browser and presses enter the data.properties file will gets created(if does not exist) in the folder where .war is present. Please use the login credentials which has rights to create and edit files.
Logic behind the result: At the time of controller loading, data.properties is read for the data. When the user submits new data it is again over-written into data.properties file. Data stored in data.properties will be in the following form:

timetakentosort=71253
numbers=2;12;34;45;654
numberofposchange=1

9) Exception handling and validations: 
	a) Server Side: If there is any exception at the time of processing then that exception is catched and exception's message will be send back to web gui and shown on the screen by applying red color css.
	b) Client Side: Only number is allowed in the text box and its mandatory.


Tack och Hälsningar

