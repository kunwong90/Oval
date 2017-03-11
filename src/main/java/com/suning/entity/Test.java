package com.suning.entity;

import java.util.ArrayList;
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

		Validator validator = new Validator();
		List<ConstraintViolation> violations = validator.validate(person);
		for (ConstraintViolation violation : violations) {
			System.out.println(violation.getMessage());
		}
		
	}

}
