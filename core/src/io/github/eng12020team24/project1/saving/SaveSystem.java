package io.github.eng12020team24.project1.saving;

import com.badlogic.gdx.utils.Json;
import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.characters.Infiltrator;
import io.github.eng12020team24.project1.characters.NeutralNPC;
import io.github.eng12020team24.project1.system.StationSystem;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

        writeSaveToFile();
    }

    private void writeSaveToFile() throws IOException {
        JSONObject save = new JSONObject();

        save.put("Infiltrators", generateInfiltratorSave());
        save.put("NPCS", extractNpcInfo(npcs));
        save.put("Systems", extractSystemsInfo(systems));
        save.put("Difficulty", difficulty);
        save.put("Auber", extractAuberInfo(auber));

        try{
            file.write(save.toString());
            System.out.println("Successfully saved JSON Object to File");
            System.out.println("\nJson Object: " + save.toString());
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            file.flush();
            file.close();
        }
    }

    private JSONObject generateInfiltratorSave() {
        JSONObject infiltratorObject = new JSONObject();

        infiltratorObject.put("alreadyAdded", extractInfiltratorInfo(infiltrators));
        infiltratorObject.put("toAdd", extractInfiltratorInfo(infiltratorsToAdd));

        System.out.println(infiltratorObject);

        return infiltratorObject;
    }

    private JSONArray extractInfiltratorInfo(ArrayList<Infiltrator> infiltrators) {
        JSONArray infiltratorInfo = new JSONArray();

        for (Infiltrator infiltrator: infiltrators) {
            JSONObject object = new JSONObject();

            object.put("x", infiltrator.getXPos());
            object.put("y", infiltrator.getYPos());
            object.put("name", infiltrator.getClass().getSimpleName());

            infiltratorInfo.put(object);
        }

        return infiltratorInfo;
    }

    private JSONArray extractNpcInfo(ArrayList<NeutralNPC> npcs) {
        JSONArray npcInfo = new JSONArray();

        for (NeutralNPC npc: npcs) {
            JSONObject object = new JSONObject();

            object.put("x", npc.getXPos());
            object.put("y", npc.getYPos());

            npcInfo.put(object);
        }

        return npcInfo;
    }

    private JSONArray extractSystemsInfo(ArrayList<StationSystem> systems) {
        JSONArray systemsInfo = new JSONArray();

        for(StationSystem system: systems) {
            JSONObject object = new JSONObject();

            object.put("x", system.getX());
            object.put("y", system.getY());
            object.put("status", system.getStatus());

            systemsInfo.put(object);
        }

        return systemsInfo;
    }

    private JSONObject extractAuberInfo(Auber auber)
    {
        JSONObject auberObject = new JSONObject();

        auberObject.put("x", auber.getXPos());
        auberObject.put("y", auber.getYPos());
        auberObject.put("health", auber.getHealth());

        return auberObject;
    }

}
