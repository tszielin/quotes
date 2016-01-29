package tszielin.quotes.domain;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nbp-param")
public class NBPParameter extends Base {
    private static final long serialVersionUID = -5079734028848951208L;
    private final static String URL = "http://www.nbp.pl/kursy/xml/a%sz%s.xml";
    private final static Format ID_FORMATTER = new DecimalFormat("000");
    private final static Format DATE_FORMATTER = new SimpleDateFormat("yyMMdd");
    
    private Date publicated;
    private int tableId = 1;

    public NBPParameter() {
    }

    public Date getPublicated() {
        return publicated == null ? new Date(1_009_926_000_000L) : publicated;
    }

    protected void setPublicated(Date publicated) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(publicated);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        this.publicated = calendar.getTime();
    }
    
    public int getTableId() {
        return tableId;
    }

    protected void setTableId(int tableId) {
        this.tableId = tableId;
    }
    
    public String getURL() {
        return String.format(URL, ID_FORMATTER.format(tableId), DATE_FORMATTER.format(getPublicated()));
    }
    
    public NBPParameter next(boolean changeNumber) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getPublicated());
        int year = calendar.get(Calendar.YEAR);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        setTableId(year != calendar.get(Calendar.YEAR) ? 1 : changeNumber ? getTableId() + 1 : getTableId());
        setPublicated(calendar.getTime());
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.publicated == null) ? 0 : this.publicated.hashCode());
        result = prime * result + this.tableId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        NBPParameter other = (NBPParameter)obj;
        if (this.publicated == null) {
            if (other.publicated != null)
                return false;
        } else if (!this.publicated.equals(other.publicated))
            return false;
        if (this.tableId != other.tableId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "publicated=" + this.publicated + ", tableId=" + this.tableId;
    }
}
