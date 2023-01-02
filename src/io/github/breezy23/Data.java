package io.github.breezy23;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import io.github.breezy23.modifiers.Modifiers;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;


public class Data implements Serializable {
    private static transient final long serialVersionUID = -1681012206529286330L;

    public HashMap<Modifiers, Boolean> mods;

    // Can be used for saving
    public Data(HashMap<Modifiers, Boolean> mods) {
        this.mods = mods;
    }
    // Can be used for loading
    public Data(Data loadedData) {
        mods = loadedData.mods;
    }

    public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Data loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            Data data = (Data) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void save() {
        HashMap<Modifiers, Boolean> dataMods = new HashMap<>();
        if(BBORS.data == null) {
            for(Modifiers mod : Modifiers.values()) {
                dataMods.put(mod, false);
            }
        } else {
            dataMods = BBORS.data.mods;
        }

        new Data(dataMods).saveData("BBORS.data");
        Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
    }
    public static Data load() {
        File file = new File("BBORS.data");

        // Call save() to create blank data
        if(!file.exists()) { save(); }

        Data data = new Data(Data.loadData("BBORS.data"));

        Bukkit.getServer().getLogger().log(Level.INFO, "Data loaded");

        return data;
    }
}