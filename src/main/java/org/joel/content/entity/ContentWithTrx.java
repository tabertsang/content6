package org.joel.content.entity;

import java.util.List;

public class ContentWithTrx {
	private int id;
	private String title;
	private String summary;
	private String image;
	private String detail;
	private int price;
	private List<Trx> trxes;
	
	
	
	public ContentWithTrx(Integer id, String title, String summary, String image, String detail, Integer price) {
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.image = image;
		this.detail = detail;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<Trx> getTrxes() {
		return trxes;
	}
	public void setTrxes(List<Trx> trxes) {
		this.trxes = trxes;
	}
}
