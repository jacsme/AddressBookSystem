# AddressBookSystem

This project is developed using Java 1.8, Spring Boot, Junit and Mockito and Maven 3.3.9. Spring Boot Framework is implemented as it will give some advantages for the setup, we don't need to setup the application server because it has its built-in appserver. Follow the below instruction and email jack.lord.hermoso@gmail.com if you have any comments and questions. 

# Configuration
## Maven:	
	- Download and extract apache-maven-3.3.9 
	- Save it to c:\apache-maven-3.3.9

## Java:
	- Download Java 1.8 and save it to c:\Program Files\Java\jdk1.8.0_91
	
## Environment Variable:
	- Setup the System Variable with the following:
		-- Variable		| Value	
		-- CATALINA_HOME   | c:\apache-maven-3.3.9
		-- M2_HOME			| c:\apache-maven-3.3.9
		-- M2				| %M2_HOME%\bin
		-- JAVA_HOME		| c:\Program Files\Java\jdk1.8.0_91
		-- Path			| %JAVA_HOME%\bin; %M2%;  [add this at the end of the path value]

## Eclipse:
	- Download the latest Eclipse IDE with maven plugins [Oxygen]	
	- Clone this project to your local repository, and import the project to eclipse	- Refresh the project
	- Right click on the project, select properties. On the properties window, configure the Java Build Path point the JRE System Library to jdk1.8.0_91. 
	- And on the Java Compiler select the version 1.8 on the drop down box.
	- Make a clean and build to your local project in eclipse.

## Data:
	- Data should only be persisted in memory, using simple data structures using List
	
## Run the program:
	- Run the spring boot application, on your project right click on the AddressBookApplication located in the com.hermoso, Run As > Java Application
	- The system will be ran in http://localhost:8080/AddressBookSystem and all the api are predefined in my postman
	- Download the postman from Chrome which will be used for accessing the program for integration testing. Import the predefined collection located in src/main/resources [AddressBookURL.postman_collection.json] of the project AddressBookSystem.


# API URL
1. http://localhost:8080/v1/addnewcontact
  - This will be saved the records to List data structure from a json formatted requestbody
  - Request 
    - Headers: None
    - Body :
      ``` 
      {
		"firstName": "Jack Lord", 
		"lastName": "Hermoso", 
		"contactDetails": [{"phoneNumbers": "04218745165"}]
	  }
  - Response
    - Body :
        ``` 
        {
            "status": "SUCCESS",
            "message": "Contact is successfully added"
        } 
2. http://localhost:8080/AddressBookSystem/v1/removecontact/Jack Lord/Hermoso/04218745165
  - This will delete the records from the data structure
  - Request 
    - Headers: None
	- Response
    - Body :
        ``` 
        {
            "status": "SUCCESS",
            "message": "Contact is successfully removed"
        } 
 3. http://localhost:8080/AddressBookSystem/v1/addressbookalllist
  - This will retrieve the records from the data structure
  - Request 
    - Headers: None
    - Response
    - Body :
        ``` 
        [
			{
				"firstName": "Jack Lord",
				"lastName": "Hermoso",
				"contactDetails": [
					{
						"phoneNumbers": "04218745165"
					}
				]
			},
			{
				"firstName": "John",
				"lastName": "Hermoso",
				"contactDetails": [
					{
						"phoneNumbers": "04218745145"
					}
				]
			}
		]
	
 4. http://localhost:8080/AddressBookSystem/v1/addressbookuniquelist/Jack Lord/Hermoso
  - This will retrieve the records from the data structure to get the unique address book list
  - Request 
    - Headers:
    - Response
     Body :
     ```
        [
			{
				"firstName": "Jack Lord",
				"lastName": "Hermoso",
				"contactDetails": [
					{
						"phoneNumbers": "04218745165"
					}
				]
			}
		]
 
