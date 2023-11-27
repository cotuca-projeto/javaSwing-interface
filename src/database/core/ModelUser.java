/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.core;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import database.dbos.User;

/**
 *
 * @author Rafael
 */
public class ModelUser extends AbstractTableModel {

    List<User> user = new ArrayList<User>();

    String[] column = {"id","Name", "LastN","Username", "Email","Created_at", "Updated_at"};

    public void refreshTable(List<User> newUser){
        user.clear();
        for (int i = 0;i < newUser.size();i++){
            user.add(newUser.get(i));
        }
        this.fireTableDataChanged();
    }

    public User returnUser(int index){
        return user.get(index);
    }

    @Override
    public int getRowCount() {
        return user.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    @Override
    public String getColumnName(int columns){
        return column[columns];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0 || columnIndex == 5 || columnIndex == 6 ){
            return false;
        }
        return true;
    }

    @Override
    /**
     *  This empty implementation is provided so users don't have to implement
     *  this method if their data model is not editable.
     *
     *  @param  aValue   value to assign to cell
     *  @param  rowIndex   row of cell
     *  @param  columnIndex  column of cell
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try{
            switch (columnIndex) {
                case 1 -> user.get(rowIndex).setFirstName((String) aValue);
                case 2 -> user.get(rowIndex).setLastName((String) aValue);
                case 3 -> user.get(rowIndex).setUsername((String) aValue);
                case 4 -> user.get(rowIndex).setEmail((String) aValue);
            }
        } catch (Exception error){}
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> user.get(rowIndex).getId();
            case 1 -> user.get(rowIndex).getFirstName();
            case 2 -> user.get(rowIndex).getLastName();
            case 3 -> user.get(rowIndex).getUsername();
            case 4 -> user.get(rowIndex).getEmail();
            case 5 -> user.get(rowIndex).getCreatedAt();
            case 6 -> user.get(rowIndex).getUpdatedAt();
            default -> null;
        };
        
    }
    
}
