# FinalProject (Roll The Dice)

Live version deployed on an AWS server can be found here.

![HomePage](https://drive.google.com/file/d/1Dx7HYNTVXgTxJRdDonkgs-nR6Xx675d1/view?usp=sharing)
![ProfilePage](https://drive.google.com/file/d/1SUXeoTS75i5Z06Pr2fb53V0cYyREiqLl/view?usp=sharing)
![EventDisplay](https://drive.google.com/file/d/1kBMo9NETpcckS1JvjGj0vEvCj8Egm7tk/view?usp=sharing)
![EventCommentSection](https://drive.google.com/file/d/1QJCMgnBWXMXLjNVOV6okyoIdJMLeix2Z/view?usp=sharing)
![AdvancedSearch](https://drive.google.com/file/d/1Mxw5yyR5dWRCXsEd0PclU9bomrwFKd7w/view?usp=sharing)

## Overview



This was a team project that developed a full-stack web application, built over 7 days. The website was developed by four individuals remotely using Zoom, Slack, Trello, Git, and Github for collaboration.

Roll the Dice is a board game meet up organizer, that allows users to create and manage meet ups(events) with their friends online. Without logging in, the user has access to limited features on the website, which includes viewing the home page, about us page, and the login/register page. If someone does not have an account, they can register a new one. During account creation, the users password is encrypted when stored in the database. If the user already has an account they can directly log in. Once a user is logged in, they have access to more features. Firstly, they are able to edit their profile page such as adding a profile picture or a description about themselves. From this page, they are able to see all of their friends, games, and meet ups. From a users friend list, they are able to click on a friend to view their profile page. Other features for the user including creating new events and games. Once an event is created, other users are able to see and join this event. On the display of the meet up, users and see relevant information to the event such as address, date, other guests and more. There is also a comment section for each event for users to discuss the up coming meet up. Users are also able to use the search bar at the top to look for users, games, or events. When clicked, the user is directed to the results as well as the option for a more specific search.


## File Structure
[Entities](https://github.com/A-Shumway42/FinalProject/tree/main/JPARollTheDice/src/main/java/com/skilldistillery/rollthedice/entities)
<br>
[Controllers](https://github.com/A-Shumway42/FinalProject/tree/main/RollTheDice/src/main/java/com/skilldistillery/rollthedice/controllers)
<br>
[Repositories](https://github.com/A-Shumway42/FinalProject/tree/main/RollTheDice/src/main/java/com/skilldistillery/rollthedice/repositories)
<br>
[Services](https://github.com/A-Shumway42/FinalProject/tree/main/RollTheDice/src/main/java/com/skilldistillery/rollthedice/services)
<br>
[Angular Components](https://github.com/A-Shumway42/FinalProject/tree/main/ngRollTheDice/src/app/components)
<br>
[Angular Models](https://github.com/A-Shumway42/FinalProject/tree/main/ngRollTheDice/src/app/models)
<br>
[Angular Services](https://github.com/A-Shumway42/FinalProject/tree/main/ngRollTheDice/src/app/services)
<br>

## Features
* Account creation
* Basic auth security
* User permissions
* Basic and advanced searches
* Create and update games, meet ups, and addresses
* Friend list
* Comments section

## Technologies
* Java 1.8
* JUnit 5
* Spring Boot
* Spring Tool Suite
* Apache Tomcat Server
* HTML
* CSS
* MAMP
* SQL (MySQL)
* Git terminal
* MAC OS
* Bootstrap 3
* Google
* Github
* Java Persistence API & Hibernate
* REST
* MySQL Workbench
* Gradle
* Trello
* Amazon Web Services
* Chrome Developer Tools

## What We Learned
### Angular
This was one of our first opportunities to practice with the Angular framework and we learned a lot through the process. One of the features we found most useful was using ngModels. This allowed us to dynamically perform CRUD operations and display our data in unique ways. Although there is still much to learn about Angular, this project greatly improved our understanding of angular and provides us the foundation to learn more.

### Basic Auth security
Similar to angular, this was one of our first opportunities to practice website security (Basic Auth). This includes configuring the SecurityConfig to allow certain access to non-authenticated users, using authServices to confirm users authentication, and to encrypt passwords in the database. Security is a complex subject that we hope to learn more about in the future as it provides a very valuable service to your website.

## Project Owners
[Andrew Shumway (Repository Owner)](https://github.com/A-Shumway42)
<br>
[Ian Verderame (Scrum Master)](https://github.com/ianverderame)
<br>
[Max McGregor(DBA)](https://github.com/maxmcgregor)
<br>
[Jack Himes](https://github.com/JackHimes)
<br>

## Future Directions For The FinalProject
* Find game meet ups by location
* Improve UX/UI
* Add new features for users to interact with each other

## Database Schema
![Schema](https://drive.google.com/file/d/1eX7sNsdW7fmlcT8eXCpseKlzfCzLjYxV/view?usp=sharing)
