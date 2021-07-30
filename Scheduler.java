import java.io.FileNotFoundException;
import java.util.*;


public class Scheduler extends Registry
{
	// where Day have a size of 5, representing Mon to Fri, and for each map, the KeySet is the time that already in use with a Value of 1 (Not in use if not contain the KeySet)
	public Map<Integer, String> MON = new HashMap<Integer, String>();
	public Map<Integer, String> TUE = new HashMap<Integer, String>();
	public Map<Integer, String> WED = new HashMap<Integer, String>();
	public Map<Integer, String> THU = new HashMap<Integer, String>();
	public Map<Integer, String> FRI = new HashMap<Integer, String>();

	public List<Map<Integer, String>> Day = new ArrayList<Map<Integer, String>>();

	int DayIndex;

	public void main()
	{
		Day.add(MON);
		Day.add(TUE);
		Day.add(WED);
		Day.add(THU);
		Day.add(FRI);
	}

	boolean checkday, checkduration, checkstarttime, checkcourse;
	boolean checkcollision = true;
	boolean checkfit = true;	// check if there is enough time for the course to be scheduled in a specific day
	// ex: Monday have one hour unused time left, but system want to schedule 2 hour on Monday, then return false
	
	TreeMap<String,ActiveCourse> schedule;
		
	public Scheduler(TreeMap<String,ActiveCourse> courses) throws FileNotFoundException { schedule = courses; }


	public boolean ifcanschedule(String courseCode, String day, int startTime, int duration)	// try to schedule at given time
	{
		// reset these two boolean before every time using the method
		boolean checkcollision = true;
		boolean checkfit = true;

		// check course code
		if (trycourse(courseCode))	{ checkcourse = true; }
		else	{ checkcourse = false; }


		// check day
		switch (day)
		{
			case "MON": checkday = true;	DayIndex = 0;	break;
			case "TUE": checkday = true;	DayIndex = 1;	break;
			case "WED": checkday = true;	DayIndex = 2;	break;
			case "THU": checkday = true;	DayIndex = 3;	break;
			case "FRI": checkday = true;	DayIndex = 4;	break;
			default: checkday = false;		break;
		}


		// check start time
		String hour, minute;
		String time = Integer.toString(startTime);	// have to convert the given start time to String to process

		if (time.length() == 3)		{ time = "0" + time; }	// if there is only three digit(800-->08:00), add a zero to the front
		if (time.length() != 4)		{  System.out.println("Invalid StartTime"); return false; } // check if the time given is valid

		// split hour and minute and convert them back to int:

		hour = time.substring(0,2);
		minute = time.substring(2,4);
		int h = Integer.parseInt(hour);
		int m = Integer.parseInt(minute);

		if (h < 8 || h > 17) { checkstarttime = false; }        // hour   range: 8<= h <=17
		else if (m < 00 || m >= 60) { checkstarttime = false; }        // minute range: 0<= m < 60
		else { checkstarttime = true; }


		// check duration
		switch (duration)
		{
			case 1: checkduration = true;	break;
			case 2: checkduration = true;	break;
			case 3: checkduration = true;	break;
			default: checkduration = false;	break;
		}


		// check collision:
		// check if given time period is in using
		Map<Integer,String> thisday = Day.get(DayIndex);

		for (int i = startTime; i <= (startTime + duration * 100); i += 100)
		{
			if (thisday.containsKey(i))
			{
				checkcollision = false;
				break;
			}
		}


		// check fit
		if (startTime + (duration-1)*100 > 1700) { checkfit = false; }


		if (checkcourse && checkday && checkstarttime && checkduration && checkcollision && checkfit) { return true; }
		else	{ return false;}
	}


	public void setDayAndTime(String courseCode, String day, int startTime, int duration)
	{
		if (ifcanschedule(courseCode, day, startTime, duration))
		{
			Map<Integer,String> thisday = Day.get(DayIndex);
			for (int i = startTime; i <= (startTime + (duration-1) * 100); i += 100)
			{
				thisday.put(i, courseCode);
			}

			ActiveCourse thiscourse = courses.get(courseCode);
			Map<Integer, Integer> theday = thiscourse.lectureDay.get(DayIndex);
			theday.put(startTime, duration);
			System.out.println("Scheduled");
		}
		else if (! checkcourse)		{ System.out.println("Course Not Found"); }
		else if (! checkday)		{ System.out.println("Invalid Day"); }
		else if (! checkstarttime)	{ System.out.println("Invalid StartTime"); }
		else if (! checkduration)  	{ System.out.println("Invalid Duration"); }
		else if (! checkcollision)	{ System.out.println("Given Period Overlaps with Another Scheduled Course"); }
		else if (! checkfit)		{ System.out.println("The End Time Is Over 17:00"); }
		else						{ System.out.println("Not Scheduled"); }
		// System.out.println(Day);		// TESTCODE: used for check if it's been successfully scheduled
	}



	public boolean AutoSchedule(String courseCode, int duration)
	{
		Set<String> daynames = new TreeSet<String>();
		daynames.add("MON");
		daynames.add("TUE");
		daynames.add("WED");
		daynames.add("THU");
		daynames.add("FRI");

		for (String day : daynames)
		{
			for (int i = 800; i <= 1700; i+=100)
			{
				// System.out.println("Day: " + day + "---Time: " + i + " ;Duration: " +duration);		// TESTCODE: used for check the auto schedule
				if (ifcanschedule(courseCode, day, i, duration))
				{
					setDayAndTime(courseCode, day, i, duration);
					return true;
				}
				//else if (! checkcourse)		{ System.out.println("Course Not Found"); }
				//else if (! checkduration)  	{ System.out.println("Invalid Duration"); }
				else if (! checkcourse)		{ System.out.println("Course Not Found"); }
				else if (! checkday)		{ System.out.println("Invalid Day"); }
				else if (! checkstarttime)	{ System.out.println("Invalid StartTime"); }
				else if (! checkduration)  	{ System.out.println("Invalid Duration"); }
				else if (! checkcollision)	{ System.out.println("Given Period Overlaps with Another Scheduled Course"); }
				else if (! checkfit)		{ System.out.println("The End Time Is Over 17:00"); }
			}
		}
		System.out.println("Cannot Find A Proper Time to Schedule This Course");
		return false;
	}


	public void clearSchedule(String courseCode)
	{
		if (trycourse(courseCode))
		{
			for (Map<Integer, String> today : Day)
			{
				Set<Map.Entry<Integer,String>> entrySet = today.entrySet();
				entrySet.removeIf(thistime -> thistime.getValue().equals(courseCode));
			}

			ActiveCourse thiscourse = courses.get(courseCode);
			thiscourse.lectureDay.clear();
			System.out.println("Scheduled Course Cleared");
		}
	}
		
	public void printSchedule()
	{
		// System.out.println(Day);		// TESTCODE: used for check if it's been successfully scheduled
		System.out.print("\t\tMon\t\tTue\t\tWed\t\tThu\t\tFri");

		for (int i = 800; i < 1700; i+=100)
		{
			if (i == 800)	{ System.out.print("\n" + "0800" + "\t"); }		// format is weired for 0800 and 0900 for it only shows 800 and 900
			else if (i == 900)	{ System.out.print("\n" + "0900" + "\t"); }		// therefore, add these two special case to avoid that
			else 	{ System.out.print("\n" + i + "\t"); }

			for (int a =0; a < Day.size(); a++)
			{
				Map<Integer,String> thisday = Day.get(a);
				System.out.print(thisday.get(i) + "\t");
			}
		}

	}


	/*
	private class InvalidDayException extends RuntimeException
	{
		public InvalidDayException(String message) 	{ super(message); }
	}


	private class InvalidDurationParameter extends RuntimeException
	{
		public InvalidDurationParameter(String message) { super(message); }
	}


	private class InvalidTimeException extends RuntimeException
	{
		public InvalidTimeException(String message) { super(message); }
	}


	private class UnknownCourseException extends RuntimeException
	{
		public UnknownCourseException() {}
	}


	private class LectureTimeCollisionException extends RuntimeException
	{
		public LectureTimeCollisionException(String message) { super(message); }
	}
	 */

}

