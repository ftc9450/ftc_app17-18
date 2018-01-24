package org.firstinspires.ftc.team9450.subsystems;

import java.util.ArrayList;

/**
 * Created by dhruv on 1/22/18.
 */

public class SubsystemManager{
    private ArrayList<Subsystem> systems;

    public SubsystemManager() {
        systems = new ArrayList<Subsystem>();
    }

    public SubsystemManager add(Subsystem s) {
        systems.add(s);
        return this;
    }

    public void stopSystems() {
        for (Subsystem s: systems) s.stop();
    }

    public void loopSystems() {
        for (Subsystem s: systems) s.loop();
    }
}
