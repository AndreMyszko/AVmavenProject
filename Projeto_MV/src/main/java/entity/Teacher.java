package entity;

public class Teacher extends Person{
	//Attributes
	private int cod;
	private String rp;
	private String teaching;
	private String salary;
	
	//Constructors
	public Teacher() {
		super();
	}

	public Teacher(String rp, String teaching, String salary,
			String name, String email, String phone, String address) {
		super(name, email, phone, address);
		this.rp = rp;
		this.teaching = teaching;
		this.salary = salary;
	}
	
	public Teacher(int cod, String name, String email, String phone, String address, 
			String teaching, String rp, String salary, int codTea) {
		super(cod, name, email, phone, address);
		this.cod = codTea;
		this.teaching = teaching;
		this.salary = salary;
	}
	
	
	//Getters and Setters	
	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public String getRp() {
		return rp;
	}

	public void setRp(String rp) {
		this.rp = rp;
	}

	public String getTeaching() {
		return teaching;
	}

	public void setTeaching(String teaching) {
		this.teaching = teaching;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	
}
