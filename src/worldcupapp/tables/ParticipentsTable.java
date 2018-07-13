package worldcupapp.tables;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.*;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import worldcupapp.Data;
import worldcupapp.Main;
import worldcupapp.NumberConverter;
import worldcupapp.utils.FindUtils;
import worldcupapp.utils.Storage;
import worldcupapp.utils.TableUtils;
import worldcupapp.Team;

import java.util.concurrent.atomic.AtomicReference;

public class ParticipentsTable
{
    private static final ObservableList status = FXCollections.observableArrayList("Eliminated", "Not Eliminated");

    private static String keyPressed = "", name = "";

    private static TableView<Team> tableView;

    public static TableView create(int index, String name)
    {
        ParticipentsTable.name = name;
        ObservableList<Team> data = FXCollections.observableArrayList();
        Team[] teams = Data.results.get(index).getTeams();
        for (Team team : teams)
        {
            data.add(team);
        }


        TableColumn<Team, String> teamColumn = new TableColumn<>("Teams");
        teamColumn.setCellValueFactory(new PropertyValueFactory("name"));//TableUtils.newColumn("Teams","name",200);

        TableColumn<Team, Integer> pointsColumn = TableUtils.newColumn("Points", 200, true);
        pointsColumn.setCellFactory(param -> new TextFieldTableCell<>(new NumberConverter()));

        pointsColumn.setOnEditCommit(event ->
        {
            TableUtils.refreshTable(Main.personTableView, Data.results);
            Team rowValue = event.getRowValue();
            Integer newValue = event.getNewValue();
            rowValue.setTotal(newValue);
            //Data.results.clear();
            Storage.reload();
//            Main.refresh();
//            TableUtils.refreshTable(Main.personTableView, Data.results);

        });
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        TableColumn<Team, String> isOutColumn = TableUtils.newColumn("Is Out", 200, true);
        //isOutColumn.setCellFactory(ChoiceBoxListCell.foristView(status));
        isOutColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(status));

        isOutColumn.setOnEditCommit(event ->
        {
            Team rowValue = event.getRowValue();

            String newValue = event.getNewValue();
            switch (newValue)
            {
                case "Eliminated":
                    rowValue.setIsOut(true);
                    break;
                case "Not Eliminated":
                    rowValue.setIsOut(false);
                    break;
            }
            Storage.reload();
        });
        isOutColumn.setCellValueFactory(param ->
        {
            Boolean value = param.getValue().getIsOut();
            return new SimpleObjectProperty<>(value ? "Eliminated" : "Not Eliminated");
        });

        tableView = new TableView<>();
        tableView.getColumns().setAll(teamColumn, pointsColumn, isOutColumn);
        tableView.setItems(data);
        tableView.setEditable(true);


        return tableView;
    }

    private static void keypresed(KeyEvent event)
    {

        String ckey = event.getCharacter();
        keyPressed += ckey;
        for (Team team : FindUtils.findParticipentsTeam(name))
        {
            if (team.getName().startsWith(keyPressed))
            {
                boolean found = false;
                for (Team team1 : tableView.getItems())
                {
                    if (team1 == team) found = true;
                }
                if(found)
                {
                    tableView.getSelectionModel().select(team);
                }
            }
        }

    }

    private static class BooleanConvertor extends StringConverter
    {
        @Override
        public String toString(Object object)
        {
            if (object instanceof Boolean)
            {
                Boolean o = (Boolean) object;
                return o.toString().equalsIgnoreCase("true") ? "Eliminated" : "Not Eliminated";
            }
            return null;
        }

        @Override
        public Object fromString(String string)
        {
            return string.equalsIgnoreCase("Eliminated") ? true : false;
        }
    }
}
