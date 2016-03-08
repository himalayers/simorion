package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ool201
 */
public class VelocityMode implements Mode{
    private int voicenumber;
    private int i;
    private int j;
    //OK button functionality
    @Override
    public void OKEventAction(){
        //change the voice
        
        //Tom's code
    }
    
    //Matrix buttons functionality
    
    public void MatrixEventAction(){
        //What happens when a button is clicked? 
        //get i and j 
        //show that a button has been selected (call Ildens function on innerbutton)
        //what does the i and j equate to?
        //update the lcd screen  
        //Wunmi's code
    }
    @Override
    public void MatrixEventAction(int i, int j) {
        this.i=i;
        this.j=j;
        GUI gui=new GUI();
        gui.L2.addActionListener((ActionListener) this);
    }
    

    public void actionPerformed(ActionEvent ae){   
             GUI gui=new GUI();
               for(int i=0;i<16;i++)
                   for (int j=0;j<16;j++)
                       if(gui.ibutton.buttons[i][j]==ae.getSource()){
                    voicenumber=(i+(j*16));
               //feel free to change this
               //looking for a way to make the co-ords stay in the 0-127 range
                 if(voicenumber<127){
                      Controller.getCurrentLayer().setVelocity(voicenumber);
                gui.display.setText("" + Controller.getCurrentLayer().getVelocity());
       }
                 else{
                     voicenumber=voicenumber-127;
                      Controller.getCurrentLayer().setVelocity(voicenumber);
                     gui.display.setText("" + Controller.getCurrentLayer().getVelocity());
                 }
                
                }
               
        }
   }

    
    
