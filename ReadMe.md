==============================
== Socle JavaEE application ==
==============================

------------------
** Requirements **
------------------
* JDK 1.8
* Apache Tomcat 8
* MySQL 5.7
* Maven 3
* Docker 1.10.3
* Docker-compose 1.7.0

-----------------
** Quick Start **
-----------------
Login: admin
Password: admin00
------------------------------
** How to build the project **
------------------------------
1- "mvn clean install"

---------------------------------------------
** How to run the project in Tomcat server **
---------------------------------------------
  1-	Create an empty database "db_socle"
  2-	Define your Datasource in your Tomcat Server at localhost-config/Context.xml like below :
	
<Resource name="jdbc/dataSource" auth="Container" type="javax.sql.DataSource" maxPoolSize="100" maxStatements="180" minPoolSize="5" username="root" password=""
driverClassName="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/db_access_control?useUnicode=yes&amp;characterEncoding=UTF-8" />
		
  3-	Add your project to tomcat then run the server
  
------------------------------------------------
** How to run the project in docker container **
------------------------------------------------
  1- VERSION=${POM_VERSION} docker-compose --project-name socle-javaee -f environnement_integration.yml up --build -d
