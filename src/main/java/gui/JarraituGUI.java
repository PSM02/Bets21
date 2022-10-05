package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Jarraitzailea;
import domain.Registered;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JarraituGUI extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JButton btnNewButton;
	private DefaultComboBoxModel<String> users = new DefaultComboBoxModel<String>();
	private JTextField textField;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JarraituGUI frame = new JarraituGUI(null);
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
	public JarraituGUI(Registered u) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		BLFacade facade = MainGUI.getBusinessLogic();
		
		
		comboBox = new JComboBox();
		comboBox.setBounds(28, 11, 376, 31);
		contentPane.add(comboBox);
		List<Registered> Users = facade.getRegistered();
		boolean jadanikJarraitua = false;
		for(Registered r:Users) {
			jadanikJarraitua = false;
			if(!r.getUsername().equals(u.getUsername())) {
				for(Jarraitzailea j:r.getFollowers()) {
					if(j.getJarraitzailea().equals(u)) {
						jadanikJarraitua = true;
					}
				}
				if(!jadanikJarraitua) {
					users.addElement(r.getUsername());
				}
			}
		}
		comboBox.setModel(users);
		
		
		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Seguir"));
		btnNewButton.setBounds(141, 221, 144, 31);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facade.jarraitu(u, (String) comboBox.getSelectedItem(), Float.parseFloat(textField.getText()));	
			    users.removeElement(comboBox.getSelectedItem());
			    comboBox.removeAllItems();
				comboBox.setModel(users);
			}
			
		});
		
		lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Restriccion"));
		lblNewLabel.setBounds(28, 196, 106, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(144, 193, 141, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
