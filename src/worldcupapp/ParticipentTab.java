package worldcupapp;

import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import worldcupapp.tables.ParticipentsTable;

import java.util.ArrayList;
import java.util.List;

public class ParticipentTab
{

    public static Tab create(int index)
    {
        String name = Data.results.get(index).name;
        List nums = new ArrayList();
        TableView participentsTable = ParticipentsTable.create(index,name);
        Tab tab = new Tab(name,participentsTable);
        tab.setClosable(false);
        return tab;
    }
}
