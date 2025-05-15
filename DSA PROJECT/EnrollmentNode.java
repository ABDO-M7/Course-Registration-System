public class EnrollmentNode {

        int studentID;
        int courseID;
        EnrollmentNode nextInStudentList; 
        EnrollmentNode nextInCourseList; 

        EnrollmentNode(int studentID, int courseID) {
            this.studentID = studentID;
            this.courseID = courseID;
            this.nextInStudentList = null;
            this.nextInCourseList = null;
        }
        
}