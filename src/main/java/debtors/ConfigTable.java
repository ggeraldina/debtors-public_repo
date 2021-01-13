package debtors;

public class ConfigTable {
    private static final String sectionName = "table";
    public final String TITLE = InOutConfig.readParameter(sectionName, "title0") + "\n"
                                + InOutConfig.readParameter(sectionName, "title1");
    public final String COLUMN_0 = InOutConfig.readParameter(sectionName, "column0");
    public final String COLUMN_1 = InOutConfig.readParameter(sectionName, "column1");
    public final String COLUMN_2 = InOutConfig.readParameter(sectionName, "column2");
    public final String COLUMN_3 = InOutConfig.readParameter(sectionName, "column3");
    public final String COLUMN_4 = InOutConfig.readParameter(sectionName, "column4");
    public final String TOTAL_SUM = InOutConfig.readParameter(sectionName, "totalSum");
    public final String SIGNATURE_JOB = InOutConfig.readParameter(sectionName, "signatureJob");
    public final String SIGNATURE_NAME = InOutConfig.readParameter(sectionName, "signatureName");

}