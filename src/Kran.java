public class Kran {

    public Kran() {
        z =  Kran_State.Z0;
    }

    public enum Kran_State {

        //X Y Z sind hier keine richtigen Koordinaten, da wir diese noch nicht ermitteln konnten
        Z0("Ruhezustand",0,0,0),
        Z_KALIBRIEREN("Kran kalibriert sich", 0, 0, 0),
        Z_AUFSAMMELN_LAGER("Block aus Lager aufsammeln", 1,1,1),
        Z_ABSETZEN_BAND("Block auf das Band abgelegt", 1,1,1),
        Z_AUFNEHMEN_BAND("Vom Band aufgesammelt", 1,1,1),
        Z_ABSETZEN_END("Am Ziel abgesetzt", 1,1,1);



        String s;
        int x;
        int y;
        int z;

        Kran_State(String s, int x, int y, int z) {
            this.s = s;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public enum Elektromagnet_State{
        Z_ELEKTROMAGNET_AN("Elektromagnet an"),
        Z_ELEKTROMAGNET_AUS("Elektromagnet aus");

        String s;

        Elektromagnet_State(String s){this.s = s;}
    }


    public enum Kran_Bit{

        X_MOTOR_RECHTS("x Achse rechts",00000000000000000000000000000000000000000000000001),
        X_MOTOR_LINKS("x Achse links",000000000000000000000000000000000000000000000000001),
        Y_MOTOR_VOR("y Achse vor",0000000000000000000000000000000000000000000000000001),
        Y_MOTOR_ZURUECK("y Achse zurück",00000000000000000000000000000000000000000000000000001),
        Z_MOTOR_HOCH("z Achse hoch",000000000000000000000000000000000000000000000000000001),
        Z_MOTOR_RUNTER("y Achse runter",0000000000000000000000000000000000000000000000000000001),
        X_INITIATOR("x Initiator", 0000000000000000000000000001),
        Y_INITIATOR("y Initiator", 00000000000000000000000000001),
        Z_INITIATOR("z Initiator", 000000000000000000000000000001),
        ELEKTROMAGNET_AN("Elektromagnet an", 00000000000000000000000000000000000000000000000000000001),
        ELEKTROMAGNET_AUS("Elektromagnet aus", 00000000000000000000000000000000000000000000000000000000),
        X_ET("x Achse Endtaster", 1),
        Y_ET("y Achse Endtaster", 1),
        Z_ET("z Achse Endtaster", 1);




        String description;
        int b;

        Kran_Bit(String description, int o) {
            this.description = description;
            this.b = o;
        }
    }

    private Kran_State z;
    private static Elektromagnet_State elektomagnet;

    public static Elektromagnet_State getElektomagnet() {
        return elektomagnet;
    }

    public static void setElektomagnet(Elektromagnet_State elektomagnett) {
        elektomagnet = elektomagnett;
    }
}
