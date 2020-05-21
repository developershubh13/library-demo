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
            /books/add - to add a book
            /books/getAll - to read all books
            /books/getById - to read book with given Id
            /books/getByTitle -to read book with given title
            /books/getByAuthor - to read book with given author
            /books/deleteById/{id} - to delete a book with given id
           
          Students API
            /students/add - to add a student
            /students/getAllStudents - to read all the students
            /students/getStudentById/{id} - to read a student with a given Id(studentID)
            /studnets/deleteById/{id} - to delete a student with a given id(studentID)
            /students/update/{id} - to pdate a student with a given id(studentID)
            
            /students/getAllBooks - to read all books
            /student/getBookById/{id} - to read book with given id(bookID)
            /students/getBookByTitle/{title} -ro read a book with a given title
            /students/getBookByAuthor/{author} -to get a book with a given author
            /students/getBooksWithStudent/{studentId} -given a studentID search all books issued by that student
            /students/issueBookById/{bookID}/{studentID} -issue a book with given bookID by a student with given studentID
         
            
            
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
