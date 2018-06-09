package trantrongkim.com.controller;

import java.awt.event.ActionEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.sun.istack.internal.logging.Logger;

import jdk.internal.dynalink.ChainedCallSite;
import trantrongkim.com.controller.*;
import trantrongkim.com.model.Lich_Tay;
import trantrongkim.com.view.ViewLich;;



public class Main
{
	
	public static void setTheme() 
	{
		try {
            //here you can put the selected theme class name in JTattoo
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        } 
		catch (ClassNotFoundException ex) 
		{
            java.util.logging.Logger.getLogger(ViewLich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) 
		{
            java.util.logging.Logger.getLogger(ViewLich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
		catch (IllegalAccessException ex) 
		{
            java.util.logging.Logger.getLogger(ViewLich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
		catch (javax.swing.UnsupportedLookAndFeelException ex) 
		{
            java.util.logging.Logger.getLogger(ViewLich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
	}
	public static void main(String argv[]) {
		// TODO Auto-generated constructor stub
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				setTheme();
				ViewLich vl;
				Lich_Tay lt;
				lt=new Lich_Tay();
				vl = new ViewLich(lt);
				vl.initUI();
				vl.handAction();	
			}
		});
		
		
	}
}
