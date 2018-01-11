package com.learn.entity;

import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.Range;

public class CheckWithCheckExample {
    @Min(1960)
    private int year;

    @Range(min=1, max=12)
    private int month;

    @CheckWith(value = DayCheck.class, message = "非法的天数")
    private int day;

    private static class DayCheck implements CheckWithCheck.SimpleCheck
    {
        public boolean isSatisfied(Object validatedObject, Object value) {
            if (validatedObject instanceof CheckWithCheckExample) {
                CheckWithCheckExample dayEntity = (CheckWithCheckExample) validatedObject;
                //闰年
                if ((dayEntity.getYear() % 4 == 0 && dayEntity.getYear() % 100 != 0 || dayEntity.getYear() % 400 == 0) && dayEntity.getMonth() == 2 && dayEntity.getDay() == 29) {
                    return true;
                }
            }
            return false;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

}
