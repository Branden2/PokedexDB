-- ---------------------------------------------------------------------------
-- Professor Oak's Pokedex Database
-- ---------------------------------------------------------------------------

Professor Oak's Pokedex Database is a GUI application that allows users
to modify and view various entries in a Pokedex database, including
species of pokemon, trainers, attacks, evolutions and more. The application
is written in Java and retrieves all of the data from a MySQL database.

-- ---------------------------------------------------------------------------
--  Getting Started
-- ---------------------------------------------------------------------------

You Will Need
------------------------------------------------------------------------------
    + The most recent version of Java Development Kit
    + A Java IDE to run the files from
    + A local instance of a MySQL Server

Installation
------------------------------------------------------------------------------
  
  1. Open a local instance of a MySQL server (this can be done easily with
     MySQL Workbench) and run the provided Pokedex.sql script. This populates
     the database with data that the GUI will use.
     
  2. Open the source folder in an IDE of your choice and point to the 
     mysql-connector-java-5.1.46.jar located in src/libs file as a dependency.
     This allows the program to use the JDBC conncector to connect
     to the database.
     
  3. Run GUI.java to start the program.
     
-- ---------------------------------------------------------------------------  
-- Built With
-- ---------------------------------------------------------------------------

+ Java JDK 1.8.0_161 with JavaFX 8
    - Language used to create front end application and GUI.
        ~ https://java.com/en/download/

+ IntelliJ IDEA Community Edition 2017.3
    - Java IDE used to develop front end application and GUI.
        ~ https://www.jetbrains.com/idea/

+ MySQL Workbench 6.3.10 CE
    - Offical GUI for MySQL used to create the database.
        ~ https://www.mysql.com/products/workbench/

+ MySQL Connector/J 5.1.46
    - JDBC Driver used to connect the application to the database.
        ~ https://dev.mysql.com/downloads/connector/j/

-- --------------------------------------------------------------------------
-- Authors
-- --------------------------------------------------------------------------

Developed By:

+ Bailey Weir - Front End Developer
+ Branden Rice - Back End Developer
+ Brandon Patterson - QA Analyst
+ Stephen Karavos - Database Design

-- -------------------------------------------------------------------------
-- License
-- -------------------------------------------------------------------------

See License.txt
