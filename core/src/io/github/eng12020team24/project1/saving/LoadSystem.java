package io.github.eng12020team24.project1.saving;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LoadSystem {
    private JSONObject json;

    public LoadSystem(String fileName) throws IOException {
        json = new JSONObject(readAllBytesJava7(fileName));
    }

    /**
     * Helper function to convert file to string
     * @param filePath String of file path to load
     * @return String containing file contents
     */
    private static String readAllBytesJava7(String filePath)
    {
        String content = "";

        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return content;
    }

    /**
     * Generates a list containing the infiltrator info from save of infiltrators not already spawned
     * @return ArrayList of infiltrator info
     */
    public ArrayList<ArrayList> generateInfiltratorToAddList()
    {
        JSONObject object = (JSONObject) json.get("Infiltrators");
        JSONArray infiltrators = (JSONArray) object.get("toAdd");

        ArrayList<ArrayList> toAdd = new ArrayList<>();

        for (int i = 0; i < infiltrators.length(); i++) {
            JSONObject infiltrator = infiltrators.getJSONObject(i);
            ArrayList list = new ArrayList();

            list.add(infiltrator.get("x"));
            list.add(infiltrator.get("y"));
            list.add(infiltrator.get("name"));

            toAdd.add(list);
        }

        return toAdd;
    }

    /**
     * Generates a list containing the infiltrator info from save of infiltrators already spawned
     * @return ArrayList of infiltrator info
     */
    public ArrayList<ArrayList> generateInfiltratorList()
    {
        JSONObject object = (JSONObject) json.get("Infiltrators");
        JSONArray infiltrators = (JSONArray) object.get("alreadyAdded");

        ArrayList<ArrayList> added = new ArrayList<>();

        for (int i = 0; i < infiltrators.length(); i++) {
            JSONObject infiltrator = infiltrators.getJSONObject(i);
            ArrayList list = new ArrayList();

            list.add(infiltrator.get("x"));
            list.add(infiltrator.get("y"));
            list.add(infiltrator.get("name"));

            added.add(list);
        }

        return added;
    }

    /**
     * Generates a list containing NPC info from save file
     * @return ArrayList with NPC info
     */
    public ArrayList<ArrayList> generateNpcList()
    {
        JSONArray object = (JSONArray) json.get("NPCS");

        ArrayList<ArrayList> npcs = new ArrayList<>();

        for (int i = 0; i < object.length(); i++) {
            JSONObject npc = object.getJSONObject(i);
            ArrayList list = new ArrayList();

            list.add(npc.get("x"));
            list.add(npc.get("y"));

            npcs.add(list);
        }

        return npcs;
    }

    /**
     * Generates a list containing systems info
     * @return ArrayList with systems info
     */
    public ArrayList<ArrayList> generateSystemsList()
    {
        JSONArray object = (JSONArray) json.get("Systems");

        ArrayList<ArrayList> systems = new ArrayList<>();

        for (int i = 0; i < object.length(); i++) {
            JSONObject system = object.getJSONObject(i);
            ArrayList list = new ArrayList();

            list.add(system.get("x"));
            list.add(system.get("y"));
            list.add(system.get("status"));
            list.add(system.get("health"));

            systems.add(list);
        }

        return systems;
    }

    /**
     * Generates a list containing the current PowerUps info
     * @return ArrayList containing PowerUp info
     */
    public ArrayList<ArrayList> generateCurrentPowerupsList()
    {
        JSONObject object = (JSONObject) json.get("Powerups");
        JSONArray powerups = (JSONArray) object.get("current");

        ArrayList<ArrayList> current = new ArrayList<>();

        for(int i = 0; i < powerups.length(); i++)
        {
            JSONObject powerup = powerups.getJSONObject(i);
            ArrayList list = new ArrayList();

            list.add(powerup.get("name"));
            list.add(powerup.get("xPos"));
            list.add(powerup.get("yPos"));
            list.add(powerup.get("timer"));

            current.add(list);
        }

        return current;
    }

    /**
     * Generates a list containing the unused PowerUps info
     * @return ArrayList containing PowerUp info
     */
    public ArrayList<ArrayList> generateUnusedPowerupsList()
    {
        JSONObject object = (JSONObject) json.get("Powerups");
        JSONArray powerups = (JSONArray) object.get("unused");

        ArrayList<ArrayList> unused = new ArrayList<>();

        for(int i = 0; i < powerups.length(); i++)
        {
            JSONObject powerup = powerups.getJSONObject(i);
            ArrayList list = new ArrayList();

            list.add(powerup.get("name"));
            list.add(powerup.get("xPos"));
            list.add(powerup.get("yPos"));
            list.add(powerup.get("timer"));

            unused.add(list);
        }

        return unused;
    }

    /**
     * Returns saved difficulty level
     * @return int containing difficulty level
     */
    public int getDifficulty()
    {
        return (int) json.get("Difficulty");
    }

    /**
     * Returns JSONObject containing saved Auber details
     * @return JSONObject containing saved Auber details
     */
    public JSONObject getAuberDetails()
    {
        return (JSONObject) json.get("Auber");
    }
}
