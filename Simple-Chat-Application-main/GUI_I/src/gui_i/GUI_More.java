/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_i;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author muzam
 */
public class GUI_More extends JFrame {
    
    
    JPanel nPanel;
    JMenuBar nBar;
    JMenu  fileMenu;
    JMenuItem newFile;
    JMenuItem saveFile;
    JMenuItem openFile;
    JMenuItem deletFile;
     
    
    public GUI_More(){
    
        setMinimumSize(new Dimension(300,200));
        nPanel = new JPanel();
        nBar = new JMenuBar();
        fileMenu = new JMenu("File");
        
        
        openFile = new JMenuItem("Open File");
        openFile.addActionListener(new openFile());
        
        
        deletFile = new JMenuItem("Delete File");
        
        
        
        saveFile = new JMenuItem("Save File");
        saveFile.addActionListener(new saveFile());
        
        newFile  =  new JMenuItem("new  File");
        
        
        
        
        
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(deletFile);
        
        
        nBar.add(fileMenu);
                
        
        
        
        
        
        nPanel.setLayout(new GridLayout(10,1));
        nPanel.add(nBar);
        
        add(nPanel, BorderLayout.NORTH);
        
        
        
                
        setVisible(true);
    
    
    
    }
    
    
    public static void main(String args[]){
   
        GUI_More gui = new GUI_More();
    
    
    
    }
    
    
    
    
    
    private class openFile implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        nPanel.setBackground(Color.GREEN);
        
        }}
        
    private class saveFile implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
            
                nPanel.setBackground(Color.WHITE);
            
            }
  
    
    
    
    
    
    
    
    
    }
    
    
    
}
