package com.suning.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

public class Test {
	
	public static void main(String[] args) {
		
		Person person = new Person();
		person.setAddress("11");
		person.setAge(505);
		person.setName("99");
		person.setPhone("22222222222");
		person.setMail("ddd@dd.com");
		person.setPassword("1");
		person.setPassword1("12");
		List<String> list = new ArrayList<>();
		list.add("a");
		person.setList(list);
		validator(person);
		
		Man man = new Man();
		validator(man);

		ValidateWithMethod1 validateWithMethod1 = new ValidateWithMethod1();
		validateWithMethod1.setMonth(2);
		validateWithMethod1.setYear(2000);
		validateWithMethod1.setDay(28);
		validator(validateWithMethod1);

		CheckWithCheckExample dayEntity = new CheckWithCheckExample();
		dayEntity.setYear(2000);
		dayEntity.setMonth(3);
		dayEntity.setDay(55);
		validator(dayEntity);

		WhenExample whenExample = new WhenExample();
		whenExample.setFieldA("fieldA");
		validator(whenExample);

		AssertExample assertExample = new AssertExample();
		assertExample.setSex("未知");
		validator(assertExample);

		FutureExample futureExample = new FutureExample();
		futureExample.setDate("2017-07-22 00:00:00");
		futureExample.setDate1(new Date(new Date().getTime() + 1000));
		validator(futureExample);
	}

	private static void validator(Object object) {
		Validator validator = new Validator();
		List<ConstraintViolation> violations = validator.validate(object);
		if (violations.size() > 0) {
			System.err.println("错误信息:" + violations.get(0).getMessage() + ",错误值:" + violations.get(0).getInvalidValue());
		}
	}

}
