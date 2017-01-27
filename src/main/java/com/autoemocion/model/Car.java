package com.autoemocion.model;




public class Car {
	private String title;
	private String subtitle;
	private String description;
	private String price;
	private String imgaUrl;
	private String carUrl;
	
	
	public Car(String title, String subtitle, String description, String price, String imgaUrl, String carUrl) {
		
		this.title = title;
		this.subtitle = subtitle;
		this.description = description;
		this.price = price;
		this.imgaUrl = imgaUrl;
		this.carUrl = carUrl;
	}


	public String getCarUrl() {
		return carUrl;
	}


	public void setCarUrl(String carUrl) {
		this.carUrl = carUrl;
	}


	public String getTitle() {
		return title;
	}


	@Override
	public String toString() {
		return "Car [title=" + title + ", subtitle=" + subtitle + ", description=" + description + ", price=" + price
				+ ", imgaUrl=" + imgaUrl + "]";
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSubtitle() {
		return subtitle;
	}


	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getImgaUrl() {
		return imgaUrl;
	}


	public void setImgaUrl(String imgaUrl) {
		this.imgaUrl = imgaUrl;
	}
	
}
