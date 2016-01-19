package com.test;

import java.util.Date;

public class CustomerHistory {

	private Long id;
	private String record_amount;
	private Date upload_date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecord_amount() {
		return record_amount;
	}

	public void setRecord_amount(String record_amount) {
		this.record_amount = record_amount;
	}

	public Date getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}

}
