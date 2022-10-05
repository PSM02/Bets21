package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Registered;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DiruaSartuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiruaSartuGUI frame = new DiruaSartuGUI(null);
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
	public DiruaSartuGUI(Registered user) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("amountofmoney"));
		lblNewLabel.setBounds(10, 66, 224, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(256, 63, 143, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("putmoney"));
		btnNewButton.setBounds(125, 172, 198, 45);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				try {
					float dirua = Float.parseFloat(textField.getText());
					BLFacade facade = MainGUI.getBusinessLogic();
					facade.addMoney(dirua, user);
					lblNewLabel_1.setText(ResourceBundle.getBundle("Etiquetas").getString("MoneySaved"));
//				} catch (Exception e1) {
//					lblNewLabel_1.setText("Zerbait gaizki, berriro Saiatu");
//				}
			}

		});
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(125, 147, 198, 14);
		contentPane.add(lblNewLabel_1);
	}
}
