<h2> TTU caterign </h2>

<h3> Hajusarhitektuurid & Tarkvara kvaliteeet ja standardi aine projekt </h3>
=========================


Template spring 3 rest api app with JPA and annotation based configuration 

<h3> Ettevalmistamien </h3>
======

käiviatada create_db.sql   @localhost mysql
Paigalada JDK7, tomcat7 bin ja maven M2HOME keskkonna muutujaga.


<h3> Ehitamine </h3>
======

./tks/mvn clean package -DskipTests=true


<h3> Käivitamine </h3>
======

deplay WAR to webapps & restart tomcat container

 
<h3> Ühiktestimine </h3>
=======

mvn test -Ptest



