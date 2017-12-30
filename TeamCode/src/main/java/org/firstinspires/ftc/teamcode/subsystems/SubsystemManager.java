package org.firstinspires.ftc.teamcode.subsystems;

import java.util.ArrayList;
/**
 * Created by O on 10/28/2017.
 */

public class SubsystemManager {
    private ArrayList<Subsystem> subsystems;

    public SubsystemManager() {
        subsystems = new ArrayList<>();
    }

    public SubsystemManager add(Subsystem s) {
        subsystems.add(s);
        return this;
    }

    public void loopSystems() {
        for(Subsystem s : subsystems) {
            s.loop();
        }
    }
}
