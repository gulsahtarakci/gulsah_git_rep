package class_load;

public class Customer {

	private int id;
	private String name;
	private String lastName;
	private String address;
	private String company;
	private Employee rep;

	public Customer(int id, String name, String lastName, String address,
			String company) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getCompany() {
		return company;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", lastName="
				+ lastName + ", address=" + address + ", company=" + company
				+ "]";
	}

	public Employee getRep() {
		return rep;
	}

	public void setRep(Employee rep) {
		this.rep = rep;
	}
	
}
