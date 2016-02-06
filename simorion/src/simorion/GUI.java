/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simorion;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.TextField;
import javax.swing.JPanel;
import java.awt.Insets;
import java.awt.GridBagConstraints;


/**
 *
 * @author Student
 */
public class GUI extends javax.swing.JFrame {
    /*
    * program created by wunmi ;)
    * this part makes objects of the mode classes,
    * and the innerbuttons
    */  
    
    public JButton button = new JButton();
    final TextField display =new TextField("LCD",30);
    final OKButton  ok = new OKButton("OK");
    final ONButton  on = new ONButton("ON");
    final L1Button  L1 = new L1Button("L1");
    final L2Button  L2 = new L2Button("L2");
    final L3Button  L3 = new L3Button("L3");
    final L4Button  L4 = new L4Button("L4");
    final R1Button  R1 = new R1Button("R1");
    final R2Button  R2 = new R2Button("R2");
    final R3Button  R3 = new R3Button("R3");
    final R4Button  R4 = new R4Button("R4");
    final innerbuttons butt = new innerbuttons();
    
    /*
     * empty classes for the mode buttons
     * 
     */
   
   class OKButton extends JButton {
        OKButton(String s){
            super(s);
        }
    }
        
    
    class ONButton extends JButton {
        ONButton(String s){
            super(s);
        }
        }

    class L1Button extends JButton{
        L1Button (String s){
            super(s);
        }
    }
    class L2Button extends JButton{
        L2Button (String s){
            super(s);
        }
    }
    class L3Button extends JButton{
        L3Button (String s){
            super(s);
        } 
    }
    class L4Button extends JButton{
        L4Button (String s){
            super(s);
        } 
    }
    class R1Button extends JButton{
        R1Button (String s){
            super(s);
        }
    }
   class R2Button extends JButton{
        R2Button (String s){
            super(s);
  }
   }
   class R3Button extends JButton{
        R3Button (String s){
            super(s);
        }
   }
   class R4Button extends JButton{
        R4Button (String s){
            super(s);
        }
   }
   //the array of inner buttons
   class innerbuttons extends JButton{
        public innerbuttons(){
        // new grid object to arrange the buttons
        
        setLayout(new GridLayout(16,16));        
       JButton [][] button=new JButton[16][16];
       for (int i=0;i<button.length;i++){
            for (int j=0;j<button[i].length;j++){
            button[i][j]= new JButton("");
            add(button[i][j]);
            
            
            
            }
       }
        }
    
    }
   
   
   
    /**
     * Creates new form GUI
     */
    public GUI() {
        JPanel panl=new JPanel(new GridBagLayout());
        JPanel panr=new JPanel(new GridBagLayout());
        JPanel panb=new JPanel(new GridBagLayout());
        JPanel panh=new JPanel(new GridBagLayout());
        
        setTitle("Simori-on");
        //using GridBagConstraints to specify the look of the 
        //whole program.
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,15,15,15);
        gbc.gridx=0;
        gbc.gridy=0;
        panl.add(L1,gbc);
        panr.add(R1,gbc);
        panb.add(ok,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        panl.add(L2,gbc);
        panr.add(R2,gbc);
        gbc.gridx=0;
        gbc.gridy=2;
        panl.add(L3,gbc);
        panr.add(R3,gbc);
        gbc.gridx=0;
        gbc.gridy=3;
        panl.add(L4,gbc);
        panr.add(R4,gbc);
        
        gbc.gridx=10;
        gbc.gridy=0;
        panb.add(display, gbc);
        gbc.gridx = 0;
        panh.add(on,gbc);
       
        add(panl, BorderLayout.WEST);
        add(panr, BorderLayout.EAST);
        add(panb, BorderLayout.PAGE_END);
        add(panh, BorderLayout.BEFORE_FIRST_LINE);
        add(butt, BorderLayout.CENTER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        JFrame frame = new GUI();
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,400);
        frame.setVisible(true);
        frame.setResizable(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
