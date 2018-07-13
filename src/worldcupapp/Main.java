package worldcupapp;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import worldcupapp.tables.ResultsTable;
import worldcupapp.utils.Storage;
import worldcupapp.utils.TableUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application
{

    public static TableView<Person> personTableView = ResultsTable.create();
    static Person[] people = new Person[0];
    Tab resultsTab;
    TabPane tabPane = new TabPane();
    public static Stage stage;
    List<Tab> participentTabs = new ArrayList<>();

    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * This is called when the program is closed
     *
     * @param event the current event sent by the Window.
     * @see WindowEvent
     */
    private static void onClosed(WindowEvent event)
    {
        Storage.save(References.fileRef);
    }


    @Override
    public void start(Stage primaryStage)
    {

        stage = primaryStage;
        primaryStage.setOnCloseRequest(Main::onClosed);
        System.out.println((Data.results.size()));

        MenuBar menuBar = Menus.create();

        BorderPane root = new BorderPane(personTableView, menuBar, null, null, null);

        final Scene scene = new Scene(root, 600, 400);

        scene.setOnKeyPressed(event ->
        {
            KeyCombination saveCombo = new KeyCharacterCombination("s", KeyCombination.META_DOWN);
            if (saveCombo.match(event))
            {
                Storage.save();
                TableUtils.refreshTable(personTableView, Data.results);
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Results");

    }


    {

        people = Storage.load();
        refresh();
        participentTabs = Arrays.asList(resultsTab, ParticipentTab.create(0), ParticipentTab.create(1));
        //System.out.println(Data.peopl/eTeams.get(0)[0].total);
        personTableView = ResultsTable.create();

        resultsTab = new Tab("Results", personTableView);
        resultsTab.setClosable(false);

        tabPane.getTabs().setAll(Arrays.asList(resultsTab));

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            TableUtils.refreshTable(Main.personTableView, Data.results);
        });

        System.out.println("People total: " + people[2].getName());
        for (int i = 0, peopleLength = people.length; i < peopleLength; i++)
        {
            Person person = people[i];
            tabPane.getTabs().addAll(ParticipentTab.create(i));
        }

        //addListeners();
    }


    private void addListeners()
    {
        Data.peopleTeams.addListener((InvalidationListener) c ->
        {
            ObservableList<Person> results = Data.results;
            for (int i = 0, resultsSize = results.size(); i < resultsSize; i++)
            {
                Person result = results.get(i);
                result.setTeams(Data.peopleTeams.get(i));
            }

        });
    }

    public static void refresh()
    {

        Data.results.forEach(person ->
        {
            Data.peopleTeams.addAll(person.getTeams());
        });
        Data.peopleTeams.forEach(teams ->
        {
            for (Team team : teams)
            {
                int t = 0;
                if (team.getPoints() == null) return;
                for (int point : team.points)
                {
                    System.out.println(point);
                    t += point;
                }
                team.setTotal(t);
            }

        });

        Data.results.forEach(person ->
        {
            int t = 0;
            for (Team team : person.getTeams())
            {


                t += team.getTotal();
            }
            person.setPoints(t);


        });
        TableUtils.refreshTable(personTableView, Data.results);

    }

}