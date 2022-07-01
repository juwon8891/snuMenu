package com.example.snufoodmenu.controller;
import com.example.snufoodmenu.response.Menu;
import com.example.snufoodmenu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    @GetMapping("test")
    public String test(){
        return "test";
    }

    @GetMapping("/menu")
    public Menu checkMenu(){
        menuService.wantMenu();
        return new Menu();
    }
    @PostMapping("/menu")
    public ResponseEntity<String> challenge(@RequestBody SlackChallenge sc) {
        String created = sc.getChallenge();
        log.info("created: " + created);
        log.info(String.valueOf(ResponseEntity.status(HttpStatus.OK).body("challenge")));
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
