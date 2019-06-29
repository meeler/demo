package cn.meeler.test.consumerhi.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "provider-hi")
public interface HiServiceRemote {

    @GetMapping("hi")
    String sayHi(@RequestParam("name") String name);
}
