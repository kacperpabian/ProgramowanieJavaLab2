package sample;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ControllerStuff {

    @FXML
    private TableView<TableViewStuff> tableStuff;
    @FXML
    private TableColumn<TableViewStuff, String> columnType;
    @FXML
    private TableColumn<TableViewStuff, String> columnName;
    @FXML
    private TableColumn<TableViewStuff, String> columnDescription;
    @FXML
    private TableColumn<TableViewStuff, ImageView> columnImage;

    private String jsonStuffInfo = "Resources/stuffInfo.json";
    private String jsonStuffString = "";
    List<Stuff> stuffList;

    public String getJsonStuffString() {
        return jsonStuffString;
    }

    public void setJsonStuffString(String jsonStuffString) {
        this.jsonStuffString = jsonStuffString;
    }

    public String getJsonStuffInfo() {
        return jsonStuffInfo;
    }

    public void setJsonStuffInfo(String jsonStuff) {
        this.jsonStuffInfo = jsonStuff;
    }

    public void chooseTemplate()
    {

        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String file = dialog.getDirectory() + dialog.getFile();
        setJsonStuffInfo(file);
    }

    public void readFile() throws FileNotFoundException {
        chooseTemplate();

        File file = new File(getJsonStuffInfo());
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine()) {
            setJsonStuffString(getJsonStuffString()+sc.nextLine());
        }

        String temp = "";
        temp = getJsonStuffString().replace("\t", "");
        setJsonStuffString(temp);

    }

    public void getJson() throws JSONException, FileNotFoundException {

        readFile();
        Map<String, Stuff> javaRootMapObject = new Gson().fromJson(getJsonStuffString(), Map.class);
        System.out.println(((Map)((List) ((Map) (javaRootMapObject.get("stuffInfo"))).get("stuff")).get(0)).values());
        stuffList = new ArrayList<>();
        for(int i=0; i<((List) ((Map) (javaRootMapObject.get("stuffInfo"))).get("stuff")).size(); i++)
        {
            Stuff tempStuff = new Stuff(
                    ((Map)((List) ((Map) (javaRootMapObject.get("stuffInfo"))).get("stuff")).get(i)).get("type").toString(),
                    ((Map)((List) ((Map) (javaRootMapObject.get("stuffInfo"))).get("stuff")).get(i)).get("name").toString(),
                    ((Map)((List) ((Map) (javaRootMapObject.get("stuffInfo"))).get("stuff")).get(i)).get("imgPath").toString(),
                    ((Map)((List) ((Map) (javaRootMapObject.get("stuffInfo"))).get("stuff")).get(i)).get("description").toString()
            );
            stuffList.add(tempStuff);
        }

        setTableStuff();

    }

    public void getStuffToTable(ObservableList<TableViewStuff> data)
    {
        for (int i = 0; i < stuffList.size(); i++)
        {
            TableViewStuff tableViewStuffTemp = new TableViewStuff(stuffList.get(i).getType(), stuffList.get(i).getName(), stuffList.get(i).getDescription(),
            new ImageView(new Image(stuffList.get(i).getImgPath())));
            data.add(tableViewStuffTemp);
        }
    }


    public void setTableStuff ()
    {

        final ObservableList<TableViewStuff> data =
                FXCollections.observableArrayList();
        getStuffToTable(data);

        columnName.setMinWidth(100);
        columnName.setCellValueFactory(new PropertyValueFactory<TableViewStuff, String>("name"));

        columnDescription.setMinWidth(100);
        columnDescription.setCellValueFactory(new PropertyValueFactory<TableViewStuff, String>("description"));

        columnType.setMinWidth(100);
        columnType.setCellValueFactory(new PropertyValueFactory<TableViewStuff, String>("type"));

        columnImage.setCellValueFactory(new PropertyValueFactory<TableViewStuff, ImageView>("image"));
        //columnImage.setPrefWidth(200);

        tableStuff.setItems(data);

    }

}
