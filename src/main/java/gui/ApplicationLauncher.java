package gui;

import java.awt.Color;
import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import businessLogic.FacadeFactory;

public class ApplicationLauncher { 
	
	public static void main(String[] args) {

		ConfigXML c=ConfigXML.getInstance();
	
		System.out.println(c.getLocale());
		
		Locale.setDefault(new Locale(c.getLocale()));
		
		System.out.println("Locale: "+Locale.getDefault());
		
		MainGUI a=new MainGUI();
		a.setVisible(true);

		//Facade faktoria sortu
		FacadeFactory ff = new FacadeFactory();
		
		//ematen zaigun BLFacade objektuarekin (lokala edo web-zerbitzua) BussinessLogic jarri
		MainGUI.setBussinessLogic(ff.sortuFacade(c));

	}
}
