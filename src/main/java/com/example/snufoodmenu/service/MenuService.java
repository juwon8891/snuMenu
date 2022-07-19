package com.example.snufoodmenu.service;

import com.example.snufoodmenu.response.Menu;
import com.example.snufoodmenu.user.SnuRestaurant;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@NoArgsConstructor
public class MenuService {

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
    public void wantMenu(){
        log.info("식당 메뉴 조회");
        this.getLunch(process());
    }
    public void getLunch(Document document) {
        List<String> list = new ArrayList<>();
        String menuMessage = "";
        Elements lunch = document.getElementsByClass("views-field views-field-field-lunch");
        SnuRestaurant snuRestaurant = new SnuRestaurant();
        List<String> menu = snuRestaurant.getSnuRestaurant();
        for(int i=0; i<=2; i++){
            Element element = lunch.get(i);
            menuMessage += element.text() + "\n" + menu.get(i) + "\n";
        }
        menuMessage = menuMessage.trim();
        log.info("[Lunch] = " + menuMessage);
        Menu.setMenu(menuMessage);
    }
    public void getDinner(Document document){
        List<String> list = new ArrayList<>();
        String menuMessage = "";
        Elements dinner = document.getElementsByClass("views-field views-field-field-dinner");
        SnuRestaurant snuRestaurant = new SnuRestaurant();
        List<String> restaurant = snuRestaurant.getSnuRestaurant();
        for(int i=0; i<=3; i++){
            Element element = dinner.get(i);
            menuMessage += element.text() + "\n" + restaurant.get(i) + "\n";
        }
        menuMessage = menuMessage.trim();//
        log.info("[Dinner] = " + menuMessage);
        Menu.setMenu(menuMessage);
    }
}