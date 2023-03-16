public class Controle extends Automat{

    private boolean isCalibrated = false;


    /**
     * The constructor of the abstract class Automat
     *
     * @param inputMask  The mask that is used to filter the sensor values
     * @param outputMask The mask that is used to filter the aktuator values
     */
    public Controle(int inputMask, int outputMask) {
        super(inputMask, outputMask);
    }

    @Override
    public int transition(int input) {

        if(!isCalibrated)setZero(input);

        return 0;
    }

    private void setZero(int input){

        int xET = input>>;
        int yET = input>>;
        int zET = input;

        if(xET){

        }
        if(yET){

        }
        if(zET){

        }



    }
}
