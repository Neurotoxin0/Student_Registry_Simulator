import java.util.ArrayList;


// Two student objects should be compared by their name
public class Student implements Comparable
{
  private String name;
  private String id;
  public  ArrayList<CreditCourse> courses;
  
  
  public Student(String name, String id)
  {
	 this.name = name;
	 this.id   = id;
	 courses   = new ArrayList<CreditCourse>();
  }


  public String getId()
  {
	  return id;
  }


  public String getName()
  {
	  return name;
  }


  /*
  // add a credit course to list of courses for this student
  // create a CreditCourse object
  // set course active
  // add to courses array list
   */
  public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
  {
      CreditCourse course = new CreditCourse(courseName, courseCode, descr, format, sem, grade);   // tmp: course
      course.setActive();
      courses.add(course);
  }


  // Prints a student transcript
  // Prints all completed (i.e. non active) courses for this student (course code, course name, semester, letter grade
  public void printTranscript()
  {
	  for (CreditCourse AllCourses : courses)
	  {
	    if (!AllCourses.getActive())   { System.out.println(AllCourses.displayGrade()); }     // if not active, which is, finished; print grade
      }
  }


  /*
  // Prints all active courses this student is enrolled in
  // see variable active in class CreditCourse
   */
  public void printActiveCourses()
  {
      for (CreditCourse AllCourses : courses)
      {
          if (AllCourses.getActive())   {System.out.println("\n" + AllCourses.getInfo());}   // if active, print course
      }
  }


  /*
  // Drop a course (given by courseCode)
  // Find the credit course in courses arraylist above and remove it
  // only remove it if it is an active course
   */
  public void removeActiveCourse(String courseCode)
  {
	 for (int i =0; i < courses.size(); i++)
     {
         if (courses.get(i).getCode().equals(courseCode) && (courses.get(i).getActive()))     {courses.remove(i);}    // find course using coursecode and check if it's active and drop the course
     }
  }


  public String toString() { return "Student ID: " + id + " Name: " + name; }


  /*
  // override equals method inherited from superclass Object
  // if student names are equal *and* student ids are equal (of "this" student
  // and "other" student) then return true
  // otherwise return false
  // Hint: you will need to cast other parameter to a local Student reference variable
   */
  public boolean equals(Object other)
  {
      Student otherstudent = (Student) other;
      if (getId().compareTo(otherstudent.getId()) == 0 || getName().compareTo(otherstudent.getName()) == 0)      {return true;}      // check both ID and Name to ensure it's the same person
      return false;
  }


    public int compareTo(Object other)
    {
        Student student = (Student) other;
        return getName().compareTo(student.getName());
    }

}
