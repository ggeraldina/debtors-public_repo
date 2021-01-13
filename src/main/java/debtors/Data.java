package debtors;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import java.util.List;

public class Data {
    // private static final Logger LOG = LoggerFactory.getLogger(Data.class);
    private int clientId;
    private String clientName;
    private String clientAdr;
    private List<Dept> dept;
    private Double totalSum;

    public Data(int clientId, String clientName, String clientAdr, List<Dept> dept) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientAdr = clientAdr;
        this.dept = dept;
        this.totalSum = countSum();       
    }

    private double countSum() {
        double sum = 0;
        for (Dept deptSum : dept) {
            sum += deptSum.getSum();
        }
        return sum;
    }

    /**
     * @return the clientId
     */
    public int getClientId() {
        return clientId;
    }
     
    /**
     * @return the clientName
     */
    public String getClientName() {
        return clientName;
    }
    
    /**
     * @return the clientAdr
     */
    public String getClientAdr() {
        return clientAdr;
    }

    /**
     * @return the dept
     */
    public List<Dept> getDept() {
        return dept;
    }

    /**
     * @return the totalSum
     */
    public double getTotalSum() {
        return totalSum;
    }
}