package simorion;

import static simorion.Controller.layers;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Mark Fowden
 * @author Olawunmi Lawal
 * @author Tom Fullalove
 */
public class SaveMode implements Mode {
    private char currentLetter;
    private boolean error = true;
    private final int LCASEAZSTART = 97;
    private final int UCASEAZSTART = 65;
    private final int NUMBERSTART = 48;
    private final int ALPHABETLENGTH = 26;
    private String filename = "";
    private boolean matrixPressed = false;
    private Timer timer =  new Timer();
    private final int FLASHSPEED = 50;
    
    @Override
    public void OKEventAction(){
        if(!error){
            if(matrixPressed){
                
                if(currentLetter=='*' && filename.length()>0){
                    filename = filename.substring(0, filename.length()-1);
                }else if(currentLetter!='*'){
                    filename += currentLetter;
                }
                
                Controller.mainGUI.display.setText(filename);
                matrixPressed = false;
                Controller.mainGUI.ibutton.turnOffAllDots();
                
                //Pause the timer that flashes the selection
                timer.cancel();
                
            }else{
                //CHECK IF FILE ALREADY EXISTS
                File f = new File("src/simorion/songs/" + filename + ".song");
                if(f.exists() && !f.isDirectory()){
                    //Overwriting?
                }
                
                //call toms function with filename.song
                saveLayers("src/simorion/songs/" + filename + ".song");
                
                //exit save mode
                Controller.setMode(Controller.performanceMode);
                //Reset file name for next time
                filename = "";
            }
        }
    }
    
    public class FlashSelection extends TimerTask{
        public String oldname = filename + "_";
        public String newname = filename;
        @Override
        public void run(){
            //Switch the display graphic
            if(Controller.mainGUI.display.getText().equals(newname)){
                Controller.mainGUI.display.setText(oldname);
            }else{
                Controller.mainGUI.display.setText(newname);
            }
        }
    }
    
    @Override
    public void MatrixEventAction(int i, int j){
        
        //Make the timer which flash the letters
        timer.cancel();
        timer = new Timer();
        FlashSelection flashselection = new FlashSelection();
        timer.schedule(flashselection, 0, FLASHSPEED);
        
        int gridNumber = j + i*16 + 1;
        if(gridNumber <= 26){
            currentLetter = (char)(gridNumber+LCASEAZSTART-1);
            error = false;
            flashselection.newname = filename + currentLetter;
            
        }else if(gridNumber<=52){
            currentLetter = (char)(gridNumber+UCASEAZSTART-ALPHABETLENGTH-1);
            error = false;
            flashselection.newname = filename + currentLetter;
            
        }else if(gridNumber<=62){
            currentLetter = (char)(gridNumber+NUMBERSTART-ALPHABETLENGTH*2-1);
            error = false;
            flashselection.newname = filename + currentLetter;
            
        }else if(gridNumber==63){
            currentLetter = '*';
            error = false;
            flashselection.oldname = filename;
            flashselection.newname = filename.substring(0,filename.length()-1) + "_";
            
        }else{
            error = true;
            flashselection.newname = "ERROR";
            flashselection.oldname = "ERROR";
        }
        matrixPressed = true;
        Controller.mainGUI.ibutton.turnOffAllDots();
        Controller.mainGUI.ibutton.lightUp(i, j, true, true);
    }
    
    public static void saveLayers(String filename){
        try
        {
            // Write to disk with FileOutputStream
            FileOutputStream f_out = new FileOutputStream(filename);
           
            // Write object with ObjectOutputStream
            ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
           
            // Write object out to disk
            obj_out.writeObject ( layers );
            //instrument/voice and velocity are saved in the 'layers' array
           
            //loopspeed
            obj_out.writeObject ( Controller.PAUSETIME );
           
            //looppoint
            obj_out.writeObject ( Controller.loopPoint );
        }
        catch(Exception e)
        {
            System.out.print("Save failed.");
            System.exit(1);
        }      
    }
}
