package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Request {

	private int id;
	private String device;
	private String description;
	private int register_id;
	private String status;

	public Customer customer = new Customer();

	DBConnection dbcon = new DBConnection();
	Connection con = null;
	Statement st = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	// Default constructor
	public Request() {

	}

	// createRequest() constructor
	public Request(int id, String device, String description, int register_id) {
		this.id = id;
		this.device = device;
		this.description = description;
		this.register_id = register_id;
	}

	// requestList() constructor
	public Request(int id, String device, String description, int register_id, String status) {
		this.id = id;
		this.device = device;
		this.description = description;
		this.register_id = register_id;
		this.status = status;
	}

	// CustomerGUI'da bulunan "Yeni Talep Oluþtur" butonunun fonksiyonu
	public boolean createRequest(String device, String description, int register_id) throws SQLException {
		boolean key = false;
		try {
			String query = "INSERT INTO ariza.request (device, description, register_id) VALUES (?, ?, ?)";
			con = dbcon.getConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, device);
			pst.setString(2, description);
			pst.setInt(3, register_id);
			pst.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key) {
			return true;
		} else {
			return false;
		}
	}

	// ManagerGUI'da bulunan tabloya arýza taleplerini ekleyen fonksiyon
	public ArrayList<Request> requestList() throws SQLException {
		ArrayList<Request> talepList = new ArrayList<Request>();
		String query = "SELECT * FROM ariza.request";
		try {
			con = dbcon.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query);
			Request talep;
			while (rs.next()) {
				talep = new Request(rs.getInt("id"), rs.getString("device"), rs.getString("description"),
						rs.getInt("register_id"), rs.getString("status"));
				talepList.add(talep);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return talepList;
	}

	public boolean delRequest(int id) throws SQLException {
		boolean key = false;
		String query = "DELETE FROM ariza.request WHERE id = ?";
		try {
			con = dbcon.getConnection();
			pst = con.prepareStatement(query);
			pst.setInt(1, id);
			key = true;
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

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRegister_id() {
		return register_id;
	}

	public void setRegister_id(int register_id) {
		this.register_id = register_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
