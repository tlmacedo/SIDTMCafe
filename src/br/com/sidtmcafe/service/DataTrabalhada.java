package br.com.sidtmcafe.service;

import br.com.sidtmcafe.interfaces.Constants;
import org.opensaml.xmlsec.encryption.Public;
//import sun.jvm.hotspot.memory.LoaderConstraintEntry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;

public class DataTrabalhada implements Constants {

    public static String getStrIntervaloDatas(LocalDate data1, LocalDate data2) {
        if (data2 == null)
            data2 = LocalDate.now();
        Period period = Period.between(data1, data2);
        String strPeriodo = "";
        if (period.getYears() > 0) {
            strPeriodo += period.getYears() + " ";
            if (period.getYears() == 1) strPeriodo += "Ano ";
            else strPeriodo += "Anos ";
        }
        if (period.getMonths() > 0) {
            strPeriodo += period.getMonths() + " ";
            if (period.getMonths() == 1) strPeriodo += "Mês ";
            else strPeriodo += "Meses ";
        }
        if (period.getDays() > 0) {
            strPeriodo += "e " + period.getDays() + " ";
            if (period.getDays() == 1) strPeriodo += "dia ";
            else strPeriodo += "dias ";
        }
        if (strPeriodo.equals("")) {
            strPeriodo = " hoje ";
        }
        return strPeriodo;
    }

}
