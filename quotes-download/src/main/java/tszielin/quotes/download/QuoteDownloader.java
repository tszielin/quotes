package tszielin.quotes.download;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import tszielin.quotes.aggregation.Aggregation;
import tszielin.quotes.domain.Quote;

abstract public class QuoteDownloader implements Uploadable {
    public class DateRange {
        private Date start;
        private Date finish;
        
        public Date getStart() {
            return start;
        }
        
        public Date getFinish() {
            return finish;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((this.finish == null) ? 0 : this.finish.hashCode());
            result = prime * result + ((this.start == null) ? 0 : this.start.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            DateRange other = (DateRange)obj;
            if (this.finish == null) {
                if (other.finish != null)
                    return false;
            } else if (!this.finish.equals(other.finish))
                return false;
            if (this.start == null) {
                if (other.start != null)
                    return false;
            } else if (!this.start.equals(other.start))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "[start=" + this.start + ", finish=" + this.finish + "]";
        }
    }
    
    abstract protected Aggregation<? extends Quote> getAggregation();
    
    protected Map<String,DateRange> getDateRange() {
        Map<String,DateRange> map = new HashMap<>();
        for (Quote quote : getAggregation().first()) {
            if (map.containsKey(quote.getCurrency().getCode())) {
                map.get(quote.getCurrency().getCode()).start = quote.getPublicated();
            }
            else {
                DateRange range = new DateRange();
                range.start = quote.getPublicated();
                map.put(quote.getCurrency().getCode(), range);
            }
        }
        for (Quote quote : getAggregation().last()) {
            if (map.containsKey(quote.getCurrency().getCode())) {
                map.get(quote.getCurrency().getCode()).finish = quote.getPublicated();
            }
            else {
                DateRange range = new DateRange();
                range.finish = quote.getPublicated();
                map.put(quote.getCurrency().getCode(), range);
            }
        }
        return map;
    }
}
