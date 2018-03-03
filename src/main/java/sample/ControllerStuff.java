package sample;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
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

    private String jsonStuffInfo = "Resources/stuffInfo.json";
    private String jsonStuffString = "";

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
        String jsonLine = " { \"data\": { \"translations\": [ { \"translatedText\": \"Hello world\" }] } } ";
        System.out.println(getJsonStuffString());
        Map<String, Object> javaRootMapObject = new Gson().fromJson(getJsonStuffString(), Map.class);
        //System.out.println(((Map) ((List) ((Map) (javaRootMapObject.get("data"))).get("translations")).get(0)).get("translatedText"));
        System.out.println(((Map) ((List) ((Map) (javaRootMapObject.get("stuffInfo"))).get("stuff")).get(0)).values());
        List<Stuff> stuffList = new ArrayList<>();
        Stuff stuff1 = new Stuff();
        stuff1.setDescription("123");
        stuff1.setImgPath("333");
        stuff1.setName("elo");
        stuff1.setType("nie");
        stuffList.add(stuff1);
        System.out.println(stuff1);
//        Gson g = new Gson();
//        System.out.println(getJsonStuffString());
//        List<Stuff> stuffs = new ArrayList<>();
//        stuffs = g.fromJson(getJsonStuffString(), List.class);
//        System.out.println(stuffs[0].getDescription());
//
//        System.out.println(g.toJson(stuff));
    }

}
