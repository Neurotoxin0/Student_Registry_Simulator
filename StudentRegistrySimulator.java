import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentRegistrySimulator
{
  public static void main(String[] args)
  {

  	  Registry registry = null;

  	  try
	  {
	  	registry = new Registry();
	  }
	  catch (FileNotFoundException object)
	  {
		  System.out.println("Student File Not Found");
		  System.exit(-1);
	  }
	  catch (ArrayIndexOutOfBoundsException object)
	  {
		  System.out.println("Bad File Format in students.txt");
		  System.exit(-1);
	  }
	  finally
	  {
		  System.out.println("Loaded Students from File\n");
	  }

	  Scheduler scheduler = null;

  	  try
	  {
		  scheduler = new Scheduler(registry.courses);
	  }
	  catch (FileNotFoundException object)
	  {
		  System.out.println("Course File Not Found");
		  System.exit(-1);
	  }

  	  scheduler.main();		// activate the "main" method in scheduler

	  Scanner scanner = new Scanner(System.in);
	  System.out.print("Please Enter Command First:\n>");


	  while (scanner.hasNextLine())
	  {
		  String inputLine = scanner.nextLine();
		  if (inputLine == null || inputLine.equals("")) continue;


		  Scanner commandLine = new Scanner(inputLine);
		  String command = commandLine.next();


		  if (command == null || command.equals("")) continue;


		  else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
		  {
			  registry.printAllStudents();
		  }


		  else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT")) {
			  System.out.println("Exit");
			  return;
		  }


		  else if (command.equalsIgnoreCase("REG"))
		  {
			  // register a new student in registry
			  // get name and student id string
			  // e.g. reg JohnBoy 74345
			  // Checks:
			  //  ensure name is all alphabetic characters
			  //  ensure id string is all numeric characters
			  System.out.println("Please Enter Student's Name and ID:");
			  String name = scanner.next();
			  String id   = scanner.next();

			  // ensure name:
			  if (!name.matches("[a-zA-Z]+"))
			  {
			  		System.out.println("Invalid Symbol in Name");
				    System.out.print("\nPlease Enter Command:\n>");	// will not exit the system if invalid symbol in name, will continue
			  		continue;
			  }

			  // ensure Id:
			  if (!id.matches("[0-9]+"))
			  {
				  System.out.println("Invalid Symbol in ID");
				  System.out.print("\nPlease Enter Command:\n>");		// will not exit the system if invalid symbol in ID, will continue
				  continue;
			  }
			  registry.addNewStudent(name, id);
		  }


		  else if (command.equalsIgnoreCase("DEL"))
		  {
			  // delete a student from registry
			  // get student id
			  // ensure numeric
			  // remove student from registry
			  System.out.println("Please Enter Student's ID:");
			  String id   = scanner.next();

			  if (!id.matches("[0-9]+"))		// check ID format
			  {
				  System.out.println("Invalid Symbol in ID");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }
			  if (!registry.trystudent(id))		// if not found student
			  {
			  	  System.out.println("Student Not Found");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }
			  registry.removeStudent(id);
		  }


		  else if (command.equalsIgnoreCase("ADDC"))
		  {
			 // add a student to an active course
			 // get student id and course code strings
			 // add student to course (see class Registry)
			  System.out.println("Please Enter Student's ID and The CourseCode:");
			  String id   = scanner.next();
			  String code   = scanner.next().toUpperCase();

			  if (!id.matches("[0-9]+"))		// check ID format
			  {
				  System.out.println("Invalid Symbol in ID");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }
			  if (!registry.trystudent(id))		// if not found student
			  {
				  System.out.println("Student Not Found");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }
			  if (! registry.trycourse(code))		// if not found course
			  {
				  System.out.println("Course Not Found");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }
			  registry.addCourse(id, code);
		  }


		  else if (command.equalsIgnoreCase("DROPC"))
		  {
			  // get student id and course code strings
			  // drop student from course (see class Registry)
			  System.out.println("Please Enter Student's ID and The CourseCode:");
			  String id   = scanner.next();
			  String code   = scanner.next().toUpperCase();

			  if (!id.matches("[0-9]+"))		// check ID format
			  {
				  System.out.println("Invalid Symbol in ID");
				  System.out.print("\nPlease Enter Command:\n>");		// will not exit the system if invalid symbol in ID, will continue
				  continue;
			  }
			  if (!registry.trystudent(id))		// if not found student
			  {
				  System.out.println("Student Not Found");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }
			  if (!registry.trycourse(code))		// if not found course
			  {
				  System.out.println("Course Not Found");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }
			  registry.dropCourse(id, code);
		  }


		  else if (command.equalsIgnoreCase("PAC"))
		  {
			  // print all active courses
			  registry.printActiveCourses();
		  }


		  else if (command.equalsIgnoreCase("PCL"))
		  {
			  // get course code string
			  // print class list (i.e. students) for this course
			  System.out.print("\nPlease Enter CourseCode:\n>");
			  String code   = scanner.next().toUpperCase();

			  if (! registry.trycourse(code))		// if not found course
			  {
				  System.out.println("Course Not Found");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }
			  registry.printClassList(code);
		  }


		  else if (command.equalsIgnoreCase("PGR"))
		  {
			  // get course code string
			  // print name, id and grade of all students in active course
			  System.out.print("\nPlease Enter CourseCode:\n>");
			  String code   = scanner.next().toUpperCase();

			  if (! registry.trycourse(code))		// if not found course
			  {
				  System.out.println("Course not found");
				  System.out.print("\nPlease enter command:\n>");
				  continue;
			  }
			  registry.printGrades(code);
		  }


		  else if (command.equalsIgnoreCase("PSC"))
		  {
			  // get student id string
			  // print all credit courses of student
			  System.out.println("Please Enter Student's ID:");
			  String id   = scanner.next();

			  if (!id.matches("[0-9]+"))		// check ID format
			  {
				  System.out.println("Invalid Symbol in ID");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }
			  if (!registry.trystudent(id))		// if not found student
			  {
				  System.out.println("Student Not Found");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }
			  registry.printStudentCourses(id);
		  }


		  else if (command.equalsIgnoreCase("PST"))
		  {
			  // get student id string
			  // print student transcript
			  System.out.println("Please Enter Student's ID:");
			  String id   = scanner.next();

			  if (!id.matches("[0-9]+"))
			  {
				  System.out.println("Invalid Symbol in ID");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }
			  registry.printStudentTranscript(id);
		  }


		  else if (command.equalsIgnoreCase("SFG"))
		  {
			  // set final grade of student
			  // get course code, student id, numeric grade
			  // use registry to set final grade of this student (see class Registry)
			  System.out.println("Please Enter CourseCode, Student's ID and The Grade:");
			  String code = scanner.next().toUpperCase();
			  String id   = scanner.next();
			  String grade= scanner.next();		// for using matches(), have to set to String first


			  if (!id.matches("[0-9]+"))
			  {
				  System.out.println("Invalid Symbol in ID");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }

			  if (! registry.trycourse(code))		// if not found course
			  {
				  System.out.println("Course not found");
				  System.out.print("\nPlease enter command:\n>");
				  continue;
			  }

			  if (!registry.trystudent(id))		// if not found student
			  {
				  System.out.println("Student Not Found");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }

			  if (!grade.matches("[0-9]+"))
			  {
				  System.out.println("Invalid Symbol in Grade");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }

			  Double DoubleGrade = Double.parseDouble(grade);	// convert String grade to double
			  if (DoubleGrade > 100 || DoubleGrade < 0)		// invalid grade
			  {
				  System.out.println("Invalid Grade");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }
			  registry.setFinalGrade(code, id, DoubleGrade);
		  }


		  else if (command.equalsIgnoreCase("SCN"))
		  {
			  // get course code
			  // sort list of students in course by name (i.e. alphabetically)
			  // see class Registry
			  System.out.print("\nPlease Enter CourseCode:\n>");
			  String code = scanner.next().toUpperCase();
			  if (! registry.trycourse(code))		// if not found course
			  {
				  System.out.println("Course Not Found");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }
			  registry.sortCourseByName(code);
			  
		  }


		  else if (command.equalsIgnoreCase("SCI"))
		  {
			// get course code
			// sort list of students in course by student id
			// see class Registry
			  System.out.print("\nPlease Enter CourseCode:\n>");
			  String code = scanner.next().toUpperCase();

			  if (! registry.trycourse(code))		// if not found course
			  {
				  System.out.println("Course Not Found");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }
			  registry.sortCourseById(code);
		  }


		  else if (command.equalsIgnoreCase("SCH"))
		  {
			  // Schedule lecture time for a course
			  System.out.print("\nPlease Enter CourseCode, Day, StartTime and Duration:\n>");
			  String coursecode = scanner.next().toUpperCase();
			  String day = scanner.next().toUpperCase();
			  int starttime,duration;

			  // ensure day:
			  if (!day.matches("[a-zA-Z]+"))
			  {
				  System.out.println("Invalid Symbol in Day");
				  System.out.print("\nPlease Enter Command:\n>");	// will not exit the system if invalid symbol in name, will continue
				  continue;
			  }

			  // ensure starttime:
			  try 	{ starttime = scanner.nextInt(); }
			  catch	(InputMismatchException Object)
			  {
				  System.out.println("Invalid Symbol in Start Time");
				  continue;
			  }

			  // ensure duration:
			  try 	{ duration = scanner.nextInt(); }
			  catch	(InputMismatchException Object)
			  {
				  System.out.println("Invalid Symbol in Duration");
				  continue;
			  }

			  scheduler.setDayAndTime(coursecode,day,starttime,duration);
		  }


		  else if (command.equalsIgnoreCase("SCHA"))
		  {
			  // Schedule lecture time for a course
			  System.out.print("\nPlease Enter CourseCode and Duration:\n>");
			  String coursecode = scanner.next().toUpperCase();
			  int duration;

			  // ensure duration:
			  try 	{ duration = scanner.nextInt(); }
			  catch	(InputMismatchException Object)
			  {
				  System.out.println("Invalid Symbol in Duration");
				  continue;
			  }

			  scheduler.AutoSchedule(coursecode,duration);
		  }


		  else if (command.equalsIgnoreCase("CSCH"))
		  {
			  // Clears the schedule if the given course
			  System.out.print("\nPlease Enter CourseCode:\n>");
			  String code = scanner.next().toUpperCase();

			  if (! registry.trycourse(code))		// if not found course
			  {
				  System.out.println("Course Not Found");
				  System.out.print("\nPlease Enter Command:\n>");
				  continue;
			  }
			  scheduler.clearSchedule(code);
		  }


		  else if (command.equalsIgnoreCase("PSCH"))
		  {
			  // Prints the entire schedule
			  System.out.print("\nPrinting the Entire Schedule:\n");

			  scheduler.printSchedule();
		  }

		  System.out.print("\n\nPlease Enter Command:\n>");
	  }
  }

}