package cn.meeler.test.controller.pictransf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class PictureTransform {

    @RequestMapping("transf")
    public void picTransf2Any(@RequestParam("geshi")String type,@RequestParam("quality")String quality,@RequestParam("suofang")String suofang, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletResponse response) throws IOException {
        FileUtils.transfPicToAnyType(file,type,suofang,quality,response);
    }
    @RequestMapping("/test1")
    public void picTransf2AnyTest(HttpServletResponse response) throws IOException {
        FileUtils.transfPicToAnyType1(response);
    }
}
