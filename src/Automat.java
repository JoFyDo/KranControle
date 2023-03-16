public abstract class Automat {

    protected int inputMask = 0;
    protected int outputMask = 0;

    /**
     * The constructor of the abstract class Automat
     * @param inputMask The mask that is used to filter the sensor values
     * @param outputMask The mask that is used to filter the aktuator values
     */
    public Automat(int inputMask, int outputMask) {
        this.inputMask = inputMask;
        this.outputMask = outputMask;
    }

    /**
     * The transition method that has to available in all controller classes and can be used to send the sensor values to their respective controllers
     * @param input The sensor values
     * @return The new aktuator values
     */
    public abstract int transition(int input);

    /**
     * @return The indices of the sensors that are used by the controller
     */
    public int getInputMask() {
        return inputMask;
    }

    /**
     * @return The indices of the aktuators that are used by the controller
     */
    public int getOutputMask() {
        return outputMask;
    }
}