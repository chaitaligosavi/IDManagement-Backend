package com.idmanagement.model;

public class CustomResponse<E> {
	private int statusCode = 200;

	private String description;

	private E data;

	private long date = System.currentTimeMillis();

	private String requestPath;

	public CustomResponse(int statusCode, String description, E data, long timestamp, String path) {
		super();
		this.date = timestamp;
		this.statusCode = statusCode;
		this.description = description;
		this.data = data;
		this.requestPath = path;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	@Override
	public String toString() {
		return "CustomResponse [statusCode=" + statusCode + ", description=" + description + ", data=" + data
				+ ", date=" + date + ", requestPath=" + requestPath + "]";
	}

}
