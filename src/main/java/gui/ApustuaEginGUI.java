package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;

import domain.Boleto;
import domain.Kuota;
import domain.Question;
import domain.Registered;
import domain.extendedIterator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;


public class ApustuaEginGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private Registered registered;
	private DefaultComboBoxModel<String> Kuotak = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> boletoak = new DefaultComboBoxModel<String>();

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

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
	private JComboBox comboBox;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	public ApustuaEginGUI(Registered registered)
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
		this.setSize(new Dimension(851, 627));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(127, 15, 140, 25));
		jLabelQueries.setBounds(430, 240, 406, 14);
		jLabelEvents.setBounds(430, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(385, 541, 167, 38));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);


		jCalendar1.setBounds(new Rectangle(127, 50, 225, 150));

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

						extendedIterator<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						while (events.hasNext()){
							domain.Event ev = events.next();
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
		
		scrollPaneEvents.setBounds(new Rectangle(430, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(430, 262, 378, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			
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
		comboKuotaList.setBounds(222, 265, 173, 22);
		getContentPane().add(comboKuotaList);
		
		apustusButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("makeabet"));
		apustusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=tableQueries.getSelectedRow();
				Question selectedQ = (Question) tableModelQueries.getValueAt(i,2);
				float dirua=Float.parseFloat(fieldDirua.getText());
				Vector<String> kuota=new Vector<String>();
				kuota.add((String) comboKuotaList.getSelectedItem());
				String boleto = (String) comboBox.getSelectedItem();
				int bId = 0;
				if (boleto != null) {
					String[] vec = boleto.split(" ");
					bId = Integer.parseInt(vec[0]);
				}
				BLFacade facade = MainGUI.getBusinessLogic();
				if(selectedQ.getBetMinimum() < dirua) {
					boolean ondo = facade.apustuEgin(registered, dirua, kuota, false, bId);
					if(ondo) {
						lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("wellsaved"));
					} else lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("wrongsaved"));
				}
				boletoak.removeAllElements();
				for(Boleto b:registered.getBoletoak()) {
					boletoak.addElement(b.toString());
				}
				comboBox.setModel(boletoak);
			}
		});
		apustusButton.setBounds(127, 508, 173, 50);
		getContentPane().add(apustusButton);
		
		fieldDirua = new JTextField();
		fieldDirua.setText("");
		fieldDirua.setBounds(182, 433, 124, 20);
		getContentPane().add(fieldDirua);
		fieldDirua.setColumns(10);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(127, 483, 140, 14);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Dinero"));
		lblNewLabel_1.setBounds(94, 436, 49, 14);
		getContentPane().add(lblNewLabel_1);
		
		comboBox = new JComboBox();
		comboBox.setBounds(22, 265, 173, 22);
		getContentPane().add(comboBox);
		for(Boleto b:registered.getBoletoak()) {
			boletoak.addElement(b.toString());
		}
		comboBox.setModel(boletoak);
		
		lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Kuota"));
		lblNewLabel_2.setBounds(222, 240, 49, 14);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BoletosDisp"));
		lblNewLabel_3.setBounds(22, 240, 188, 14);
		getContentPane().add(lblNewLabel_3);

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}