package com.example.snufoodmenu;

import com.example.snufoodmenu.crawling.SnuFoodMenuCrawling;

public class SnuFoodMenuApplication {
    public static void main(String[] args) {
        SnuFoodMenuCrawling food = new SnuFoodMenuCrawling();
        food.sendMessage();
    }
}