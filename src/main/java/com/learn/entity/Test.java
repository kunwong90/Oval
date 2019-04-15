package com.learn.entity;

import com.learn.utils.ValidationUtils;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		validateWithMethod1.setDay(29);
		validateWithMethod1.setSaleNo("16121145");
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
		futureExample.setDate1(new Date(System.currentTimeMillis() + 1000));
		validator(futureExample);


		CollectionSize collectionSize = new CollectionSize();
		List<String> list1 = new ArrayList<>();
		list1.add("name");
		collectionSize.setList(list1);
		Map<String, String> map = new HashMap<>();
		collectionSize.setMap(map);
		ValidationUtils.validate(collectionSize);


		RsfQrCodePaymentReqDto rsfQrCodePaymentReqDto = new RsfQrCodePaymentReqDto();
		rsfQrCodePaymentReqDto.setSystemSource("PPOS");
		rsfQrCodePaymentReqDto.setPayType("05");
		ValidationUtils.validate(rsfQrCodePaymentReqDto);
	}

	private static void validator(Object object) {
		Validator validator = new Validator();
		List<ConstraintViolation> violations = validator.validate(object);
		if (violations.size() > 0) {
			System.err.println("错误信息:" + violations.get(0).getMessage() + ",错误值:" + violations.get(0).getInvalidValue());
		}
	}

}
