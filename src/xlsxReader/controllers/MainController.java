package xlsxReader.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import xlsxReader.model.Winner;

import javax.xml.transform.Result;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainController implements Initializable {

    @FXML
    private Stage stage;

    @FXML
    private TableColumn lpNumberTc;

    @FXML
    private TableColumn nameSurnameTc;

    @FXML
    private TableColumn forAcvhivementTc;

    @FXML
    private TableColumn atTournamentTc;

    @FXML
    private TableColumn tournamentPlaceTc;

    @FXML
    private TableColumn competitionYearTc;

    @FXML
    private Button generateSerialLetter;

    @FXML
    private Button conversionDataToDiplomas;

    @FXML
    private Button deleteRow;

    @FXML
    private Button readNewFile;

    @FXML
    private Button generateEachOtherAthlete;

    @FXML
    private TableView<Record> winnerTableView = new TableView<>();

    private ObservableList<Record> dataList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Group group = new Group();

        //lpNumberTc = new TableColumn("f1");
        lpNumberTc.setCellValueFactory(new PropertyValueFactory<>("f1"));

        //nameSurnameTc = new TableColumn("f2");
        nameSurnameTc.setCellValueFactory(new PropertyValueFactory<>("f2"));

        //forAcvhivementTc = new TableColumn("f3");
        forAcvhivementTc.setCellValueFactory(new PropertyValueFactory<>("f3"));

        //atTournamentTc = new TableColumn("f4");
        atTournamentTc.setCellValueFactory(new PropertyValueFactory<>("f4"));

        //tournamentPlaceTc = new TableColumn("f5");
        tournamentPlaceTc.setCellValueFactory(new PropertyValueFactory<>("f5"));

        //competitionYearTc = new TableColumn("f6");
        competitionYearTc.setCellValueFactory(new PropertyValueFactory<>("f6"));

        winnerTableView.setItems(dataList);

        readCSV();

        //TODO loading CSV TO TABLE+ TEST
        //TODO LOADING CSV TO TABLE ON BUTTON CHOSE FILE
    }

    public class Record {

        private SimpleStringProperty f1, f2, f3, f4, f5, f6;

        public String getF1() {
            return f1.get();
        }

        public String getF2() {
            return f2.get();
        }

        public String getF3() {
            return f3.get();
        }

        public String getF4() {
            return f4.get();
        }

        public String getF5() {
            return f5.get();
        }

        public String getF6() {
            return f6.get();
        }

        public Record(String f1, String f2, String f3, String f4, String f5, String f6) {
            this.f1 = new SimpleStringProperty(f1);
            this.f2 = new SimpleStringProperty(f2);
            this.f3 = new SimpleStringProperty(f3);
            this.f4 = new SimpleStringProperty(f4);
            this.f5 = new SimpleStringProperty(f5);
            this.f6 = new SimpleStringProperty(f6);
        }
    }

    private void readCSV() {
        String csvFile = "C:\\Users\\mfliszkiewicz\\IdeaProjects\\xlsxFileReader\\dyplomy.csv";
        String FieldDelimiter = ";";
        System.setProperty("prism.order", "ANSI");
        String  goldMedal = "Za zdobycie złotego medalu";
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(csvFile));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] cells = line.split(FieldDelimiter, -1);

                Record record = new Record(cells[0], cells[1], cells[2], cells[3], cells[4], cells[5]);

                if(record.getF3().equals("za zdobycie pierwszego miejsca")) {
                    record.f3.set("złoto");
                }
                if(record.getF3().equals("za zdobycie drugiego miejsca")) {
                    record.f3.set("srebro");
                }
                if(record.getF3().equals("za zdobycie trzeciego miejsca")) {
                    record.f3.set("brąz");
                }
                if(record.getF3().equals("za zajęcie pierwszego miejsca")) {
                    record.f3.set("złoto");
                }
                if(record.getF3().equals("za zajęcie drugiego miejsca")) {
                    record.f3.set("srebro");
                }
                if(record.getF3().equals("za zajęcie trzeciego miejsca")) {
                    record.f3.set("brąz");
                }
                dataList.add(record);
            }

        } catch (FileNotFoundException fileEx) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, fileEx);
        } catch (IOException ioEx) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ioEx);
        }
    }


    @FXML
    private void readNewFileAction(ActionEvent newFileReadEvent) throws Exception {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Pliki xlsx", "*.csv")
        );

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println("Plik: " + file.getAbsolutePath());
            //loadingDataToTable(file);
        }
    }

    @FXML
    private void generateSerialLetterAction (ActionEvent actionEventGenerate) {

        Writer writerCSV = null;

        try{
            File file = new File("C:\\Serial.csv");
            writerCSV = new BufferedWriter(new FileWriter(file));
            for(Record record : dataList){
                String textSerial =  record.getF2()+","+record.getF3()+","+record.getF5()+","+record.getF6();
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }


    @FXML
    private void deleteRowAction(ActionEvent deleteRowActionEvent) {

    }


}
