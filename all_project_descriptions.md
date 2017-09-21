# Java/Android Project Definitions - Solo Project

## To-Do List

Goal: Practice what we learned in the Java / Android weeks and research how to persist data.

### MVP
You are required to write an Android app which will allow the user to maintain a task list. The user should be able to enter tasks and display them in a list. The list should only show the bare details of each task. Selecting a task from the list should bring up another screen which shows full details of the task.
Users should also be able to mark tasks as completed.

### Project Extensions

* Allow the user to prioritise and/or categorise tasks
* Use the calendar to set due dates on tasks.

---


## Card game

Goal: Create a card game in Android.

We recommend something reasonably straight forward like BlackJack. The logic for your app will be in Java classes and you can use the methods in these classes in Android activites to create a UI.

***HINT*** Think of the lessons where we wrote regular Java classes in Android Studio, ran unit tests on them, and then used them in our Eight Ball app.

### MVP
The game does not need to have much or any interaction. The aim is to display the results of the Java logic. For example, if you had two players being dealt two cards each your screen might look something like:

```
  Player 1: Ace of Hearts, 9 of Diamonds
  Player 2: 3 of Clubs, 2 of Hearts

  Player 1 Wins!
```

### Project Extensions

* Possibly add another card game
* Improve the UI

---


## Fantasy Zoo Manager

Goal: Practice OO modelling  in Java (unit tests, no UI)

You have been asked to create a zoo management app so that we can manage our dragons, unicorns and other mystical beings.

### MVP

The user must be able to add enclosures, add / remove animals, feed animals and anything else you can dream up!

### Project Extensions

* Animals cannot be placed in the same enclosure if they would eat one and other.
* Animal babies can be sold(!) to other zoos :/
* Visitors can enter / exit the zoo if there are tickets left.
* View the total income for the zoo.
* Animals can randomly go on a rampage and break out of their enclosures

## Food Tracker

Goal: Practice OO and UI design with what we learned in Java / Android weeks

### MVP
You are required to write an Android app that allows a user to track the food they eat. Users should be able to enter what they eat and when (date/time) and for what meal.

### Project Extension
The app should show the user a record of what they have eaten over a given period e.g. In March I drank lots of Irn-Bru.

---

## Shop

Goal: Practice OO modelling in Java (unit tests, no UI)

You are required to build an app that allows a Shop to sell goods to a Customer. Stock and items are not important.

### MVP
The Shop must be able to:

* Make a Sale
  - The customer funds go down, shop sales go up
* Give a refund
  - The customer funds go up, shop refunds go up
* Report on income
  - Total sales minus total refunds

The Customer must:

* Have a collection of possible Payment Methods:
  - CreditCard (default), DebitCard
* Be able to select a Payment Method to pay at any Shop
* Be able to select a Payment Method to recieve a refund onto a given Payment Method

(You may find HashMaps useful for this project)

### Project Extensions

* Add stock tracking
* Ask some of your colleagues about functionality they would see beneficial. Can you add anything?

---

## General Rules (Applies to ALL projects)

* ***DO NOT USE ANY FRAMEWORKS*** - this includes Java Swing and similar packages. If in any doubt about a library you want to use ask an instructor.
* There will still be stand-up at 9am every morning (10am on Friday) - you are still expected to be in every day during the project.
* If you have an idea for a project outwith the specs defined above then please discuss this with the instructors before going ahead.

---

## UX

## Before you begin

Before you begin coding, consider stopping for a minute and think about the user of your product.

Ask yourself the following questions:

* What user needs is your app going to answer? Can you document that?
* Why would your users want to use this over something similar that they've seen elsewhere?
* How could you make sure that your app uses interface solutions that users are familiar with?
* What is the user journey — how will users interact with your app, and how many steps will be necessary to complete their goals?

1. Use this [user needs template](https://goo.gl/zHbfud) to document things that people may want from your app
2. Download and print out the [template for the user journey](https://goo.gl/zXkgtZ) — use it to map steps of the journey. Remember about user actions and system responses. What's required for every particular screen?

If your project has an user-facing interface, make sure to prepare a simple sketch of the app (or a wireframe with UXPin if you wish), sketching out all major steps of the user journey you've identified. Think of potential problems and ways of addressing these in a way that makes the user experience smooth and pleasant.

If your project doesn't have an end-interface, simply document user needs and user journeys (if you can). Try approaching the problem logically — how many steps will complete the user's task?

## After you sketch your application out

Show it to your colleague and ask them how does it come across to them. Don't guide them; give them a goal to complete and listen as they describe their way through your sketches. Can you think of anything that could be improved? If so, sketch quickly and test with the same colleague. See if they think it got any better.

Only then write your code — don't spend time building something that isn't focused on user needs.

### After you're done

* Show your application to a couple of colleagues. Can they use it? Is there anything that could be quickly changed to make it more user-friendly?
