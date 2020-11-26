package entity;

public class Student extends Person {

	//Attributes
	private int cod;
	private String ra;
	private String course;
	private String period;
	
	//Constructors
	public Student() {
		super();
	}

	public Student(String name, String email, String phone, String address, 
			String ra, String course, String period) {
		super(name, email, phone, address);
		this.ra = ra;
		this.course = course;
		this.period = period;
	}
	
	public Student(String name, String email, String phone, String address, 
			String period, String ra, String course, int cod) {
		super(name, email, phone, address);
		this.cod = cod;
		this.ra = ra;
		this.course = course;
		this.period = period;
	}

	public Student(int cod, String name, String email, String phone, String address, 
			  int codStu, String ra, String course, String period  ) {
		super(cod, name, email, phone, address);
		this.cod = codStu;
		this.ra = ra;
		this.course = course;
		this.period = period;
	}
	
	//Getters and Setters
	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}	
	
}
