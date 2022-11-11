package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import businessLogic.BLFacade;
import businessLogic.FacadeFactory;
import configuration.ConfigXML;
import configuration.UtilDate;

public class probaIterator {

	public static void main(String[] args) {
		// obtener el objeto Facade local
		ConfigXML c = ConfigXML.getInstance();
		BLFacade blFacade = (new FacadeFactory()).sortuFacade(c);
		Date date;
		try {
			date = UtilDate.newDate(2022, 11, 17); // 17 del mes que viene
			extendedIterator<Event> i = blFacade.getEvents(date);
			Event e;
			System.out.println("_____________________");
			System.out.println("ATZETIK AURRERAKA");
			i.goLast();// Azkeneko elementuan kokatu
			while (i.hasPrevious()) {
				e = i.previous();
				System.out.println(e.toString());
			}
			System.out.println();
			System.out.println("_____________________");
			System.out.println("AURRETIK ATZERAKA");
			i.goFirst(); // Lehen elem. kokatu
			while (i.hasNext()) {
				e = i.next();
				System.out.println(e.toString());

			}

		} catch (Exception e) {

		}
	}
}
