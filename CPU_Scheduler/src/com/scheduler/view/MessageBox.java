package com.scheduler.view;
import javax.swing.JOptionPane;

public class MessageBox {

    public void updateMsg(String Message,String Title){
        JOptionPane.showMessageDialog(null, Message,Title, JOptionPane.INFORMATION_MESSAGE);
    }

}
