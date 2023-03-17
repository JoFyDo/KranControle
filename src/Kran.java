public class Kran {

    public Kran() {
        z =  Kran_State.Z0;
    }

    public enum Kran_State {
        Z0("Ruhezustand");

        String s;

        Kran_State(String s) {
            this.s = s;
        }

    }


    public enum Kran_Bit{

        X_MOTOR_RECHTS("x Achse rechts",1),
        X_MOTOR_LINKS("x Achse links",1),
        Y_MOTOR_VOR("y Achse vor",1),
        Y_MOTOR_ZURUECK("y Achse zurück",1),
        Z_MOTOR_HOCH("z Achse hoch",1),
        Z_MOTOR_RUNTER("y Achse runter",1),
        X_INITIATOR("x Initiator", 1),
        Y_INITIATOR("y Initiator", 1),
        Z_INITIATOR("z Initiator", 1);



        String description;
        int b;

        Kran_Bit(String description, int o) {
            this.description = description;
            this.b = o;
        }
    }

    private Kran_State z;
}
