//package xlsxReader.controllers;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Scanner;
//
//import javafx.fxml.FXML;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//
//
//public class XlsxReaderController {
//
//
//
//        public static void main(String[] args) throws IOException{
//
//            System.out.println("Podaj nazwę pliku z dyplomami");
//            Scanner nameSheetScanner = new Scanner(System.in);
//            String nameSheet = nameSheetScanner.nextLine();
//
//            File xlsxFile = new File("src/main/resources/"+nameSheet+".xlsx");
//            FileInputStream xlsxFileStream = new FileInputStream(xlsxFile);
//
//            //creating workbook for xlsx
//            XSSFWorkbook workbook = new XSSFWorkbook(xlsxFileStream);
//            //get first shit of file
//            XSSFSheet sheet = workbook.getSheetAt(0);
//            //iterate rows
//
//            Iterator<Row> rowIt = sheet.iterator();
//
//            while (rowIt.hasNext()){
//                Row row = rowIt.next();
//
//                Iterator<Cell> cellIterator = row.cellIterator();
//
//                while (cellIterator.hasNext()){
//                    Cell cell = cellIterator.next();
//                    List<Cell> cells = new ArrayList<Cell>();
//                    cells.add(cell);
//                    //System.out.print(cell.toString()+";");
//                    System.out.print(cells);
//                }
//                System.out.println();
//
//            }
//            workbook.close();
//            xlsxFileStream.close();
//        }
//    }
//
//
//private void loadingDataToTable(File file){
//        Path pathToFile = Paths.get(file.getAbsolutePath());
//        ArrayList<String> fileReaderList = new ArrayList<String>();
//        try {
//        fileReaderList = (ArrayList) Files.readAllLines(pathToFile, Charset.forName("ISO-8859-1"));
//        } catch (IOException IOex){
//        System.out.println("Brak pliku!");
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Błędny odczyt pliku!");
//        alert.setHeaderText("Brak pliku!");
//        alert.showAndWait();
//        }
//
//        ArrayList<PieChart.Data> loopList = new ArrayList<PieChart.Data>();
//        for (String listString : fileReaderList){
//        String[] dataList = listString.split(",");
//        if (dataList.length > 1 ){
//        loopList.add(new PieChart.Data(dataList[0], Double.parseDouble(dataList[1])));
//        }
//        }
//        ObservableList<PieChart.Data> dataObservableList = FXCollections.observableArrayList(loopList);
//        winnerTableView.itemsProperty().set(dataObservableList);
//        lpNumberTc.setCellValueFactory(new PropertyValueFactory<PieChart.Data, String>("A"));
//        nameSurnameTc.setCellValueFactory(new PropertyValueFactory<PieChart.Data, String>("B"));
//        forAcvhivementTc.setCellValueFactory(new PropertyValueFactory<PieChart.Data, String>("C"));
//        atTournamentTc.setCellValueFactory(new PropertyValueFactory<PieChart.Data, String>("F"));
//        tournamentPlaceTc.setCellValueFactory(new PropertyValueFactory<PieChart.Data, String>("G"));
//        competitionYearTc.setCellValueFactory(new PropertyValueFactory<PieChart.Data, String>("E"));
//        }