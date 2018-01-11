package com.learn.entity;

import java.util.List;
import net.sf.oval.constraint.Assert;
import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Range;
import net.sf.oval.constraint.Size;

public class Person {
	
	@NotNull(message="主键不能为空")
	@NotBlank(message="主键不能为空")
	private String id;
	
	@NotBlank
	@MaxLength(value=10, message="姓名长度不能超过10位")
	private String name;

	@Range(max=200, min=0, message="非法年龄")
	private int age;
	
	@NotNull(when="js:_this.id == 909", message="性别不能为空")
	private String sex;
	
	private String address;
	
	@NotBlank
	@NotNull(message="手机不能为空")
	@Length(max=11, min=11, message="手机长度不符合规范")
	private String phone;
	
	@Email
	@NotNull(when="js:_this.phone != null", message="邮箱不能为空")
	private String mail;
	
	@NotBlank(message="密码不能为空")
	@NotNull(message="密码不能为空")
	private String password;
	
	@NotBlank(message="确认密码不能为空")
	@NotNull(message="确认密码不能为空")
	@Assert(expr = "_value ==_this.password", lang = "js", message="两次密码不一致")
	private String password1;


	@NotNull(message="集合不能为空")
	@Size(min=1, message="集合内容至少大于1")
	private List<String> list;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
}
