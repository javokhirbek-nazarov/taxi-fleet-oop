# Taxi Fleet Application

Project is written purely with java without any frameworks, 
this allowed to showcase proper usage of oop principles and design patterns. 
Application output is shown only in console

## How to run
- Have **Java 11** or newer version (**17** preferred) installed 
- Clone the project (`git clone git@github.com:javokhirbek-nazarov/taxi-fleet-oop.git`)
- Go to root folder of the project and open a terminal there
- run `./gradlew run --console=plain` command in the terminal
- You should see output being printed in the console

## How to run unit tests
- Go to root folder of the project and open a terminal there
- run `./gradlew test` command in the terminal

## Project design breakdown

### Observer pattern
As taxis should be able to change their status (AVAILABLE/BOOKED)
and booking center publishing new booking to all available taxis. 
**Observer pattern** suits well to the above requirements and provides 
flexible and loosely coupled solution. When taxi becomes AVAILABLE they subscribe to booking service and 
when they change their status to BOOKED they unsubscribe. 
In turn booking service publishes new booking to all subscribed (AVAILABLE) taxis 

![img.png](diagrams/observer.png)

### State Pattern
Booking should have many states like NEW, TAKEN, COMPLETE and more.
And what is more taxi drivers or clients can do many actions like create, complete, take, cancel and more
When we implement these methods we soon realise that for each method we need to check 
state of booking and perform action appreciate for that state making usage of if/else statements abundant.
These kind of code hard to change and maintain. Instead, here using **State pattern** allows having easy to read code 
and makes adding new state really easy like waiting state or more. 

![img.png](diagrams/state.png)

### Template pattern
As we can have many types of dashboards and each have repeating structure 
it was best to use **Template pattern** here. Actual statistics generation is decided by child classes
however base Dashboard class decides where to call that logic and provides nice public interface

![img.png](diagrams/template.png)

### Strategy pattern
Each taxi has a taxi agent which has the capability to send message to other taxis or
booking center. Taxi agent should have an algorithm of how to deliver messages. 
And preferably if we can change this logic in runtime it would make our code flexible and easy to change.
For example, we can by default provide direct communication logic but if need be taxi agent can use 
logging communication logic which is great for debugging. Because of this it best suited to use
**Strategy pattern** here.

![img.png](diagrams/strategy.png)

### Singleton Pattern
DirectCommunicationStrategy class only responsible to deliver message to recipient
even though there is nothing wrong with creating new instance, it causes more memory usage 
and overhead to create object, thus considering it is a service class it is better to make it singleton
which is good for performance and memory usage.

### Static Factory Pattern
LogCommunicationStrategy provides static method to create its instance
this allows better approach to create objects as static method names make them easier to understand 
the difference between overloaded implementations.

## Sequence Diagrams

### New booking
![img.png](diagrams/new-booking.png) \
![img.png](diagrams/new-booking-update.png)

### Dashboard statistics
![img.png](diagrams/dashboard-statistics.png)

### Taxi set status
![img.png](diagrams/taxi-set-status.png)

### List taxis
![img.png](diagrams/list-taxis.png)

### Cancel booking
![img.png](diagrams/booking-cancel.png)

### Complete booking
![img.png](diagrams/booking-complete.png)

### Add taxi
![img.png](diagrams/add-taxi.png)


## Answers:
1. What aspect of this exercise did you find most interesting?
    
Some many possibilities to use design patterns, makes you add more features even though not asked. 
Exactly right amount of expectations are asked to showcase good programming skills

2. What did you find most cumbersome?

Lack of communication, in reality working on this kind of project should be based on clinet requiremnts
and engineer should decide on using design patterns based on change requirements that may arise in the future too 
In this task this was not possible as future changes are not specified and there was no way of asking about them
Also, it was really hard for me to choose between pure java (better shows OOP, Design Pattern skills) with CLI (Hard to use)
and Framework base (much easier to use, shows framework knowledge, limited to framework design) with React UI (more appealing to use)
