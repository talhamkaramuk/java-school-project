package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Messager;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ForgetPassGUI extends JFrame {

	private JPanel contentPane;
	private JTextField tf_Email;
	private JTextField tf_Question;
	private JTextField tf_Answer;

	Random r1 = new Random();
	Random r2 = new Random();
	Random r3 = new Random();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgetPassGUI frame = new ForgetPassGUI();
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
	public ForgetPassGUI() {
		setTitle("Ariza Takip Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 250);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 255));
		panel.setBounds(10, 11, 324, 195);
		contentPane.add(panel);
		panel.setLayout(null);

		JTextPane tp_Description = new JTextPane();
		tp_Description.setEditable(false);
		tp_Description.setBackground(new Color(204, 204, 255));
		tp_Description.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tp_Description.setText(
				"Sifrenizi sifirlamak icin email adresinizi giriniz.\r\nGelen emaildeki baglantidan sifrenizi sifirlayabilirsiniz.");
		tp_Description.setBounds(10, 11, 304, 40);
		panel.add(tp_Description);

		tf_Email = new JTextField();
		tf_Email.setBackground(new Color(255, 255, 255));
		tf_Email.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tf_Email.setBounds(62, 70, 200, 20);
		panel.add(tf_Email);
		tf_Email.setColumns(10);

		int s1, s2, islem;
		s1 = r1.nextInt(10);
		s2 = r2.nextInt(10);
		islem = r3.nextInt(2);
		randomGenerator(s1, s2, islem);

		tf_Question = new JTextField();
		tf_Question.setHorizontalAlignment(SwingConstants.CENTER);
		tf_Question.setEditable(false);
		tf_Question.setBackground(new Color(255, 255, 255));
		tf_Question.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tf_Question.setBounds(102, 100, 50, 20);
		panel.add(tf_Question);
		tf_Question.setColumns(10);
		if (islem == 0) {
			tf_Question.setText(s1 + "+" + s2 + "=");
		} else if (islem == 1) {
			tf_Question.setText(s1 + "-" + s2 + "=");
		}

		tf_Answer = new JTextField();
		tf_Answer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' && tf_Answer.getText().length() < 2
						|| e.getKeyChar() == '-' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					tf_Answer.setEditable(true);
				} else {
					tf_Answer.setEditable(false);
				}
			}
		});
		tf_Answer.setBackground(new Color(255, 255, 255));
		tf_Answer.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tf_Answer.setBounds(172, 100, 50, 20);
		panel.add(tf_Answer);
		tf_Answer.setColumns(10);

		JButton btn_Send = new JButton("Gonder");
		btn_Send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tf_Email.getText().length() == 0) {
					Messager.showMessage("fill");
				} else if (tf_Answer.getText().length() == 0) {
					Messager.showMessage(tf_Question.getText() + "  islemini cevaplayýnýz!");
				} else if (Integer.parseInt(tf_Answer.getText()) != randomGenerator(s1, s2, islem)) {
					Messager.showMessage("Yanlis cevap!");
				} else {
					Messager.showMessage("success");
					LoginGUI lGUI = new LoginGUI();
					lGUI.setVisible(true);
					dispose();
				}
			}
		});
		btn_Send.setFocusable(false);
		btn_Send.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_Send.setBackground(new Color(255, 255, 255));
		btn_Send.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_Send.setBounds(112, 150, 100, 30);
		panel.add(btn_Send);
	}

	public int randomGenerator(int s1, int s2, int islem) {
		int result = 0;
		switch (islem) {
		case 0:
			result = s1 + s2;
			break;
		case 1:
			result = s1 - s2;
			break;
		default:
			break;
		}
		return result;
	}
}
