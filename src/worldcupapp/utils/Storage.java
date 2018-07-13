package worldcupapp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.stage.FileChooser;
import worldcupapp.Data;
import worldcupapp.Main;
import worldcupapp.Person;
import worldcupapp.References;

import java.io.*;
import java.util.Arrays;

public class Storage
{
    private static Person[] people;
    private static String file = "resources/data/data.json";
    public static void save()
    {
        try (FileWriter writer = new FileWriter(file))
        {
            writer.write("");
            Person[] results = Data.results.toArray(new Person[0]);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(results, writer);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("SAVED!");
    }

    public static void save(String file)
    {
        Storage.file = file;
        System.loadLibrary();
        save();
    }

    public static void loadStorage()
    {
        ChooseFile();
        if(References.fileRef.equals(""))
            System.exit(-1);

        try (FileReader fileReader = new FileReader(References.fileRef))
        {
            people = new Gson().fromJson(fileReader, Person[].class);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


        Data.results = FXCollections
                .observableArrayList(Arrays.asList(people));
    }

    private static void ChooseFile()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your Data file");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Json","*.json"));

        References.fileRef = fileChooser.showOpenDialog(Main.stage).getAbsolutePath();
    }

    public static void reload()
    {
        save();
        load();
    }
    public static Person[] load()
    {
        loadStorage();
        return people;
    }
}
