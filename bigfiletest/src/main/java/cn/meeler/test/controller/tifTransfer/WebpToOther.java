package cn.meeler.test.controller.tifTransfer;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WebpToOther {
    public void webp2Other(String path,String exportPath) throws IOException {
        File file1= new File(path);
        boolean exists = file1.exists();
        File file2= new File(exportPath);
        try {
            BufferedImage read = ImageIO.read(file1);
            ImageIO.write(read, "tif", file2);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }

//    将训练的图片转成tif
    public static void trans(){
        File[] jpgImage = getJPGImage("D:/verifycode");
        List<File> list = Arrays.asList(jpgImage);
        for (File file : list) {
            String name = file.getName();
            String substring = name.substring(name.indexOf(".")-1, name.indexOf("."));
            System.out.println(substring);
//            File file2= new File("D:/verifycode/"+substring+".tif");
//            try {
//                BufferedImage read = ImageIO.read(file);
//                ImageIO.write(read, "jpg", file2);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }finally {
//
//            }
        }
    }

    private static File[] getJPGImage(String s) {
        File targetFolder = new File(s);
        return targetFolder.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return false;
                }
                return true;
            }
        });
    }


    public static void main(String[] args) throws IOException {
//        String[] ss = ImageIO.getReaderFormatNames();
//        System.out.println(Arrays.asList(ss));
//        WebpToOther webpToOther = new WebpToOther();
//        webpToOther.webp2Other("D:/tupian/cherry.webp","D:/tupian/cheery.jpg");
//        webpToOther.webp2Other("D:/tupian/aaa.jpg","D:/tupian/TIF001.ico");
//        boolean b = webpToOther.compressPic("D:/tupian/aaa.jpg", "D:/tupian/bbb.jpg","0.1");
//        Thumbnails.of(new File("D:/tupian/aaa.jpg")).scale(1f).outputQuality(0.1).toFile(new File("D:/tupian/ccc.jpg"));
        //        System.out.println(b);

        trans();
    }
}
