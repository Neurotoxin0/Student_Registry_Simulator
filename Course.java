public class Course
{
	private String code;
	private String name;
	private String description;
	private String format;


	public Course()
	{
	  this.code        = "";
	  this.name        = "";
	  this.description = "";
	  this.format      = "";
	}


	public Course(String name, String code, String descr, String fmt)
	{
	  this.code        = code;
	  this.name        = name;
	  this.description = descr;
	  this.format      = fmt;
	}


	public String getCode() { return code; }


	public String getName() { return name; }


	public String getFormat()
	{
	  return format;
	}


	public String getDescription()
	{
	  return code +" - " + name + "\n" + description + "\n" + format;
	}


	public String getInfo()
	 {
	   return code +" - " + name;
	 }


	 public static String convertNumericGrade(double grade)
	 {
		 if (grade >= 90)  {return "A+";}
		 if (grade >= 85 && grade < 90)  {return "A";}
		 if (grade >= 80 && grade < 85)  {return "A-";}
		 if (grade >= 75 && grade < 80)  {return "B+";}
		 if (grade >= 70 && grade < 75)  {return "B";}
		 if (grade >= 65 && grade < 70)  {return "B-";}
		 if (grade >= 60 && grade < 65)  {return "C+";}
		 if (grade >= 55 && grade < 60)  {return "C";}
		 if (grade >= 50 && grade < 55)  {return "C-";}
		 if (grade >= 45 && grade < 50)  {return "D+";}
		 if (grade >= 40 && grade < 45)  {return "D";}
		 if (grade >= 35 && grade < 40)  {return "D-";}
		 else    {return "F";}
	 }

}
