package br.com.sidtmcafe.service;

import br.com.sidtmcafe.interfaces.Constants;
import org.opensaml.xmlsec.encryption.Public;
import sun.jvm.hotspot.memory.LoaderConstraintEntry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class DatasTrabalhadas implements Constants {

    public static String getStrIntervaloDatas(LocalDateTime data1, LocalDateTime data2) {
        if (data2 == null)
            data2 = LocalDateTime.now();
        Period period = Period.between(data1.toLocalDate(), data2.toLocalDate());
        String periodoTemp = "";
        if (period.getYears() > 0)
            periodoTemp += period.getYears() + " Anos ";
        if (period.getMonths() > 0)
            periodoTemp += period.getMonths() + " Meses ";
        if (period.getDays() > 0)
            periodoTemp += period.getDays() + " dias ";
        if (periodoTemp.equals(""))
            periodoTemp = " hoje ";
        return periodoTemp;
    }
}
