import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("LanguageDetectionInspection")
public class Controle extends Automat {

    private boolean isCalibrated = false;

    private int xTurns;
    private int yTurns;
    private int zTurns;
    private int xIniOld = 0;
    private int yIniOld = 0;
    private int zIniOld = 0;
    private Kran kran;
    List<Kran.Kran_State> kranArrayList;
    private int counterList = 0;


    /**
     * The constructor of the abstract class Automat
     *
     * @param inputMask  The mask that is used to filter the sensor values
     * @param outputMask The mask that is used to filter the aktuator values
     */
    public Controle(int inputMask, int outputMask) {
        super(inputMask, outputMask);
        kran = new Kran();
        kranArrayList = kran.getAllStates();
    }

    @Override
    public int transition(int input) {

        int returnValue = 0;
        if(counterList > 4){
            counterList = 0;
        }

        switch (kran.getZ()){
            case Z_KALIBRIEREN -> returnValue = setZero(input);
            case Z_AUFSAMMELN_LAGER -> returnValue = navigate(Kran.Kran_State.Z_AUFSAMMELN_LAGER.x, Kran.Kran_State.Z_AUFSAMMELN_LAGER.y, Kran.Kran_State.Z_AUFSAMMELN_LAGER.z);
            case Z_ABSETZEN_BAND -> returnValue = navigate(Kran.Kran_State.Z_ABSETZEN_BAND.x, Kran.Kran_State.Z_ABSETZEN_BAND.y, Kran.Kran_State.Z_ABSETZEN_BAND.z);
            case Z_AUFNEHMEN_BAND -> returnValue = navigate(Kran.Kran_State.Z_AUFNEHMEN_BAND.x, Kran.Kran_State.Z_AUFNEHMEN_BAND.y, Kran.Kran_State.Z_AUFNEHMEN_BAND.z);
            case Z_ABSETZEN_END -> returnValue = navigate(Kran.Kran_State.Z_ABSETZEN_END.x, Kran.Kran_State.Z_ABSETZEN_END.y, Kran.Kran_State.Z_ABSETZEN_END.z);
        }

        updateLocation(input);

        return returnValue;
    }

    // unsere Startposition ist (0|0|0). Für die x-Achse bedeutet das ganz rechts, y-Achse ganz hinten und z-Achse ganz oben


    private int setZero(int input) {

        int returnVal = 0;
        int xET = input & Kran.Kran_Bit.X_ET.b;
        int yET = input & Kran.Kran_Bit.Y_ET.b;
        int zET = input & Kran.Kran_Bit.Z_ET.b;
        boolean x = false;
        boolean y = false;
        boolean z = false;

        if (xET != 1) {
            returnVal += Kran.Kran_Bit.X_MOTOR_LINKS.b;
        } else {
            x = true;
        }
        if (yET != 1) {
            returnVal += Kran.Kran_Bit.Y_MOTOR_ZURUECK.b;
        } else {
            y = true;
        }
        if (zET != 1) {
            returnVal += Kran.Kran_Bit.Z_MOTOR_HOCH.b;
        } else {
            z = true;
        }

        if (x && y && z) {
            kran.setZ(kranArrayList.get(counterList));
            counterList++;
        }
        return returnVal;
    }

    private void updateLocation(int input) {
        int xIni = input & Kran.Kran_Bit.X_INITIATOR.b;
        int yIni = input & Kran.Kran_Bit.Y_INITIATOR.b;
        int zIni = input & Kran.Kran_Bit.Z_INITIATOR.b;

        int xMotorR = input & Kran.Kran_Bit.X_MOTOR_RECHTS.b;
        int xMotorL = input & Kran.Kran_Bit.X_MOTOR_LINKS.b;
        int yMotorV = input & Kran.Kran_Bit.Y_MOTOR_VOR.b;
        int yMotorZ = input & Kran.Kran_Bit.Y_MOTOR_ZURUECK.b;
        int zMotorH = input & Kran.Kran_Bit.Z_MOTOR_HOCH.b;
        int zMotorR = input & Kran.Kran_Bit.Z_MOTOR_RUNTER.b;

        if (xIni != xIniOld && xMotorR == 1) {
            xTurns--;
        } else if (xIni != xIniOld && xMotorL == 1) {
            xTurns++;
        }
        if (yIni != yIniOld && yMotorV == 1) {
            yTurns++;
        } else if (yIni != yIniOld && yMotorZ == 1) {
            yTurns--;
        }
        if (zIni != zIniOld && zMotorH == 1) {
            zTurns--;
        } else if (zIni != zIniOld && zMotorR == 1) {
            zTurns++;
        }

        xIniOld = xIni;
        yIniOld = yIni;
        zIniOld = zIni;


    }

    private int navigate(int xRotations, int yRotations, int zRotations) {

        int returnvalue = 0;

        if (xTurns < xRotations) {
            returnvalue = returnvalue | Kran.Kran_Bit.X_MOTOR_LINKS.b;
        } else if (xTurns > xRotations) {
            returnvalue = returnvalue | Kran.Kran_Bit.X_MOTOR_RECHTS.b;
        }

        if (yTurns < yRotations) {
            returnvalue = returnvalue | Kran.Kran_Bit.Y_MOTOR_ZURUECK.b;
        } else if (yTurns > yRotations) {
            returnvalue = returnvalue | Kran.Kran_Bit.Y_MOTOR_VOR.b;
        }

        if (zTurns < zRotations) {
            returnvalue = returnvalue | Kran.Kran_Bit.Z_MOTOR_RUNTER.b;
        } else if (zTurns > zRotations) {
            returnvalue = returnvalue | Kran.Kran_Bit.Z_MOTOR_HOCH.b;
        }

        if (xTurns == xRotations && yTurns == yRotations && zTurns == zRotations) {
            kran.setZ(kranArrayList.get(counterList));
            counterList++;
            returnvalue = returnvalue | toggleMagnet();
        }

        return returnvalue;
    }

    private int toggleMagnet() {

        if (Kran.getElektomagnet() == Kran.Elektromagnet_State.Z_ELEKTROMAGNET_AUS) {
            Kran.setElektomagnet(Kran.Elektromagnet_State.Z_ELEKTROMAGNET_AN);
            return Kran.Kran_Bit.ELEKTROMAGNET_AN.b;
        } else {
            Kran.setElektomagnet(Kran.Elektromagnet_State.Z_ELEKTROMAGNET_AUS);
            return Kran.Kran_Bit.ELEKTROMAGNET_AUS.b;
        }
    }


}
