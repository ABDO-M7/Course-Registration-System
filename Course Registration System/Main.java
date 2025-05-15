import java.util.Scanner;
public class Main {
    private static void displayMenu() {
        System.out.println("\n╔═════════════════════════════════════════════════════════╗");
        System.out.println("║                                                         ║");
        System.out.println("║           COURSE REGISTRATION SYSTEM                    ║");
        System.out.println("║                                                         ║");
        System.out.println("╠═════════════════════════════════════════════════════════╣");
        System.out.println("║ 1.  [+] Add Student                                     ║");
        System.out.println("║ 2.  [+] Add Course                                      ║");
        System.out.println("║ 3.  [-] Remove Student                                  ║");
        System.out.println("║ 4.  [-] Remove Course                                   ║");
        System.out.println("║ 5.  [=] Enroll Student in Course                        ║");
        System.out.println("║ 6.  [x] Remove Enrollment                               ║");
        System.out.println("║ 7.  [#] List Courses by Student                         ║");
        System.out.println("║ 8.  [#] List Students by Course                         ║");
        System.out.println("║ 9.  [?] Check if Course is Full                         ║");
        System.out.println("║ 10. [?] Check if Student is Normal                      ║");
        System.out.println("║ 11. [#] Get Last Student Added                          ║");
        System.out.println("║ 12. [#] Get Last Course Added                           ║");
        System.out.println("║ 13. [<] Undo                                            ║");
        System.out.println("║ 14. [>] Redo                                            ║");
        System.out.println("║ 15. [A] Sort Students by Course ID                      ║");
        System.out.println("║ 16. [A] Sort Courses by Student ID                      ║");
        System.out.println("║ 0.  [X] Exit                                            ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
    }

    private static void printHeader(String title) {
        System.out.println("\n┌─────────────────────────────────────────────────────┐");
        System.out.println("│ " + centerText(title, 53) + " │");
        System.out.println("└─────────────────────────────────────────────────────┘");
    }
    
    private static String centerText(String text, int width) {
        int padding = width - text.length();
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < leftPadding; i++) {
            sb.append(" ");
        }
        sb.append(text);
        for (int i = 0; i < rightPadding; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
    
    private static void printSeparator() {
        System.out.println("\n─────────────────────────────────────────────────────");
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        
        System.out.println("\n╔═════════════════════════════════════════════════════════╗");
        System.out.println("║                                                         ║");
        System.out.println("║       WELCOME TO THE COURSE REGISTRATION SYSTEM         ║");
        System.out.println("║                                                         ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
        
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            System.out.print("\n>> Please select an option: ");
            while (!scanner.hasNextInt()) {
                System.out.println("[!] Please enter a valid number!");
                scanner.next();
                displayMenu();
                System.out.print("\n>> Please select an option: ");
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    printHeader("ADD STUDENT");
                    System.out.print("Enter student ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("[!] Please enter a valid student ID!");
                        scanner.next();
                    }
                    int studentID = scanner.nextInt();
                    system.addStudent(studentID);
                    printSeparator();
                    break;

                case 2:
                    printHeader("ADD COURSE");
                    System.out.print("Enter course ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("[!] Please enter a valid course ID!");
                        scanner.next();
                    }
                    int courseID = scanner.nextInt();
                    system.addCourse(courseID);
                    printSeparator();
                    break;

                case 3:
                    printHeader("REMOVE STUDENT");
                    System.out.print("Enter student ID to delete: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("[!] Please enter a valid student ID!");
                        scanner.next();
                    }
                    studentID = scanner.nextInt();
                    system.deleteStudent(studentID);
                    printSeparator();
                    break;

                case 4:
                    printHeader("REMOVE COURSE");
                    System.out.print("Enter course ID to delete: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("[!] Please enter a valid course ID!");
                        scanner.next();
                    }
                    courseID = scanner.nextInt();
                    system.deleteCourse(courseID);
                    printSeparator();
                    break;

                case 5:
                    printHeader("ENROLL STUDENT IN COURSE");
                    System.out.print("Enter student ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("[!] Please enter a valid student ID!");
                        scanner.next();
                    }
                    studentID = scanner.nextInt();
                    System.out.print("Enter course ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("[!] Please enter a valid course ID!");
                        scanner.next();
                    }
                    courseID = scanner.nextInt();
                    system.enrollStudent(studentID, courseID);
                    printSeparator();
                    break;

                case 6:
                    printHeader("REMOVE ENROLLMENT");
                    System.out.print("Enter student ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("[!] Please enter a valid student ID!");
                        scanner.next();
                    }
                    studentID = scanner.nextInt();
                    System.out.print("Enter course ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("[!] Please enter a valid course ID!");
                        scanner.next();
                    }
                    courseID = scanner.nextInt();
                    system.removeEnrollment(studentID, courseID);
                    printSeparator();
                    break;

                case 7:
                    printHeader("LIST COURSES BY STUDENT");
                    System.out.print("Enter student ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("[!] Please enter a valid student ID!");
                        scanner.next();
                    }
                    studentID = scanner.nextInt();
                    system.listCoursesByStudent(studentID);
                    printSeparator();
                    break;

                case 8:
                    printHeader("LIST STUDENTS BY COURSE");
                    System.out.print("Enter course ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("[!] Please enter a valid course ID!");
                        scanner.next();
                    }
                    courseID = scanner.nextInt();
                    system.listStudentsByCourse(courseID);
                    printSeparator();
                    break;

                case 9:
                    printHeader("CHECK IF COURSE IS FULL");
                    System.out.print("Enter course ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("[!] Please enter a valid course ID!");
                        scanner.next();
                    }
                    courseID = scanner.nextInt();
                    boolean isFull = system.isFullCourse(courseID);
                    System.out.println("[?] Is course " + courseID + " full? " + (isFull ? "[YES]" : "[NO]"));
                    printSeparator();
                    break;

                case 10:
                    printHeader("CHECK IF STUDENT IS NORMAL");
                    System.out.print("Enter student ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("[!] Please enter a valid student ID!");
                        scanner.next();
                    }
                    studentID = scanner.nextInt();
                    boolean isNormal = system.isNormalStudent(studentID);
                    System.out.println("[?] Does student " + studentID + " have a normal course load? " + (isNormal ? "[YES]" : "[NO]"));
                    printSeparator();
                    break;

                case 11:
                    printHeader("GET LAST STUDENT ADDED");
                    int lastStudent = system.getLastStudentAdded();
                    if (lastStudent == -1) {
                        System.out.println("[i] No students added yet.");
                    } else {
                        System.out.println("[#] Last student added: " + lastStudent);
                    }
                    printSeparator();
                    break;

                case 12:
                    printHeader("GET LAST COURSE ADDED");
                    int lastCourse = system.getLastCourseAdded();
                    if (lastCourse == -1) {
                        System.out.println("[i] No courses added yet.");
                    } else {
                        System.out.println("[#] Last course added: " + lastCourse);
                    }
                    printSeparator();
                    break;

                case 13:
                    printHeader("UNDO OPERATION");
                    system.undo();
                    printSeparator();
                    break;

                case 14:
                    printHeader("REDO OPERATION");
                    system.redo();
                    printSeparator();
                    break;

                case 15:
                    printHeader("SORT STUDENTS BY COURSE ID");
                    System.out.print("Enter course ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("[!] Please enter a valid course ID!");
                        scanner.next();
                    }
                    courseID = scanner.nextInt();
                    system.sortStudentsBYID(courseID);
                    printSeparator();
                    break;

                case 16:
                    printHeader("SORT COURSES BY STUDENT ID");
                    System.out.print("Enter student ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("[!] Please enter a valid student ID!");
                        scanner.next();
                    }
                    studentID = scanner.nextInt();
                    system.sortCoursesBYID(studentID);
                    printSeparator();
                    break;

                case 0:
                    System.out.println("\n╔═════════════════════════════════════════════════════════╗");
                    System.out.println("║                                                         ║");
                    System.out.println("║            EXITING THE SYSTEM. GOODBYE!                 ║");
                    System.out.println("║                                                         ║");
                    System.out.println("╚═════════════════════════════════════════════════════════╝");
                    System.exit(0);
                    break;

                default:
                    System.out.println("[!] Invalid choice. Please select a number between 0 and 16.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
