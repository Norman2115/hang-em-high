# Hang Em High
## Overview
Inspired by the Hangman concept, our project Hang Em High is a educational game designed to make learning Malaysian history exciting. It has two game modes: Question-Based and Word-Guessing. Players can test their knowledge through multiple-choice questions or guess historical words based on hints. This project aligns with Sustainable Development Goal 4 (Quality Education).

This project is developed as a part of the **PRG3205 Data Structure and Algorithms** course at INTI International University. The main purpose of this assignment is to demonstrate the application of data structures and algorithms to solve real-world problems in a gamified educational context.

## Features
* Each correct answers earns points, wrong answers cost lives.
* Two Game Modes:
  - Question-Based Game (Timed multiple-choice questions)
  - Word-Guessing Game
* Difficulty Levels: Choose between Easy, Medium and Hard.
* Leaderboard: Compete for score in each game mode and difficulty level.
* Streak Scoring: Gain bonus points for consecutive correct answers (Question-Based).

## Technologies
* Frontend: HTML, CSS and JavaScript
* Backend: Java Spring Boot
* Database: Firebase Firestore

## Installation
### Prerequisites
* Frontend: Any modern web browser (Chrome, Firefox, etc.)
* Backend: Java 8+ and Spring Boot
* Database: Firebase Firestore
* Maven: To manage project dependencies and for run the backend

### Project Setup
1. Refer to the **Appendix 9.1** of the documentation in the PDF.
2. To check if Maven is installed, run the command in terminal:
``` bash
mvn -v
```
3. Clone the repository from Github to local machine:
``` bash
git clone https://github.com/Norman2115/hang-em-high.git
```
4. Navigate to the project directory:
``` bash
cd your/project/directory/local/path
```
5. Run the Spring Boot Application:
``` bash
mvn spring-boot:run --debug
```
6. Access the game:
``` bash
http://localhost:8080/html/index.html
```

## Contributors
* Teo Chung Henn
* Liew Wen Yen
* Chong Ken Ji
