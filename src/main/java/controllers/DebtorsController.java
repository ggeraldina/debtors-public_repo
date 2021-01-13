package controllers;

import app.*;
import debtors.InOutConfig;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.event.ActionEvent;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class DebtorsController {
    private static final Logger LOG = LoggerFactory.getLogger(DebtorsController.class);
    private static final String sectionNameTable = "table";
    private static final String sectionNameTotalTable = "totalTable";
    private static final String sectionNameUrl = "url";
    private static final String sectionNameSettings = "settings";

    @FXML
    private Button startButton;

    @FXML
    private ProgressIndicator progress;

    @FXML
    private TextField dateFieldTable;

    @FXML
    private TextField title0Table;

    @FXML
    private TextField title1Table;

    @FXML
    private TextField column0Table;

    @FXML
    private TextField column1Table;

    @FXML
    private TextField column2Table;

    @FXML
    private TextField column3Table;

    @FXML
    private TextField column4Table;

    @FXML
    private TextField totalSumTable;

    @FXML
    private TextField signatureNameTable;

    @FXML
    private TextField signatureJobTable;

    @FXML
    private Button cancelButtonTable;

    @FXML
    private Button saveButtonTable;

    @FXML
    private TextField nameTotalList;

    @FXML
    private TextField title0TotalTable;

    @FXML
    private TextField title1TotalTable;

    @FXML
    private TextField title2TotalTable;

    @FXML
    private TextField column0TotalTable;

    @FXML
    private TextField column1TotalTable;

    @FXML
    private TextField column2TotalTable;

    @FXML
    private TextField column3TotalTable;

    @FXML
    private TextField column4TotalTable;

    @FXML
    private TextField totalSumTotalTable;

    @FXML
    private TextField signatureTopTotalTable;

    @FXML
    private TextField signatureBottomTotalTable;

    @FXML
    private Button cancelButtonTotalTable;

    @FXML
    private Button saveButtonTotalTable;

    @FXML
    private TextField pathInFile;

    @FXML
    private TextField pathOutDirectory;

    @FXML
    private TextField numberMonths;

    @FXML
    private CheckBox creatingTables;

    @FXML
    private Button cancelButtonGeneralSettings;

    @FXML
    private Button saveButtonGeneralSettungs;

    @FXML
    private void initialize() {
        showInfoTable();
        showInfoTotalTable();
        showInfoGeneralSettings();

        cancelButtonTable.setDisable(true);
        saveButtonTable.setDisable(true);
        cancelButtonTotalTable.setDisable(true);
        saveButtonTotalTable.setDisable(true);
        cancelButtonGeneralSettings.setDisable(true);
        saveButtonGeneralSettungs.setDisable(true);

        configureChangingButton();

        // Filter for a number of months - only digits
        numberMonths.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numberMonths.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void showInfoTable() {
        // textField dateField
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String date = dateFormat.format(new Date());
        dateFieldTable.setPromptText(date);

        title0Table.setText(InOutConfig.readParameter(sectionNameTable, "title0"));
        title1Table.setText(InOutConfig.readParameter(sectionNameTable, "title1"));
        column0Table.setText(InOutConfig.readParameter(sectionNameTable, "column0"));
        column1Table.setText(InOutConfig.readParameter(sectionNameTable, "column1"));
        column2Table.setText(InOutConfig.readParameter(sectionNameTable, "column2"));
        column3Table.setText(InOutConfig.readParameter(sectionNameTable, "column3"));
        column4Table.setText(InOutConfig.readParameter(sectionNameTable, "column4"));
        totalSumTable.setText(InOutConfig.readParameter(sectionNameTable, "totalSum"));
        signatureNameTable.setText(InOutConfig.readParameter(sectionNameTable, "signatureName"));
        signatureJobTable.setText(InOutConfig.readParameter(sectionNameTable, "signatureJob"));
    }

    private void showInfoTotalTable() {
        nameTotalList.setText(InOutConfig.readParameter(sectionNameUrl, "nameTotalList"));
        title0TotalTable.setText(InOutConfig.readParameter(sectionNameTotalTable, "title0"));
        title1TotalTable.setText(InOutConfig.readParameter(sectionNameTotalTable, "title1"));
        title2TotalTable.setText(InOutConfig.readParameter(sectionNameTotalTable, "title2"));
        column0TotalTable.setText(InOutConfig.readParameter(sectionNameTotalTable, "column0"));
        column1TotalTable.setText(InOutConfig.readParameter(sectionNameTotalTable, "column1"));
        column2TotalTable.setText(InOutConfig.readParameter(sectionNameTotalTable, "column2"));
        column3TotalTable.setText(InOutConfig.readParameter(sectionNameTotalTable, "column3"));
        column4TotalTable.setText(InOutConfig.readParameter(sectionNameTotalTable, "column4"));
        totalSumTotalTable.setText(InOutConfig.readParameter(sectionNameTotalTable, "totalSum"));
        signatureTopTotalTable.setText(InOutConfig.readParameter(sectionNameTotalTable, "signatureTop"));
        signatureBottomTotalTable.setText(InOutConfig.readParameter(sectionNameTotalTable, "signatureBottom"));
    }

    private void showInfoGeneralSettings() {
        pathInFile.setText(InOutConfig.readParameter(sectionNameUrl, "fileIn"));
        pathOutDirectory.setText(InOutConfig.readParameter(sectionNameUrl, "folderOut"));
        numberMonths.setText(InOutConfig.readParameter(sectionNameSettings, "numberMonths"));
        creatingTables
                .setSelected(Boolean.parseBoolean(InOutConfig.readParameter(sectionNameSettings, "creatingTables")));
    }

    private void configureChangingButton() {
        configureChangingButtonTable();
        configureChangingButtonTotalTable();
        configureChangingButtonGeneralSettings();
    }

    private void configureChangingButtonTable() {
        title0Table.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTable(sectionNameTable, "title0", newValue);
        });
        title1Table.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTable(sectionNameTable, "title1", newValue);
        });
        column0Table.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTable(sectionNameTable, "column0", newValue);
        });
        column1Table.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTable(sectionNameTable, "column1", newValue);
        });
        column2Table.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTable(sectionNameTable, "column2", newValue);
        });
        column3Table.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTable(sectionNameTable, "column3", newValue);
        });
        column4Table.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTable(sectionNameTable, "column4", newValue);
        });
        totalSumTable.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTable(sectionNameTable, "totalSum", newValue);
        });
        signatureNameTable.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTable(sectionNameTable, "signatureNameTable", newValue);
        });
        signatureJobTable.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTable(sectionNameTable, "signatureJob", newValue);
        });
    }

    private void configureChangingButtonTotalTable() {
        nameTotalList.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTotalTable(sectionNameUrl, "nameTotalList", newValue);
        });
        title0TotalTable.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTotalTable(sectionNameTotalTable, "title0", newValue);
        });
        title1TotalTable.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTotalTable(sectionNameTotalTable, "title1", newValue);
        });
        title2TotalTable.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTotalTable(sectionNameTotalTable, "title2", newValue);
        });
        column0TotalTable.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTotalTable(sectionNameTotalTable, "column0", newValue);
        });
        column1TotalTable.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTotalTable(sectionNameTotalTable, "column1", newValue);
        });
        column2TotalTable.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTotalTable(sectionNameTotalTable, "column2", newValue);
        });
        column3TotalTable.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTotalTable(sectionNameTotalTable, "column3", newValue);
        });
        column4TotalTable.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTotalTable(sectionNameTotalTable, "column4", newValue);
        });
        totalSumTotalTable.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTotalTable(sectionNameTotalTable, "totalSum", newValue);
        });
        signatureTopTotalTable.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTotalTable(sectionNameTotalTable, "signatureTop", newValue);
        });
        signatureBottomTotalTable.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonTotalTable(sectionNameTotalTable, "signatureBottom", newValue);
        });
    }

    private void configureChangingButtonGeneralSettings() {
        pathInFile.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonGeneralSettings(sectionNameUrl, "fileIn", newValue);
        });
        pathOutDirectory.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonGeneralSettings(sectionNameUrl, "folderOut", newValue);
        });
        numberMonths.textProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonGeneralSettings(sectionNameSettings, "numberMonths", newValue);
        });
        creatingTables.selectedProperty().addListener((observable, oldValue, newValue) -> {
            updateDisableButtonGeneralSettings(sectionNameSettings, "creatingTables", Boolean.toString(newValue));
        });
    }

    private void updateDisableButtonTable(String sectionName, String parametrName, String newValue) {
        if (newValue.equals(InOutConfig.readParameter(sectionName, parametrName))) {
            cancelButtonTable.setDisable(true);
            saveButtonTable.setDisable(true);
        } else {
            cancelButtonTable.setDisable(false);
            saveButtonTable.setDisable(false);
        }
    }

    private void updateDisableButtonTotalTable(String sectionName, String parametrName, String newValue) {
        if (newValue.equals(InOutConfig.readParameter(sectionName, parametrName))) {
            cancelButtonTotalTable.setDisable(true);
            saveButtonTotalTable.setDisable(true);
        } else {
            cancelButtonTotalTable.setDisable(false);
            saveButtonTotalTable.setDisable(false);
        }
    }

    private void updateDisableButtonGeneralSettings(String sectionName, String parametrName, String newValue) {
        if (newValue.equals(InOutConfig.readParameter(sectionName, parametrName))) {
            cancelButtonGeneralSettings.setDisable(true);
            saveButtonGeneralSettungs.setDisable(true);
        } else {
            cancelButtonGeneralSettings.setDisable(false);
            saveButtonGeneralSettungs.setDisable(false);
        }
    }

    @FXML
    private void clickStart(ActionEvent event) {
        new ThreadProgress(startButton, progress);
    }

    @FXML
    private void clickCancelTable(ActionEvent event) {
        showInfoTable();
        cancelButtonTable.setDisable(true);
        saveButtonTable.setDisable(true);
    }

    @FXML
    private void clickSaveTable(ActionEvent event) {
        InOutConfig.writeParameter(sectionNameTable, "title0", title0Table.getText());
        InOutConfig.writeParameter(sectionNameTable, "title1", title1Table.getText());
        InOutConfig.writeParameter(sectionNameTable, "column0", column0Table.getText());
        InOutConfig.writeParameter(sectionNameTable, "column1", column1Table.getText());
        InOutConfig.writeParameter(sectionNameTable, "column2", column2Table.getText());
        InOutConfig.writeParameter(sectionNameTable, "column3", column3Table.getText());
        InOutConfig.writeParameter(sectionNameTable, "column4", column4Table.getText());
        InOutConfig.writeParameter(sectionNameTable, "totalSum", totalSumTable.getText());
        InOutConfig.writeParameter(sectionNameTable, "signatureName", signatureNameTable.getText());
        InOutConfig.writeParameter(sectionNameTable, "signatureJob", signatureJobTable.getText());
        cancelButtonTable.setDisable(true);
        saveButtonTable.setDisable(true);
    }

    @FXML
    private void clickCancelTotalTable(ActionEvent event) {
        showInfoTotalTable();
        cancelButtonTotalTable.setDisable(true);
        saveButtonTotalTable.setDisable(true);
    }

    @FXML
    private void clickSaveTotalTable(ActionEvent event) {
        InOutConfig.writeParameter(sectionNameUrl, "nameTotalList", nameTotalList.getText());
        InOutConfig.writeParameter(sectionNameTotalTable, "title0", title0TotalTable.getText());
        InOutConfig.writeParameter(sectionNameTotalTable, "title1", title1TotalTable.getText());
        InOutConfig.writeParameter(sectionNameTotalTable, "title2", title2TotalTable.getText());
        InOutConfig.writeParameter(sectionNameTotalTable, "column0", column0TotalTable.getText());
        InOutConfig.writeParameter(sectionNameTotalTable, "column1", column1TotalTable.getText());
        InOutConfig.writeParameter(sectionNameTotalTable, "column2", column2TotalTable.getText());
        InOutConfig.writeParameter(sectionNameTotalTable, "column3", column3TotalTable.getText());
        InOutConfig.writeParameter(sectionNameTotalTable, "column4", column4TotalTable.getText());
        InOutConfig.writeParameter(sectionNameTotalTable, "totalSum", totalSumTotalTable.getText());
        InOutConfig.writeParameter(sectionNameTotalTable, "signatureTop", signatureTopTotalTable.getText());
        InOutConfig.writeParameter(sectionNameTotalTable, "signatureBottom", signatureBottomTotalTable.getText());
        cancelButtonTotalTable.setDisable(true);
        saveButtonTotalTable.setDisable(true);
    }

    @FXML
    private void getPathInFile(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage primaryStage = (Stage) source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        try {
            fileChooser.setTitle(new String("Выбрать файл dbf".getBytes("windows-1251"), "utf-8"));            
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage());
        }
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("DBF", "*.dbf"));
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            String path = file.getAbsolutePath();
            pathInFile.setText(path);
        }
    }

    @FXML
    private void getPathOutDirectory(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage primaryStage = (Stage) source.getScene().getWindow();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        try {
            directoryChooser.setTitle(
                new String("Выбрать папку для сохранения результата работы программы".getBytes("windows-1251"), "utf-8")
            );            
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage());
        }
        File dir = directoryChooser.showDialog(primaryStage);
        ;
        if (dir != null) {
            String path = dir.getAbsolutePath();
            pathOutDirectory.setText(path);
        }
    }

    @FXML
    private void clickCancelGeneralSettings(ActionEvent event) {
        showInfoGeneralSettings();
        cancelButtonGeneralSettings.setDisable(true);
        saveButtonGeneralSettungs.setDisable(true);
    }

    @FXML
    private void clickSaveGeneralSettings(ActionEvent event) {
        InOutConfig.writeParameter(sectionNameUrl, "fileIn", pathInFile.getText());
        InOutConfig.writeParameter(sectionNameUrl, "folderOut", pathOutDirectory.getText());
        InOutConfig.writeParameter(sectionNameSettings, "numberMonths", numberMonths.getText());
        InOutConfig.writeParameter(
            sectionNameSettings, "creatingTables",
            Boolean.toString(creatingTables.isSelected())
        );
        cancelButtonGeneralSettings.setDisable(true);
        saveButtonGeneralSettungs.setDisable(true);
    }
}