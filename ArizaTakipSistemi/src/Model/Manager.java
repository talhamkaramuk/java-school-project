package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Helper.DBConnection;

public class Manager extends Customer{

	private int id;
	private String name;
	private String surname;
	private String phone;
	private String address;
	private String email;
	private String username;
	private String password;

	DBConnection dbcon = new DBConnection();
	Connection con = null;
	Statement st = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	// Default constructor
	public Manager() {

	}

	public Manager(int id, String name, String surname, String phone, String address, String email, String username,
			String password) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
