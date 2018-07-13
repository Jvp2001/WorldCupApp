package worldcupapp;

import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class References
{
    public static final String PEOPLE = "resources/data/data.json";

    public static String fileRef = "";


    public static final Type resultsType = new TypeToken<ArrayList<Person>>(){}.getType();
}
