package com.learn.entity;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoreTest {

    private Validator validator;
    private Store store;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        store = new Store();
        store.setStoreName("测试店铺");
        store.setId("1");
        store.setType(1);
        store.setContent("短内容");
    }

    // ==================== storeName / storeEnglishName 互斥非空 ====================

    @Test
    void testValidStore() {
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.isEmpty(), "完整Store应该通过验证");
    }

    @Test
    void testOnlyStoreName() {
        store.setStoreEnglishName(null);
        store.setStoreName("测试店铺");
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.isEmpty(), "只有中文名称应该通过");
    }

    @Test
    void testOnlyStoreEnglishName() {
        store.setStoreName(null);
        store.setStoreEnglishName("TestStore");
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.isEmpty(), "只有英文名称应该通过");
    }

    @Test
    void testBothNamesNull() {
        store.setStoreName(null);
        store.setStoreEnglishName(null);
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.size() >= 2, "两个名称都为null应该有至少2个错误");
    }

    @Test
    void testBothNamesEmpty() {
        store.setStoreName("");
        store.setStoreEnglishName("");
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.size() >= 1, "两个名称都为空字符串应该触发校验");
    }

    @Test
    void testBothNamesBlank() {
        store.setStoreName("   ");
        store.setStoreEnglishName("   ");
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.size() >= 1, "两个名称全是空格应该触发校验");
    }

    // ==================== storeName 长度校验 ====================
    // 注意: @Length(when="js:_value != null && _value.trim.length > 0")
    // Nashorn中_value.trim.length对Java String的.length属性求值有问题，
    // 导致when条件可能不生效，超长字符串不会触发@Length校验

    @Test
    void testStoreNameMinLength() {
        store.setStoreName("A");
        store.setStoreEnglishName(null);
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("店铺中文名称长度")));
    }

    @Test
    void testStoreNameMaxLength() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) sb.append("测");
        store.setStoreName(sb.toString());
        store.setStoreEnglishName(null);
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("店铺中文名称长度")));
    }

    @Test
    void testStoreNameExceedsMaxLength() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 21; i++) sb.append("测");
        store.setStoreName(sb.toString());
        store.setStoreEnglishName(null);
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("店铺中文名称长度必须在1到20之间")));
    }

    @Test
    void testStoreNameNullSkipsLengthCheck() {
        store.setStoreName(null);
        store.setStoreEnglishName("TestStore");
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("店铺中文名称长度")));
    }

    // ==================== storeEnglishName 长度校验 ====================

    @Test
    void testStoreEnglishNameMinLength() {
        store.setStoreEnglishName("A");
        store.setStoreName(null);
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("店铺英文名称长度")));
    }

    @Test
    void testStoreEnglishNameMaxLength() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 30; i++) sb.append("E");
        store.setStoreEnglishName(sb.toString());
        store.setStoreName(null);
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("店铺英文名称长度")));
    }

    @Test
    void testStoreEnglishNameExceedsMaxLength() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 31; i++) sb.append("E");
        store.setStoreEnglishName(sb.toString());
        store.setStoreName(null);
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("店铺英文名称长度必须在1到30之间")));
    }

    // ==================== id 校验 ====================

    @Test
    void testIdNotNull() {
        store.setId(null);
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("id不能为null")));
    }

    @Test
    void testIdBlank() {
        store.setId("");
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("id不能为空字符串或全部是空格")));
    }

    @Test
    void testIdWhitespace() {
        store.setId("   ");
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("id不能为空字符串或全部是空格")));
    }

    @Test
    void testIdValid() {
        store.setId("abc123");
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("id")));
    }

    // ==================== type 校验 ====================

    @Test
    void testTypeNotNull() {
        store.setType(null);
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("type不能为空")));
    }

    @Test
    void testTypeZero() {
        store.setType(0);
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("type的值必须大于等于1")));
    }

    @Test
    void testTypeNegative() {
        store.setType(-1);
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("type的值必须大于等于1")));
    }

    @Test
    void testTypeMinValid() {
        store.setType(1);
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("type")));
    }

    @Test
    void testTypeLargeValue() {
        store.setType(999);
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("type")));
    }

    // ==================== content + ContentCheck 校验 ====================

    @Test
    void testContentNotNull() {
        store.setContent(null);
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(!violations.isEmpty(), "content为null应该触发校验");
    }

    @Test
    void testContentBlank() {
        store.setContent("");
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(!violations.isEmpty(), "空字符串应该触发校验");
    }

    @Test
    void testContentType1WithinLimit() {
        store.setType(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) sb.append("a");
        store.setContent(sb.toString());
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("评论内容超长")));
    }

    @Test
    void testContentType1ExceedsLimit() {
        store.setType(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 21; i++) sb.append("a");
        store.setContent(sb.toString());
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("评论内容超长")));
    }

    @Test
    void testContentType2WithinLimit() {
        store.setType(2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 30; i++) sb.append("b");
        store.setContent(sb.toString());
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("评论内容超长")));
    }

    @Test
    void testContentType2ExceedsLimit() {
        store.setType(2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 31; i++) sb.append("b");
        store.setContent(sb.toString());
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("评论内容超长")));
    }

    @Test
    void testContentType3NoLimit() {
        store.setType(3);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) sb.append("c");
        store.setContent(sb.toString());
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("评论内容超长")));
    }

    // ==================== 多字段组合 ====================

    @Test
    void testAllFieldsInvalid() {
        store.setStoreName(null);
        store.setStoreEnglishName(null);
        store.setId(null);
        store.setType(null);
        store.setContent(null);
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.size() >= 4, "多字段无效应该有多个错误");
    }

    @Test
    void testBothNamesAndContentInvalid() {
        store.setStoreName(null);
        store.setStoreEnglishName(null);
        store.setType(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 21; i++) sb.append("a");
        store.setContent(sb.toString());
        List<ConstraintViolation> violations = validator.validate(store);
        assertTrue(violations.size() >= 2, "名称和内容同时无效应该有多个错误");
    }
}
