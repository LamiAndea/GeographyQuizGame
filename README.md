# GeographyQuizGame
Geography Quiz Game: 
Random geography questions are read from a text file after the User selects the number of geography questions they want to answer.
Each Question has four multiple-choice answers. At the end of the Quiz, the score of the User is revealed, and the questions the User got wrong
and the correct answers to those questions are also revealed. 

Functionality: 
The project demonstrates OOP, as there are two Java files:

QuizGame:
A main file to interact with the User by collecting the number of questions a user selects, printing the questions, and storing the answers to 
each question

QuizGameLoader:
The second java file acts as a backend file. A file reader is used to store all the questions, options, and their answers. Then random numbers 0-90 are selected 
using a random number generator to select the number of questions. Then a method is used to check if the User's answers and return the users score
