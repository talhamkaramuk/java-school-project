package View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DBConnection;
import Model.Customer;
import Model.Request;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomerGUI extends JFrame {

	private JPanel contentPane;
	public static Customer customer = new Customer();
	public static Request request = new Request();

	public static JList list_RequestList = new JList();
	DefaultListModel model = new DefaultListModel();

	DBConnection dbcon = new DBConnection();
	Connection con = null;
	Statement st = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	private JTextField tf_requestID;
	private JTextField tf_Device;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerGUI frame = new CustomerGUI(customer);
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
	public CustomerGUI(Customer customer) throws SQLException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				LoginGUI lGUI = new LoginGUI();
				lGUI.setVisible(true);
				dispose();
			}
		});
		setTitle("Ariza Takip Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 470);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 255));
		panel.setBounds(10, 11, 435, 419);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbl_Welcome = new JLabel("Hosgeldin " + customer.getName() + " " + customer.getSurname());
		lbl_Welcome.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Welcome.setBounds(10, 11, 270, 20);
		panel.add(lbl_Welcome);

		JButton btn_newRequest = new JButton("Yeni Ariza Talebi");
		btn_newRequest.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_newRequest.setFocusable(false);
		btn_newRequest.setBackground(new Color(255, 255, 255));
		btn_newRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RequestGUI rGUI = new RequestGUI(customer);
				rGUI.setVisible(true);
			}
		});
		btn_newRequest.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_newRequest.setBounds(10, 60, 150, 25);
		panel.add(btn_newRequest);

		JButton btn_Exit = new JButton("Cikis Yap");
		btn_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lGUI = new LoginGUI();
				lGUI.setVisible(true);
				dispose();
			}
		});
		btn_Exit.setBackground(new Color(255, 255, 255));
		btn_Exit.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_Exit.setFocusable(false);
		btn_Exit.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_Exit.setBounds(320, 11, 100, 25);
		panel.add(btn_Exit);

		JLabel lbl_requestID = new JLabel("Talep ID:");
		lbl_requestID.setFocusable(false);
		lbl_requestID.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_requestID.setBounds(270, 95, 150, 20);
		panel.add(lbl_requestID);

		tf_requestID = new JTextField();
		tf_requestID.setFocusable(false);
		tf_requestID.setEditable(false);
		tf_requestID.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tf_requestID.setBorder(UIManager.getBorder("TextField.border"));
		tf_requestID.setBackground(new Color(255, 255, 255));
		tf_requestID.setBounds(270, 120, 150, 20);
		panel.add(tf_requestID);
		tf_requestID.setColumns(10);

		JLabel lbl_Device = new JLabel("Cihaz Turu:");
		lbl_Device.setFocusable(false);
		lbl_Device.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Device.setBounds(270, 155, 150, 20);
		panel.add(lbl_Device);

		tf_Device = new JTextField();
		tf_Device.setFocusable(false);
		tf_Device.setEditable(false);
		tf_Device.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tf_Device.setBorder(UIManager.getBorder("TextField.border"));
		tf_Device.setBackground(new Color(255, 255, 255));
		tf_Device.setBounds(270, 180, 150, 20);
		panel.add(tf_Device);
		tf_Device.setColumns(10);

		JLabel lbl_Desc = new JLabel("Aciklama:");
		lbl_Desc.setFocusable(false);
		lbl_Desc.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Desc.setBounds(270, 215, 150, 20);
		panel.add(lbl_Desc);

		JTextArea ta_Desc = new JTextArea();
		ta_Desc.setWrapStyleWord(true);
		ta_Desc.setLineWrap(true);
		ta_Desc.setFocusable(false);
		ta_Desc.setEditable(false);
		ta_Desc.setBorder(UIManager.getBorder("TextField.border"));
		ta_Desc.setBackground(new Color(255, 255, 255));
		ta_Desc.setFont(new Font("SansSerif", Font.PLAIN, 13));
		ta_Desc.setBounds(270, 240, 150, 60);
		panel.add(ta_Desc);

		JLabel lbl_Status = new JLabel("Durum:");
		lbl_Status.setFocusable(false);
		lbl_Status.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Status.setBounds(270, 315, 150, 20);
		panel.add(lbl_Status);

		JTextArea ta_Status = new JTextArea();
		ta_Status.setEditable(false);
		ta_Status.setBounds(270, 340, 150, 60);
		panel.add(ta_Status);

		JLabel lbl_RequestList = new JLabel("Ariza Taleplerim");
		lbl_RequestList.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_RequestList.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_RequestList.setBounds(10, 105, 125, 20);
		panel.add(lbl_RequestList);

		list_RequestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_RequestList.setFont(new Font("SansSerif", Font.PLAIN, 15));
		list_RequestList.setBorder(UIManager.getBorder("TextField.border"));
		list_RequestList.setBounds(10, 128, 125, 280);
		panel.add(list_RequestList);
//		refreshRequestList();
		String query = "SELECT id FROM ariza.request WHERE register_id = " + customer.getId();
		con = dbcon.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query);
		while (rs.next()) {
			int data = rs.getInt("id");
			model.addElement(data);
		}
		list_RequestList.setModel(model);

		JButton btn_Select = new JButton("Sec");
		btn_Select.setBackground(new Color(255, 255, 255));
		btn_Select.setFocusable(false);
		btn_Select.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_Select.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_Select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!list_RequestList.isSelectionEmpty()) {
					int selValue = (int) list_RequestList.getSelectedValue();
					String query = "SELECT * FROM ariza.request WHERE id = " + selValue;
					try {
						con = dbcon.getConnection();
						pst = con.prepareStatement(query);
						rs = pst.executeQuery();
						while (rs.next()) {
							tf_requestID.setText("" + rs.getInt("id"));
							tf_Device.setText("" + rs.getString("device"));
							ta_Desc.setText("" + rs.getString("description"));
							if (rs.getString("status") == null) {
								ta_Status.setText("");
							} else {
								ta_Status.setText(rs.getString("status"));
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		btn_Select.setBounds(152, 230, 100, 25);
		panel.add(btn_Select);

		JButton btn_Refresh = new JButton("Yenile");
		btn_Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel refresh = (DefaultListModel) list_RequestList.getModel();
				refresh.removeAllElements();
				String query = "SELECT id FROM ariza.request WHERE register_id = " + customer.getId();
				try {
					con = dbcon.getConnection();
					st = con.createStatement();
					rs = st.executeQuery(query);
					while (rs.next()) {
						int data = rs.getInt("id");
						model.addElement(data);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				list_RequestList.setModel(model);
			}
		});
		btn_Refresh.setFocusable(false);
		btn_Refresh.setBackground(new Color(255, 255, 255));
		btn_Refresh.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_Refresh.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btn_Refresh.setBounds(145, 128, 60, 20);
		panel.add(btn_Refresh);

	}
}
