package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Admin;
import domain.Existitu_adminDa;
import domain.User;

import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class Login_RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton erregistratu;
	private JButton login;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_RegisterGUI frame = new Login_RegisterGUI();
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
	public Login_RegisterGUI() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(182, 52, 158, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UserName"));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(46, 55, 131, 17);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(46, 111, 82, 17);
		contentPane.add(lblNewLabel_1);
		
		login = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		login.setBackground(new Color(255, 153, 204));
		login.setFont(new Font("Tahoma", Font.PLAIN, 15));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = String.valueOf(passwordField.getPassword());
				BLFacade facade = MainGUI.getBusinessLogic();
				User u = facade.Login(username, password);
				if(u==null) {
					lblNewLabel_2.setText(ResourceBundle.getBundle("Etiquetas").getString("SomethingWrong"));
				}else {
					if(u instanceof Admin) {
						JFrame a = new AdminGUI(u);
						a.setVisible(true);
					} else {
						JFrame a = new LogeatutaGUI(u);
						a.setVisible(true);
					}
				}
			}
		});
		login.setBounds(46, 209, 153, 32);
		contentPane.add(login);
		
		erregistratu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		erregistratu.setBackground(new Color(255, 153, 204));
		erregistratu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		erregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = String.valueOf(passwordField.getPassword());
				BLFacade facade = MainGUI.getBusinessLogic();
				boolean ondoEginda = facade.register(username, password);
				if(ondoEginda) {
					lblNewLabel_2.setText(ResourceBundle.getBundle("Etiquetas").getString("savedUser"));
					textField.setText("");
					passwordField.setText("");
				} else {
					lblNewLabel_2.setText(ResourceBundle.getBundle("Etiquetas").getString("Userexists"));
				}
			}
		});
		erregistratu.setBounds(239, 209, 153, 32);
		contentPane.add(erregistratu);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(182, 105, 158, 32);
		contentPane.add(passwordField);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(46, 162, 346, 17);
		contentPane.add(lblNewLabel_2);
	}
}