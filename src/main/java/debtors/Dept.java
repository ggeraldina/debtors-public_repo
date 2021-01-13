package debtors;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import java.util.Date;

public class Dept {
    // private static final Logger LOG = LoggerFactory.getLogger(Dept.class);
    private Date month;
    private double sum;

    public Dept(Date month, double sum) {
        this.month = month;
        this.sum = sum;
    }

    /**
     * @return the month
     */
    public Date getMonth() {
        return month;
    }

    /**
     * @return the sum
     */
    public double getSum() {
        return sum;
    }
}