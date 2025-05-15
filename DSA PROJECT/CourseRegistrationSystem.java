import java.util.Stack;

public class CourseRegistrationSystem {
    private StudentNode studentList;
    private CourseNode courseList;
    private int lastStudentAdded;
    private int lastCourseAdded;
    private Stack<Action> undoStack;
    private Stack<Action> redoStack;


    public CourseRegistrationSystem() {
        studentList = null;
        courseList = null;
        lastStudentAdded = -1;
        lastCourseAdded = -1;
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }


    public boolean isFullCourse(int courseID) {
        CourseNode course = findCourse(courseID);
        if (course == null) {
            System.out.println("[!] Course not found!");
            return false;
        }
        boolean isFull = course.getStudentCount() >= 30;
        return isFull;
    }


    public boolean isFullStudent(int studentID) {
        StudentNode student = findStudent(studentID);
        if (student == null) {
            System.out.println("[!] Student not found!");
            return false;
        }
        boolean isFull = student.getCousresCount() > 7;
        return isFull;
    }

    
    public boolean isNormalStudent(int studentID) {
        StudentNode student = findStudent(studentID);
        if (student == null) {
            System.out.println("[!] Student not found!");
            return false;
        }
        boolean isNormal = student.getCousresCount() >= 2 && student.getCousresCount() <= 7;
        return isNormal;
    }

    public boolean isStudentEnrolled(int studentID, int courseID) {
        StudentNode student = findStudent(studentID);
        if (student == null) return false;
        EnrollmentNode current = student.firstEnrollment;
        while (current != null) {
            if (current.courseID == courseID) return true;
            current = current.nextInStudentList;
        }
        return false;
    }



    private StudentNode findStudent(int studentID) {
        StudentNode current = studentList;
        while (current != null) {
            if (current.getStudentID() == studentID) return current;
            current = current.nextStudent;
        }
        return null;
    }

    private CourseNode findCourse(int courseID) {
        CourseNode current = courseList;
        while (current != null) {
            if (current.getCourseID() == courseID) return current;
            current = current.nextCourse;
        }
        return null;
    }

    public void addStudent(int studentID) {
        if (findStudent(studentID) != null) {
            System.out.println("[!] Student already exists!");
            
        } else {
            
            StudentNode newNode = new StudentNode(studentID);
            newNode.nextStudent = studentList;
            studentList = newNode;
            lastStudentAdded = studentID;
            System.out.println("[+] Student with ID " + studentID + " added successfully.");
        }
    }
    public void addCourse(int courseID){
        if(findCourse(courseID) !=null){
            System.out.println("[!] Course already exists!");
        }else{
            CourseNode newNode=new CourseNode(courseID);
            newNode.nextCourse=courseList;
            courseList=newNode;
            lastCourseAdded=courseID;
            System.out.println("[+] Course with ID " + courseID + " added successfully.");
        }
    }

    public void deleteStudent(int studentID) {
        StudentNode current = studentList;
        StudentNode previous = null;
    
        while (current != null && current.getStudentID() != studentID) {
            previous = current;
            current = current.nextStudent;
        }
    
        if (current == null) {
            System.out.println("[!] Student not found!");
            return;
        }
    
        EnrollmentNode enrollment = current.firstEnrollment;
        while (enrollment != null) {
            EnrollmentNode temp = enrollment;
            enrollment = enrollment.nextInStudentList; 
            removeEnrollment(studentID, temp.courseID);
        }
    
        if (previous == null) {
            studentList = current.nextStudent;
        } else {
            previous.nextStudent = current.nextStudent;
            }
            if (studentList == null) {
                lastStudentAdded = -1; 
            } else if (studentID == lastStudentAdded) {
                StudentNode temp = studentList;
                lastStudentAdded = temp.getStudentID();
                while (temp.nextStudent != null) {
                    temp = temp.nextStudent;
                    lastStudentAdded = temp.getStudentID();
                }
            }    
    
        System.out.println("[+] Student with ID " + studentID + " has been deleted successfully.");
    }
    
    public void deleteCourse(int courseID) {
        CourseNode current = courseList;
        CourseNode previous = null;

        while (current != null && current.getCourseID() != courseID) {
            previous = current;
            current = current.nextCourse;
        }

        if (current == null) {
            System.out.println("[!] Course not found!");
            return;
        }

        EnrollmentNode enrollment = current.firstEnrollment;
        while (enrollment != null) {
            EnrollmentNode temp = enrollment;
            enrollment = enrollment.nextInCourseList;
            removeEnrollment(temp.studentID, courseID);
        }

        if (previous == null) {
            courseList = current.nextCourse;
        } else {
            previous.nextCourse = current.nextCourse;
        }

        if (courseList == null) {
            lastCourseAdded = -1; 
        } else if (courseID == lastCourseAdded) {
            CourseNode temp = courseList;
            lastCourseAdded = temp.getCourseID();
            while (temp.nextCourse != null) {
                temp = temp.nextCourse;
                lastCourseAdded = temp.getCourseID();
            }
        }

        System.out.println("[+] Course with ID " + courseID + " has been deleted successfully.");
    }
    
    public int getLastStudentAdded() {
        return lastStudentAdded;
    }

  
    public int getLastCourseAdded() {
        return lastCourseAdded;
    }

    public boolean enrollStudent(int studentID, int courseID){
        StudentNode student = findStudent(studentID);
        CourseNode course = findCourse(courseID);

        if(student == null || course == null){
            System.out.println("[!] Student or Course not found!"); 
            return false; 
        }
        if(isFullCourse(courseID)){
            System.out.println("[!] Course is full!");
            return false;
        }
        if(isFullStudent(studentID)){
            System.out.println("[!] Student has maximum course load!");
            return false;
        }
        if(isStudentEnrolled(studentID, courseID)){
            System.out.println("[i] Student is already enrolled in this course!");
            return false;
        }

        // Create a new enrollment node
        EnrollmentNode newNode = new EnrollmentNode(studentID, courseID);
        
        // Add to student's enrollment list
        newNode.nextInStudentList = student.firstEnrollment;
        student.firstEnrollment = newNode;
        student.incrementCourseCount();
        
        // Add to course's enrollment list
        newNode.nextInCourseList = course.firstEnrollment;
        course.firstEnrollment = newNode;
        course.incrementStudentCount();
        
        undoStack.push(new Action("enroll", studentID, courseID));
        redoStack.clear();
        
        System.out.println("[+] Student " + studentID + " successfully enrolled in course " + courseID);
        return true;
    }

    public void removeEnrollment(int studentID, int courseID) {

        StudentNode student = findStudent(studentID);
        CourseNode course = findCourse(courseID);

        if (student == null || course == null){
            System.out.println("[!] Student or Course not found!"); 
            return;
        }

     
        EnrollmentNode curr = student.firstEnrollment;
        EnrollmentNode prev = null;
        while (curr != null && (curr.studentID != studentID || curr.courseID != courseID)) {
            prev = curr;
            curr = curr.nextInStudentList;
        }

        if (curr == null){
            System.out.println("[!] Student is not enrolled in this course!");
            return;
        } 

        
        if (prev == null) {
            student.firstEnrollment = curr.nextInStudentList;
        } else {
            prev.nextInStudentList = curr.nextInStudentList;
        }
        student.decrementCourseCount();

        
        curr = course.firstEnrollment;
        prev = null;
        while (curr != null && (curr.studentID != studentID || curr.courseID != courseID)) {
            prev = curr;
            curr = curr.nextInCourseList;
        }
        if (curr == null){
            System.out.println("[!] Enrollment not found in course list!");
            return;
        } 


       
        if (prev == null) {
            course.firstEnrollment = curr.nextInCourseList;
        } else {
            prev.nextInCourseList = curr.nextInCourseList;
        }
        course.decrementStudentCount();

        
        undoStack.push(new Action("remove", studentID, courseID));
        redoStack.clear();
        System.out.println("[+] Enrollment removed successfully.");

    }
    

    public void listStudentsByCourse(int courseID) {
        CourseNode course = findCourse(courseID);
    
        if (course == null) {
            System.out.println("[!] Course not found!");
            return;
        }
    
        EnrollmentNode current = course.firstEnrollment;
    
        if (current == null) {
            System.out.println("[i] No students enrolled in this course.");
            return;
        }
    
        System.out.println("\n┌─────────────────────────────────────────────────────┐");
        System.out.println("│           STUDENTS ENROLLED IN COURSE " + courseID + padRight(courseID) + " │");
        System.out.println("└─────────────────────────────────────────────────────┘");
        System.out.println("[#] Total students: " + course.getStudentCount());
        System.out.println("─────────────────────────────────────────────────────");
    
        while (current != null) {
            System.out.println("[*] Student ID: " + current.studentID);
            current = current.nextInCourseList;
        }
        System.out.println("─────────────────────────────────────────────────────");
    } 
    
    private String padRight(int number) {
        return padRight(number, 0);
    }
    
    private String padRight(int number, int extraSpaces) {
        // Add padding based on the number of digits plus extra spaces
        String numStr = String.valueOf(number);
        int digits = numStr.length();
        StringBuilder padding = new StringBuilder();
        
        // Calculate padding needed (adjust as needed)
        int spacesToAdd = 10 - digits + extraSpaces;
        for (int i = 0; i < spacesToAdd; i++) {
            padding.append(" ");
        }
        
        return padding.toString();
    }
    
    
    public void listCoursesByStudent(int studentID){
        StudentNode student=findStudent(studentID);
        if(student==null){
            System.out.println("[!] Student not found!");
            return;
        }
        EnrollmentNode current=student.firstEnrollment;

        if(current==null){
            System.out.println("[i] No courses enrolled by this student!");
            return;
        }
        
        System.out.println("\n┌─────────────────────────────────────────────────────┐");
        System.out.println("│           COURSES ENROLLED BY STUDENT " + studentID + padRight(studentID) + " │");
        System.out.println("└─────────────────────────────────────────────────────┘");
        System.out.println("[#] Total courses: " + student.getCousresCount());
        System.out.println("─────────────────────────────────────────────────────");

        while(current!=null){
            System.out.println("[*] Course ID: " + current.courseID);
            current=current.nextInStudentList;
        }
        System.out.println("─────────────────────────────────────────────────────");
    }

    public void undo(){
        if(undoStack.isEmpty()){
            System.out.println("[i] Nothing to undo!");
            return;
        }
        Action action=undoStack.pop();
        if(action.type.equalsIgnoreCase("enroll")){
            removeEnrollment(action.studentID, action.courseID);
            redoStack.push(new Action("enroll", action.studentID, action.courseID));
            System.out.println("[<] Undo successful: Enrollment of student " + action.studentID + " in course " + action.courseID + " has been reversed.");
        }else if(action.type.equalsIgnoreCase("remove")){
            enrollStudent(action.studentID, action.courseID);
            redoStack.push(new Action("remove", action.studentID, action.courseID));
            System.out.println("[<] Undo successful: Removal of student " + action.studentID + " from course " + action.courseID + " has been reversed.");
        }
    }

    
    public void sortStudentsBYID(int courseID) {
        CourseNode course = findCourse(courseID);

        if (course == null) {
            System.out.println("[!] Course not found!");
            return;
        }

        EnrollmentNode current = course.firstEnrollment;

        if (current == null) {
            System.out.println("[i] No students enrolled in this course.");
            return;
        }

        int count = 0;
        EnrollmentNode temp = current;
        while (temp != null) {
            count++;
            temp = temp.nextInCourseList;
        }

        int[] studentIDs = new int[count];
        temp = current;
        for (int i = 0; i < count; i++) {
            studentIDs[i] = temp.studentID;
            temp = temp.nextInCourseList;
        }

        boolean swapped;
        for (int i = 0; i < count - 1; i++) {
            swapped = false;
            for (int j = 0; j < count - i - 1; j++) {
                if (studentIDs[j] > studentIDs[j + 1]) {
                    int swap = studentIDs[j];
                    studentIDs[j] = studentIDs[j + 1];
                    studentIDs[j + 1] = swap;
                    swapped = true;
                }
            }
            if (!swapped) break; 
        }
        
        System.out.println("\n┌─────────────────────────────────────────────────────┐");
        System.out.println("│       STUDENTS IN COURSE " + courseID + " (SORTED BY ID)" + padRight(courseID, 4) + " │");
        System.out.println("└─────────────────────────────────────────────────────┘");
        System.out.println("[#] Total students: " + count);
        System.out.println("─────────────────────────────────────────────────────");
        for (int i = 0; i < count; i++) {
            System.out.println("[*] Student ID: " + studentIDs[i]);
        }
        System.out.println("─────────────────────────────────────────────────────");
    }

    public void sortCoursesBYID(int studentID) {
        StudentNode student = findStudent(studentID);

        if (student == null) {
            System.out.println("[!] Student not found!");
            return;
        }

        EnrollmentNode current = student.firstEnrollment;

        if (current == null) {
            System.out.println("[i] No courses enrolled by this student!");
            return;
        }

        int count = 0;
        EnrollmentNode temp = current;
        while (temp != null) {
            count++;
            temp = temp.nextInStudentList;
        }

        int[] courseIDs = new int[count];
        temp = current;
        for (int i = 0; i < count; i++) {
            courseIDs[i] = temp.courseID;
            temp = temp.nextInStudentList;
        }

        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (courseIDs[j] > courseIDs[j + 1]) {
                    int swap = courseIDs[j];
                    courseIDs[j] = courseIDs[j + 1];
                    courseIDs[j + 1] = swap;
                }
            }
        }

        System.out.println("\n┌─────────────────────────────────────────────────────┐");
        System.out.println("│       COURSES BY STUDENT " + studentID + " (SORTED BY ID)" + padRight(studentID, 4) + " │");
        System.out.println("└─────────────────────────────────────────────────────┘");
        System.out.println("[#] Total courses: " + count);
        System.out.println("─────────────────────────────────────────────────────");
        for (int i = 0; i < count; i++) {
            System.out.println("[*] Course ID: " + courseIDs[i]);
        }
        System.out.println("─────────────────────────────────────────────────────");
    }
    
    public void redo(){
        if(redoStack.isEmpty()){
            System.out.println("[i] Nothing to redo!");
            return;
        }
        Action action=redoStack.pop();
        if(action.type.equalsIgnoreCase("enroll")){
            enrollStudent(action.studentID, action.courseID);
            undoStack.push(new Action("enroll", action.studentID, action.courseID));
            System.out.println("[>] Redo successful: Student " + action.studentID + " has been enrolled in course " + action.courseID + ".");
        }else if(action.type.equalsIgnoreCase("remove")){
            removeEnrollment(action.studentID, action.courseID);
            undoStack.push(new Action("remove", action.studentID, action.courseID));
            System.out.println("[>] Redo successful: Student " + action.studentID + " has been removed from course " + action.courseID + ".");
        }
    } 
    
    
}

