/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package gui_i;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Ahsan Ali
 */
public class CalcGUI extends JFrame implements ActionListener{
    JLabel result;
    JTextField txt1;
    JTextField txt2;
    JButton calc;
    JPanel txtpanel;
    
    public CalcGUI(){
    
        result  = new JLabel("Result");
        calc = new JButton("Calculate");
        calc.addActionListener(this);
        txtpanel = new JPanel();
        txtpanel.setLayout(new FlowLayout());
                
                
        txt1 = new JTextField();
        txt1.setPreferredSize(new Dimension(150,20));
        txt2 = new JTextField();
        txt2.setPreferredSize(new Dimension(150,20));
//        txt1.setBackground(Color.red);
        
        txtpanel.add(txt1);
        txtpanel.add(txt2);
               
                
                
        add(result, BorderLayout.CENTER);
        add(calc, BorderLayout.SOUTH);
        add(txtpanel, BorderLayout.NORTH);
       
        setMinimumSize(new Dimension(500,200));
        setVisible(true);
    
    
    }
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
       CalcGUI  gui = new CalcGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int  input1 = Integer.parseInt(txt1.getText());
        int input2 = Integer.parseInt(txt2.getText());
        int r = input1+input2;
        
        result.setText(""+r);
        
    }
}
