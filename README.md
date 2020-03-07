# SlackAPIAutomation


This project has automated tests for Slack channel apis.

Test suite flow:
● Create a new Channel
● Join the newly created Channel
● Rename the Channel
● List all Channels and Validate if the Channel name has changed successfully
● Archive the Channel
● Validate if the Channel is archived successfully

### Modules

* Slack API
https://api.slack.com/methods


### Tech

This project uses a number of open source projects to work properly:

* Java
* TestNG
* Slf4j
* Lombok

### How to run this project

Checkout the project from gitlab using following command
```sh
$ git clone https://github.com/shirishameka/SlackAPIAutomation.git
```

Get in-to the project folder

```sh
$ cd SlackAPIAutomation
```

To build the project and run the tests
```sh
$ mvn clean install
```

To only run the tests
```sh
$ mvn clean test
```

### Reading Logs

Tests write logs while they are running which provide further information about what happened if case of failure.
These files reside in ./logs directory and can be viewed locally in your browser by navigating to that folder.


``` 
Note : Token will expire, hence we have to generate new token each time and modify it in "Constants file"
To Generate token : https://api.slack.com/legacy/custom-integrations/legacy-tokens
 ```
