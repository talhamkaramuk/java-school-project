package Model;

import java.sql.*;

import Helper.*;

public class Customer {

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
	public Customer() {

	}

	// Register() constructor
	public Customer(int id, String name, String surname, String phone, String address, String email, String username,
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

	// LoginGUI'da bulunan "Kayýt Ol" butonunun fonksiyonu
	public boolean Register(String name, String surname, String phone, String address, String email, String username,
			String password) throws SQLException {
		boolean key = false;
		boolean duplicate = false;

		try {
			String query1 = "SELECT * FROM ariza.register WHERE username = '" + username + "'";
			String query2 = "INSERT INTO ariza.register (name, surname, phone, address, email, username, password) VALUES (?,?,?,?,?,?,?)";
			con = dbcon.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				duplicate = true;
				Messager.showMessage("Bu kullanici adi sistemde mevcuttur, lütfen baska bir kullanici adi girin!");
				break;
			}

			if (!duplicate) {
				pst = con.prepareStatement(query2);
				pst.setString(1, name);
				pst.setString(2, surname);
				pst.setString(3, phone);
				pst.setString(4, address);
				pst.setString(5, email);
				pst.setString(6, username);
				pst.setString(7, password);
				pst.executeUpdate();
				key = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (key) {
			return true;
		} else {
			return false;
		}
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
