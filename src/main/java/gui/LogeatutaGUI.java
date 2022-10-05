package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.Registered;
import domain.User;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class LogeatutaGUI extends JFrame{

	private JPanel contentPane;
	private JButton btnNewButton;
	private User user;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogeatutaGUI frame = new LogeatutaGUI(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LogeatutaGUI(User u) {
		user = u;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(39, 11, 180, 39);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new FindQuestionsGUI();

				a.setVisible(true);				
			}
			
		});
		
		btnNewButton_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Putmoney"));
		btnNewButton_1.setBounds(39, 61, 180, 39);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new DiruaSartuGUI((Registered) user);

				a.setVisible(true);				
			}
			
		});
		
		btnNewButton_2 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SeeMovements"));
		btnNewButton_2.setBounds(39, 111, 180, 39);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new MugimenduakIkusiGUI((Registered) user);

				a.setVisible(true);				
			}
			
		});
		
		btnNewButton_3 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("makeabet"));
		btnNewButton_3.setBounds(39, 161, 180, 39);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new ApustuaEginGUI((Registered) user);

				a.setVisible(true);				
			}
			
		});
		
		btnNewButton_4 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("deletebet"));
		btnNewButton_4.setBounds(39, 211, 180, 41);
		contentPane.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new ApustuaEzabatuGUI((Registered) user);

				a.setVisible(true);				
			}
			
		});
		
		//btnNewButton_5 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LogeatutaGUI.btnNewButton_5.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton_5 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Seguir")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton_5.setBounds(229, 11, 180, 39);
		contentPane.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new JarraituGUI((Registered) user);

				a.setVisible(true);				
			}
			
		});
		
		btnNewButton_6 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EnviarMensaje")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new MezuaBidaliGUI((Registered) user);

				a.setVisible(true);	
			}
		});
		btnNewButton_6.setBounds(229, 61, 180, 39);
		contentPane.add(btnNewButton_6);
		
		btnNewButton_7 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DobleApuesta")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton_7.setBounds(229, 111, 180, 39);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new ApustuAnitzkoitzaGUI((Registered) user);

				a.setVisible(true);	
			}
		});
		contentPane.add(btnNewButton_7);
	}
}
