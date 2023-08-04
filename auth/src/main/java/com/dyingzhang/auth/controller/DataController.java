package com.dyingzhang.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DyingZhang
 * @date 2023-08-04 下午 5:04
 * @discription
 */
@RestController
public class DataController {

    @GetMapping("/data")
    public String data() {
        return "data";
    }
}
