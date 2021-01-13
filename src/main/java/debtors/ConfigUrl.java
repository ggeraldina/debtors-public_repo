package debtors;

public class ConfigUrl {
    private static final String sectionName = "url";
    public final String IN = InOutConfig.readParameter(sectionName, "fileIn");
    public final String OUT = InOutConfig.readParameter(sectionName, "folderOut");
    public final String NAME_TOTAL_LIST = InOutConfig.readParameter(sectionName, "nameTotalList");    
}