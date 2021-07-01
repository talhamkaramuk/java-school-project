package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Messager;
import Model.Customer;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField tf_Name;
	private JTextField tf_Surname;
	private JTextField tf_Phone;
	private JTextField tf_Username;
	private JPasswordField pf_Password;
	private JPasswordField pf_PasswordAgain;

	Customer customer = new Customer();
	private JTextField tf_Email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setTitle("Ariza Takip Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 370, 390);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 255));
		panel.setBounds(10, 11, 334, 329);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbl_Name = new JLabel("Ad:");
		lbl_Name.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Name.setBounds(10, 30, 100, 20);
		panel.add(lbl_Name);

		tf_Name = new JTextField();
		tf_Name.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tf_Name.setBounds(120, 30, 200, 20);
		panel.add(tf_Name);
		tf_Name.setColumns(10);

		JLabel lbl_Surname = new JLabel("Soyad:");
		lbl_Surname.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Surname.setBounds(10, 55, 100, 20);
		panel.add(lbl_Surname);

		tf_Surname = new JTextField();
		tf_Surname.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tf_Surname.setBounds(120, 55, 200, 20);
		panel.add(tf_Surname);
		tf_Surname.setColumns(10);

		JLabel lbl_Phone = new JLabel("Telefon:");
		lbl_Phone.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Phone.setBounds(10, 80, 100, 20);
		panel.add(lbl_Phone);

		tf_Phone = new JTextField();
		tf_Phone.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tf_Phone.setBounds(120, 80, 200, 20);
		panel.add(tf_Phone);
		tf_Phone.setColumns(10);

		JLabel lbl_Address = new JLabel("Adres:");
		lbl_Address.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Address.setBounds(10, 105, 100, 20);
		panel.add(lbl_Address);

		JTextArea ta_Address = new JTextArea();
		ta_Address.setWrapStyleWord(true);
		ta_Address.setLineWrap(true);
		ta_Address.setBorder(UIManager.getBorder("TextField.border"));
		ta_Address.setFont(new Font("SansSerif", Font.PLAIN, 13));
		ta_Address.setBounds(120, 105, 200, 50);
		panel.add(ta_Address);

		JLabel lbl_Email = new JLabel("Email:");
		lbl_Email.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Email.setBounds(10, 160, 100, 20);
		panel.add(lbl_Email);

		tf_Email = new JTextField();
		tf_Email.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tf_Email.setBounds(120, 160, 200, 20);
		panel.add(tf_Email);
		tf_Email.setColumns(10);

		JLabel lbl_Username = new JLabel("Kullanici Adi:");
		lbl_Username.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Username.setBounds(10, 185, 100, 20);
		panel.add(lbl_Username);

		tf_Username = new JTextField();
		tf_Username.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tf_Username.setBounds(120, 185, 200, 20);
		panel.add(tf_Username);
		tf_Username.setColumns(10);

		JLabel lbl_Password = new JLabel("Sifre:");
		lbl_Password.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Password.setBounds(10, 210, 100, 20);
		panel.add(lbl_Password);

		pf_Password = new JPasswordField();
		pf_Password.setFont(new Font("SansSerif", Font.PLAIN, 13));
		pf_Password.setBounds(120, 210, 200, 20);
		panel.add(pf_Password);

		JLabel lbl_PasswordAgain = new JLabel("Sifre Tekrar:");
		lbl_PasswordAgain.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_PasswordAgain.setBounds(10, 235, 100, 20);
		panel.add(lbl_PasswordAgain);

		pf_PasswordAgain = new JPasswordField();
		pf_PasswordAgain.setFont(new Font("SansSerif", Font.PLAIN, 13));
		pf_PasswordAgain.setBounds(120, 235, 200, 20);
		panel.add(pf_PasswordAgain);

		JButton btn_Register = new JButton("Kayit Ol");
		btn_Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tf_Name.getText().length() == 0 || tf_Surname.getText().length() == 0
						|| tf_Phone.getText().length() == 0 || ta_Address.getText().length() == 0
						|| tf_Email.getText().length() == 0 || tf_Username.getText().length() == 0
						|| pf_Password.getText().length() == 0 || pf_PasswordAgain.getText().length() == 0) {
					Messager.showMessage("fill");
				} else if (!(pf_Password.getText().equals(pf_PasswordAgain.getText()))) {
					Messager.showMessage("Sifreniz yanlis tekrar etmektedir, lütfen kontrol ediniz!");
				} else {
					try {
						boolean control = customer.Register(tf_Name.getText(), tf_Surname.getText(), tf_Phone.getText(),
								ta_Address.getText(), tf_Email.getText(), tf_Username.getText(), pf_Password.getText());
						if (control) {
							Messager.showMessage("success");
							LoginGUI lGUI = new LoginGUI();
							lGUI.setVisible(true);
							dispose();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		btn_Register.setBackground(new Color(255, 255, 255));
		btn_Register.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_Register.setFocusable(false);
		btn_Register.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_Register.setBounds(120, 280, 100, 30);
		panel.add(btn_Register);
	}
}
