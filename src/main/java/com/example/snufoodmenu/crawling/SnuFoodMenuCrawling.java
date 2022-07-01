package com.example.snufoodmenu.crawling;

import com.example.snufoodmenu.service.SlackAlterService;
import com.example.snufoodmenu.user.SnuRestaurant;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class SnuFoodMenuCrawling {

    /**
     * 조회할 URL셋팅 및 Document 객체 로드하기
     */
    private static final String url = "https://snuco.snu.ac.kr/ko/foodmenu";
    public Document process() {
        Connection conn = Jsoup.connect(url);
        Document document = null;
        try {
            document = conn.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
    /**
     * data가져오기
     */
    public String getLunch(Document document) {
        List<String> list = new ArrayList<>();
        String menuMessage = "";
        Elements lunch = document.getElementsByClass("views-field views-field-field-lunch");
        SnuRestaurant snuRestaurant = new SnuRestaurant();
        List<String> menu = snuRestaurant.getSnuRestaurant();
        return getFoodMenu(menuMessage, lunch, menu);
    }
    public String getDinner(Document document){
        List<String> list = new ArrayList<>();
        String menuMessage = "";
        Elements dinner = document.getElementsByClass("views-field views-field-field-dinner");
        SnuRestaurant snuRestaurant = new SnuRestaurant();
        List<String> restaurant = snuRestaurant.getSnuRestaurant();
        return getFoodMenu(menuMessage, dinner, restaurant);
    }

    @NotNull
    private String getFoodMenu(String menuMessage, Elements dinner, List<String> restaurant) {
        for(int i=0; i<=3; i++){
            Element element = dinner.get(i);
            if (i == 3){
                menuMessage += element.text() + "\n";
            }else menuMessage += element.text() + "\n" + restaurant.get(i) + "\n";
        }
        menuMessage = menuMessage.trim();
        return menuMessage;
    }

    public void sendMessage(){
        SnuFoodMenuCrawling snu = new SnuFoodMenuCrawling();
        SlackAlterService alertService = new SlackAlterService();
        //String lunchMessage = snu.getLunch(snu.process());
        String DinnerMessage = snu.getDinner(snu.process());
        //log.info(lunchMessage);
        log.info(DinnerMessage);
        alertService.slackSendMessage(DinnerMessage);
    }
}