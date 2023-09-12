package com.neoris.aplicationprogramminginterface.infrastructure;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utilidades {

    public static Date getDateFormat(String fecha) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
        return formato.parse(fecha);
    }

    public static Date asDate(LocalDate localDate) {
        return  Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate convertStringToLocalDate(String dateToConvert) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
        return format.parse(dateToConvert).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
