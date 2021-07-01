package View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.DBConnection;
import Helper.Messager;
import Model.Manager;
import Model.Request;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class ManagerGUI extends JFrame {

	private JPanel contentPane;

	public static Manager manager = new Manager();
	public static Request request = new Request();

	private JTable table_Request;
	private DefaultTableModel requestModel;
	private Object[] requestData = null;

	DBConnection dbcon = new DBConnection();
	Connection con = null;
	Statement st = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerGUI frame = new ManagerGUI(manager);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public ManagerGUI(Manager manager) throws SQLException {

		requestModel = new DefaultTableModel();
		Object[] colRequest = new Object[5];
		colRequest[0] = "Ariza ID";
		colRequest[1] = "Cihaz Turu";
		colRequest[2] = "Aciklama";
		colRequest[3] = "Musteri ID";
		colRequest[4] = "Durum";
		requestModel.setColumnIdentifiers(colRequest);
		requestData = new Object[5];

		setTitle("Ariza Takip Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 255));
		panel.setBounds(10, 11, 1015, 449);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbl_Welcome = new JLabel("Hosgeldin " + manager.getName() + " " + manager.getSurname());
		lbl_Welcome.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Welcome.setBounds(10, 11, 400, 20);
		panel.add(lbl_Welcome);

		JButton btn_Exit = new JButton("Cikis Yap");
		btn_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lGUI = new LoginGUI();
				lGUI.setVisible(true);
				dispose();
			}
		});
		btn_Exit.setBackground(new Color(255, 255, 255));
		btn_Exit.setFocusable(false);
		btn_Exit.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_Exit.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_Exit.setBounds(900, 11, 100, 25);
		panel.add(btn_Exit);

		JLabel lbl_tableTitle = new JLabel("Ariza Talepleri");
		lbl_tableTitle.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_tableTitle.setBounds(0, 60, 150, 20);
		panel.add(lbl_tableTitle);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 85, 580, 353);
		panel.add(scrollPane);

		table_Request = new JTable(requestModel);
		table_Request.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Request.setFont(new Font("SansSerif", Font.PLAIN, 13));
		table_Request.getColumnModel().getColumn(0).setPreferredWidth(10);
		table_Request.getColumnModel().getColumn(1).setPreferredWidth(100);
		table_Request.getColumnModel().getColumn(2).setPreferredWidth(150);
		table_Request.getColumnModel().getColumn(3).setPreferredWidth(10);
		table_Request.getColumnModel().getColumn(4).setPreferredWidth(30);
		table_Request.setRowHeight(20);
		scrollPane.setViewportView(table_Request);

		JLabel lbl_cInfo = new JLabel("Musteri Bilgileri");
		lbl_cInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cInfo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_cInfo.setBounds(700, 85, 300, 20);
		panel.add(lbl_cInfo);

		JTextArea ta_cInfo = new JTextArea();
		ta_cInfo.setEditable(false);
		ta_cInfo.setLineWrap(true);
		ta_cInfo.setBackground(new Color(255, 255, 255));
		ta_cInfo.setBorder(UIManager.getBorder("TextField.border"));
		ta_cInfo.setWrapStyleWord(true);
		ta_cInfo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		ta_cInfo.setBounds(700, 110, 300, 80);
		panel.add(ta_cInfo);

		JLabel lbl_rInfo = new JLabel("Ariza Talebi");
		lbl_rInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_rInfo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_rInfo.setBounds(700, 210, 300, 20);
		panel.add(lbl_rInfo);

		JTextArea ta_rInfo = new JTextArea();
		ta_rInfo.setEditable(false);
		ta_rInfo.setWrapStyleWord(true);
		ta_rInfo.setLineWrap(true);
		ta_rInfo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		ta_rInfo.setBackground(new Color(255, 255, 255));
		ta_rInfo.setBorder(UIManager.getBorder("TextField.border"));
		ta_rInfo.setBounds(700, 235, 300, 80);
		panel.add(ta_rInfo);

		JLabel lbl_Status = new JLabel("Durum");
		lbl_Status.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Status.setBounds(700, 335, 150, 20);
		panel.add(lbl_Status);

		JTextArea ta_Status = new JTextArea();
		ta_Status.setLineWrap(true);
		ta_Status.setWrapStyleWord(true);
		ta_Status.setBackground(new Color(255, 255, 255));
		ta_Status.setBorder(UIManager.getBorder("TextField.border"));
		ta_Status.setFont(new Font("SansSerif", Font.PLAIN, 13));
		ta_Status.setBounds(700, 360, 150, 60);
		panel.add(ta_Status);

		JButton btn_Select = new JButton("Sec");
		btn_Select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_Request.getSelectionModel().isSelectionEmpty()) {
					Messager.showMessage("Lutfen tabloda bulunan bir ariza talebi seciniz!");
				} else {
					int selRow = table_Request.getSelectedRow();
					String selRequest = table_Request.getModel().getValueAt(selRow, 0).toString();
					String selDevice = table_Request.getModel().getValueAt(selRow, 1).toString();
					String selDesc = table_Request.getModel().getValueAt(selRow, 2).toString();
					String selCustomer = table_Request.getModel().getValueAt(selRow, 3).toString();
					String query = "SELECT name, surname, phone, address, email FROM ariza.register WHERE id = "
							+ selCustomer;
					try {
						con = dbcon.getConnection();
						st = con.createStatement();
						rs = st.executeQuery(query);
						while (rs.next()) {
							ta_cInfo.setText("Ad Soyad: " + rs.getString("name") + " " + rs.getString("surname") + "\n"
									+ "Telefon: " + rs.getString("phone") + "\n" + "Adres: " + rs.getString("address")
									+ "\n" + "Eposta: " + rs.getString("email"));
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					ta_rInfo.setText("Ariza ID: " + selRequest + "\n" + "Cihaz Turu: " + selDevice + "\n" + "Aciklama: "
							+ selDesc);

					String query2 = "SELECT status FROM ariza.request WHERE id = " + selRequest;
					try {
						con = dbcon.getConnection();
						st = con.createStatement();
						rs = st.executeQuery(query2);
						while (rs.next()) {
							ta_Status.setText(rs.getString("status"));
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}

				}
			}
		});

		btn_Select.setFocusable(false);
		btn_Select.setBackground(new Color(255, 255, 255));
		btn_Select.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_Select.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_Select.setBounds(590, 230, 100, 25);
		panel.add(btn_Select);

		JButton btn_updateRequest = new JButton("Guncelle");
		btn_updateRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!table_Request.getSelectionModel().isSelectionEmpty()) {
					if (ta_Status.getText().length() == 0) {
						Messager.showMessage("Lutfen ilgili alani doldurunuz!");
					} else if (ta_Status.getText().length() != 0) {
						int selRow = table_Request.getSelectedRow();
						String selID = table_Request.getModel().getValueAt(selRow, 0).toString();
						String durum = ta_Status.getText();
						String query = "UPDATE ariza.request SET status = ? WHERE id = ?";
						Messager.showMessage("success");
						try {
							con = dbcon.getConnection();
							pst = con.prepareStatement(query);
							pst.setString(1, durum);
							pst.setString(2, selID);
							pst.executeUpdate();
							updateRequestList();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				} else {
					Messager.showMessage("Guncellemek istediginiz talebi seciniz!");
				}

			}
		});

		btn_updateRequest.setFocusable(false);
		btn_updateRequest.setBackground(new Color(255, 255, 255));
		btn_updateRequest.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_updateRequest.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_updateRequest.setBounds(880, 360, 120, 25);
		panel.add(btn_updateRequest);

		JButton btn_delRequest = new JButton("Sil");
		btn_delRequest.setFocusable(false);
		btn_delRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!table_Request.getSelectionModel().isSelectionEmpty()) {
					if (Messager.confirm("del")) {

						int selRow = table_Request.getSelectedRow();
						int selID = (int) table_Request.getModel().getValueAt(selRow, 0);
						String query = "DELETE FROM ariza.request WHERE id = ?";
						Messager.showMessage("success");
						try {
							con = dbcon.getConnection();
							pst = con.prepareStatement(query);
							pst.setInt(1, selID);
							pst.executeUpdate();
							updateRequestList();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}

					}
				} else {
					Messager.showMessage("Silmek istediðiniz talebi seciniz!");
				}
			}
		});
		btn_delRequest.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_delRequest.setBackground(new Color(255, 255, 255));
		btn_delRequest.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_delRequest.setBounds(880, 393, 120, 25);
		panel.add(btn_delRequest);

		for (int i = 0; i < request.requestList().size(); i++) {
			requestData[0] = request.requestList().get(i).getId();
			requestData[1] = request.requestList().get(i).getDevice();
			requestData[2] = request.requestList().get(i).getDescription();
			requestData[3] = request.requestList().get(i).getRegister_id();
			requestData[4] = request.requestList().get(i).getStatus();
			requestModel.addRow(requestData);
		}
	}

	public void updateRequestList() throws SQLException {
		DefaultTableModel clearList = (DefaultTableModel) table_Request.getModel();
		clearList.setRowCount(0);
		for (int i = 0; i < request.requestList().size(); i++) {
			requestData[0] = request.requestList().get(i).getId();
			requestData[1] = request.requestList().get(i).getDevice();
			requestData[2] = request.requestList().get(i).getDescription();
			requestData[3] = request.requestList().get(i).getRegister_id();
			requestData[4] = request.requestList().get(i).getStatus();
			requestModel.addRow(requestData);
		}

	}
}
