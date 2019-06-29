package cn.meeler.test.consumerhi.controller;

import cn.meeler.test.consumerhi.service.HiServiceRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Autowired
    HiServiceRemote hiServiceRemote;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        return hiServiceRemote.sayHi( name );
    }

}
