package com.webmvc.chiken.util;

import com.webmvc.chiken.model.DAO.BillDB;
import com.webmvc.chiken.model.DAO.ProductDB;
import com.webmvc.chiken.model.entity.BillEntity;
import com.webmvc.chiken.model.entity.BillItemEntity;
import com.webmvc.chiken.model.entity.CustomerEntity;
import com.webmvc.chiken.model.entity.LineItemEntity;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MyUtil {
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static String format(Double value){
        DecimalFormat currency = new DecimalFormat("###,###,###");
        if (value == null){
            value = 0.0;
        }
        return   currency.format(value) + " VND";
    }
    public static String sqlDate(){
        LocalDate toDate = LocalDate.now();
        DateTimeFormatter sqlFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return toDate.format(sqlFormat);
    }
    public static String codeDate(){
        LocalDate toDate = LocalDate.now();
        DateTimeFormatter sqlFormat = DateTimeFormatter.ofPattern("ddMMyyyy");
        return toDate.format(sqlFormat);
    }
    public static String createBillCode( CustomerEntity customer){
        int countBill = BillDB.getCountBillToDate(Date.valueOf(sqlDate()),customer);
        return codeDate()    + String.valueOf(customer.getCustomerId()) + String.valueOf(countBill + 1);
    }

}
