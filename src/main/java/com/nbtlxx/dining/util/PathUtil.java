package com.nbtlxx.dining.util;

/**
 * Created by chenxu on 17/12/29.
 */
public class PathUtil {
    private static String seperator = System.getProperty("file.separator");
    private static String imageBasePath;

    public static String getShopImagePath(Integer shopId) {
        String imagePath = "/upload/images/item/shop/"+shopId+"/";
        return imagePath.replace("/",seperator);
    }

    public static String getImageBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/projectdev/image";
        } else {
            basePath = "/Users/chenxu/Documents/work/image/";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }
}
