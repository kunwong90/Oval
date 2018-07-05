package com.learn.entity;

import com.learn.utils.ValidationUtils;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;


/**
 * NotNull只能用来判断对象是否为空
 * NotBlank用来判断对象是否是空字符串或者全部是空格
 * NotEmpty用来判断对象是否是空字符串，不能用来判断是否全部是空格
 */
public class Store {


    /**
     * 店铺中文名称
     */
    @NotNull(when = "js:_this.storeEnglishName == null", message = "店铺中文名称和店铺英文名称不能同时为空1")
    @NotBlank(message = "店铺中文名不能为空")
    @Length(min = 1, max = 20, message = "店铺英文名称长度必须在{min}到{max}之间")
    private String storeName;

    /**
     * 店铺英文名称
     */
    @NotNull(when = "js:_this.storeName == null", message = "店铺中文名称和店铺英文名称不能同时为空3")
    @NotBlank(message = "店铺英文名称不能为空")
    @Length(min = 1, max = 30, message = "店铺英文名称长度必须在{min}到{max}之间")
    private String storeEnglishName;

    @NotNull(message = "id不能为null")
    @NotBlank(message = "id不能为空字符串或全部是空格")
    @Length(min = 1)
    private String id;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreEnglishName() {
        return storeEnglishName;
    }

    public void setStoreEnglishName(String storeEnglishName) {
        this.storeEnglishName = storeEnglishName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void main(String[] args) {
        Store store = new Store();
        store.setStoreEnglishName("english");
        store.setId("1");
        ValidationUtils.validate(store);
    }
}
