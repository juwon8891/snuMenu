package com.example.snufoodmenu.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SnuRestaurant{
    public ArrayList<String> restaurant;
    public List<String> getSnuRestaurant(){
        restaurant = new ArrayList<>(Arrays.asList(
                "학생회관식당",
                "자하연식당",
                "예술계식당",
                "라운지오",
                "두레미담",
                "동원관식당",
                "기숙사식당",
                "공대간이식당",
                "3식당",
                "302동식당",
                "301동식당",
                "220동식당",
                ""));
        return restaurant;
    }
}
