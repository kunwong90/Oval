package com.learn.entity;

import com.learn.utils.ValidationUtils;
import net.sf.oval.constraint.*;


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

    /**
     * 当type=1时，content长度不能超过20
     * 当type=2时，content长度不能超过30
     */
    private Integer type;

    @NotNull
    @NotBlank
    //@Length(min = 1, when = "js:_this.type == 1", max = 10)
    @CheckWith(value = ContentCheck.class, message = "评论内容超长")
    private String content;

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

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public static void main(String[] args) {
        Store store = new Store();
        store.setStoreEnglishName("english");
        store.setId("1");
        store.setType(1);
        store.setContent("1111111111111111111111");
        ValidationUtils.validate(store);
    }

    private static class ContentCheck implements CheckWithCheck.SimpleCheck {
        @Override
        public boolean isSatisfied(Object validatedObject, Object value) {
            if (validatedObject instanceof Store) {
                Store store = (Store) validatedObject;
                if (store.type != null) {
                    if (store.type == 1) {
                        if (store.content.length() > 20) {
                            return false;
                        }
                    } else if (store.type == 2) {
                        if (store.content.length() > 30) {
                            return false;
                        }
                    } else {
                        return true;
                    }
                    return true;
                }
            }
            return false;
        }
    }
}
