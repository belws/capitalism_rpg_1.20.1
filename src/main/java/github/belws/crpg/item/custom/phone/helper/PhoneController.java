package github.belws.crpg.item.custom.phone.helper;

import github.belws.crpg.item.custom.phone.apps.App;
import github.belws.crpg.item.custom.phone.apps.ClockApp;
import github.belws.crpg.item.custom.phone.apps.NpcManagerApp;

public class PhoneController {
    private static App activeApp = null;
    private static int selectedApp = 0;

    private static final App[] APPS = {
            new ClockApp(),
            new NpcManagerApp()
    };


    // Getters
    public static App getActiveApp() {
        return activeApp;
    }

    public static int getSelectedAppIndex() {
        return selectedApp;
    }

    public static int getAppCount() {
        return APPS.length;
    }

    public static App getApp(int index) {
        return APPS[index];
    }

    public static void selectPreviousApp() {
        selectedApp = Math.max(0, selectedApp -1);
    }

    public static void selectNextApp() {
        selectedApp = Math.min(APPS.length - 1, selectedApp + 1);
    }



    public static void openSelectedApp() {
        APPS[selectedApp].open();
    }

    public static void openApp(App app) {
        activeApp = app;
    }

    public static void closeApp() {
        activeApp = null;
    }
}
