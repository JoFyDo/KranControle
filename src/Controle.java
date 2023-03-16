public class Controle extends Automat {

    private boolean isCalibrated = false;

    private int xTurns;
    private int yTurns;
    private int zTurns;


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

        if (!isCalibrated) setZero(input);

        updateLocation(input);

        return 0;
    }


    private int setZero(int input) {


        int returnVal = 0;
        int xET = input >> 17;
        int yET = input >> 18;
        int zET = input >> 20;

        if (xET != 1) {
            returnVal += Kran.Kran_Bit.X_MOTOR_LINKS.b;
        }
        if (yET != 1) {
            returnVal += Kran.Kran_Bit.Y_MOTOR_ZURUECK.b;
        }
        if (zET != 1) {
            returnVal += Kran.Kran_Bit.Z_MOTOR_HOCH.b;
        }
        return returnVal;
    }

    private void updateLocation(int input){
        int xIni = >> ;
        int yIni;
        int zIni;


    }

    private int navigate(int xRotations, int yRotations, int zRotations){

        int returnvalue = 0;

        if(xTurns < xRotations){
            returnvalue = returnvalue | Kran.Kran_Bit.X_MOTOR_LINKS.b;
        }

        else if(xTurns > xRotations){
            returnvalue = returnvalue | Kran.Kran_Bit.X_MOTOR_RECHTS.b;
        }

        if(yTurns < yRotations){
            returnvalue = returnvalue | Kran.Kran_Bit.Y_MOTOR_ZURUECK.b;
        }

        else if(yTurns > yRotations){
            returnvalue = returnvalue | Kran.Kran_Bit.Y_MOTOR_VOR.b;
        }

        if(zTurns < zRotations){
            returnvalue = returnvalue | Kran.Kran_Bit.Z_MOTOR_RUNTER.b;
        }

        else if(zTurns > zRotations){
            returnvalue = returnvalue | Kran.Kran_Bit.Z_MOTOR_HOCH.b;
        }
        return  returnvalue;
    }


}
