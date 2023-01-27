package spring_study.concertInfo.typeconverter;


import org.springframework.format.Formatter;

import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DateRangeFormatter implements Formatter<Map<String, Date>> {

    @Override
    public Map<String, Date> parse(String text, Locale locale) throws ParseException {
        String[] dateRange = text.split("~");
        Date stDate = Date.valueOf(dateRange[0]);
        Date edDate = Date.valueOf(dateRange[1]);

        Map<String, Date> date = new HashMap<>();
        date.put("stDate", stDate);
        date.put("edDate", edDate);
        return date;
    }

    @Override
    public String print(Map<String, Date> object, Locale locale) {
        return null;
    }
}
