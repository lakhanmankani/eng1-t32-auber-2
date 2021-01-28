package io.github.eng12020team24.project1;

import com.badlogic.gdx.utils.Json;
import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.characters.Infiltrator;
import io.github.eng12020team24.project1.characters.NeutralNPC;
import io.github.eng12020team24.project1.system.StationSystem;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import

public class SaveSystem {
    private Json json;
    private ArrayList<Infiltrator> infiltrators;
    private ArrayList<Infiltrator> infiltratorsToAdd;
    private ArrayList<NeutralNPC> npcs;
    private ArrayList<StationSystem> systems;
    private int difficulty;
    private Auber auber;
    private FileWriter file;

    public SaveSystem(ArrayList<Infiltrator> infiltrators, ArrayList<NeutralNPC> npcs, ArrayList<StationSystem> systems, int difficulty, Auber auber, ArrayList<Infiltrator> infiltratorsToAdd) throws IOException {
        this.infiltrators = infiltrators;
        this.npcs = npcs;
        this.systems = systems;
        this.difficulty = difficulty;
        this.auber = auber;
        this.infiltratorsToAdd = infiltratorsToAdd;

        json = new Json();
        file = new FileWriter("save.txt");

        generateInfiltratorSave();
    }

    private void generateInfiltratorSave()
    {
        ArrayList<Object> data = new ArrayList<>();

        for(Infiltrator infiltrator: infiltratorsToAdd) {
            JSONObject object = new JSONObject();

            data.add(infiltrator.getXPos());
            data.add(infiltrator.getYPos());
            data.add(infiltrator.getClass().getSimpleName());
        }

        for (Infiltrator infiltrator: infiltrators) {
            data.add(infiltrator.getXPos());
            data.add(infiltrator.getYPos());
            data.add(infiltrator.getClass().getSimpleName());
        }

        System.out.println(json.prettyPrint(data));
    }
}
