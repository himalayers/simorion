package simorion;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import javax.sound.midi.Synthesizer;

/**
 * Sets up the GUI and controls program flow, mode changes and user interactivity
 * @author Mark Fowden
 */
public class Controller {
    static Layer currentLayer;
    static final int XLEN = 16;
    static final int YLEN = 16;
    static GUI mainGUI;
    static MusicPlayer musicplayer;
    static Synthesizer synthesizer;
    static Layer[] layers;
    static int PAUSETIME = 1;
    static Mode voiceMode = new VoiceMode();
    static Mode layerMode = new LayerMode();
    static Mode loopPointMode = new LoopPointMode();
    static Mode velocityMode = new VelocityMode();
    static Mode loopSpeedMode = new LoopSpeedMode();
    static Mode performanceMode = new PerformanceMode();
    static Mode currentMode = performanceMode;
    static int SleepTimer=0;
    static int loopPoint=16;
    
    public static void main(String[] args){
        //Create GUI
        mainGUI = new GUI();
        mainGUI.setUp();
        
        //Create synthesizer and music player
        musicplayer = new MusicPlayer();
        synthesizer = musicplayer.getSynthesizer();
        
        //Must set up the logical layer objects in order to manipulate them when on click.
        layers = new Layer[16];
        
        for (int i = 0; i<16; i++)
        {
            layers[i] = new Layer();
        }
        setCurrentLayer(0);

        //Continuously check whether performance mode increments should be triggered
        while(true){
            if (mainGUI.on.getState())
            {
                if(currentMode instanceof PerformanceMode && SleepTimer<=0){
                    ((PerformanceMode)currentMode).doEvents();
                    SleepTimer = PAUSETIME;
                }
                else
                {
                    try{
                        Thread.sleep(60000/160);
                    }catch(Exception e){
                        System.out.println("You stopped me from waiting");
                        System.exit(1);
                    }
                    SleepTimer--;
                }
            }
        }
    }
    
    /**
     * Resets the sleep timer
     * @author Tom Fullalove
     */
    public static void resetSleep(){
        SleepTimer = 0;
    }
    
    /**
     * Gets the current layer being displayed by the GUI
     * 
     * @return the current logical layer
     * @author Mark Fowden
     */
    public static Layer getCurrentLayer(){
        return currentLayer;
    }
    
    /**
     * Set the layer to be displayed by the GUI
     * 
     * @param layer the layer to be set (as an integer)
     * @author Tom Fullalove
     */
    public static void setCurrentLayer(int layer){
        currentLayer = layers[layer];
    }
    
    /**
     * Gets the mode the simorion is currently in
     * 
     * @return the current Mode
     * @author Ilden Dengtash
     */
    public static Mode getMode(){
        return currentMode;
    }
    
    /**
     * Sets the mode for the simorion
     * 
     *@param mode the mode to set for the simorion
     *@author Ilden Dengtash
     */
    public static void setMode(Mode mode){
        currentMode = mode;
        mainGUI.ibutton.turnOffAllDots();
    }
    
    /**
     * Resets all the layers in the simorion
     * 
     * @author Ilden Dengtash
     */
    public static void clearAllLayers(){
        for(Layer layer:layers){
            layer.clearDots();
        }
    }
    
    /**
     * Sets the loop point for performance mode
     * 
     * @param newLoopPoint the new loop point for performance mode
     * @author Tom Fullalove
     */
    public static void setLoopPoint(int newLoopPoint){
        loopPoint=newLoopPoint+1;
    }
    
    
    /**
     * Set the loop speed of the simorion clock hand
     * 
     * @param loopSpeed new loop speed for the simorion
     * @author Tom Fullalove
     */
    public static void setLoopSpeed(int loopSpeed){
        double frac = 1/(double)(loopSpeed);
        PAUSETIME = (int) (frac*160);
    }
    
    /**
     * Gets a specific Layer of the simorion
     * 
     * @param layerNo The specific layer number to get
     * @return the layer fetched
     * @author Ilden Dengtash
     */
    public static Layer getLayer(int layerNo){
        return layers[layerNo];
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
            obj_out.writeObject ( PAUSETIME );
            
            //looppoint
            obj_out.writeObject ( loopPoint );
        }
        catch(Exception e)
        {
            System.out.print("wasted save");
        }       
    }
    
    public static void loadLayers(String filename){
         try{
            // Read from disk using FileInputStream
            FileInputStream f_in = new FileInputStream(filename);
            
            // Read object using ObjectInputStream
            ObjectInputStream obj_in = new ObjectInputStream (f_in);
            
            // Read an object
            layers = (Layer[]) obj_in.readObject();
            
            PAUSETIME=(int) obj_in.readObject();
            
            loopPoint=(int) obj_in.readObject();
            
            currentLayer=(Layer) layers[0];
            
            mainGUI.ibutton.turnOffAllDots();
            mainGUI.ibutton.relight(currentLayer);
        }
        catch(Exception e)
        {
            System.out.print("wasted load");
        }  
    }
}
