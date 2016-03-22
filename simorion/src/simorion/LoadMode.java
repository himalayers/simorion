/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simorion;
import java.io.*;
import static simorion.Controller.layers;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author mjf213
 */
public class LoadMode implements Mode {
    private boolean error = true;
    File[] matchingFiles;
    File rootName;
    String[] fileNames;
    File toLoad;
    
    @Override
    public void OKEventAction(){
        if(!error){
            //Load the selected file, reset the performance mode clock so it plays from the beginning
            loadLayers(toLoad.getAbsolutePath());
            Controller.performanceMode.resetPerformanceClock();
            
            //Exit mode
            Controller.setMode(Controller.performanceMode);
            Controller.mainGUI.ibutton.relight(Controller.getCurrentLayer());
        }
    }

    @Override
    public void MatrixEventAction(int i, int j) {
        
        int selected = j + i*16;
        if(selected < fileNames.length){
            Controller.mainGUI.display.setText(fileNames[selected]);
            toLoad = matchingFiles[selected];
            error = false;
        }else{
            Controller.mainGUI.display.setText("ERROR");
            error = true;
        }
        
        Controller.mainGUI.ibutton.turnOffAllDots();
        Controller.mainGUI.ibutton.lightUp(i, j, true, true);
   }
    
    public boolean updateFileList(){
       
        //Find all the .songs on this computer
        File root = new File("src/simorion/songs/");
        matchingFiles = root.listFiles(new FilenameFilter(){
            @Override
            public boolean accept(File dir, String name){
                return name.endsWith(".song");
            }
        });
        
        //Check if any were found
        if(matchingFiles==null || matchingFiles.length==0){
            return false;
        }

        //Get their names
        fileNames = new String[matchingFiles.length];
        for(int fcounter = 0; fcounter < matchingFiles.length; fcounter++){
            fileNames[fcounter] = matchingFiles[fcounter].getName();
        }
        
        return true;
    }
    
    public void failAndExit(){
        //No matching files, display an error then exit save mode
        Controller.mainGUI.display.setText("NO FILES.");
        
        //Change the mode after the error message has been seen
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run(){
                Controller.setMode(Controller.performanceMode);
            }
        }, 2000
        );
        Controller.mainGUI.ibutton.relight(Controller.getCurrentLayer());
    }
    
    public static void loadLayers(String filename){
         try{
            // Read from disk using FileInputStream
            FileInputStream f_in = new FileInputStream(filename);
           
            // Read object using ObjectInputStream
            ObjectInputStream obj_in = new ObjectInputStream (f_in);
           
            // Read an object
            layers = (Layer[]) obj_in.readObject();
           
            Controller.PAUSETIME=(int) obj_in.readObject();
           
            Controller.loopPoint=(int) obj_in.readObject();
                      
            Controller.setCurrentLayer(0);
        }
        catch(Exception e)
        {
            System.out.print("Load failed.");
            System.exit(1);
        } 
    }
}
