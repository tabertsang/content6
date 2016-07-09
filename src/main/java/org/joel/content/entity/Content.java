package org.joel.content.entity;

public class Content {
		private int id;
		private String title;
		private String summary;
		private String image;
		private String detail;
		private int price;
		private Boolean isBuy;
		private Boolean isSell;
		private int buyPrice;
		private int saleNum;
		private int buyNum;
		
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

		public Boolean getIsBuy() {
			return isBuy;
		}

		public void setIsBuy(Boolean isBuy) {
			this.isBuy = isBuy;
		}

		public Boolean getIsSell() {
			return isSell;
		}

		public void setIsSell(Boolean isSell) {
			this.isSell = isSell;
		}

		public int getBuyPrice() {
			return buyPrice;
		}

		public void setBuyPrice(int buyPrice) {
			this.buyPrice = buyPrice;
		}

		public int getSaleNum() {
			return saleNum;
		}

		public void setSaleNum(int saleNum) {
			this.saleNum = saleNum;
		}

		public int getBuyNum() {
			return buyNum;
		}

		public void setBuyNum(int buyNum) {
			this.buyNum = buyNum;
		}

}
