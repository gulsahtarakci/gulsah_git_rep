package class_load;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		List<Customer> customerList = new ArrayList<>();
		Map<Integer, Employee> employeeMap = new HashMap<>();
		try {
			Class.forName("org.postgresql.Driver"); 
			System.out.println("Driver loaded.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		String url = "jdbc:postgresql://localhost/jss2014"; 
		String user = "postgres";
		String password = "1234";
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("connection created.");
			
			String randomName = NameUtil.getRandomName();
			String[] splitted = randomName.split(" ");
			String insertQuery = "INSERT INTO "
					+ "customers(name, lastname, address, company) "
					+ "VALUES (?, ?, ?, ?);";
			PreparedStatement updateStatement = connection.prepareStatement(insertQuery);
			updateStatement.setString(1, splitted[0]);
			updateStatement.setString(2, splitted[1]);
			updateStatement.setString(3, "123 "+splitted[0]+" sk.");
			updateStatement.setString(4, splitted[0]+" A.S.");
			int updateResult = updateStatement.executeUpdate();
			System.out.println(updateResult+" customer(s) inserted.");
			
			Statement statement = connection.createStatement();
			String query = "SELECT customers.name, lastname, address, "
					+ "company, customers.id, rep_id, employees.name AS rep_name "
					+ "FROM customers LEFT JOIN employees "
					+ "ON customers.rep_id = employees.id";
			ResultSet results= statement.executeQuery(query);
			while(results.next()){
				int id = results.getInt("id");
				String name = results.getString("name");
				String lastName = results.getString("lastname");
				String address = results.getString("address");
				String company = results.getString("company");
				int repId = results.getInt("rep_id");
				Customer customer = new Customer(id, name, lastName, address, company);
				if(repId!=0){
					if(!employeeMap.containsKey(repId)){
						Employee e = new Employee(repId, results.getString("rep_name"));
						employeeMap.put(repId, e);
					}
					Employee e = employeeMap.get(repId);
					customer.setRep(e);
					e.getCustomers().add(customer);
				}
				customerList.add(customer);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (Customer customer : customerList) {
			System.out.println(customer);
		}
		
		System.out.println(employeeMap);

	}

}
