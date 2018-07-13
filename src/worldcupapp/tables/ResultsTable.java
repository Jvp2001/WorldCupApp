package worldcupapp.tables;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import worldcupapp.Data;
import worldcupapp.Person;
import worldcupapp.utils.TableUtils;

public class ResultsTable
{
    private static ObservableList<Person> personObservableList;


    public static TableView<Person> create()
    {
        //personObservableList.add(new Person("Joshua",new String[]{"Bob"}, new int[]{3,3,3,3}));
        TableColumn<Person, String> name = TableUtils.newColumn("People", "name", 100);
        TableColumn<Person, Integer> points = TableUtils.newColumn("Points", "points", 100);
        TableView<Person> table = TableUtils.newTableView(Data.results, name, points/*name*/);

        return table;


    }
}
