package cn.meeler.test.http;

import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.Base64;

@SuppressWarnings("restriction")
public class ImageUtil {
    /**
     * summary:将字符串存储为文件 采用Base64解码
     */
	public static void streamSaveAsFile(InputStream is, String outFileStr) {
        FileOutputStream fos = null;
        try {
            File file = new File(outFileStr);
            BASE64Decoder decoder = new BASE64Decoder();
            fos = new FileOutputStream(file);
            byte[] buffer = decoder.decodeBuffer(is);
            fos.write(buffer, 0, buffer.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
                fos.close();
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RuntimeException(e2);
            }
        }
    }

    /**
     * summary:将Base64字符串存储为文件
     */
    public static void stringSaveAsFile(String fileStr, String outFilePath) {
        InputStream out = new ByteArrayInputStream(fileStr.getBytes());
        ImageUtil.streamSaveAsFile(out, outFilePath);
    }

    /**
     * 将流转换成字符串 使用Base64加密
     */
    public static String streamToBasement4Str(InputStream inputStream) throws IOException {
        byte[] bt = toByteArray(inputStream);
        inputStream.close();
        String out = Base64.getEncoder().encodeToString(bt);
        return out;
    }

    /**
     * 将流转换成字符串
     */
    public static String fileToString(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream is = new FileInputStream(file);
        String fileStr = ImageUtil.streamToBasement4Str(is);
        return fileStr;
    }

    /**
     * summary:将流转化为字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        byte[] result = null;
        try {
            int n = 0;
            while ((n = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            result = out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
        return result;

    }

    /**
     * 将base64转成临时文件，并返回临时文件的路径
     *
     * @param encode
     * @param originalName
     * @return
     */
    public static String Base64ToFile(String encode, String originalName) {
        String temppath = "./temp/" + originalName;
        byte[] decode = Base64.getDecoder().decode(encode);
        File file = new File(temppath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(decode);
            return temppath;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (bos != null) {
                try {
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
