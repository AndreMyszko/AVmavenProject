package entity;

public abstract class Person {
	
	//Attributes
	private int cod;
	private String name;
	private String email;
	private String phone;
	private String address;
	
	//Constructors
	public Person() {
		
	}

	public Person(String name, String email, String phone, String address) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public Person(int cod, String name, String email, String phone, String address) {
		this.cod = cod;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	
	//Getters and Setters
	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}