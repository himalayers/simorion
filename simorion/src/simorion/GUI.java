package simorion;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.TextField;
import javax.swing.JPanel;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.BorderFactory;
import javax.swing.border.*;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;

/*
* @author Olawunmi Lawal
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
    final ONButton  on = new ONButton("OFF");
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
     * @author Tom Fullalove
     */
    class ONButton extends JButton implements ActionListener{
        boolean state;
        ONButton(String s){
            super(s);
            addActionListener(this);
            state=false;//false means off
        }
        
        @Override
        public void actionPerformed(ActionEvent e){
            //Code that happens when button clicked
            Image gridOffImg;
            try{
                gridOffImg = ImageIO.read(getClass().getResource("resources/gridoff.png"));
            
                for (int i=0;i<butt.buttons.length;i++)
                {
                    for (int j=0;j<butt.buttons[i].length;j++)
                    {
                        if (!state)
                        {
                            Controller.getCurrentLayer().ClearDots();
                            butt.buttons[i][j].setIcon(new ImageIcon(gridOffImg));
                        }
                        butt.buttons[i][j].setEnabled(state);
                    }
                }
                
            } catch (IOException ex) {
                //image not found
            }
            
            ok.setEnabled(state);
            L1.setEnabled(state);
            L2.setEnabled(state);
            L3.setEnabled(state);
            L4.setEnabled(state);
            R1.setEnabled(state);
            R2.setEnabled(state);
            R3.setEnabled(state);
            R4.setEnabled(state);
            display.setEnabled(state);
            
            state = !state;
            if (state)
            {
                this.setText("ON");
            }
            else
            {
                this.setText("OFF");
            }
        }
    }
    
    /*
     * empty classes for the mode buttons
     * 
     */
    class OKButton extends JButton {
        OKButton(String s){
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
   class innerbuttons extends JPanel
   {
       JButton [][] buttons;
        public innerbuttons()
        {
            // new grid object to arrange the buttons

            setLayout(new GridLayout(16,16));        
            buttons=new JButton[16][16];
            
            Border noBorder = BorderFactory.createEmptyBorder(); //used in for loop
            Image gridOffImg;
            try{
                gridOffImg = ImageIO.read(getClass().getResource("resources/gridoff.png"));
            
                for (int i=0;i<buttons.length;i++)
                {
                    for (int j=0;j<buttons[i].length;j++)
                    {
                        buttons[i][j]= new JButton("");
                        buttons[i][j].addActionListener(new OnClickActionListener(i,j,buttons[i][j]));
                        buttons[i][j].setBackground(Color.white);


                        buttons[i][j].setBorder(noBorder);
                        buttons[i][j].setIcon(new ImageIcon(gridOffImg));

                        add(buttons[i][j]);
                    }
                }
            } catch (IOException ex) {
                //image not found
            }
        }
        
        
        
        private class OnClickActionListener implements ActionListener{
            private final int i;
            private final int j;
            private final JButton button;
            
            public OnClickActionListener(int i, int j, JButton button){
                this.i = i;
                this.j = j;
                this.button = button;
            } 
            @Override
            public void actionPerformed(ActionEvent e){
                //Code that happens when button clicked
                
                //Change colour (put picture changing here).
                if(Controller.getCurrentLayer().toggleDot(i,j)){
                    try {
                        Image gridOnImg = ImageIO.read(getClass().getResource("resources/gridon.png"));
                        button.setIcon(new ImageIcon(gridOnImg));
                    } catch (IOException ex) {
                        //
                    }   
                } else {
                    try {
                        Image gridOffImg = ImageIO.read(getClass().getResource("resources/gridoff.png"));
                        button.setIcon(new ImageIcon(gridOffImg));
                    } catch (IOException ex) {
                        //
                    }   
                }
            }
        }
    }
   
   
   
    /**
     * @author Olawunmi Lawal
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
        
        //We could seriously truncate this code by eliminating the multiple copies of gbc.gridx=0;
        //Putting it in a for loop, and using arraylists for the Ri and Li objects,
        //But some guy on the internet said to specify gbc's every time an add is called...
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
       
        add(butt);
        
        add(panl, BorderLayout.WEST);
        add(panr, BorderLayout.EAST);
        add(panb, BorderLayout.PAGE_END);
        add(panh, BorderLayout.BEFORE_FIRST_LINE);
        
        new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    on.doClick();
                }
            }, 
            0
        );
    }
    
    
    //Pre-generated code we're not meant to change
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

    public void setUp(){
        JFrame frame = new GUI();
        
        frame.setUndecorated(true);
        frame.setShape(new RoundRectangle2D.Double(0,0,970,905,200,200));
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(970,905);
        frame.setVisible(true);
        frame.setResizable(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    
}
