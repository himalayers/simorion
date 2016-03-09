package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ool201
 */
public class LoopSpeedMode implements Mode, ActionListener{
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
        gui.L4.addActionListener((ActionListener) this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
          GUI gui=new GUI();
          for ( i=0; i<16; i++){
                for( j=0; j<16; j++){
                    //gets the button source
               if(gui.ibutton.buttons[i][j]==ae.getSource()){
                voicenumber=(i+(j*16));
                
                if(voicenumber<=160){
               Controller.clockhand.settime(voicenumber);
               gui.display.setText(""+ Controller.clockhand.gettime());
                
       }
                //feel free to edit.same problem as velocitymode
                else{
                    voicenumber-=160;
                    Controller.clockhand.settime(voicenumber);
                   gui.display.setText(""+ Controller.clockhand.gettime());
                
                }
                }
          }
       
   }
    }
}

