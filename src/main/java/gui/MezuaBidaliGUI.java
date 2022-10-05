package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Mezua;
import domain.Registered;
import domain.User;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class MezuaBidaliGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBox;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private DefaultComboBoxModel<String> users = new DefaultComboBoxModel<String>();
	private JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MezuaBidaliGUI frame = new MezuaBidaliGUI(null);
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
	public MezuaBidaliGUI(User u) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		BLFacade facade = MainGUI.getBusinessLogic();
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mezua m = facade.getUser(u).getMezuaErabiltzaile((String) comboBox.getSelectedItem());
				if(m != null) {
					textPane.setText(m.getMezua());
				} else {
					textPane.setText("");
				}
			}
		});
		comboBox.setBounds(152, 11, 243, 22);
		contentPane.add(comboBox);
		List<User> Users = facade.getAllUsers();
		for(User user:Users) {
			if(!user.getUsername().equals(u.getUsername())) {
				users.addElement(user.getUsername());
			}
		}
		comboBox.setModel(users);
		
		
//		Mezua m = u.getMezuaErabiltzaile(u, (String) comboBox.getSelectedItem());
//		if(m != null) {
//			textArea.setText(m.getMezua());
//		}
		
		lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Destinatario"));
		lblNewLabel.setBounds(10, 15, 49, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(152, 52, 243, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Mensaje"));
		lblNewLabel_1.setBounds(10, 55, 49, 14);
		contentPane.add(lblNewLabel_1);
		
		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EnviarMensaje"));
		btnNewButton.setBounds(152, 83, 109, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User u1 = u;
				String u2 = (String) comboBox.getSelectedItem();
				String mezua = textField.getText();
				facade.mezuaBidali(mezua, u1, u2);
				Mezua m = facade.getUser(u).getMezuaErabiltzaile((String) comboBox.getSelectedItem());
				if(m != null) {
					textPane.setText(m.getMezua());
				}
			}
			
		});
		contentPane.add(btnNewButton);
		
		textPane = new JTextPane();
		textPane.setBounds(10, 117, 416, 146);
		contentPane.add(textPane);
	}
}
