# MoodFlip

MoodFlip is a self-help web site consisting of a card game coupled with a built-in social media website with the goal of treating mild-moderate forms of depression.  

## Components

1. Card game: users are assigned cards containing daily missions which they complete for points

2. Communication: forum, instant messaging and notification system

3. User home page: status updates, activity log, self assessment

4. Mood tracking: user mood pattern log, chart and report

5. Personalisation: user account policy, customisation, privacy and moderation

## What is this repository for?

* Spring/Hibernate source code 
* 1.0

## How do I get set up?

* Install Spring Tool Suite
* Clone this repo into a dir
* Import project into STS
* In STS, right click root dir in package explorer view, go to Maven > update project > ok.  Start exploring code.
* Project uses HSQLDB.  For config settings see: 
    * /src/main/webapp/WEB-INF/spring/appServlet/persistence-context.xml 
    * /src/main/resources/database.properties
* Sample data for depression assessment results, mood tracking log, card data, user, playlist is preloaded from /src/main/resources/db.
  * card_game_data.txt: sample card data for testing.  The data is parsed by custom tags like 123title123 that represent the card title.  
  * populateDB.sql: depression assessment question data and forum categories
  * parser for card game data, user creation, playlist, mood tracking data and depression assessment results is in /src/main/java/au/moodflip/service/InitDbService.java.  2 users created: user and admin.  
    * User has USER_ROLE and admin has USER_ROLE + ADMIN_ROLE for authorisation.  
    * login/password: user/user or admin/admin
* Spring config is in /src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml
* Tests are in src/test/java.  DB config is separate from main project and is stored in src/test/resources.  Read wiki for more info.  
* Deployment instructions: start server in STS and launch localhost:8080/moodflip/

## Deploying Moodflip to cloud

[Pivotal Web Services](https://run.pivotal.io/) (PWS) is the reference implementation of open source [Cloud Foundry](https://www.cloudfoundry.org/) (CF) and a commercial service with trial provision. PWS and CF is developed by [Pivotal](https://pivotal.io/), the company behind Spring Framework. 

Instructions for deploying moodflip to PWS:
1. Register fora account [here](https://run.pivotal.io/).
2. Download and install cf command line tool from [here](https://docs.run.pivotal.io/cf-cli/install-go-cli.html)
3. Goto your local moodflip project directory.
4. Run in the terminal: `cf api api.run.pivotal.io`
5. Run: `cf login` and authenticate with your username and password.
6. Run: `mvn clean package` to get the war file in the target directory.
7. Run: `cf push moodflip ‐p target/moodflip‐1.0.0‐BUILD‐SNAPSHOT.war`
8. Browse to the url mentioned in the success message.In the above case it is:http://moodflip.cfapps.io
9. You can also use the Eclipse/STS plugin for cloudfoundry if you want.
