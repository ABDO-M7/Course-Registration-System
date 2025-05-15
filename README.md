# Course Registration System

A Java-based command-line application for managing student course registrations using linked lists and other data structures.

## Features

- **Student Management**

  - Add and remove students
  - Track last student added

- **Course Management**

  - Add and remove courses
  - Track last course added
  - Check if a course is full (maximum 30 students)

- **Enrollment Management**

  - Enroll students in courses
  - Remove student enrollments
  - Check if a student has a normal course load (2-7 courses)
  - Check if a student has maximum course load (over 7 courses)

- **Data Querying**

  - List all courses taken by a student
  - List all students enrolled in a course
  - Sort courses by student ID
  - Sort students by course ID

- **History Management**
  - Undo previous operations
  - Redo operations

## Data Structures Used

- **LinkedLists**: Used to maintain student and course lists
- **Nodes**: StudentNode, CourseNode, and EnrollmentNode to store data
- **Stacks**: For implementing undo and redo functionality

## How to Run

1. Compile all Java files:

   ```
   javac *.java
   ```

2. Run the main program:

   ```
   java Main
   ```

3. Follow the on-screen menu to interact with the system

## System Requirements

- Java Runtime Environment (JRE) 8 or higher
- Command-line interface or terminal that supports ASCII characters

## Classes Overview

- **Main**: User interface and menu system
- **CourseRegistrationSystem**: Core logic and operations
- **StudentNode**: Data structure for student information
- **CourseNode**: Data structure for course information
- **EnrollmentNode**: Represents enrollment relationships
- **Action**: Supports undo/redo operations

## Usage Limits

- A course can have a maximum of 30 students
- A student with 0-1 courses is considered to have a light course load
- A student with 2-7 courses is considered to have a normal course load
- A student with more than 7 courses is considered to have a heavy course load

## Project Structure

```
.
├── Main.java                    # Main application and UI
├── CourseRegistrationSystem.java # Core system logic
├── StudentNode.java             # Student node structure
├── CourseNode.java              # Course node structure
├── EnrollmentNode.java          # Enrollment relationship
└── Action.java                  # Action for undo/redo
```

## Example Operations

1. Add students and courses
2. Enroll students in courses
3. View which students are taking a specific course
4. View which courses a student is taking
5. Sort students in a course by ID
6. Sort courses taken by a student by ID
7. Undo/redo operations
8. Check course capacity
