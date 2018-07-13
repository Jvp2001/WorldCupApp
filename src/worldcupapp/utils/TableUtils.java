package worldcupapp.utils;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.List;

public class TableUtils
{
    public static <S extends Object, T extends Object> TableView<S> newTableView(ObservableList<S> list, TableColumn... columns)
    {
        TableView<S> tableView = new TableView<S>(list);
        tableView.getColumns().setAll(columns);
        return tableView;
    }


    public static <S extends Object, T> TableColumn<S, T> newColumn(String name, String propName, double minWidth)
    {
        TableColumn<S, T> column = new TableColumn<>(name);
        column.setCellValueFactory(new PropertyValueFactory<>(propName));
        column.setMinWidth(minWidth);

        return column;

    }

    public static <S extends Object, T> TableColumn<S, T> newColumn(String name, double minWidth)
    {
        TableColumn<S, T> column = new TableColumn<>(name);
        column.setMinWidth(minWidth);

        return column;

    }

    public static <S extends Object, T> TableColumn<S, T> newColumn(String name, String propName, double minWidth, boolean bEditable)
    {
        TableColumn<S, T> column = newColumn(name, propName, minWidth);
        column.setEditable(true);
        return column;
    }

    public static <S extends Object, T> TableColumn<S, T> newColumn(String name, double minWidth, boolean bEditable)
    {
        TableColumn<S, T> column = newColumn(name, minWidth);
        column.setEditable(true);
        return column;
    }


    public static <S> void refreshTable(final TableView<S> tableView, final List<S> tableList)
    {
        tableView.setItems(null);
        tableView.layout();
        tableView.setItems(FXCollections.observableArrayList(tableList));
        tableView.layout();
    }


}
