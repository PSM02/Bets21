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
import domain.Apustua;
import domain.Registered;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ApustuaEzabatuGUI extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private DefaultComboBoxModel<String> apustuak = new DefaultComboBoxModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApustuaEzabatuGUI frame = new ApustuaEzabatuGUI(null);
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
	public ApustuaEzabatuGUI(Registered user) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 24, 416, 35);
		contentPane.add(comboBox);
		BLFacade facade = MainGUI.getBusinessLogic();
		Registered r = (Registered) facade.getUser(user);
		if (!r.getApustuak().isEmpty()) {
			for (Apustua a : r.getApustuak()) {
				apustuak.addElement(a.toString());
			}
		}
		comboBox.setModel(apustuak);
		
		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Delete"));
		btnNewButton.setBounds(81, 208, 266, 35);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = (String) comboBox.getSelectedItem();
				String string[] = str.split(" ");
				facade.apustuaEzabatu(user, Integer.parseInt(string[0]));	
				comboBox.removeItem(str);
			}
		});
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(131, 183, 167, 14);
		contentPane.add(lblNewLabel);
	}
}
