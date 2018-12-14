# Billsmanager

Application created in Spring Boot with Thymeleaf as server-side rendering engine.
Contains two sites:
* Mainpage - to list all documents and upload new one.
* Edit page - to maintain already uploaded files.

Css styling: www.materializecss.com

## Instalation
* git clone git@git.aleri.de:AnetaSkoniecka/billsmanager.git folder-name
* IntelliJ -> Import Project -> Select folder-name -> Pick "Import project from external model" and Maven -> Next
* In the next window pick "Import Maven project automaticly" -> Next
* Should be already selected "de.aleri:bills-manager...." -> Next
* Add SDK Java 1.8 -> Next -> Finish
* Wait until maven import all dependencies from POM.xml
* Build the project
* On the right side open "Maven Projects"
* In the tree run bills-manager->Plugins->spring-boot->spring-boot:run
* Open page http://localhost:8080

## Switch database
Right now the application is handled by H2 database (in memory) to easy deploy the application. For production purspose database should be switched for e.g. MySql.

### MySql
* Go to file pom.xml and uncomment dependency mysql and comment dependency h2database (lines 48-59).
* Create and start Your mysql database.
* Put credentials in the file src/main/resources/application.properties (lines 5-9)

## Ap view:
![appview](/uploads/89f63f4c90c5e54f405cba7e186981a7/appview.PNG)
