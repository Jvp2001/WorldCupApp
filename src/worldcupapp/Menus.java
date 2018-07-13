package worldcupapp;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import worldcupapp.utils.Storage;

import java.io.File;

public class Menus
{
    private static MenuBar menuBar;
    private static Menu fileMenu;
    private static MenuItem saveItem, loadItem;

    private static boolean hasSaved = false;

    public static MenuBar create()
    {
        saveItem = new MenuItem("Save");
        loadItem = new MenuItem("Load");

        fileMenu = new Menu("File", null, saveItem, new SeparatorMenuItem(), loadItem);

        menuBar = new MenuBar(fileMenu);


        saveItem.setOnAction(Menus::onSave);
        loadItem.setOnAction(Menus::onOpen);

        if (System.getProperty("os.name").startsWith("Mac"))
        {
            menuBar.setUseSystemMenuBar(true);
        }
        return menuBar;
    }

    private static void onOpen(ActionEvent event)
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog((Window) event.getSource());
        if (file != null)
        {
            hasSaved = true;
            References.fileRef = file.getAbsolutePath();
            Storage.loadStorage();
        }

    }

    private static void onSave(ActionEvent event)
    {
        if (!hasSaved)
        {

        }
    }
}
