<h2> TTU caterign </h2>

<h3> Hajusarhitektuurid & Tarkvara kvaliteeet ja standardi ainete projekt </h3>
=========================

Template spring 3 rest api app with JPA and annotation based configuration.
Unittest and live seperate datasource configuration.
H2 in memory database with init testdata script configuation.

<h3> Ettevalmistamine</h3>
======

käiviatada create_db.sql   @localhost mysql
Paigalada JDK7, tomcat7 ja maven


<h3> Ehitamine </h3>
======

./tks/catering-core mvn clean package -DskipTests=true


<h3> Käivitamine </h3>
======

deplay WAR to webapps & restart tomcat container

maven war plugin automatically moves war to C:\tomcat\webapps

http://localhost:8080/catering-core/menu/
 
<h3> Ühiktestimine </h3>
=======

mvn test -Ptest



