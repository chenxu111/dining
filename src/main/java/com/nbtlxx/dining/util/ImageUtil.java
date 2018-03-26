package com.nbtlxx.dining.util;

import com.nbtlxx.dining.common.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by chenxu on 17/12/29.
 */
public class ImageUtil {

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private static final SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random random =new Random();

    public static String generateThumbnail(ImageHolder imageHolder, String targetAddr) {
        //获取不重复随机名
        String realFileName = getRandomFileName();
        //获取文件扩展名
        String fileExtension= getFileExtension(imageHolder.getFileName());
        //创建文件夹
        makeDirPath(targetAddr);
        //获取文件相对路径
        String relativeAddr = targetAddr+realFileName+fileExtension;
        //获取文件保存的目标路径
        String path = PathUtil.getImageBasePath()+relativeAddr;
        File dest = new File(PathUtil.getImageBasePath()+relativeAddr);

        //调用thumbnails生成带水印的图片
        try {
            Thumbnails.of(imageHolder.getInputStream()).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
//            logger.error(e.toString());
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }


    private static String getRandomFileName() {
        int ranNum = random.nextInt(8889999)+10000;
        String nowTimeStr = simpleDateFormat.format(new Date());
        return nowTimeStr+ranNum;
    }

    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImageBasePath()+targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

}
