package worldcupapp;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.$Gson$Preconditions;
import javafx.beans.value.ChangeListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Team
{
    String name;
    int total;
    int points[];

    @SerializedName(alternate = "is out", value = "bIsOut")
    boolean bIsOut = false;

    public Team(String name, int[] points)
    {
        this.name = name;
        this.points = points;
    }

    public Team(String name, int[] points, boolean bIsOut)
    {
        this.name = name;
        this.points = points;
        this.bIsOut = bIsOut;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getTotal()
    {
        return total;

    }

    public int[] getPoints()
    {
        return points;
    }

    public void setTotal(int total)
    {

        this.total = total;
    }


    public void setPoints(int[] points)
    {
        this.points = points;
    }

    public void addPoints(int points)
    {
        this.total += points;
    }

    public boolean getIsOut()
    {
        return bIsOut;
    }

    public void setIsOut(boolean bIsOut)
    {
        this.bIsOut = bIsOut;
    }



}
