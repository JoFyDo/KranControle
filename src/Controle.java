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

    private int setZero(int input){

        int returnVal = 0;
        int xET = input>>17;
        int yET = input>>18;
        int zET = input>>20;

        if(xET != 1){

        }
        if(yET != 1){

        }
        if(zET != 1){

        }



    }
}
