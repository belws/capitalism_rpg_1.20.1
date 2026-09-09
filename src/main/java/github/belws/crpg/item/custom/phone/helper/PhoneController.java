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

    public static App getApp(int index) { return APPS[index]; }

    public static void selectPreviousApp() {
        selectedApp = Math.max(0, selectedApp -1);
    }

    public static void selectNextApp() {
        selectedApp = Math.min(APPS.length - 1, selectedApp + 1);
    }



    public static void openSelectedApp() {
        openApp(APPS[selectedApp]);
    }

    public static void openApp(App app) {
        activeApp = app;
        app.onOpen();
    }

    public static void closeApp() {
        activeApp = null;
    }

    public static void handleAction(PhoneAction action) {
        if (activeApp != null) {
            if (action == PhoneAction.BACK) {
                closeApp();
            } else {
                activeApp.handleAction(action);
            }
            return;
        }

        switch (action) {
            case LEFT -> selectPreviousApp();
            case RIGHT -> selectNextApp();
            case CONFIRM -> openSelectedApp();
            default -> {
                //Other actions do nothing on the home screen
            }
        }
    }
}
