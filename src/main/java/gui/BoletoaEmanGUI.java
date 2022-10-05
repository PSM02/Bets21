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

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class BoletoaEmanGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JLabel lblNewLabel;
	private JComboBox comboBox;
	private DefaultComboBoxModel<String> users = new DefaultComboBoxModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoletoaEmanGUI frame = new BoletoaEmanGUI();
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
	public BoletoaEmanGUI() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		BLFacade facade = MainGUI.getBusinessLogic();
		
		rdbtnNewRadioButton = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("GanarMas"));
		rdbtnNewRadioButton.setBounds(72, 97, 111, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("PerderMenos"));
		rdbtnNewRadioButton_1.setBounds(225, 97, 111, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("TipoBoleto"));
		lblNewLabel.setBounds(72, 76, 111, 14);
		contentPane.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(72, 30, 264, 22);
		contentPane.add(comboBox);
		List<Registered> Users = facade.getRegistered();
		for(Registered r:Users) {
			users.addElement(r.getUsername());
		}
		comboBox.setModel(users);
		
		lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UserName"));
		lblNewLabel_1.setBounds(72, 5, 264, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(72, 164, 111, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Multiplier")+ ":");
		lblNewLabel_2.setBounds(72, 139, 196, 14);
		contentPane.add(lblNewLabel_2);
		
		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EnviarBoleto"));
		btnNewButton.setBounds(141, 229, 127, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String efektua = null;
				if(rdbtnNewRadioButton.isSelected()) {
					efektua = "up";
				} else if (rdbtnNewRadioButton_1.isSelected()) {
					efektua = "down";
				}
				String user = (String) comboBox.getSelectedItem();
				float biderkatzaile = Float.parseFloat(textField.getText());
				facade.boletoaGehitu(user, efektua, biderkatzaile);
			}
		});
		
	}
}
