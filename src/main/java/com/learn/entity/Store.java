package com.learn.entity;

import com.learn.utils.ValidationUtils;
import net.sf.oval.Validator;
import net.sf.oval.constraint.*;
import net.sf.oval.context.OValContext;
import org.apache.commons.lang3.StringUtils;


/**
 * NotNull只能用来判断对象是否为空
 * NotBlank用来判断对象是否是空字符串或者全部是空格
 * NotEmpty用来判断对象是否是空字符串，不能用来判断是否全部是空格
 */
public class Store {


    /**
     * 店铺中文名称
     */
    @NotNull(when = "js:_this.storeEnglishName == null", message = "店铺中文名称和店铺英文名称不能同时为空")
    @ValidateWithMethod(methodName = "isBlankStoreName", parameterType = String.class, message = "店铺中文名称和店铺英文名称不能同时为空")
    //@CheckWith(value = CheckStore.class, message = "店铺中文名称和店铺英文名称不能同时为空")
    @Length(when = "js:_value != null && _value.trim.length > 0", min = 1, max = 20, message = "店铺中文名称长度必须在{min}到{max}之间")
    private String storeName;

    /**
     * 店铺英文名称
     */
    @NotNull(when = "js:_this.storeName == null", message = "店铺中文名称和店铺英文名称不能同时为空")
    //@CheckWith(value = CheckStore.class, message = "店铺中文名称和店铺英文名称不能同时为空")
    @ValidateWithMethod(methodName = "isBlankStoreName", parameterType = String.class, message = "店铺中文名称和店铺英文名称不能同时为空")
    @Length(when = "js:_value != null && _value.trim.length > 0", min = 1, max = 30, message = "店铺英文名称长度必须在{min}到{max}之间")
    private String storeEnglishName;

    @NotNull(message = "id不能为null")
    @NotBlank(message = "id不能为空字符串或全部是空格")
    @Length(min = 1)
    private String id;

    /**
     * 当type=1时，content长度不能超过20
     * 当type=2时，content长度不能超过30
     * type不能为null并且不等于0
     */
    @NotNull(message = "type不能为空")
    @Min(value = 1, message = "type的值必须大于等于1")
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
        store.setStoreEnglishName(null);
        store.setStoreName("好到家");
        store.setId("1");
        store.setType(0);
        store.setContent("99");
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

    /**
     * storeName和storeEnglishName全部为null，方法不执行
     */
    private static class CheckStore implements CheckWithCheck.SimpleCheck {
        @Override
        public boolean isSatisfied(Object o, Object o1) {
            if (o instanceof Store) {
                Store store = (Store) o;
                if (StringUtils.isAllBlank(store.getStoreName(), store.getStoreEnglishName())) {
                    return false;
                }
                return true;
            }
            return false;
        }
    }

    /**
     * storeName和storeEnglishName全部为null，方法不执行
     * @param name
     * @return
     */
    private boolean isBlankStoreName(String name) {
        if (StringUtils.isAllBlank(this.storeName, this.storeEnglishName)) {
            return false;
        }
        return true;
    }
}
