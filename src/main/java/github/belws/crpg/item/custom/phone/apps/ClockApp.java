package github.belws.crpg.item.custom.phone.apps;

import github.belws.crpg.CapitalismRpg;
import github.belws.crpg.client.ClientWakeUpState;
import github.belws.crpg.item.custom.phone.ui.ButtonElement;
import github.belws.crpg.item.custom.phone.ui.NumberElement;
import github.belws.crpg.network.ModNetwork;
import github.belws.crpg.network.RequestWakeUpSettingsPacket;
import github.belws.crpg.network.SetWakeUpTimePacket;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.jline.utils.Log;

public class ClockApp extends App {

    private final NumberElement hourElement;
    private final NumberElement minuteElement;

    private int alarmHour;
    private int alarmMinute;
    private boolean alarmSet = false;

    public ClockApp() {
        super(
                "Clock",
                new ResourceLocation(
                        CapitalismRpg.MOD_ID,
                        "gui/apps/app1-clock.png"
                ),
                new ResourceLocation(
                        CapitalismRpg.MOD_ID,
                        "gui/clock-app-screen.png"
                )
        );

        addElement(new ButtonElement(
                70,180,110,20,
                "Add Alarm",
                this::createAlarm
        ));

        //TODO: add container to constructor either as boolean or argb
        //
        // I will eventually make it so the outline wont be displayed for this.
        // Also I might change the index version of element selection to loop
        hourElement = new NumberElement(
                70, 100, 70, 20,
                "H",
                0, 23, 6
        );

        minuteElement = new NumberElement(
                120, 100, 70, 20,
                "M",
                0,59,0
        );

        addElement(hourElement);
        addElement(minuteElement);
    }

    @Override
    public void render(
            GuiGraphics graphics,
            Font font,
            int phoneX,
            int phoneY
    ) {
        super.render(graphics, font, phoneX, phoneY);

        String status;

        if (!ClientWakeUpState.hasReceivedSettings()) {
            status = "Loading...";
        } else if (!ClientWakeUpState.isCustomWakeUpAllowed()) {
            status = "Single-player only";
        } else {
            int minutes = ClientWakeUpState.getWakeUpMinutes();

            if (minutes == -1) {
                status = "No alarm set";
            } else {
                status = String.format(
                        "Alarm: %02d:%02d",
                        minutes / 60,
                        minutes % 60
                );
            }
        }
        graphics.drawCenteredString(
                font,
                status,
                phoneX + 125,
                phoneY + 145,
                0xFFFFFF
        );
    }

    private void createAlarm() {
        if (!ClientWakeUpState.hasReceivedSettings()
            || !ClientWakeUpState.isCustomWakeUpAllowed()) {
            return;
        }

        int hour = hourElement.getValue();
        int minute = minuteElement.getValue();

        ClientWakeUpState.reset();


        ModNetwork.CHANNEL.sendToServer(
                new SetWakeUpTimePacket(hour, minute)
        );

        //Test
        System.out.printf(
                "Alarm set for %02d:%02d%n",
                hour,
                minute
        );
    }

    @Override
    public void onOpen() {
        ClientWakeUpState.reset();

        ModNetwork.CHANNEL.sendToServer(
                new RequestWakeUpSettingsPacket()
        );
    }
}
