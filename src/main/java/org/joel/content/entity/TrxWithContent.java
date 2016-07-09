package org.joel.content.entity;

public class TrxWithContent {
	private int trxId; //订单号
	private int id; //产品id
	private String title; //标题
	private String image; //图片
	private int buyPrice; //购买时的价格
	private int buyNum; //购买数量
	private long buyTime; //购买时间
	
	public int getTrxId() {
		return trxId;
	}
	public void setTrxId(int trxId) {
		this.trxId = trxId;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public long getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(long buyTime) {
		this.buyTime = buyTime;
	}
	
}
