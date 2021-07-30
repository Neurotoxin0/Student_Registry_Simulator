import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class Registry
{
   public TreeMap<String,Student> students = new TreeMap<String, Student>();
   public TreeMap<String,ActiveCourse> courses  = new TreeMap<String, ActiveCourse>();


   public Registry() throws FileNotFoundException,RuntimeException
   {
       // Add some students
	   // read from a file
       String name, id, line;
       File read = new File("students.txt");
       Scanner scanner = new Scanner(read);

       while (scanner.hasNextLine())
       {                                // choose to separate each line by taking them out to process, for avoiding bad file / format issues
           line = scanner.nextLine();   // the line that is being processed
           String[] SpliterLine = line.split(" ");
           name = SpliterLine[0];
           id   = SpliterLine[1];

           // ensure name:
           if (!name.matches("[a-zA-Z]+") || name.equals(""))      // check name to make sure it's legal and not empty
           {
               scanner.close();
               System.out.println("Invalid Name for Student: " + name);
               throw new ArrayIndexOutOfBoundsException();  // choose this exception to prevent when processing split(line 27), there may cause ArrayIndexOutOfBoundsException
           }

           // ensure Id:
           if (!id.matches("[0-9]+") || id.equals(""))      // check id to make sure it's legal and not empty
           {
               scanner.close();
               System.out.println("Invalid ID for Student: " + name + " for id: " + id);
               throw new ArrayIndexOutOfBoundsException();
           }

           Student tmpStudent = new Student(name, id);  // if passed check, then add student
           students.put(id, tmpStudent);

       }


       // Add some active courses
       ArrayList<Student> list = new ArrayList<Student>();  // used to store students that enrolled for this course
                                                      // empty when courses created
       // CPS209
	   String courseName = "Computer Science II";
	   String courseCode = "CPS209";
	   String descr = "Learn how to write complex programs!";
	   String format = "3Lec 2Lab";
	   ActiveCourse CPS209 = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
	   courses.put("CPS209",CPS209);
	   CPS209.main();   // used to activate the "main" method so that the list "lectureDay" can work properly

	   // CPS511
	   list.clear();
	   courseName = "Computer Graphics";
	   courseCode = "CPS511";
	   descr = "Learn how to write cool graphics programs";
	   format = "3Lec";
       ActiveCourse CPS511 = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
       courses.put("CPS511",CPS511);
       CPS511.main();   // used to activate the "main" method so that the list "lectureDay" can work properly
	   
	   // CPS643
	   list.clear();
	   courseName = "Virtual Reality";
	   courseCode = "CPS643";
	   descr = "Learn how to write extremely cool virtual reality programs";
	   format = "3Lec 2Lab";
       ActiveCourse CPS643 = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
       courses.put("CPS643",CPS643);
       CPS643.main();   // used to activate the "main" method so that the list "lectureDay" can work properly

       // CPS706
       courseName = "Computer Networks";
       courseCode = "CPS706";
       descr = "Learn about Computer Networking";
       format = "3Lec 1Lab";
       ActiveCourse CPS706 = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
       courses.put("CPS706",CPS706);
       CPS706.main();   // used to activate the "main" method so that the list "lectureDay" can work properly

       // CPS616
       courseName = "Algorithms";
       courseCode = "CPS616";
       descr = "Learn about Algorithms";
       format = "3Lec 1Lab";
       ActiveCourse CPS616 = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
       courses.put("CPS616",CPS616);
       CPS616.main();   // used to activate the "main" method so that the list "lectureDay" can work properly
   }


   public boolean trystudent(String studentId)      // try to find student
   {
       if (students.containsKey(studentId))     { return true; }
       else { return false; }
   }


   public boolean trycourse(String courseCode)      // try to find course
   {
       courseCode = courseCode.toUpperCase();   // convert coursecode to upperCase;
       if (courses.containsKey(courseCode))     { return true; }
       else { return false; }
   }


   public boolean addNewStudent(String name, String id)
   {
       if (students.containsKey(id) || students.containsValue(name))  // find if student name or id is already in the system
       {
           System.out.println("Student Name or ID already exist");
           return false;
       }
       Student newstudent = new Student(name, id);   // tmp: newstudent
	   students.put(id,newstudent);    // otherwise, add the student
       System.out.println("Added");
       return true;
   }


   public boolean removeStudent(String studentId)
   {
       if (trystudent(studentId))   // if found student
       {
           students.remove(studentId);
           System.out.println("Deleted");
           return true;
       }
       return false;
   }


   // Print all registered students
   public void printAllStudents()
   {
       Set<String> keySet = students.keySet();
	   for (String eachstudent : keySet)
	   {
		   System.out.println(students.get(eachstudent));
	   }
   }


   // Given a studentId and a course code, add student to the active course
   public void addCourse(String studentId, String courseCode)
   {
       boolean enrolled = false;
       if (trystudent(studentId))   // if found student
       {
           Student thestudent = students.get(studentId);     // tmp: thestudent
           for (CreditCourse enrolledcourses : thestudent.courses)     // searching the student's enrolled courses
           {
               if (enrolledcourses.getCode().equalsIgnoreCase(courseCode))     // if already enrolled
               {
                   enrolled = true;
               }
           }

           if (!enrolled)
           {
               if (trycourse(courseCode))      // find the course in ActiveCourse
                   {
                       ActiveCourse specificcourse = courses.get(courseCode);
                       specificcourse.students.add(thestudent);    // add student to ActiveCourse.students
                       thestudent.addCourse(specificcourse.getName(), specificcourse.getCode(), specificcourse.getDescription(), specificcourse.getFormat(), specificcourse.getSemester(), 0);     // initial grade should be set to zero
                       System.out.println("Added");
                   }
           }
           else { System.out.println("Already Enrolled"); }
       }
   }



   // Given a studentId and a course code, drop student from the active course
   public void dropCourse(String studentId, String courseCode)
   {
       // remove the course from Student.courses:
       if (trystudent(studentId))   // if found student
       {
           Student thestudent = students.get(studentId);     // tmp: thestudent
           // remove the course from Student.courses:
           for (int i = 0; i < thestudent.courses.size(); i++)
           {
               if (thestudent.courses.get(i).getCode().equalsIgnoreCase(courseCode))     // found the course in Student.courses
               {
                   thestudent.courses.remove(i);   // remove the course using index [i], which gets from method trystudent()
               }
           }

           // remove the student from ActiveCourse.students:
           if (trycourse(courseCode))
           {
               ActiveCourse specificcourse = courses.get(courseCode);
               for (int i =0; i < specificcourse.students.size(); i++)    // searching this student's index in ActiveCourse.students
                   {
                       if ((specificcourse.students.get(i)).getId().equals(studentId))    // identify Id to see if it's the same person
                       {
                           specificcourse.students.remove(i);    // i is the student's index in the list, and removing the student
                           System.out.println("Dropped");
                       }
                   }
           }
       }
   }


   // Print all active courses
   public void printActiveCourses()
   {
       System.out.println("\nPrinting All Active Courses:\n");
       Set<String> keySet = courses.keySet();
	   for (String eachcourse : keySet)
	   {
	       ActiveCourse thecourse = courses.get(eachcourse);
	       System.out.println(thecourse.getDescription() + "\n");
	   }
   }


   // Print the list of students in an active course
   public void printClassList(String courseCode)
   {
	 if (trycourse(courseCode))
     {
         ActiveCourse thecourse = courses.get(courseCode);   // tmp: thecourse; found the course
         thecourse.printClassList();    // using existing method from ActiveCourse to print all students
     }
   }


   // Given a course code, find course and sort class list by student name
   public void sortCourseByName(String courseCode)
   {
       if (trycourse(courseCode))
       {
           ActiveCourse thecourse = courses.get(courseCode);   // tmp: thecourse
           thecourse.sortByName();      // existing sorting method
       }
   }


   // Given a course code, find course and sort class list by student name
   public void sortCourseById(String courseCode)
   {
       if (trycourse(courseCode))
       {
           ActiveCourse thecourse = courses.get(courseCode);   // tmp: thecourse
           thecourse.sortById();      // existing sorting method
       }
   }


   // Given a course code, find course and print student names and grades
   public void printGrades(String courseCode)
   {
       if (trycourse(courseCode))
       {
           ActiveCourse thecourse = courses.get(courseCode);   // tmp: thecourse
           System.out.println("\nPrinting Students' Grades:\n");
           thecourse.printGrades();      // using existing method to print grades
       }
   }


   // Given a studentId, print all active courses of student
   public void printStudentCourses(String studentId)
   {
	   if (trystudent(studentId))
       {
           Student thestudent = students.get(studentId);
           System.out.println("Printing All Credit Courses of Student:");
           thestudent.printActiveCourses();     // using existing method from Student to print courses
       }
   }


   // Given a studentId, print all completed courses and grades of student
   public void printStudentTranscript(String studentId)
   {
       if (trystudent(studentId))
       {
           Student thestudent = students.get(studentId);
           thestudent.printTranscript();     // existing method from Student
       }
   }


   // Given a course code, student id and numeric grade
   // set the final grade of the student
   public void setFinalGrade(String courseCode, String studentId, double grade)
   {
       boolean Studentfound = false;

	   if (trystudent(studentId))   // if found student
       {
           Student thestudent = students.get(studentId);
           for (CreditCourse specificcourse : thestudent.courses)   // search enrolled courses
           {
               if (specificcourse.getCode().equalsIgnoreCase(courseCode))     // if coursecode matched
               {
                   Studentfound = true;
                   System.out.println("Grade Set");
                   specificcourse.setGrade(grade);  // set grade
                   specificcourse.setInactive();    // set inactive
               }
           }
           if (!Studentfound)   { System.out.println("Student Not Found In the Course"); }
       }
   }
}
