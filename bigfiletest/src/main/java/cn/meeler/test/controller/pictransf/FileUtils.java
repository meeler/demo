package cn.meeler.test.controller.pictransf;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * 文件格式转换的工具类
 */
public class FileUtils {
    private static Map<String,String> imageTypeMap = null;
    private static Set<String> validTypes = null;

    static {
        String[] readerFormatNames = ImageIO.getReaderFormatNames();
        validTypes = new HashSet<String>(Arrays.asList(readerFormatNames));
        imageTypeMap = new HashMap<String, String>();
        imageTypeMap.put(".tif","image/tiff");
        imageTypeMap.put(".fax","image/fax");
        imageTypeMap.put(".gif","image/gif");
        imageTypeMap.put(".ico","image/x-icon");
        imageTypeMap.put(".jfif","image/jpeg");
        imageTypeMap.put(".jpe","image/jpeg");
        imageTypeMap.put(".jpeg","image/jpeg");
        imageTypeMap.put(".jpg","image/jpeg");
        imageTypeMap.put(".net","image/pnetvue");
        imageTypeMap.put(".png","image/png");
        imageTypeMap.put(".rp","image/vnd.rn-realpix");
        imageTypeMap.put(".tif","image/tiff");
        imageTypeMap.put(".tiff","image/tiff");
        imageTypeMap.put(".wbmp","image/vnd.wap.wbmp");
        imageTypeMap.put(".webp","image/webp");
    }

    /**
     * 将图片文件转成任意格式（支持格式的类型）
     * 前端有坑不能显示tif文件
     * @param file
     * @param type
     * @param suofang
     * @param quality
     * @param resp
     * @return
     */
    public static void transfPicToAnyType(MultipartFile file, String type, String suofang, String quality, HttpServletResponse resp) throws IOException {
        String[] readerFormatNames = ImageIO.getReaderFormatNames();
        List<String> strings = Arrays.asList(readerFormatNames);
        System.out.println(strings);
        String originalFilename = file.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        //不管是原来的格式还是要转的格式都必须包含在ImageIO中 否则格式转换不会成功
//        if (!validTypes.contains(ext)||!validTypes.contains(type)||!imageTypeMap.containsKey("."+type.toLowerCase())){
//            return;
//        }
//        resp.setContentType(imageTypeMap.get("."+type.toLowerCase()));
        OutputStream os = resp.getOutputStream();
        try {
            InputStream inputStream = file.getInputStream();
            BufferedImage read = ImageIO.read(inputStream);
            int width = read.getWidth();
            int height = read.getHeight();
            width = parseDoubleToInt(width * Double.parseDouble(suofang));
            height = parseDoubleToInt(height * Double.parseDouble(suofang));
            Image image = read.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            BufferedImage outputImage = new BufferedImage(width, height ,BufferedImage.TYPE_INT_RGB);
            Graphics graphics = outputImage.getGraphics();
            graphics.drawImage(image, 0, 0, null);
            graphics.dispose();
            RenderedImage renderedImage = compressPic(outputImage, quality);
            ImageIO.write(renderedImage,type,os);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            os.flush();
            os.close();
        }
    }
    public static RenderedImage compressPic(BufferedImage bufferedImage, String quality) {
        File file = null;
        ImageWriter imgWrier;
        ImageWriteParam imgWriteParams;
        // 指定写图片的方式为 jpg
        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
        imgWriteParams = new JPEGImageWriteParam(null);
        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
        // 这里指定压缩的程度，参数qality是取值0~1范围内，
        imgWriteParams.setCompressionQuality(Float.parseFloat(quality));
        imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
        ColorModel colorModel = ColorModel.getRGBdefault();
        // 指定压缩时使用的色彩模式
        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));
        try {
            imgWrier.reset();
                // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
                // OutputStream构造
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imgWrier.setOutput(ImageIO.createImageOutputStream(baos));
                // 调用write方法，就可以向输入流写图片
            imgWrier.write(null, new IIOImage(bufferedImage, null, null), imgWriteParams);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            BufferedImage read = ImageIO.read(is);
            return read;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void transfPicToAnyType1(HttpServletResponse response) throws IOException {
        File file = new File("D:/tupian/TIF001.jpg");
        BufferedImage read = ImageIO.read(file);
        ImageIO.write(read,"png",response.getOutputStream());
    }
    /**
     * 将double类型的数据转换为int，四舍五入原则
     *
     * @param sourceDouble
     * @return
     */
    private static int parseDoubleToInt(double sourceDouble) {
        int result = 0;
        result = (int) sourceDouble;
        return result;
    }
}
