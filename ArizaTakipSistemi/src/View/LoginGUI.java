package View;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DBConnection;
import Helper.Messager;
import Model.Customer;
import Model.Manager;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Toolkit;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField tf_cUsername;
	private JPasswordField pf_cPassword;
	private JTextField tf_mUsername;
	private JPasswordField pf_mPassword;

	Customer customer = new Customer();
	Manager manager = new Manager();

	// Veri tabaný (database) baðlantýsý
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
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setTitle("Ariza Takip Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 341);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tabbedPane.setFocusable(false);
		tabbedPane.setBounds(10, 70, 364, 221);
		contentPane.add(tabbedPane);

		JPanel panel_Customer = new JPanel();
		panel_Customer.setFocusable(false);
		panel_Customer.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Musteri", null, panel_Customer, null);
		panel_Customer.setLayout(null);

		JLabel lbl_cUsername = new JLabel("Kullanici Adi:");
		lbl_cUsername.setBackground(new Color(255, 255, 255));
		lbl_cUsername.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_cUsername.setBounds(20, 30, 100, 25);
		panel_Customer.add(lbl_cUsername);

		tf_cUsername = new JTextField();
		tf_cUsername.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tf_cUsername.setBounds(130, 30, 209, 25);
		panel_Customer.add(tf_cUsername);
		tf_cUsername.setColumns(10);

		JLabel lbl_cPassword = new JLabel("Sifre:");
		lbl_cPassword.setBackground(new Color(255, 255, 255));
		lbl_cPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_cPassword.setBounds(20, 70, 100, 25);
		panel_Customer.add(lbl_cPassword);

		pf_cPassword = new JPasswordField();
		pf_cPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
		pf_cPassword.setBounds(130, 70, 209, 25);
		panel_Customer.add(pf_cPassword);

		JButton btn_cLogin = new JButton("Giris Yap");
		btn_cLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tf_cUsername.getText().length() == 0 || pf_cPassword.getText().length() == 0) {
					Messager.showMessage("fill");
				} else {
					boolean key = true;
					try {
						con = dbcon.getConnection();
						st = con.createStatement();
						rs = st.executeQuery("SELECT * FROM ariza.register");
						while (rs.next()) {
							if (tf_cUsername.getText().equals(rs.getString("username"))
									&& pf_cPassword.getText().equals(rs.getString("password"))) {
								customer.setId(rs.getInt("id"));
								customer.setName(rs.getString("name"));
								customer.setSurname(rs.getString("surname"));
								customer.setPhone(rs.getString("phone"));
								customer.setAddress(rs.getString("address"));
								customer.setEmail(rs.getString("email"));
								customer.setUsername(rs.getString("username"));
								customer.setPassword(rs.getString("password"));
								key = false;
								CustomerGUI cGUI = new CustomerGUI(customer);
								cGUI.setVisible(true);
								dispose();
							}

						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (key) {
						Messager.showMessage("Kullanici adi veya sifre yanlis!");
					}
				}

			}
		});
		btn_cLogin.setBackground(new Color(255, 255, 255));
		btn_cLogin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_cLogin.setFocusable(false);
		btn_cLogin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_cLogin.setBounds(60, 135, 100, 30);
		panel_Customer.add(btn_cLogin);

		JButton btn_cRegister = new JButton("Kayit Ol");
		btn_cRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
			}
		});
		btn_cRegister.setBackground(new Color(255, 255, 255));
		btn_cRegister.setFocusable(false);
		btn_cRegister.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_cRegister.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_cRegister.setBounds(199, 135, 100, 30);
		panel_Customer.add(btn_cRegister);

		JLabel lbl_cForgetPass = new JLabel("Sifremi Unuttum!");
		lbl_cForgetPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ForgetPassGUI fpGUI = new ForgetPassGUI();
				fpGUI.setVisible(true);
			}
		});
		lbl_cForgetPass.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lbl_cForgetPass.setBounds(130, 105, 209, 15);
		panel_Customer.add(lbl_cForgetPass);

		JPanel panel_Manager = new JPanel();
		panel_Manager.setFocusable(false);
		panel_Manager.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Yonetici", null, panel_Manager, null);
		panel_Manager.setLayout(null);

		JLabel lbl_mUsername = new JLabel("Kullanici Adi:");
		lbl_mUsername.setBackground(new Color(255, 255, 255));
		lbl_mUsername.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_mUsername.setBounds(20, 30, 100, 25);
		panel_Manager.add(lbl_mUsername);

		tf_mUsername = new JTextField();
		tf_mUsername.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tf_mUsername.setColumns(10);
		tf_mUsername.setBounds(130, 30, 209, 25);
		panel_Manager.add(tf_mUsername);

		JLabel lbl_mPassword = new JLabel("Sifre:");
		lbl_mPassword.setBackground(new Color(255, 255, 255));
		lbl_mPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_mPassword.setBounds(20, 70, 100, 25);
		panel_Manager.add(lbl_mPassword);

		pf_mPassword = new JPasswordField();
		pf_mPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
		pf_mPassword.setBounds(130, 70, 209, 25);
		panel_Manager.add(pf_mPassword);

		JButton btn_mLogin = new JButton("Giris Yap");
		btn_mLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tf_mUsername.getText().length() == 0 || pf_mPassword.getText().length() == 0) {
					Messager.showMessage("fill");
				} else {
					boolean key = true;
					try {
						con = dbcon.getConnection();
						st = con.createStatement();
						rs = st.executeQuery("SELECT * FROM ariza.manager");
						while (rs.next()) {
							if (tf_mUsername.getText().equals(rs.getString("username"))
									&& pf_mPassword.getText().equals(rs.getString("password"))) {
								manager.setId(rs.getInt("id"));
								manager.setName(rs.getString("name"));
								manager.setSurname(rs.getString("surname"));
								manager.setPhone(rs.getString("phone"));
								manager.setAddress(rs.getString("address"));
								manager.setEmail(rs.getString("email"));
								manager.setUsername(rs.getString("username"));
								manager.setPassword(rs.getString("password"));
								key = false;
								ManagerGUI mGUI = new ManagerGUI(manager);
								mGUI.setVisible(true);
								dispose();
							}

						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (key) {
						Messager.showMessage("Kullanici adi veya sifre yanlis!");
					}
				}

			}
		});

		JLabel lbl_mforgetPass = new JLabel("Sifremi Unuttum!");
		lbl_mforgetPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ForgetPassGUI fpGUI = new ForgetPassGUI();
				fpGUI.setVisible(true);
			}
		});
		lbl_mforgetPass.setBackground(new Color(255, 255, 255));
		lbl_mforgetPass.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lbl_mforgetPass.setBounds(130, 105, 209, 15);
		panel_Manager.add(lbl_mforgetPass);
		btn_mLogin.setBackground(new Color(255, 255, 255));
		btn_mLogin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_mLogin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_mLogin.setFocusable(false);
		btn_mLogin.setBounds(130, 135, 100, 30);
		panel_Manager.add(btn_mLogin);

		JLabel lbl_Welcome = new JLabel("HOSGELDINIZ");
		lbl_Welcome.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lbl_Welcome.setBounds(127, 25, 140, 25);
		contentPane.add(lbl_Welcome);
	}
}
