import java.util.*;

/**
 * Created by tonykim on 11/21/16.
 */
public class SymbolTimePair implements Comparable<SymbolTimePair> {
    public String symbol;
    public Date time;

    SymbolTimePair(String symbol, Date time){
        this.symbol = symbol;
        this.time = time;
    }

    @Override
    public int compareTo(SymbolTimePair target){
        return this.time.compareTo(target.time);
    }
}
