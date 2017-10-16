package com.autoemocion.service.otomoto;

import com.autoemocion.model.Car;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OtomotoService {
    private static final Logger LOG = LoggerFactory.getLogger(OtomotoService.class);


    public static Document getOtomotoDoc() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://autoemocion.otomoto.pl/").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36").get();

        } catch (IOException e) {
            LOG.warn(e.getMessage());
        }
        return doc;

    }

    public  List<String> getTitiles(Document doc) {
        List<String> titlelist = new ArrayList<String>();
        Elements offertitleclass = doc.getElementsByClass("offer-title__link");
        for (Element item : offertitleclass) {
            String title = item.attr("title");
            titlelist.add(title);
        }
        return titlelist;
    }

    public  List<String> getSubtitiles(Document doc) {
        List<String> subtitles = new ArrayList<String>();
        Elements subtitiles = doc.getElementsByClass("offer-item__subtitle offer-item__subtitle--short");
        for (Element item : subtitiles) {
            subtitles.add(item.text());
        }
        return subtitles;
    }

    public  List<String> getPrices(Document doc) {
        List<String> priceslist = new ArrayList<String>();
        Elements prices = doc.getElementsByClass("offer-price__number");
        for (Element item : prices) {
            priceslist.add(item.text());
        }

        return priceslist;
    }

    public  List<String> getDescription(Document doc) {
        List<String> descriptionlist = new ArrayList<String>();
        Elements prices = doc.getElementsByClass("offer-item__bottom-row ");
        for (Element item : prices) {
            descriptionlist.add(item.text());
        }

        return descriptionlist;
    }

    public  List<String> imageUrl(Document doc) {
        List<String> imageUrl = new ArrayList<String>();
        Elements imagediv = doc.getElementsByClass("offer-item__photo-link");
        for (Element item : imagediv) {
            String attr = item.attr("style");
            imageUrl.add(attr.substring(attr.indexOf("https://"), attr.indexOf(")")));
        }
        return imageUrl;
    }

    public  List<String> carUrl(Document doc) {
        List<String> urlList = new ArrayList<>();
        Elements offertitleclass = doc.getElementsByClass("offer-title__link");
        for (Element item : offertitleclass) {
            String url = item.attr("href");
            urlList.add(url);
        }
        return urlList;

    }

    public List<Car> listOfCars() throws IOException {
        Document doc = OtomotoService.getOtomotoDoc();
        List<String> titles = getTitiles(doc);
        List<String> subtitles = getSubtitiles(doc);
        List<String> prices = getPrices(doc);
        List<String> descriptions = getDescription(doc);
        List<String> imgUrls = imageUrl(doc);
        List<Car> listofcars = new ArrayList<>();
        List<String> urlList = carUrl(doc);
        for (int i = 0; i < titles.size(); i++) {
            Car car = new Car(titles.get(i), subtitles.get(i), descriptions.get(i), prices.get(i), imgUrls.get(i), urlList.get(i));
            listofcars.add(car);
        }
        return listofcars;
    }



}
