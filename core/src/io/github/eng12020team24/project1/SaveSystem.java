package io.github.eng12020team24.project1;

import com.badlogic.gdx.utils.Json;
import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.characters.Infiltrator;
import io.github.eng12020team24.project1.characters.NeutralNPC;
import io.github.eng12020team24.project1.system.StationSystem;

import java.util.ArrayList;

public class SaveSystem {
    private Json json;
    private ArrayList<Infiltrator> infiltrators;
    private ArrayList<NeutralNPC> npcs;
    private ArrayList<StationSystem> systems;
    private int difficulty;
    private Auber auber;

    public SaveSystem(ArrayList<Infiltrator> infiltrators, ArrayList<NeutralNPC> npcs, ArrayList<StationSystem> systems, int difficulty, Auber auber)
    {
        this.infiltrators = infiltrators;
        this.npcs = npcs;
        this.systems = systems;
        this.auber = auber;

        json = new Json();
    }
}
