package io.github.eng12020team24.project1.saving;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LoadSystem {
    private JSONObject json;

    public LoadSystem(String fileName) throws IOException {
        json = new JSONObject(readAllBytesJava7(fileName));
    }

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

    public boolean getDifficulty()
    {
        return (boolean) json.get("Difficulty");
    }
}
