package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Registered;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MugimenduakIkusiGUI extends JFrame {

	private JPanel contentPane;
	private JTextPane textPane;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MugimenduakIkusiGUI frame = new MugimenduakIkusiGUI(null);
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
	public MugimenduakIkusiGUI(Registered u) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textPane = new JTextPane();
		textPane.setBounds(10, 29, 416, 223);
		contentPane.add(textPane);
		BLFacade facade = MainGUI.getBusinessLogic();
		textPane.setText(facade.getMugimenduak(u));
		
		lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Dinero"));
		lblNewLabel.setBounds(10, 4, 416, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("Dinero")+ facade.getDirua(u));
	}
}
