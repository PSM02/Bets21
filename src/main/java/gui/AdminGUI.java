package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.Admin;
import domain.User;

import javax.swing.JButton;

public class AdminGUI extends JFrame{

	private User user;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	private JButton btnNewButton_8;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI frame = new AdminGUI(null);
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
	public AdminGUI(User u) {
		user = u;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		btnNewButton.setBounds(10, 11, 186, 30);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new CreateQuestionGUI(null);

				a.setVisible(true);		
			}
		});
		
		btnNewButton_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		btnNewButton_1.setBounds(10, 52, 186, 30);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new FindQuestionsGUI();

				a.setVisible(true);		
			}
		});
		
		btnNewButton_2 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SetFee"));
		btnNewButton_2.setBounds(10, 93, 186, 30);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new CreateKuotaGUI();

				a.setVisible(true);		
			}
		});
		
		btnNewButton_3 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		btnNewButton_3.setBounds(10, 134, 186, 30);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new CreateEventGUI();

				a.setVisible(true);		
			}
		});
		
		btnNewButton_4 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DeleteEvent"));
		btnNewButton_4.setBounds(10, 175, 186, 30);
		contentPane.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new GertaeraEzabatuGUI();

				a.setVisible(true);		
			}
		});
		
		
		btnNewButton_5 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("PutResults"));
		btnNewButton_5.setBounds(10, 216, 186, 36);
		contentPane.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new EmaitzakIpiniGUI();

				a.setVisible(true);		
			}
		});
		
		btnNewButton_6 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EnviarMensaje")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new MezuaBidaliGUI(user);

				a.setVisible(true);	
			}
		});
		btnNewButton_6.setBounds(240, 11, 186, 30);
		contentPane.add(btnNewButton_6);
		
		btnNewButton_7 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DuplicarEvento"));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new GertaeraAukeratuGUI();

				a.setVisible(true);
			}
		});
		btnNewButton_7.setBounds(240, 52, 186, 30);
		contentPane.add(btnNewButton_7);
		
		btnNewButton_8 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DarBoleto")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton_8.setBounds(240, 93, 186, 30);
		contentPane.add(btnNewButton_8);
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new BoletoaEmanGUI();

				a.setVisible(true);
			}
		});
	}
}
