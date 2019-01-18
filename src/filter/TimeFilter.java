package filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFilter extends Filter {

    private Date from;

    private Date to;

    public void setFrom(Date from) {
       this.from = from;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    @Override
    public boolean filtrate(String line) {
        Date date = parseDate(line.substring(0,18));
        if((date.after(from)||date.equals(from)) && (date.before(to)||date.equals(to))){
            if (nextFilter==null){
                return true;
            }
            return nextFilter.filtrate(line);
        }else {
            return false;
        }
    }

    public Date parseDate(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
        try {
            return simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

}
