package debtors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jamel.dbf.*;
import org.jamel.dbf.utils.DbfUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class BaseDBF {
    private static final Logger LOG = LoggerFactory.getLogger(BaseDBF.class);
    private ConfigUrl configUrl = new ConfigUrl();
    public void readDBF() {
        try (DbfReader reader = new DbfReader(new File(configUrl.IN))) {
            TotalTable totalTable = new TotalTable();
            Object[] row = reader.nextRecord();
            while (row != null) {
                int currentClientId = ((Number) row[0]).intValue();
                String name = new String(DbfUtils.trimLeftSpaces((byte[]) row[15]), "Cp866");
                String address = new String(DbfUtils.trimLeftSpaces((byte[]) row[16]), "Cp866");     
                ArrayList<Dept> listDept = new ArrayList<>();
                while (row != null && currentClientId == ((Number) row[0]).intValue()) {   
                    Date date = (Date) row[6];
                    double sum = ((Number) row[7]).doubleValue();
                    Dept dept = new Dept(date, sum);
                    listDept.add(dept);
                    row = reader.nextRecord();             
                }
                Data data = new Data(currentClientId, name, address, listDept);
                int numberMonths = Integer.parseInt(InOutConfig.readParameter("settings", "numberMonths"));
                if (listDept.size() < numberMonths) {
                    LOG.info("Ignore id " + currentClientId + " - number of months: " + listDept.size());
                    continue;
                }
                boolean creatingTables = Boolean.parseBoolean(InOutConfig.readParameter("settings", "creatingTables"));
                if (creatingTables) {                    
                    Table tab = new Table();
                    tab.createRecord(data);
                }
                totalTable.addRowRecordData(data);
            }  
            totalTable.saveToFile();
        } catch (Exception e) {
            LOG.error("reader \n" + e.getMessage());
        } 
    }
}
