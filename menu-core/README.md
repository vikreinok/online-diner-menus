<h1> TTU caterign </h1>

Hajusarhitektuurid & Tarkvara kvaliteeet ja standardi aine projekt
=========================


Template spring 3 rest api app with JPA and annotation based configuration

Ettevalmistamien
======

k�iviatada create_db.sql   @localhost mysql
Paigalada JDK7, tomcat7 bin ja maven M2HOME keskkonna muutujaga.


Ehitamine
======

./tks/mvn clean package -DskipTests=true


K�ivitamine
======

deplay WAR to webapps & restart tomcat container


�hiktestimine
=======

mvn test -Ptest



