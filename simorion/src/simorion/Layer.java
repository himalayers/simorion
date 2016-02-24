package simorion;
import java.io.Serializable;
import java.util.Arrays;

/*
*This handles a 16x16 boolean array which represent a layer of music.
*@author Tom Fullalove
*
*/
public class Layer implements Serializable{
    private boolean[][] dots;
    private int instrument;
    private int velocity;
    
    public Layer(){
        dots = new boolean[16][16];
        instrument = 0;
        velocity = 300;
    }
    
    /*
    *Given the coordinates of a button it will toggle its stored value.
    *@author Tom Fullalove
    *@param posx the x coordinate of the button to be toggled
    *@param posy the y coordinate of the button to be toggled
    *@return returns the state of the button after being toggled
    */
    public boolean toggleDot(int posx, int posy){
        if (dots[posx][posy])
        {
            dots[posx][posy] = false;
            return false;
        }
        else
        {
            dots[posx][posy] = true;
            return true;
        }
    }
    
   
    public boolean getDotState(int posx, int posy){
        return dots[posx][posy];
    }
    
    public boolean[] getColumn(int i){
        boolean[] column = new boolean[16];
        for(int row=0;row<16;row++){
            column[row] = dots[row][i];
        }
        return column;
    }
    
    /*
    *Method to get an intrument
    *@return returns instrument
    */
    public int getInstrument(){
        return instrument;
    }
    
    /*
    *Method to get the velocity
    *@return returns velocity
    */
    public int getVelocity(){
        return velocity;
    }

    /*
    *Resets the 16x16 array of dots to false.
    *@author Ilden Dengtash
    */
    public void ClearDots(){
            dots = new boolean[16][16];
    }
}
