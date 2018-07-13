package worldcupapp.utils;

import worldcupapp.Data;
import worldcupapp.Person;
import worldcupapp.Team;

public class FindUtils
{
    public static void UpdateTeam(Team team)
    {
        Data.results.forEach(person ->
        {
            for (Team team1 : person.getTeams())
            {
                if (team == team1)
                    team1 = team;
            }

        });
    }

    public static Team[] findParticipentsTeam(String participentsName)
    {
        for (Person person : Data.results)
        {
            if (person.getName().equals(participentsName)) return person.getTeams();
        }
        return null;

    }
}
