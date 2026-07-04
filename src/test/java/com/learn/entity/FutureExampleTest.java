package com.learn.entity;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FutureExampleTest {

    private Validator validator;
    private FutureExample example;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        example = new FutureExample();
    }

    // ==================== date (String) 字段测试 ====================

    @Test
    void testDateFutureString() {
        example.setDate("2099-12-31 23:59:59");
        List<ConstraintViolation> violations = validator.validate(example);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("date")), "未来日期字符串应该通过");
    }

    @Test
    void testDatePastString() {
        example.setDate("2000-01-01 00:00:00");
        List<ConstraintViolation> violations = validator.validate(example);
        assertTrue(!violations.isEmpty(), "过去日期字符串应该触发校验");
    }

    @Test
    void testDateNullString() {
        example.setDate(null);
        List<ConstraintViolation> violations = validator.validate(example);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("date")), "null值不触发@Future校验");
    }

    @Test
    void testDateEmptyString() {
        example.setDate("");
        List<ConstraintViolation> violations = validator.validate(example);
        assertTrue(violations.size() > 0, "空字符串无法解析，应触发校验");
    }

    @Test
    void testDateInvalidFormat() {
        example.setDate("not-a-date");
        List<ConstraintViolation> violations = validator.validate(example);
        assertTrue(violations.size() > 0, "非日期格式应触发校验");
    }

    @Test
    void testDateFormatWithoutTime() {
        example.setDate("2099-12-31");
        List<ConstraintViolation> violations = validator.validate(example);
        assertTrue(violations.size() > 0, "不含时间部分的格式应触发校验");
    }

    @Test
    void testDateBoundaryNowString() {
        Calendar cal = Calendar.getInstance();
        String now = String.format("%04d-%02d-%02d %02d:%02d:%02d",
                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
        example.setDate(now);
        List<ConstraintViolation> violations = validator.validate(example);
        assertTrue(violations.size() > 0, "当前时刻不算未来");
    }

    @Test
    void testDateOneSecondFuture() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 1);
        String future = String.format("%04d-%02d-%02d %02d:%02d:%02d",
                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
        example.setDate(future);
        List<ConstraintViolation> violations = validator.validate(example);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("date")), "未来1秒应该通过");
    }

    // ==================== date1 (Date) 字段测试 ====================

    @Test
    void testDate1Future() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        example.setDate1(cal.getTime());
        List<ConstraintViolation> violations = validator.validate(example);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("date1")), "未来Date应该通过");
    }

    @Test
    void testDate1Past() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
        example.setDate1(cal.getTime());
        List<ConstraintViolation> violations = validator.validate(example);
        assertTrue(!violations.isEmpty(), "过去Date应该触发校验");
    }

    @Test
    void testDate1Null() {
        example.setDate1(null);
        List<ConstraintViolation> violations = validator.validate(example);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("date1")), "null值不触发@Future校验");
    }

    @Test
    void testDate1Now() {
        example.setDate1(new Date());
        List<ConstraintViolation> violations = validator.validate(example);
        assertTrue(violations.size() > 0, "当前时刻不算未来");
    }

    @Test
    void testDate1OneSecondFuture() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 1);
        example.setDate1(cal.getTime());
        List<ConstraintViolation> violations = validator.validate(example);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("date1")), "未来1秒应该通过");
    }

    @Test
    void testDate1FarFuture() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 100);
        example.setDate1(cal.getTime());
        List<ConstraintViolation> violations = validator.validate(example);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("date1")));
    }

    // ==================== 同时设置两个字段 ====================

    @Test
    void testBothFieldsFuture() {
        example.setDate("2099-12-31 23:59:59");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        example.setDate1(cal.getTime());
        List<ConstraintViolation> violations = validator.validate(example);
        assertTrue(violations.isEmpty(), "两个字段都是未来时间应该通过");
    }

    @Test
    void testBothFieldsPast() {
        example.setDate("2000-01-01 00:00:00");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
        example.setDate1(cal.getTime());
        List<ConstraintViolation> violations = validator.validate(example);
        assertTrue(violations.size() >= 2, "两个字段都是过去时间应该有2个错误");
    }
}
