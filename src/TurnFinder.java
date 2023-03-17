import java.util.Objects;
import java.util.Scanner;

//Diese Klasse dient dazu, die Koordinaten der einzelnen Punkte zu finden
//Da wir mit den Turns der scheiben arbeiten müssen wir diese erst herausfinden. Dies geschieht mit dieser Klasse
public class TurnFinder extends Automat {

    private int xTurns;
    private int yTurns;
    private int zTurns;
    private Scanner s;
    private boolean isZero = false;
    private boolean move = true;
    private boolean x = false;
    private boolean y = false;
    private boolean z = false;
    private int xIniOld = 0;
    private int yIniOld = 0;
    private int zIniOld = 0;

    /**
     * The constructor of the abstract class Automat
     *
     * @param inputMask  The mask that is used to filter the sensor values
     * @param outputMask The mask that is used to filter the aktuator values
     */
    public TurnFinder(int inputMask, int outputMask) {
        super(inputMask, outputMask);
        s = new Scanner(System.in);

        Thread userInputThread = new Thread(() -> {
            while (true) {
                String str = s.nextLine();
                if (Objects.equals(str, "\n")) {
                    move = false;
                    x = false;
                    y = false;
                    z = false;
                    System.out.println("X Achse:" + xTurns);
                    System.out.println("Y Achse:" + yTurns);
                    System.out.println("Z Achse:" + zTurns);
                } else if (str.toUpperCase() == "X") {
                    x = true;
                    move = true;
                } else if (str.toUpperCase() == "Y") {
                    y = true;
                    move = true;
                } else if (str.toUpperCase() == "Z") {
                    z = true;
                    move = true;
                }
            }
        });
        userInputThread.start();
    }

    @Override
    public int transition(int input) {

        if (!isZero) setZero(input);

        if (move) turn(input);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public int turn(int input) {
        int xIni = input & Kran.Kran_Bit.X_INITIATOR.b;
        int yIni = input & Kran.Kran_Bit.Y_INITIATOR.b;
        int zIni = input & Kran.Kran_Bit.Z_INITIATOR.b;
        int returnVal = 0;

        if(x){
            returnVal = returnVal | Kran.Kran_Bit.X_MOTOR_RECHTS.b;
        } else if (y) {
            returnVal = returnVal | Kran.Kran_Bit.Y_MOTOR_VOR.b;
        } else if (z) {
            returnVal = returnVal | Kran.Kran_Bit.Z_MOTOR_RUNTER.b;
        }

        if(xIni != xIniOld){
            xTurns++;
        }
        if(yIni != yIniOld){
            yTurns++;
        }
        if(zIni != zIniOld){
            zTurns++;
        }

        xIniOld = xIni;
        yIniOld = yIni;
        zIniOld = zIni;

        return returnVal;
    }

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
            isZero = true;
        }
        return returnVal;
    }

}
