package com.learn.entity;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;

import java.util.*;

public class CollectionSize {

    @NotNull(message = "List不允许为空")
    @Size(min = 1, message = "List元素个数至少大于等于1")
    private List<String> list;

    @NotNull(message = "Set不允许为空")
    @Size(min = 1, message = "Set元素个数至少大于等于1")
    private Set<String> set;

    @NotNull(message = "Map不允许为空")
    @Size(min = 1, message = "Map元素个数至少大于等于1")
    private Map<String, String> map;

    @NotNull(message = "数组不允许为")
    @Size(min = 1, message = "数组元素个数至少大于等于1")
    private String[] array;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }
}
