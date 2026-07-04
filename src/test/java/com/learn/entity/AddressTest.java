package com.learn.entity;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressTest {

    private Validator validator;
    private Address address;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        address = new Address();
        address.setCity("北京");
        List<String> streets = new ArrayList<>();
        streets.add("长安街");
        address.setStreets(streets);
        address.setRetryTimes(3);
        address.setAmount(100L);
        address.setRefundMode("RECOVER");
        List<String> list = new ArrayList<>();
        list.add("item1");
        address.setList(list);
        Set<String> set = new HashSet<>();
        set.add("item1");
        address.setSet(set);
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        address.setMap(map);
        address.setArray(new String[]{"a"});
    }

    @Test
    void testValidAddress() {
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "验证应该通过");
    }

    @Test
    void testAmountNotNull() {
        address.setAmount(null);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("金额不能为空")));
    }

    @Test
    void testAmountMustBePositive() {
        address.setAmount(0L);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("金额必须大于0")));
    }

    @Test
    void testAmountNegative() {
        address.setAmount(-1L);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("金额必须大于0")));
    }

    @Test
    void testRefundModeNotNull() {
        address.setRefundMode(null);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("退款模式不能为空")));
    }

    @Test
    void testRefundModeInvalidValue() {
        address.setRefundMode("INVALID");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("退款模式只能是REFUND或RECOVER")));
    }

    @Test
    void testRefundModeRefund() {
        address.setRefundMode("REFUND");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("退款模式为REFUND时，退款时间不能为空")));
    }

    @Test
    void testRefundModeRefundWithTime() {
        address.setRefundMode("REFUND");
        address.setRefundTime("2024-01-01");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "验证应该通过");
    }

    @Test
    void testRefundModeRecoverWithoutTime() {
        address.setRefundMode("RECOVER");
        address.setRefundTime(null);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "RECOVER模式下refundTime可以为空");
    }

    @Test
    void testCityNotNull() {
        address.setCity(null);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("城市不能为空")));
    }

    @Test
    void testStreetsNotNull() {
        address.setStreets(null);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("streets不能为空")));
    }

    @Test
    void testAmountBoundaryMinValid() {
        address.setAmount(1L);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "金额为1应该通过验证");
    }

    @Test
    void testAmountBoundaryMaxValue() {
        address.setAmount(Long.MAX_VALUE);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "金额为Long.MAX_VALUE应该通过验证");
    }

    @Test
    void testAmountBoundaryMinValue() {
        address.setAmount(Long.MIN_VALUE);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("金额必须大于0")));
    }

    @Test
    void testAmountBoundaryLargeNegative() {
        address.setAmount(-999999999L);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("金额必须大于0")));
    }

    @Test
    void testRefundModeEmptyString() {
        address.setRefundMode("");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("退款模式只能是REFUND或RECOVER")));
    }

    @Test
    void testRefundModeWhitespace() {
        address.setRefundMode("  ");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("退款模式只能是REFUND或RECOVER")));
    }

    @Test
    void testRefundModeCaseSensitivity() {
        address.setRefundMode("refund");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("退款模式只能是REFUND或RECOVER")));
    }

    @Test
    void testRefundModePartialMatch() {
        address.setRefundMode("REFUNDING");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("退款模式只能是REFUND或RECOVER")));
    }

    @Test
    void testRefundModeWithLeadingSpace() {
        address.setRefundMode(" REFUND");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("退款模式只能是REFUND或RECOVER")));
    }

    @Test
    void testRefundModeWithTrailingSpace() {
        address.setRefundMode("REFUND ");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("退款模式只能是REFUND或RECOVER")));
    }

    @Test
    void testRefundTimeEmptyStringInRefundMode() {
        address.setRefundMode("REFUND");
        address.setRefundTime("");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "空字符串不是null，应该通过验证");
    }

    @Test
    void testRefundTimeWhitespaceInRefundMode() {
        address.setRefundMode("REFUND");
        address.setRefundTime("  ");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "空白字符串不是null，应该通过验证");
    }

    @Test
    void testCityEmptyString() {
        address.setCity("");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("城市不能为空")));
    }

    @Test
    void testCityWhitespace() {
        address.setCity("   ");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("城市不能为空")));
    }

    @Test
    void testCityVeryLongString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("城");
        }
        address.setCity(sb.toString());
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "长字符串城市名应该通过验证");
    }

    @Test
    void testStreetsEmptyList() {
        address.setStreets(new ArrayList<>());
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "空列表应该通过验证");
    }

    @Test
    void testStreetsWithEmptyString() {
        List<String> streets = new ArrayList<>();
        streets.add("");
        address.setStreets(streets);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "包含空字符串的列表应该通过验证");
    }

    @Test
    void testRetryTimesZero() {
        address.setRetryTimes(0);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "retryTimes为0应该通过验证");
    }

    @Test
    void testRetryTimesNegative() {
        address.setRetryTimes(-1);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("重试次数不能为负数")));
    }

    @Test
    void testRetryTimesMaxValue() {
        address.setRetryTimes(Integer.MAX_VALUE);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "retryTimes为Integer.MAX_VALUE应该通过验证");
    }

    @Test
    void testRetryTimesMinValue() {
        address.setRetryTimes(Integer.MIN_VALUE);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("重试次数不能为负数")));
    }

    @Test
    void testMultipleValidationErrors() {
        address.setCity(null);
        address.setStreets(null);
        address.setAmount(null);
        address.setRefundMode(null);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.size() >= 4, "应该有多个验证错误");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("城市不能为空")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("streets不能为空")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("金额不能为空")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("退款模式不能为空")));
    }

    @Test
    void testAllFieldsInvalid() {
        address.setCity("");
        address.setStreets(null);
        address.setAmount(-1L);
        address.setRetryTimes(-1);
        address.setRefundMode("INVALID");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.size() >= 5, "所有字段无效时应该有多个验证错误");
    }

    @Test
    void testNullObjectValidation() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(null), "null对象应该抛出异常");
    }

    @Test
    void testRefundModeRecoverWithTime() {
        address.setRefundMode("RECOVER");
        address.setRefundTime("2024-01-01");
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "RECOVER模式带退款时间应该通过验证");
    }

    @Test
    void testAmountVeryLargeNegative() {
        address.setAmount(-Long.MAX_VALUE);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("金额必须大于0")));
    }

    @Test
    void testAmountJustAboveZero() {
        address.setAmount(2L);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "金额为2应该通过验证");
    }

    @Test
    void testRefundModeOnlyRecover() {
        address.setRefundMode("RECOVER");
        address.setRefundTime(null);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "RECOVER模式不需要退款时间");
    }

    @Test
    void testRefundModeOnlyRefundWithNullTime() {
        address.setRefundMode("REFUND");
        address.setRefundTime(null);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("退款模式为REFUND时，退款时间不能为空")));
    }

    // ==================== List 字段测试 ====================

    @Test
    void testListNotNull() {
        address.setList(null);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("List不允许为空")));
    }

    @Test
    void testListEmpty() {
        address.setList(new ArrayList<>());
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("List元素个数至少大于等于1")));
    }

    @Test
    void testListOneElement() {
        List<String> list = new ArrayList<>();
        list.add("only");
        address.setList(list);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("List")), "一个元素的List应该通过");
    }

    @Test
    void testListMultipleElements() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        address.setList(list);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("List")));
    }

    @Test
    void testListWithNullElement() {
        List<String> list = new ArrayList<>();
        list.add(null);
        address.setList(list);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("List元素个数至少大于等于1")), "包含null元素的List不应触发Size校验");
    }

    @Test
    void testListWithEmptyString() {
        List<String> list = new ArrayList<>();
        list.add("");
        address.setList(list);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("List")));
    }

    @Test
    void testListWithBlankString() {
        List<String> list = new ArrayList<>();
        list.add("   ");
        address.setList(list);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("List")));
    }

    // ==================== Set 字段测试 ====================

    @Test
    void testSetNotNull() {
        address.setSet(null);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Set不允许为空")));
    }

    @Test
    void testSetEmpty() {
        address.setSet(new HashSet<>());
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Set元素个数至少大于等于1")));
    }

    @Test
    void testSetOneElement() {
        Set<String> set = new HashSet<>();
        set.add("only");
        address.setSet(set);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("Set")), "一个元素的Set应该通过");
    }

    @Test
    void testSetMultipleElements() {
        Set<String> set = new HashSet<>();
        set.add("x");
        set.add("y");
        set.add("z");
        address.setSet(set);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("Set")));
    }

    @Test
    void testSetDuplicateElements() {
        Set<String> set = new HashSet<>();
        set.add("same");
        set.add("same");
        address.setSet(set);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("Set")), "Set去重后只剩1个元素，应该通过");
    }

    @Test
    void testSetWithEmptyString() {
        Set<String> set = new HashSet<>();
        set.add("");
        address.setSet(set);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("Set")));
    }

    // ==================== Map 字段测试 ====================

    @Test
    void testMapNotNull() {
        address.setMap(null);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Map不允许为空")));
    }

    @Test
    void testMapEmpty() {
        address.setMap(new HashMap<>());
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Map元素个数至少大于等于1")));
    }

    @Test
    void testMapOneEntry() {
        Map<String, String> map = new HashMap<>();
        map.put("k", "v");
        address.setMap(map);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("Map")), "一个entry的Map应该通过");
    }

    @Test
    void testMapMultipleEntries() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        address.setMap(map);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("Map")));
    }

    @Test
    void testMapNullValue() {
        Map<String, String> map = new HashMap<>();
        map.put("key", null);
        address.setMap(map);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("Map元素个数至少大于等于1")), "value为null的entry不应触发Size校验");
    }

    @Test
    void testMapEmptyValue() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "");
        address.setMap(map);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("Map")));
    }

    // ==================== Array 字段测试 ====================

    @Test
    void testArrayNotNull() {
        address.setArray(null);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("数组不允许为")));
    }

    @Test
    void testArrayEmpty() {
        address.setArray(new String[]{});
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("数组元素个数至少大于等于1")));
    }

    @Test
    void testArrayOneElement() {
        address.setArray(new String[]{"only"});
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("数组")), "一个元素的数组应该通过");
    }

    @Test
    void testArrayMultipleElements() {
        address.setArray(new String[]{"a", "b", "c"});
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("数组")));
    }

    @Test
    void testArrayWithNullElement() {
        address.setArray(new String[]{null});
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("数组元素个数至少大于等于1")), "包含null元素的数组不应触发Size校验");
    }

    @Test
    void testArrayWithEmptyString() {
        address.setArray(new String[]{""});
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().noneMatch(v -> v.getMessage().contains("数组")));
    }

    // ==================== 多集合同时校验 ====================

    @Test
    void testAllCollectionsInvalid() {
        address.setList(null);
        address.setSet(null);
        address.setMap(null);
        address.setArray(null);
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("List不允许为空")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Set不允许为空")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Map不允许为空")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("数组不允许为")));
    }

    @Test
    void testAllCollectionsEmpty() {
        address.setList(new ArrayList<>());
        address.setSet(new HashSet<>());
        address.setMap(new HashMap<>());
        address.setArray(new String[]{});
        List<ConstraintViolation> violations = validator.validate(address);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("List元素个数至少大于等于1")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Set元素个数至少大于等于1")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Map元素个数至少大于等于1")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("数组元素个数至少大于等于1")));
    }
}
