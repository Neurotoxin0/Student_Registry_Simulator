import java.util.*;

// Active University Course
public class ActiveCourse extends Course  // can only inherit Course but CreditCourse, for CreditCourse have an extra grade variable
{
	public ArrayList<Student> students;
	private String semester;

    public Map<Integer, Integer> Mon = new HashMap<Integer, Integer>();
    public Map<Integer, Integer> Tue = new HashMap<Integer, Integer>();
    public Map<Integer, Integer> Wed = new HashMap<Integer, Integer>();
    public Map<Integer, Integer> Thur = new HashMap<Integer, Integer>();
    public Map<Integer, Integer> Fri = new HashMap<Integer, Integer>();

    public List<Map<Integer, Integer>> lectureDay = new ArrayList<Map<Integer,Integer>>();

    public void main()
    {
       lectureDay.add(Mon);
       lectureDay.add(Tue);
       lectureDay.add(Wed);
       lectureDay.add(Thur);
       lectureDay.add(Fri);
    }


   public ActiveCourse(String name, String code, String descr, String fmt,String semester,ArrayList<Student> students)
   {
      super(name,code,descr,fmt);
      this.semester = semester;
      this.students = new ArrayList<Student>(students);   // copy a new tmp named students instead of editing the original student list
   }


   public String getSemester() { return semester; }


   // Prints the students in this course  (name and student id)
   public void printClassList()
   {
      System.out.println("Course Name: " + getName() + ":\n");
      for (Student eachstudent : students)
      {
         System.out.println(eachstudent);
      }
   }


   // Returns the numeric grade in this course for this student
   // If student not found in course, return 0
   public double getGrade(String studentId)
   {

      for (Student eachstudent : students)
      {
         if (eachstudent.getId().equals(studentId))   // if we can find the student that is attending the course
         {
            for (CreditCourse selectedcourse : eachstudent.courses)   // find specific course
            {
               if (selectedcourse.getCode().equals(super.getCode()))  {return selectedcourse.getGrade();}   // if find, return grade
            }
         }
      }
            return 0;   // no matching student found in the course
   }


   // Prints the grade of each student in this course (along with name and student id)
   public void printGrades()
   {
      for (Student eachstudent : students)
         {
            System.out.println(eachstudent + ", ID: " + getGrade(eachstudent.getId()));  // reusing getGrade() functuon from above to print each student's grade
         }
   }


   // Returns a String containing the course information as well as the semester and the number of students enrolled in the course
   public String getDescription()
   {
	   return super.getDescription() + ", Semester: " + semester + " Number of Students Enrolled: " + students.size();
   }


   // Sort the students in the course by name using the Collections.sort() method with appropriate arguments
   // Make use of a private Comparator class below
   private class NameComparator implements Comparator<Student>
   {
      public int compare(Student A, Student B)    // sort alphabetically
      {
         return A.compareTo(B);
      }
   }
   public void sortByName()      // method from above, sort by name
   {
      System.out.println("Sorted by Name");
      Collections.sort(students, new NameComparator());
   }


   // Sort the students in the course by student id using the Collections.sort() method with appropriate arguments
   // Make use of a private Comparator class below
   private class IdComparator implements Comparator<Student>
   {
      public int compare(Student A, Student B)
      {
         int IDA = Integer.parseInt(A.getId());    // ID is String type, convert to int
         int IDB = Integer.parseInt(B.getId());
         return IDA - IDB;    // sort in numerical order
      }
   }
   public void sortById()     // method from above, sort by ID
   {
      System.out.println("Sorted by ID");
      Collections.sort(students, new IdComparator());
   }

}
