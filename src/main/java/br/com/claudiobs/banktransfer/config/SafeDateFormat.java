package br.com.claudiobs.banktransfer.config;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;

public class SafeDateFormat extends DateFormat {

    private static final String[] FALLBACK_PATTERNS = new String[] {
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ss.0000Z",
            "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
            "yyyy-MM-dd'T'HH:mm:ss.SSSX",
            "yyyy-MM-dd'T'HH:mm",
            "yyyy-MM-dd'T'HH",
            "yyyy-MM-dd"
    };

    private final DateFormat format;

    public SafeDateFormat(DateFormat format) {
        this.format = (DateFormat) format.clone();
        this.calendar = format.getCalendar();
        this.numberFormat = format.getNumberFormat();
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return format.format(date, toAppendTo, fieldPosition);
    }

    @Override
    public Date parse(String source) throws ParseException {
        source = removeInvalidTimeZoneSpecification(source);
        source = removeMilliseconds(source);

        try {
            return format.parse(source);
        } catch(Exception e) {
            return DateUtils.parseDate(source, FALLBACK_PATTERNS);
        }
    }

    private String removeInvalidTimeZoneSpecification(String source) {
        return source.replaceAll("Z.+","Z");
    }

    private String removeMilliseconds(String source) {
        return source.replaceAll("\\.[0-9]{3}[Z]","+0000");
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        return format.parse(removeMilliseconds(source), pos);
    }

    @Override
    public Object clone() {
        return new SafeDateFormat((DateFormat) this.format.clone());
    }

}
