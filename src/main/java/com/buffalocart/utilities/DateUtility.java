package com.buffalocart.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {
    public String getDateOfSystem() {
        DateFormat formatdate = new SimpleDateFormat("MM/dd/yyyy");
        Date systemDate = new Date();
        String dateOfSystem = formatdate.format(systemDate);
        return dateOfSystem;
    }
}
