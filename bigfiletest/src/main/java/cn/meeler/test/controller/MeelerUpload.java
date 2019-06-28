package cn.meeler.test.controller;

import cn.meeler.test.controller.interrupt.ToLog;
import org.junit.runners.Parameterized;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/eee")
public class MeelerUpload {
    @ResponseBody
    @RequestMapping("/upload")
    public String uplaodBigFile(MultipartHttpServletRequest multiRequest) throws IOException {
        long s = System.currentTimeMillis();
//        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        Iterator<String> fileNames = multiRequest.getFileNames();
        while (fileNames.hasNext()){
            String fileName = fileNames.next();
            System.out.println(fileName);
            List<MultipartFile> files = multiRequest.getFiles(fileName);
            if (files.size()>0){
                Iterator<MultipartFile> fileIterator = files.iterator();
                while (fileIterator.hasNext()){
                    MultipartFile file = fileIterator.next();
                    String originalFilename = file.getOriginalFilename();
                    String path = "D:/testupload/";
                    File dir = new File(path);
                    if (!dir.exists()){
                        dir.mkdir();
                    }
                    String filePath = path+UUID.randomUUID().toString()+originalFilename;
                    System.out.println(filePath);
                    File dest = new File(filePath);
                    int available = file.getInputStream().available();
                    System.out.println(available);
                    if (!dest.exists()){
                        file.transferTo(dest);
                    }
                    String name = file.getName();
                    System.out.println(name);
                }
            }
        }
        long e = System.currentTimeMillis();
        long l = e - s;
        System.out.println(l);
        return "hello";
    }


    @ResponseBody
    @RequestMapping("/meeler")
    public ModelAndView test(Model model){
        Meeler meeler = new Meeler();
        meeler.setAge(18);
        meeler.setName("meeler");
        Map<String, Object> map = model.asMap();
        Object bbb = map.get("bbb");
        System.out.println(bbb);
        model.addAttribute("aaa","bbb");
        return new ModelAndView("/index.html","gaga",model);
//        return "hhhhh";
    }

    @ToLog
    @ResponseBody
    @RequestMapping("/meelertest")
    public String meeler(Model model){
        Meeler meeler = new Meeler();
        meeler.setAge(18);
        meeler.setName("meeler");
        model.addAttribute("bbb","ccc");
        return "hello world";
    }

    @ResponseBody
    @RequestMapping("/test1")
    public String test1(@RequestParam("id")Integer id,@RequestParam("name")String name){
        System.out.println(id);
        System.out.println(id);
        return "hello";
    }

}
