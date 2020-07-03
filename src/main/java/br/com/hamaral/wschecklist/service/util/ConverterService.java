package br.com.hamaral.wschecklist.service.util;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ConverterService {

    public Date stringToDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateParsed = null;
        try {
            dateParsed = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateParsed;
    }

    public String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormatted = null;
        try {
            dateFormatted = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormatted;
    }

    public String removeMask(String text) {
        return text.replaceAll("[^0-9]", "");
    }
}
