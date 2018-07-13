package worldcupapp;

public class Person
{

    String name;
    Team teams[];
    transient int points = 0;

    public Person(String name, Team[] teams)
    {
        this.name = name;
        this.teams = teams;
        for (Team team : teams)
        {
            System.out.println(teams);
            this.points += team.total;
        }


    }



    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Team[] getTeams()
    {
        return teams;
    }

    public void setTeams(Team[] teams)
    {
        this.teams = teams;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    @Override
    public String toString()
    {
        var teamBuilder = new StringBuilder();
        var output = "";
        output.format("Name: %s\n,Points: %s,Teams: %s\n", this.name, this.points,this.teams[0]);

        return output;
    }
}
