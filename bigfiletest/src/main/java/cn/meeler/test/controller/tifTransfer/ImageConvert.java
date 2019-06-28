package cn.meeler.test.controller.tifTransfer;

import com.sun.media.jai.codec.*;

import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

import javax.media.jai.*;

public class ImageConvert {

    private static Logger logger = Logger.getLogger(ImageConvert.class.getName());
    public void tif2Png(String fileAbsolutePath){

    }
    public void tif2Other(String fileAbsolutePath,String type) {
        if (fileAbsolutePath == null || "".equals(fileAbsolutePath.trim())){
            return ;
        }
        if (!new File(fileAbsolutePath).exists()){
            logger.info("系统找不到指定文件【"+fileAbsolutePath+"】");
            return ;
        }
        FileSeekableStream fileSeekStream = null;
        try {
            fileSeekStream = new FileSeekableStream(fileAbsolutePath);
            TIFFEncodeParam tiffEncodeParam = new TIFFEncodeParam();
            PNGEncodeParam pngEncodeParam = new PNGEncodeParam.RGB();
            JPEGEncodeParam jpegEncodeParam = new JPEGEncodeParam();
            ImageDecoder dec = ImageCodec.createImageDecoder("tiff", fileSeekStream, null);
            int count = dec.getNumPages();
            tiffEncodeParam.setCompression(TIFFEncodeParam.COMPRESSION_GROUP4);
            tiffEncodeParam.setLittleEndian(false);
            logger.info("该tif文件共有【" + count + "】页");
            String filePathPrefix = fileAbsolutePath.substring(0, fileAbsolutePath.lastIndexOf("."));
            for (int i = 0; i < count; i++) {
                RenderedImage renderedImage = dec.decodeAsRenderedImage(i);
                File imgFile = new File(filePathPrefix + "_" + i + ".jpg");
                File imgFile2 = new File(filePathPrefix + "_" + i + ".png");
                logger.info("每页分别保存至： " + imgFile.getCanonicalPath());
                ParameterBlock parameterBlock = new ParameterBlock();
                parameterBlock.addSource(renderedImage);
                parameterBlock.add(0.7f);
                parameterBlock.add(0.7f);
                parameterBlock.add(0.0f);
                parameterBlock.add(0.0f);
                RenderedOp img = JAI.create("scale",   parameterBlock);
                PlanarImage dst = img;
                ParameterBlock pb = new ParameterBlock();
                pb.addSource(dst);
                pb.add(imgFile.toString());
                pb.add("JPEG");
                pb.add(jpegEncodeParam);
                RenderedOp renderedOp = JAI.create("filestore",pb);
                renderedOp.dispose();

                ParameterBlock pbk = new ParameterBlock();
                pbk.addSource(renderedImage);
                pbk.add(imgFile2.toString());
                pbk.add("PNG");
                pbk.add(pngEncodeParam);
                RenderedOp renderedOp2 = JAI.create("filestore",pbk);
                renderedOp2.dispose();

          }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fileSeekStream != null){
                try {
                    fileSeekStream.close();
                } catch (IOException e) {
                }
                fileSeekStream = null;
            }
        }
    }

    public void jpg2Tif(String fileAbsolutePath) {
        OutputStream outputStream = null;
        try {
            RenderedOp renderOp = JAI.create("fileload", fileAbsolutePath);
            String tifFilePath = fileAbsolutePath.substring(0, fileAbsolutePath.lastIndexOf("."))+".tif";
            outputStream = new FileOutputStream(tifFilePath);
            TIFFEncodeParam tiffParam = new TIFFEncodeParam();
            ImageEncoder imageEncoder = ImageCodec.createImageEncoder("TIFF", outputStream, tiffParam);
            imageEncoder.encode(renderOp);
            logger.info("jpg2Tif 保存至： " + tifFilePath);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
                outputStream = null;
            }
        }
    }

    public static void main(String args[]) throws Exception{
        ImageConvert imageConvert = new ImageConvert();
        /* tif 转 jpg 格式*/
        imageConvert.tif2Other("D:/tupian/TIF001.tif","png");
        /* jpg 转 tif 格式*/
//        imageConvert.jpg2Tif("d:/1.jpg");
    }


}