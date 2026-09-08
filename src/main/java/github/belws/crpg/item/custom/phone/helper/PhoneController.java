package github.belws.crpg.item.custom.phone.helper;

import github.belws.crpg.item.custom.phone.apps.App;

public class PhoneController {
    private static App activeApp = null;

    public static App getActiveApp() {
        return activeApp;
    }

    public static void openApp(App app) {
        activeApp = app;
    }

    public static void closeApp() {
        activeApp = null;
    }
}
