package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Boleto;
import domain.Kuota;
import domain.Question;
import domain.Registered;

public class ApustuAnitzkoitzaGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private DefaultComboBoxModel<String> Kuotak = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> boletoak = new DefaultComboBoxModel<String>();

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;

	Vector<String> kuotak = new Vector<String>();
	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private JTextField fieldDirua;
	private JComboBox comboKuotaList;
	private JButton apustusButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JComboBox comboBox;
	private JLabel lblNewLabel_2;
	private JLabel lblErabilgarriDituzunBoletoak;

	public ApustuAnitzkoitzaGUI(Registered registered)
	{
		try
		{
			jbInit(registered);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void jbInit(Registered registered) throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(838, 499));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(139, 11, 140, 25));
		jLabelQueries.setBounds(39, 233, 406, 14);
		jLabelEvents.setBounds(394, 15, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);


		jCalendar1.setBounds(new Rectangle(139, 46, 225, 150));

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
//					jCalendar1.setCalendar(calendarAct);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					 
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}						
						
						jCalendar1.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
					}



					CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
													
					

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainGUI.getBusinessLogic();

						ArrayList<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(391, 46, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(40, 253, 378, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(3);

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					row.add(q);
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
				tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(2)); // not shown in JTable
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableQueries.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				comboKuotaList.removeAll();
				Kuotak.removeAllElements();
				int i=tableQueries.getSelectedRow();
				Question selectedQ = (Question) tableModelQueries.getValueAt(i,2);
				for(Kuota k:selectedQ.getFees()) {
					Kuotak.addElement(k.getDeskripzioa());
				}
				comboKuotaList.setModel(Kuotak);
			}
		});

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		
		comboKuotaList = new JComboBox();
		comboKuotaList.setBounds(428, 258, 186, 22);
		getContentPane().add(comboKuotaList);
		
		apustusButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("makeabet"));
		apustusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=tableQueries.getSelectedRow();
				Question selectedQ = (Question) tableModelQueries.getValueAt(i,2);
				float dirua=Float.parseFloat(fieldDirua.getText());
				BLFacade facade = MainGUI.getBusinessLogic();
				String boleto = (String) comboBox.getSelectedItem();
				int bId = 0;
				if (boleto != null) {
					String[] vec = boleto.split(" ");
					bId = Integer.parseInt(vec[0]);
				}
				if(selectedQ.getBetMinimum() < dirua) {
					boolean ondo = facade.apustuEgin(registered, dirua, kuotak, false, bId);
					if(ondo) {
						lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("wellsaved"));
						kuotak.removeAllElements();
					} else lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("wrongsaved"));
				}
				kuotak.removeAllElements();
				boletoak.removeAllElements();
				for(Boleto b:registered.getBoletoak()) {
					boletoak.addElement(b.toString());
				}
				comboBox.setModel(boletoak);
			}
		});
		apustusButton.setBounds(295, 419, 114, 27);
		getContentPane().add(apustusButton);
		
		fieldDirua = new JTextField();
		fieldDirua.setText("");
		fieldDirua.setBounds(158, 422, 124, 20);
		getContentPane().add(fieldDirua);
		fieldDirua.setColumns(10);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(295, 401, 140, 14);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Dinero"));
		lblNewLabel_1.setBounds(10, 425, 138, 14);
		getContentPane().add(lblNewLabel_1);
		
		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddQuota"));
		btnNewButton.setBounds(460, 357, 124, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kuota = (String) comboKuotaList.getSelectedItem();
				kuotak.add(kuota);
			}
		});
		getContentPane().add(btnNewButton);
		
		comboBox = new JComboBox();
		comboBox.setBounds(634, 256, 180, 22);
		getContentPane().add(comboBox);
		for(Boleto b:registered.getBoletoak()) {
			boletoak.addElement(b.toString());
		}
		comboBox.setModel(boletoak);
		
		lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Kuota"));
		lblNewLabel_2.setBounds(428, 233, 49, 14);
		getContentPane().add(lblNewLabel_2);
		
		lblErabilgarriDituzunBoletoak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BoletosDisp"));
		lblErabilgarriDituzunBoletoak.setBounds(634, 233, 180, 14);
		getContentPane().add(lblErabilgarriDituzunBoletoak);

	}
}
