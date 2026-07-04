package com.learn.nested.entity;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTest {

    private Validator validator;
    private Person person;
    private Address address;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        person = new Person();
        address = new Address();
        address.setProvince("广东省");
        address.setCity("深圳市");
        person.setAddress(address);
    }

    // ==================== Person 级测试 ====================

    @Test
    void testValidPerson() {
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.isEmpty(), "完整Person应该通过验证");
    }

    @Test
    void testAddressNotNull() {
        person.setAddress(null);
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("住址不能为空")));
    }

    @Test
    void testAddressValidCascade() {
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("is invalid")));
    }

    // ==================== Address.province 级联校验 ====================

    @Test
    void testProvinceNotNullCascade() {
        address.setProvince(null);
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("address is invalid") || v.getMessage().contains("is invalid")),
                "嵌套Address有错误时Person应该报address is invalid");
    }

    @Test
    void testProvinceEmptyCascade() {
        address.setProvince("");
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("is invalid")));
    }

    @Test
    void testProvinceTooShortCascade() {
        address.setProvince("京");
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("is invalid")));
    }

    @Test
    void testProvinceTooLongCascade() {
        address.setProvince("黑龙江省大兴安岭");
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("is invalid")));
    }

    @Test
    void testProvinceValidLengthCascade() {
        address.setProvince("北京市");
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("is invalid")));
    }

    @Test
    void testProvinceLengthThreeBoundaryCascade() {
        address.setProvince("广东省");
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("is invalid")));
    }

    @Test
    void testProvinceLengthFiveBoundaryCascade() {
        address.setProvince("黑龙江省");
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("is invalid")));
    }

    @Test
    void testProvinceLengthSixAboveMaxCascade() {
        address.setProvince("吉林省延边州");
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("is invalid")));
    }

    @Test
    void testProvinceLengthTwoBelowMinCascade() {
        address.setProvince("上海");
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("is invalid")));
    }

    @Test
    void testProvinceWhitespaceCascade() {
        address.setProvince("   ");
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("is invalid")), "空白字符串不是null，@NotNull应该通过");
    }

    // ==================== Address.city 级联校验 ====================

    @Test
    void testCityNotNullCascade() {
        address.setCity(null);
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("is invalid")));
    }

    @Test
    void testCityEmptyCascade() {
        address.setCity("");
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("is invalid")), "空字符串不是null，@NotNull应该通过，级联不报错");
    }

    @Test
    void testCityValidCascade() {
        address.setCity("广州");
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("is invalid")));
    }

    @Test
    void testCityWhitespaceCascade() {
        address.setCity("  ");
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("is invalid")), "空白字符串不是null，@NotNull应该通过");
    }

    // ==================== 多个级联错误同时触发 ====================

    @Test
    void testAllAddressFieldsInvalidCascade() {
        address.setProvince(null);
        address.setCity(null);
        List<ConstraintViolation> violations = validator.validate(person);
        assertFalse(violations.isEmpty(), "Address全部字段无效应该触发验证错误");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("is invalid")));
    }

    @Test
    void testProvinceInvalidAndCityInvalidCascade() {
        address.setProvince("京");
        address.setCity(null);
        List<ConstraintViolation> violations = validator.validate(person);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("is invalid")));
    }

    // ==================== 嵌套对象为 null 时的级联行为 ====================

    @Test
    void testNullAddressDoesNotCascadeValidation() {
        person.setAddress(null);
        List<ConstraintViolation> violations = validator.validate(person);
        assertFalse(violations.isEmpty(), "address为null应该有验证错误");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("住址不能为空")));
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("省份不能为空")));
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("城市不能为空")));
    }

    // ==================== Address 直接校验（验证 message 内容） ====================

    @Test
    void testAddressDirectProvinceNotNull() {
        address.setProvince(null);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("省份不能为空")));
    }

    @Test
    void testAddressDirectProvinceTooShort() {
        address.setProvince("京");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("省份长度只能在3到5之间")));
    }

    @Test
    void testAddressDirectProvinceTooLong() {
        address.setProvince("黑龙江省大兴安岭");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("省份长度只能在3到5之间")));
    }

    @Test
    void testAddressDirectCityNotNull() {
        address.setCity(null);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("城市不能为空")));
    }

    @Test
    void testAddressDirectAllFieldsInvalid() {
        address.setProvince(null);
        address.setCity(null);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("省份不能为空")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("城市不能为空")));
    }

    @Test
    void testAddressDirectValid() {
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "有效Address直接验证应该通过");
    }
}
