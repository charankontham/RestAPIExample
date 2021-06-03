package com.test;

import java.io.Serializable;

public class Employee {

	private String ename;
	private String city;
	private int deptId;
	private String mobile;
	private int age;
	private int salary;
	
	public Employee(){
		
	}
	

	public Employee(String ename, String city, int dept_id, String mobile, int age, int salary) {
		super();
		this.ename = ename;
		this.city = city;
		this.deptId = dept_id;
		this.mobile = mobile;
		this.age = age;
		this.salary = salary;
	}


	protected String getEname() {
		return ename;
	}

	protected void setEname(String ename) {
		this.ename = ename;
	}

	protected String getCity() {
		return city;
	}

	protected void setCity(String city) {
		this.city = city;
	}

	protected int getDeptId() {
		return deptId;
	}

	protected void setDeptId(int dept_id) {
		this.deptId = dept_id;
	}

	protected String getMobile() {
		return mobile;
	}

	protected void setMobile(String mobile) {
		this.mobile = mobile;
	}

	protected int getAge() {
		return age;
	}

	protected void setAge(int age) {
		this.age = age;
	}

	protected int getSalary() {
		return salary;
	}

	protected void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [ ename=" + ename + ", city=" + city + ", dept_id=" + deptId + ", mobile=" + mobile + ", age="
				+ age + ", salary=" + salary + "]";
	}

}
