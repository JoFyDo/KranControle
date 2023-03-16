public class Controle extends Automat{


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
        return 0;
    }

    private void setZero(){

    }
}
