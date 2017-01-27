package com.autoemocion.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.autoemocion.model.Car;

@Service
public class CarService {
	
	
	
	public static Document getOtomotoDoc() {
		Document doc = null;
		try {
			doc = Jsoup.connect("https://autoemocion.otomoto.pl/").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;

	}

	public static List<String> getTitiles() throws IOException {
		List<String> titlelist = new ArrayList<String>();
		Document doc = getOtomotoDoc();
		Elements offertitleclass = doc.getElementsByClass("offer-title__link");
		for (Element item : offertitleclass) {
			String title = item.attr("title");
			titlelist.add(title);
		}
		return titlelist;
	}

	public static List<String> getSubtitiles() {
		List<String> subtitles = new ArrayList<String>();
		Document doc = getOtomotoDoc();
		Elements subtitiles = doc.getElementsByClass("offer-item__subtitle offer-item__subtitle--short");
		for (Element item : subtitiles) {
			subtitles.add(item.text());
		}

		return subtitles;
	}

	public static List<String> getPrices() {
		List<String> priceslist = new ArrayList<String>();
		Document doc = getOtomotoDoc();
		Elements prices = doc.getElementsByClass("offer-price__number");
		for (Element item : prices) {
			priceslist.add(item.text());
		}
		
		return priceslist;
	}
	public static List<String> getDescription() {
		List<String> descriptionlist = new ArrayList<String>();
		Document doc = getOtomotoDoc();
		Elements prices = doc.getElementsByClass("offer-item__bottom-row ");
		for (Element item : prices) {
			descriptionlist.add(item.text());
		}
		
		return descriptionlist;
	}
	
	public static List<String> imageUrl(){
		List<String> imageUrl = new ArrayList<String>();
		Document doc = getOtomotoDoc();
		Elements imagediv = doc.getElementsByClass("offer-item__photo-link");
		
		for(Element item : imagediv){
			 String attr = item.attr("style");
	            
	            imageUrl.add(attr.substring( attr.indexOf("https://"), attr.indexOf(")") ) );
		}

		
		return imageUrl;
	}
	public static List<String> carUrl(){
		Document doc = getOtomotoDoc();
		List<String> urlList = new ArrayList<>();
		Elements offertitleclass = doc.getElementsByClass("offer-title__link");
		for (Element item : offertitleclass) {
			String url = item.attr("href");
			urlList.add(url);
		}
		
		return urlList;
		
	}
	
	public  List<Car> listOfCars() throws IOException{
		List<String> titles = getTitiles();
		List<String> subtitles = getSubtitiles();
		List<String> prices = getPrices();
		List<String> descriptions = getDescription();
		List<String> imgUrls = imageUrl();
		List<Car> listofcars = new ArrayList<>();
		List<String> urlList = carUrl();
		for(int i=0; i<titles.size();i++){
			Car car = new Car(titles.get(i),subtitles.get(i),descriptions.get(i),prices.get(i),imgUrls.get(i),urlList.get(i));
			listofcars.add(car);
		}
		
		
		return listofcars;
		
	}
	
	
	

}
