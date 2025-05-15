public  class CourseNode {
    private int  CourseID; 
    private int StudentCount; 
    EnrollmentNode firstEnrollment; 
    CourseNode nextCourse; 

    public CourseNode(int courseID) {
        this.CourseID = courseID;
        this.StudentCount = 0;
        this.firstEnrollment = null;
        this.nextCourse = null;
    }

    public int getCourseID() {
        return CourseID;
    }

    public int getStudentCount() {
        return StudentCount;
    }

    public void incrementStudentCount() {
        this.StudentCount++;
    }
    public void decrementStudentCount() {
        this.StudentCount--;
    }
    
}
