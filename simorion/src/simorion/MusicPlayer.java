package simorion;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;


/*
 * Synthesizer class.
 * @author Ilden Dengtash
 */
public class MusicPlayer
{
    private final int multiplier = 10;   
    private final static int PERCUSSION_CHANNEL = 10; 

    /*
     * Delay for a number of milliseconds.
     */
    public void delay( int ms ) 
    {
        try 
        {
            Thread.sleep( ms );
        } catch( Exception ex ) {
            Thread.currentThread().interrupt();
        }
    }

    /*
     * Method to get the synthesizer
     */
    public Synthesizer getSynthesizer() {
        Synthesizer synthesizer = null;	  
        try {
          synthesizer = MidiSystem.getSynthesizer();
          synthesizer.open();
        } catch ( Exception ex ) {
          System.out.println( ex ); System.exit( 1 );
        }
        return synthesizer;
    }

    /*
     * Method to play normal instrument.
     * @param synthesizer
     * @param channel 
     * @param program
     * @param pitch
     * @param velocity
     */
    public void playInstrument( Synthesizer synthesizer
                              , int channel
                              , int program
                              , int pitch
                              , int velocity
                              ) {
        MidiChannel[] midiChannels = synthesizer.getChannels();
        MidiChannel   midiChannel  = midiChannels[ channel ];
        Instrument[]  instruments  = synthesizer.getDefaultSoundbank().getInstruments();
        synthesizer.loadInstrument( instruments[ program ] );
        midiChannel.programChange( program );
        midiChannel.noteOn ( pitch, velocity );
        //delay( multiplier * velocity );
        midiChannel.noteOff( pitch, velocity );
    }

    /*
     * Method to play percussion instrument.
     * @param synthesizer
     * @param pitch
     * @param velocity
     */
    public void playPercussion( Synthesizer synthesizer
                              , int pitch
                              , int velocity
                              ) {
        MidiChannel[] midiChannels = synthesizer.getChannels();
        MidiChannel   midiChannel  = midiChannels[ PERCUSSION_CHANNEL ]; 
        midiChannel.noteOn ( pitch, velocity );
        delay( multiplier * velocity );
        midiChannel.noteOff( pitch, velocity );
    }
}