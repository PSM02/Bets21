package domain;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class UserAdapter extends  AbstractTableModel {
    Registered user;
    private ArrayList<Apustua> apustuak;
    private String[] colNames = new String[] {"event", "question", "eventDate", "bet"}; 
    public UserAdapter(Registered user) {
        apustuak=new ArrayList<Apustua>(user.getApustuak());
        this.user=user;
    }
    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return apustuak.size();
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: return ((Object) user.getApustuak().get(rowIndex).getKuota().get(0).getQuestion().getEvent());
            case 1: return ((Object) user.getApustuak().get(rowIndex).getKuota().get(0).getQuestion());
            case 2: return ((Object) user.getApustuak().get(rowIndex).getKuota().get(0).getQuestion().getEvent().getEventDate());
            case 3: return ((Object) user.getApustuak().get(rowIndex).getDirua());
    }
        return null;
    }

}
