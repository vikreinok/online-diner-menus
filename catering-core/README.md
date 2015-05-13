<h2> TTU dining </h2>

<h3> Technology stack </h3>
=========================

Spring 4 core, rest, data, security, ...

Features:
- Seperate datasource for unittest and live configuration.
- For unittests H2 in memory database with init testdata script configuation.
- Live data init scipt (import.sql)
- Token based authentication.

<h3> Ettevalmistamine</h3>
======

Paigalada JDK7, tomcat7 ja maven
Käiviatada create_db.sql   @localhost mysql  kontrolli baasi kasutajanime ja prooli @ live_db.properties

<h3> Ehitamine </h3>
======

./tks/catering-core mvn clean package -DskipTests=true


<h3> Käivitamine </h3>
======

deplay WAR to webapps & restart tomcat container

maven war plugin automatically moves war to C:\tomcat\webapps

http://localhost:8080/catering-core/menu/




