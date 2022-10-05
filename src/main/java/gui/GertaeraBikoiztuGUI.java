package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Question;
import domain.Registered;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;


public class GertaeraBikoiztuGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

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

	private JButton btnNewButton;

	public GertaeraBikoiztuGUI(domain.Event ev)
	{
		try
		{
			jbInit(ev);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void jbInit(domain.Event ev) throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));

		this.getContentPane().add(jLabelEventDate, null);

		jButtonClose.setBounds(new Rectangle(274, 419, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);


		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

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



				//	CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
													
					

//					try {
//						tableModelEvents.setDataVector(null, columnNamesEvents);
//						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects
//
//						BLFacade facade=MainGUI.getBusinessLogic();
//
//						Vector<domain.Event> events=facade.getEvents(firstDay);
//
//						
//						for (domain.Event ev:events){
//							Vector<Object> row = new Vector<Object>();
//
//							System.out.println("Events "+ev);
//
//							row.add(ev.getEventNumber());
//							row.add(ev.getDescription());
//							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
//							tableModelEvents.addRow(row);		
//						}
//						 // not shown in JTable
//					} catch (Exception e1) {
//
//						//jLabelQueries.setText(e1.getMessage());
//					}

				}
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);
		
		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Duplicar"));
		btnNewButton.setBounds(137, 262, 386, 61);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));
				if(ev.getEventDate()!=date) {
					facade.gertaeraBikoiztu(ev, date);
				}
				
				
			}
			
		});

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}