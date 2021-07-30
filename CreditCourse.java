public class CreditCourse extends Course
{
	private String  semester;
	public  double  grade;
	public  boolean active = false;


	public CreditCourse(String name, String code, String descr, String fmt, String semester, double grade)
	{
		super(name,code,descr,fmt);
		this.semester = semester;
		this.grade = grade;
	}


	public boolean getActive()	{ return active; }


	public void setActive()		{ active = true; }


	public void setInactive() 	{ active = false; }


	// add methods used to get semester, set grade and get grade

	public String getSemester()	{ return semester; }


	public void setGrade(double score)		{ grade = score; }


	public double getGrade()	{ return grade;}


	public String displayGrade() { return  "\n" + getCode() + " " + getName() + getSemester() + "; Grade: " + convertNumericGrade(grade); }
	
}