# Product-service
                 
 ## Basic Information
 - A Restful web service, that reads Products from a XML-file and prices from JSON file then merge the result and Stores it in-memory.
 - The path of the XML and JSON files are configurable from the application.properties file.
 - Files are loaded automatically when the application is run.
 
 ## Technology Stack
 - Spring 5 Framework
 - Spring Boot 2.4.1 
 - Java 11
 - JUnit and SpringBootTest are used for Testing.
     
 ## Install
    mvn clean install
    
 ## Run the app
    java -jar target/product-service.jar

 ## Available Endpoints
 - /getAllProducts : returns a List of all the products listed in the loaded XML file in JSON format.
 - /getSingleProduct/{sku} : returns the product with the selected sku if present.
 - /getPriceOfProduct/{sku}/{unit} : returns the price of the selected product with the selected unit.