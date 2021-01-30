package io.github.eng12020team24.project1.saving;

import com.badlogic.gdx.utils.Json;
import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.characters.Infiltrator;
import io.github.eng12020team24.project1.characters.NeutralNPC;
import io.github.eng12020team24.project1.powerup.PowerUp;
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
    private ArrayList<PowerUp> unusedPowerups;
    private ArrayList<PowerUp> currentPowerups;

    public SaveSystem(ArrayList<Infiltrator> infiltrators, ArrayList<NeutralNPC> npcs, ArrayList<StationSystem> systems, int difficulty, Auber auber, ArrayList<Infiltrator> infiltratorsToAdd, ArrayList currentPowerups, ArrayList unusedPowerups) throws IOException {
        this.infiltrators = infiltrators;
        this.npcs = npcs;
        this.systems = systems;
        this.difficulty = difficulty;
        this.auber = auber;
        this.infiltratorsToAdd = infiltratorsToAdd;
        this.unusedPowerups = unusedPowerups;
        this.currentPowerups = currentPowerups;

        json = new Json();
        file = new FileWriter("save.txt");
    }

    /**
     * Writes a JSONObject to the file save.txt
     * @throws IOException if failed to save object
     */
    public void writeSaveToFile() throws IOException {
        JSONObject save = new JSONObject();

        save.put("Infiltrators", generateInfiltratorSave());
        save.put("NPCS", extractNpcInfo(npcs));
        save.put("Systems", extractSystemsInfo(systems));
        save.put("Difficulty", difficulty);
        save.put("Auber", extractAuberInfo(auber));
        save.put("Powerups", generatePowerupsSave());

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

    /**
     * Merges the 2 different lists of infiltrators into 1 JSONObject
     * @return JSONObject containing the two lists
     */
    private JSONObject generateInfiltratorSave() {
        JSONObject infiltratorObject = new JSONObject();

        infiltratorObject.put("alreadyAdded", extractInfiltratorInfo(infiltrators));
        infiltratorObject.put("toAdd", extractInfiltratorInfo(infiltratorsToAdd));

        return infiltratorObject;
    }

    private JSONObject generatePowerupsSave() {
        JSONObject object = new JSONObject();

        object.put("unused", extractPowerupsInfo(unusedPowerups));
        object.put("current", extractPowerupsInfo(currentPowerups));

        return object;
    }

    /**
     * Converts a list of infiltrators into a JSONArray
     * @param  infiltrators ArrayList of infiltrators to parse
     * @return JSONArray containing infiltrator info
     */
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

    /**
     * Converts a list of NPCs to an JSONArray
     * @param npcs ArrayList of NPCs to parse
     * @return JSONArray of NPCs info
     */
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

    /**
     * Converts a list of StaionSystems into a JSONArray
     * @param systems List of systems to parse
     * @return JSONArray of systems information
     */
    private JSONArray extractSystemsInfo(ArrayList<StationSystem> systems) {
        JSONArray systemsInfo = new JSONArray();

        for(StationSystem system: systems) {
            JSONObject object = new JSONObject();

            object.put("x", system.getX());
            object.put("y", system.getY());
            object.put("status", system.getStatus());
            object.put("health", system.getHealth());

            systemsInfo.put(object);
        }

        return systemsInfo;
    }

    /**
     * Creates a JSONObject containing info of Auber
     * @param auber Current Auber being used
     * @return JSONObject containing Auber data
     */
    private JSONObject extractAuberInfo(Auber auber) {
        JSONObject auberObject = new JSONObject();

        auberObject.put("x", auber.getXPos());
        auberObject.put("y", auber.getYPos());
        auberObject.put("health", auber.getHealth());

        return auberObject;
    }

    private JSONArray extractPowerupsInfo(ArrayList<PowerUp> powerups) {
        JSONArray powerupsInfo = new JSONArray();

        for(PowerUp powerup : powerups)
        {
            JSONObject object = new JSONObject();

            object.put("name", powerup.name);
            object.put("xPos", powerup.getxPos());
            object.put("yPos", powerup.getyPos());
            object.put("timer", powerup.getTimer());

            powerupsInfo.put(object);
        }

        return powerupsInfo;
    }

}
