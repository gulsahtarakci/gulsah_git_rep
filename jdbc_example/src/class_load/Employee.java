package class_load;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	
	private int id;
	private String name;
	private List<Customer> customers;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Employee(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		customers = new ArrayList<>();
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", customers="
				+ customers + "]";
	}
	
	
	

}
