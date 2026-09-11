package github.belws.crpg.client;

public class ClientWakeUpState {

    private static boolean received = false;
    private static boolean customWakeUpAllowed = false;
    private static int wakeUpMinutes = -1;

    private ClientWakeUpState() {
    }

    public static void update(boolean allowed, int minutes) {
        customWakeUpAllowed = allowed;
        wakeUpMinutes = minutes;
        received = true;
    }

    public static void reset() {
        received = false;
        customWakeUpAllowed = false;
        wakeUpMinutes = -1;
    }

    public static boolean hasReceivedSettings() {
        return received;
    }

    public static boolean isCustomWakeUpAllowed(){
        return customWakeUpAllowed;
    }

    public static int getWakeUpMinutes(){
        return wakeUpMinutes;
    }
}
