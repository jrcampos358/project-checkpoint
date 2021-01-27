# Project-Checkpoints

Simple UI to display project checkpoints and tasks.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Clone Github project](#clone-github-project)
- [Prepare and Run Microservice](#prepare-and-run-microservice)
- [Add entries to the inmemory DB](#add-entries-to-the-inmemory-db)
- [Prepare and Run Web](#prepare-and-run-web)
- [List of phases when kick-starting an IT Project](#list-of-phases-when-kick-starting-an-it-project)


## Features

- ⚛ React 16
- 🌐 React Router
- 🚀 Webpack 4
- 🛠 Babel 7
- 🌈 SASS/SCSS support
- 🔄 Hot Reloading
- 🎨 Linting with airbnb config
- 🐶 Commit Hooks to prevent bad commits
- ✅ Jest/@testing-library/jest-dom

## Prerequisites

You should have Java 11, mvn (maven), node and npm executables in the path.

You also need Postman/SoapUI to invoke the Rest API to create DB entries.

All other tools should be listed in devDependencies section of package.json so they can be installed into the local node_modules (and node_modules/.bin) by simply doing npm install.

## Clone Github project

1. Clone repo using `git clone https://github.com/jrcampos358/project-checkpoint.git`
2. Move to directory: `cd project-checkpoint`. 
3. Open 2 comandline to run both web and microservice. 

## Prepare and Run Microservice 

1. Go to your 1st open terminal (assuming you are in folder 'project-checkpoint')
2. Move to 'micro' directory: `cd micro`

Note: This micro service run on port 8080

3. Run `./mvnw spring-boot:run` to start microservice

## Add entries to the inmemory DB

The current state of the app only allow you to insert 'entries' into the H2 database via the REST API.

### To add 'checkppoints' to a project, use the following test data 

Asusmptions : 

In a real world app, 'projectId' will be the container of checkpoints. As I have not provided a UI to be able to create the Project, we will use 

projectId of "8dd5f315-9788-4d00-87bb-10eed9eff566"

Please refer the attached 'screencapture' on how to enter this entries in H2 DB via REST API.



URI : 

http://localhost:8080/checkpoint


HTTP Method : 

POST

Content-Type : 

application/json

Sample Payload : Body Raw

{
   "name":"Design",
   "projectId":"8dd5f315-9788-4d00-87bb-10eed9eff566"
}



Sample Response : 

{
    "id": "9f20460f-3278-4485-8035-ec3b88a0b29b",
    "name": "Design",
    "projectId": "8dd5f315-9788-4d00-87bb-10eed9eff566",
    "complPercentage": 0,
    "tasks": []
}


NOTE : 

The value of the 'id' from the above response will be used to create the 'checkpoint' Tasks. This id will be used for the REST API were going to use.


### To add 'tasks' to a checkpoints, use the following test data 



URI : 

http://localhost:8080/task


HTTP Method : 

POST

Content-Type : 

application/json


Sample Payload : Body Raw

{
         "text":"Recent updates 1",
         "complPercentage":40,
         "checkpointId": "9f20460f-3278-4485-8035-ec3b88a0b29b"
}



Sample Response : 

{
    "id": "a5840f93-7a59-4595-9d35-8529989ef2be",
    "text": "Recent updates 1",
    "complPercentage": 40,
    "checkpointId": "9f20460f-3278-4485-8035-ec3b88a0b29b"
}


NOTE : 

The value of the 'id' from the above response will be used to create the 'checkpoint' Tasks. So you will need




## Prepare and Run Web 

1. Go to your 2nd open terminal (assuming you are in folder 'project-checkpoint')
2. Move to 'web' directory: `cd web`
3. Run `npm install` for installing dependencies
4. Run `npm start` to start web app

Note: This webapp run on port 3000

This will open a browser (preferably Chrome) and the list of 'project checkpoints and their tasks will be displayed.' 



## List of phases when kick-starting an IT Project

### Part 1: Define Your Project

#### Tasks

1. Approve the business case
2. Identify Project Objectives
3. Define Project Scope
4. Create Solution Design
5. Approve the Budget
6. Identify Initial Project Risks
7. Produce List of Deliverables 

### Part 2: Build Your Team

#### Tasks

1. Appoint Project Sponsor
2. Appoint Team Members
3. Identify Other Stakeholders
4. Identify Integration Partners
5. Define Roles and Responsibilities
6. Produce Communications Plan
7. Arrange First Team Meeting
8. Create High-Level Design

### Part 3: Set Up Your Tools

#### Tasks

1. Select Project Management Software
2. Set Up User Accounts
3. Set Up Project Filing System
4. Create Task List
5. Create Project Schedule
