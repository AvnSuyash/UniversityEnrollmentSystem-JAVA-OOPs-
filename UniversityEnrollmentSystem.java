import java.util.ArrayList;
import java.util.List;

// Base class for Student
class Student {
    private String name;
    private String studentId;
    private List<Course> enrolledCourses;

    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollInCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            course.enroll(this);
        }
    }
}

// Base class for Course
class Course {
    private String courseName;
    private String courseCode;
    private List<Student> students;
    private Syllabus syllabus;

    public Course(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.students = new ArrayList<>();
        this.syllabus = new Syllabus(this);
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void enroll(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    // Overloaded enroll method
    public void enrollStudentById(String studentId, List<Student> studentList) {
        Student student = studentList.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
        if (student != null) enroll(student);
    }

    public void enrollStudentByObject(Student student) {
        enroll(student);
    }
}

// Syllabus class as part of Course (Composition)
class Syllabus {
    private Course course;
    private List<String> topics;

    public Syllabus(Course course) {
        this.course = course;
        this.topics = new ArrayList<>();
    }

    public void addTopic(String topic) {
        topics.add(topic);
    }

    public List<String> getTopics() {
        return topics;
    }
}

// Subclass of Course: UndergraduateCourse
class UndergraduateCourse extends Course {
    public UndergraduateCourse(String courseName, String courseCode) {
        super(courseName, courseCode);
    }

    public String getLevel() {
        return "Undergraduate";
    }
}

// Subclass of Course: GraduateCourse
class GraduateCourse extends Course {
    public GraduateCourse(String courseName, String courseCode) {
        super(courseName, courseCode);
    }

    public String getLevel() {
        return "Graduate";
    }
}

// Faculty class
class Faculty {
    private String name;
    private String facultyId;
    private List<Course> assignedCourses;

    public Faculty(String name, String facultyId) {
        this.name = name;
        this.facultyId = facultyId;
        this.assignedCourses = new ArrayList<>();
    }

    public void assignCourse(Course course) {
        if (!assignedCourses.contains(course)) {
            assignedCourses.add(course);
        }
    }

    public List<Course> getAssignedCourses() {
        return assignedCourses;
    }
}

public class UniversityEnrollmentSystem {
    public static void main(String[] args) {
        // Create some students
        Student student1 = new Student("Alice", "S123");
        Student student2 = new Student("Bob", "S124");

        // Create some courses
        Course course1 = new UndergraduateCourse("Introduction to Java", "CS101");
        Course course2 = new GraduateCourse("Advanced Java Programming", "CS201");

        // Enroll students in courses
        student1.enrollInCourse(course1);
        student2.enrollInCourse(course1);

        // Create a Faculty member
        Faculty faculty = new Faculty("Dr. Smith", "F001");

        // Assign courses to the faculty
        faculty.assignCourse(course1);
        faculty.assignCourse(course2);

        // Output results
        System.out.println(student1.getName() + " is enrolled in: ");
        student1.getEnrolledCourses().forEach(course -> System.out.println(course.getCourseName()));

        System.out.println("\nFaculty " + faculty.getAssignedCourses().get(0).getCourseName() + " is assigned to: ");
        faculty.getAssignedCourses().forEach(course -> System.out.println(course.getCourseName()));
    }
}
