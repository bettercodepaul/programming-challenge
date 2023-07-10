# Workflow Description

## First Thoughts
- ok, this should be easy
- only reading the CSV files and making simple operations with the data
- should be very similar to entity framework core and data migration(automapping)
- but my Java skills are already a bit dusty and I remembered Eclipse as a cumbersome IDE
- What is the Maven wrapper? --> Google
- ok, let's go :D

## Setting up
- I have updated Eclipse and tried to install the Maven wrapper
- then I understood again why I didn´t like Eclipse in the past
- ok, maybe it´s not so easy as a Java stranger ;(
- thanks to my student status I still have free access to IntelliJ (<3) and was able to set up the project with just a few clicks

## Research
- to solve the challenge I have to find out, how can ich read the CSV files and use the data for further operations
- I want to save the data in a class model or an array
- so I thought there must be an easy  way to integrate (mapping) the data with some clicks in a data model, like in EF Core
- after some research, I found opencsv and bufferreader
- I choose bufferreader and saving the data in an array because:
  - it's an easier way to solve the challenge
  - the data and the project are not so big (at this time)
  - faster implementation
  - I found a nice Tutorial to follow: https://www.youtube.com/watch?v=-Aud0cDh-J8
- But if I had more time and more Java Skills and Knowledge I would prefer creating a Data Model Class and using Tools like opencsv because:
  - its cleaner
  - more adjustable
  - scalable
  - auto-mapping

## Coding

- after following the tutorial I was implementing the operations to solve the tasks
- I used ChatGPT(shame on my head) to make the code cleaner and to create in-line comments for better documentation
- understanding the Java syntax was with my background in C# not as difficult, as I thought.

## Testing
- I have not yet had experience with the creation of test units for my code
- but google helped me here too and I created some test units
- I am aware that I have not covered all possible scenarios with my unit test cases and I have probably not solved this task properly, but it works. :D

## Final Thoughts
- the task was not as easy, as I thought at the beginning
- mainly because of the unfamiliar Java environment
- I love IntelliJ IDEA
- The Challenge was fun :D