
public class StudentNode {
    private int StudentID;
    private int CousresCount;
    EnrollmentNode firstEnrollment;
    StudentNode nextStudent;

    public StudentNode(int studentID) {
        this.StudentID = studentID;
        this.firstEnrollment = null;
        this.nextStudent = null;
        this.CousresCount = 0;
    }
    public int getStudentID() {
        return StudentID;
    }

    public void incrementCourseCount() {
        this.CousresCount++;
    }
    public void decrementCourseCount() {
        this.CousresCount--;
    }

    public int getCousresCount() {
        return CousresCount;
    }


}
