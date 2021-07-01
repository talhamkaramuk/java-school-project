package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DBConnection;
import Helper.Messager;
import Model.Customer;
import Model.Request;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class RequestGUI extends JFrame {

	private JPanel contentPane;
	private JTextField tf_NameSurname;
	private JTextField tf_Phone;
	private JTextField tf_Email;

	public static Customer customer = new Customer();
	public static Request request = new Request();

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
					RequestGUI frame = new RequestGUI(customer);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RequestGUI(Customer customer) {
		setTitle("Ariza Takip Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 255));
		panel.setBounds(10, 11, 490, 349);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbl_NameSurname = new JLabel("Ad Soyad:");
		lbl_NameSurname.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_NameSurname.setBounds(10, 10, 200, 20);
		panel.add(lbl_NameSurname);

		tf_NameSurname = new JTextField();
		tf_NameSurname.setFocusable(false);
		tf_NameSurname.setBackground(new Color(255, 255, 255));
		tf_NameSurname.setEditable(false);
		tf_NameSurname.setText(customer.getName() + " " + customer.getSurname());
		tf_NameSurname.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tf_NameSurname.setBounds(10, 35, 200, 20);
		panel.add(tf_NameSurname);
		tf_NameSurname.setColumns(10);

		JLabel lbl_Phone = new JLabel("Telefon:");
		lbl_Phone.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Phone.setBounds(10, 70, 200, 20);
		panel.add(lbl_Phone);

		tf_Phone = new JTextField();
		tf_Phone.setFocusable(false);
		tf_Phone.setBackground(new Color(255, 255, 255));
		tf_Phone.setEditable(false);
		tf_Phone.setText("" + customer.getPhone());
		tf_Phone.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tf_Phone.setBounds(10, 95, 200, 20);
		panel.add(tf_Phone);
		tf_Phone.setColumns(10);

		JLabel lbl_Address = new JLabel("Adres:");
		lbl_Address.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Address.setBounds(10, 130, 200, 20);
		panel.add(lbl_Address);

		JTextArea ta_Address = new JTextArea();
		ta_Address.setBorder(UIManager.getBorder("TextField.border"));
		ta_Address.setFocusable(false);
		ta_Address.setEditable(false);
		ta_Address.setText("" + customer.getAddress());
		ta_Address.setWrapStyleWord(true);
		ta_Address.setLineWrap(true);
		ta_Address.setFont(new Font("SansSerif", Font.PLAIN, 13));
		ta_Address.setBounds(10, 155, 200, 60);
		panel.add(ta_Address);

		JLabel lbl_Email = new JLabel("Email:");
		lbl_Email.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Email.setBounds(10, 225, 200, 20);
		panel.add(lbl_Email);

		tf_Email = new JTextField();
		tf_Email.setFocusable(false);
		tf_Email.setBackground(new Color(255, 255, 255));
		tf_Email.setEditable(false);
		tf_Email.setText("" + customer.getEmail());
		tf_Email.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tf_Email.setBounds(10, 250, 200, 20);
		panel.add(tf_Email);
		tf_Email.setColumns(10);

		JLabel lbl_DeviceType = new JLabel("Cihaz Turu:");
		lbl_DeviceType.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_DeviceType.setBounds(280, 10, 200, 20);
		panel.add(lbl_DeviceType);

		JComboBox cbox_DeviceType = new JComboBox();
		cbox_DeviceType.setBorder(new EmptyBorder(0, 0, 0, 0));
		cbox_DeviceType.setBackground(new Color(255, 255, 255));
		cbox_DeviceType.addItem("");
		cbox_DeviceType.addItem("Bilgisayar/Cevre Birimleri");
		cbox_DeviceType.addItem("DVD Player");
		cbox_DeviceType.addItem("Monitor");
		cbox_DeviceType.addItem("Projeksiyon Cihazi");
		cbox_DeviceType.addItem("Radyo");
		cbox_DeviceType.addItem("Ses Sistemi");
		cbox_DeviceType.addItem("Telefon");
		cbox_DeviceType.addItem("Televizyon");
		cbox_DeviceType.setMaximumRowCount(10);
		cbox_DeviceType.setFont(new Font("SansSerif", Font.PLAIN, 13));
		cbox_DeviceType.setBounds(280, 35, 200, 22);
		panel.add(cbox_DeviceType);

		JTextArea ta_Desc1 = new JTextArea();
		ta_Desc1.setFocusable(false);
		ta_Desc1.setEditable(false);
		ta_Desc1.setWrapStyleWord(true);
		ta_Desc1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		ta_Desc1.setText("Arizayi birkac cumle ile tarif ediniz.");
		ta_Desc1.setLineWrap(true);
		ta_Desc1.setBackground(new Color(204, 204, 255));
		ta_Desc1.setBounds(280, 70, 205, 45);
		panel.add(ta_Desc1);

		JTextArea ta_Desc2 = new JTextArea();
		ta_Desc2.setBorder(UIManager.getBorder("TextField.border"));
		ta_Desc2.setFont(new Font("SansSerif", Font.PLAIN, 13));
		ta_Desc2.setWrapStyleWord(true);
		ta_Desc2.setLineWrap(true);
		ta_Desc2.setBounds(280, 120, 200, 150);
		panel.add(ta_Desc2);

		JButton btn_Request = new JButton("Talep Olustur");
		btn_Request.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbox_DeviceType.getSelectedIndex() == -1 || ta_Desc2.getText().length() == 0) {
					Messager.showMessage("fill");
				} else {
					String cbox = cbox_DeviceType.getSelectedItem().toString();
					try {
						boolean control = request.createRequest(cbox, ta_Desc2.getText(), customer.getId());
						if (control) {
							Messager.showMessage("Talebiniz basariyla olusturulmustur!");
							dispose();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_Request.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_Request.setBackground(new Color(255, 255, 255));
		btn_Request.setFocusable(false);
		btn_Request.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_Request.setBounds(170, 297, 150, 30);
		panel.add(btn_Request);
	}

}
