[TOC]

# MoodFlip #

MoodFlip is a self-help web site consisting of a card game coupled with a built-in social media website with the goal of treating mild-moderate forms of depression.  

Features:

* users are assigned cards containing daily missions which they complete for points

* online community via user homepage, forum and instant messaging

* surveys measure depression level and help users better understand their condition

* surveys collecting data about a user's mood, sleep and exercise patterns rendered in charts and reports

* voting system in the forum allows users to vote up/down posts

* users can suggest cards to the original poster which can also be voted up/down. 

* privacy policy  to restrict the type of content that can be viewed

## What is this repository for? ##

* Spring/Hibernate source code 
* 1.0

## How do I get set up? ##

* Install Spring Tool Suite
* Clone this repo into a dir
* Import project into STS
* In STS, right click root dir in package explorer view, go to Maven > update project > ok.  Start exploring code.
* Project uses HSQLDB.  For config settings see: 
    * /src/main/webapp/WEB-INF/spring/appServlet/persistence-context.xml 
    * /src/main/resources/database.properties
* Sample data is preloaded from /src/main/resources/db.
  * card_game_data.txt: sample cards for testing.  The data is parsed by custom tags like 123title123 that represent the card title.  
  * populateDB.sql: depression assessment question data and forum categories
  * parser for card game data and user creation is in /src/main/java/au/moodflip/service/InitDbService.java.  2 users created: user and admin.  
    * User has USER_ROLE and admin has USER_ROLE + ADMIN_ROLE for authorisation.  
    * login/password: user/user or admin/admin
* Spring config is in /src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml
* Tests are in src/test/java.  DB config is separate from main project and is stored in src/test/resources.  Read wiki for more info.  
* Deployment instructions: start server in STS and launch localhost:8080/moodflip/

## Who do I talk to? ##

The team:

* Farhana - rafsin09

* Raphassit - raphassitlee

* Runa - runa_alam

* Mark - exevangel

* Tim - tht123