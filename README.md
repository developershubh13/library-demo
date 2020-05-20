# Library Demo Rest Api Using Spring Boot.

# How to start the project?
      1.Download the project to your system.
      2.Open the project in any IDE, IntelliJ or Eclipse.
      3.The pom.xml file contains all the dependencies which the IDE will sync automatically.
      4.You need to setup MongoDB in your system.
      5.Create a database 'library' in it.
      6.Go to src/main/java and under com.example.librarydemo package run LibraryDemoApplication.java file.
 
# Database Schema
      1. Books{
           bookId: Integer
           title: String
           author: String
           noOfCopies: Integer
           students: List<Integers>-----List containing Students Id 
           teachers: List<Integers>-----List comtaining Teachers Id   
         }
        
      2. Students{
           studentId: Integer
           studentName: String
           studentClass: String
           books: List[Integers]------List containng Books Id        
         }  
         
       
      3. Teachers{
           teacherId: Integer
           teacherName: String
           subject: String
           books: List[Integers]------List containng Books Id        
         }    
     
     
# API Endpoints
      BASE URL - http://localhost:port/api . port is configured in application.properties file (8090)

         Books API

            /event/create - to create new event
            /event/viewAll - to view all hosted events based on the roles of logged in member
            /event/view/{id} - to view a particular event
            /event/viewTrending - to view trending events
            /event/viewPopular - to view popular events
            /event/viewUpcoming - to view upcoming events
            /event/update/{id} - to update an event
            /event/delete/{id} - to delete an event
           
          Students API

            /member/create - to create a new member
            /member/view/{id} - to view a member
            /member/viewAll - to view all members in organization
            /member/update/{id} - to update a member
            
          Teachers API
            /team/create - create a new team
            /team/view/{id} - to view a team
            /team/viewAll - to view all teams
            /team/update/{id} - to update a team


# Push to build with Jenkins
      I have used Jenkins pipeline for automated builds. Whenever some new code is pushed onto the master branch Jenkins will pull the  
      commit and starts the build process. Maven is the choice of build tool.
      
# Docker Images and Docker-Compose
      I have containerized libray-demo rest api and for database i am using mongodb image. Then i have used a Docker-Compose file to run  
      library-demo(spring boot application) linked with mongodb container.
