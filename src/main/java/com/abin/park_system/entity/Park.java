package com.abin.park_system.entity;

import java.util.UUID;

public class Park {
	private String park_id = UUID.randomUUID().toString();
	private String user_id;
	private String park_name;
	private String park_location;
	private String check_status = "审核中";
	private String publish_status = "未发布";
	private String park_price;
	private String park_province;
	private String park_city;
	private String remark;
	private String admin_remark;
	private String create_time;
	private String park_latitude;
	private String park_longitude;
	private String park_num;




	public String getPark_id() {
		return park_id;
	}

	public void setPark_id(String park_id) {
		this.park_id = park_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPark_name() {
		return park_name;
	}

	public void setPark_name(String park_name) {
		this.park_name = park_name;
	}

	public String getPark_location() {
		return park_location;
	}

	public void setPark_location(String park_location) {
		this.park_location = park_location;
	}

	public String getCheck_status() {
		return check_status;
	}

	public void setCheck_status(String check_status) {
		this.check_status = check_status;
	}

	public String getPublish_status() {
		return publish_status;
	}

	public void setPublish_status(String publish_status) {
		this.publish_status = publish_status;
	}

	public String getPark_price() {
		return park_price;
	}

	public void setPark_price(String park_price) {
		this.park_price = park_price;
	}

	public String getPark_province() {
		return park_province;
	}

	public void setPark_province(String park_province) {
		this.park_province = park_province;
	}

	public String getPark_city() {
		return park_city;
	}

	public void setPark_city(String park_city) {
		this.park_city = park_city;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAdmin_remark() {
		return admin_remark;
	}

	public void setAdmin_remark(String admin_remark) {
		this.admin_remark = admin_remark;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getPark_latitude() {
		return park_latitude;
	}

	public void setPark_latitude(String park_latitude) {
		this.park_latitude = park_latitude;
	}

	public String getPark_longitude() {
		return park_longitude;
	}

	public void setPark_longitude(String park_longitude) {
		this.park_longitude = park_longitude;
	}

	public String getPark_num() {
		return park_num;
	}

	public void setPark_num(String park_num) {
		this.park_num = park_num;
	}
}
