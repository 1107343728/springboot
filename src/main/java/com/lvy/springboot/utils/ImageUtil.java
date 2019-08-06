package com.lvy.springboot.utils;


import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 给图片加水印
 */
public final class ImageUtil {
    /**
     * 缺省字体名
     */
    private static final String FONT_NAME_DEFAULT = "黑体";
    /**
     * 缺省字体风格
     */
    private static final int FONT_STYLE_DEFAULT = Font.BOLD + Font.ITALIC;
    /**
     * 缺省字体大小
     */
    private static final int FONT_SIZE_DEFAULT = 20;
    /**
     * 缺省位置
     */
    private static final Location LOCATION_DEFAULT = ImageUtil.Location.RIGHT_BOTTOM;
    /**
     * 缺省颜色
     */
    private static final Color COLOR_DEFAULT = Color.WHITE;
    /**
     * 缺省透明度
     */
    private static final float ALPHA_DEFAULT = 1f;

    /**
     * 给图片加文字水印
     *
     * @param inputStream 待加水印的图片输入流
     * @param destImgPath 加水印后的图片地址
     * @param pressText 水印文字
     */
    public static void pressText(InputStream inputStream, String destImgPath,String pressText) throws Exception {
        //校验必须的参数是否为空
        if(inputStream == null || StringUtils.isBlank(destImgPath) || StringUtils.isBlank(pressText)) {
            throw new Exception("必须参数:｛srcImgPath、destImgPath、pressText｝不能为空");
        }
        try {
            pressText(inputStream,destImgPath,pressText,FONT_NAME_DEFAULT,FONT_STYLE_DEFAULT,FONT_SIZE_DEFAULT,LOCATION_DEFAULT,COLOR_DEFAULT,ALPHA_DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 给图片加文字水印
     *
     * @param srcImgPath 待加水印的图片地址
     * @param destImgPath 加水印后的图片地址
     * @param pressText 水印文字
     */
    public static void pressText(String srcImgPath, String destImgPath,String pressText) throws Exception {
        //校验必须的参数是否为空
        if(StringUtils.isBlank(srcImgPath) || StringUtils.isBlank(destImgPath) || StringUtils.isBlank(pressText)) {
            throw new Exception("必须参数:｛srcImgPath、destImgPath、pressText｝不能为空");
        }
        try {
            InputStream inputStream = new FileInputStream(new File(srcImgPath));
            pressText(inputStream,destImgPath,pressText,FONT_NAME_DEFAULT,FONT_STYLE_DEFAULT,FONT_SIZE_DEFAULT,LOCATION_DEFAULT,COLOR_DEFAULT,ALPHA_DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 给图片加文字水印
     *
     * @param srcImgPath 待加水印的图片地址
     * @param destImgPath 加水印后的图片地址
     * @param pressText 水印文字
     * @param fontName  字体名称
     * @param fontStyle 字体风格
     * @param fontSize  字体大小
     * @param location  字体位置
     * @param color  颜色
     * @param alpha  透明度
     */
    public static void pressText(String srcImgPath, String destImgPath,String pressText, String fontName, int fontStyle,
                                 int fontSize, Location location,Color color, float alpha) throws Exception {
        if(StringUtils.isNotBlank(srcImgPath)) {
            InputStream inputStream = new FileInputStream(new File(srcImgPath));
            pressText(inputStream,destImgPath,pressText,fontName,fontStyle,fontSize,location,color,alpha);
        }

    }

    /**
     * 给图片加文字水印
     *
     * @param inputStream 待加水印的图片输入流
     * @param destImgPath 加水印后的图片地址
     * @param pressText 水印文字
     * @param fontName  字体名称
     * @param fontStyle 字体风格
     * @param fontSize  字体大小
     * @param location  字体位置
     * @param color  颜色
     * @param alpha  透明度
     */
    public static synchronized void pressText(InputStream inputStream, String destImgPath,String pressText, String fontName, int fontStyle,
                                              int fontSize, Location location,Color color, float alpha) throws Exception {
        //校验必须的参数是否为空
        if(inputStream == null || StringUtils.isBlank(destImgPath) || StringUtils.isBlank(pressText)) {
            throw new Exception("必须参数:｛srcImgPath、destImgPath、pressText｝不能为空");
        }
        //为空用默认字体
        if(StringUtils.isBlank(fontName)) {
            fontName = FONT_NAME_DEFAULT;
        }
        //为空用默认字体
        if(fontStyle == 0) {
            fontStyle = FONT_STYLE_DEFAULT;
        }
        //为空用默认字体大小
        if(fontSize == 0) {
            fontSize = FONT_SIZE_DEFAULT;
        }
        //为空用默认位置
        if(location == null) {
            location = LOCATION_DEFAULT;
        }
        //为空用默认颜色
        if(color == null) {
            color = COLOR_DEFAULT;
        }
        try {
            int textWidth = getFontWidth(fontName, fontStyle, fontSize, pressText);
            Image src = ImageIO.read(inputStream);
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //得到画笔
            Graphics2D g = image.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.drawImage(src.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            g.setColor(color);
            //设置字段
            g.setFont(new Font(fontName, fontStyle, fontSize));
            //设置透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            //位置x,y
            int x = 0, y = 0;
            //根据条件设置位置
            if(location.equals(Location.LEFT_TOP)) {
                x = 30;
                y = 30;
            }
            else if (location.equals(Location.RIGHT_TOP)) {
                x = width - textWidth - 30;
                y = 30;
            } else if (location.equals(Location.LEFT_BOTTOM)) {
                x += 30;
                y = height - 30;
            } else if (location.equals(Location.RIGHT_BOTTOM)) {
                x = width - textWidth - 30;
                y = height - 30;
            } else {
                x = (width - textWidth) / 2;
                y = (height) / 2;
            }
            g.drawString(pressText, x, y);
            g.dispose();

            //得到文件格式
            String formatName = getFileFormat(destImgPath);
            //待生成的文件
            File destFile = new File(destImgPath);
            //如果文件目录不存在则创建
            if(!destFile.exists()) {
                destFile.getParentFile().mkdirs();
            }
            //保存图片到指定路径
            ImageIO.write(image,formatName,new File(destImgPath));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 计算文本占用的width
     *
     * @param fontName  字体名称
     * @param fontStyle 字体风格
     * @param fontSize  字体大小
     * @param pressText 输入文本
     * @return 文字所占用的宽带
     */
    public static int getFontWidth(String fontName, int fontStyle, int fontSize, String pressText) {
        Font f = new Font(fontName, fontStyle, fontSize);
        FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
        return fm.stringWidth(pressText);
    }

    /**
     * 位置枚举
     */
    public enum Location {
        LEFT_TOP("leftTop","左上角"),
        RIGHT_TOP("rightTop","右上角"),
        LEFT_BOTTOM("leftBottom","左下角"),
        RIGHT_BOTTOM("rightBottom","右下角");
        Location(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private String code;

        private String desc;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 根据地址得到文件类型
     * @param path
     * @return
     */
    public static String getFileFormat(String path) {
        if(path == null) {
            return null;
        }
        int index = path.lastIndexOf(".");
        String fileFormat = path.substring(index + 1);
        return fileFormat;
    }

    public static void main(String[] args) {
        try {
            pressText("D:\\pictrue\\abcd.jpeg","D:\\pictrue\\abcd.jpeg", "深圳罗湖口岸20190801161258", "黑体", Font.BOLD + Font.ITALIC, 20, Location.RIGHT_BOTTOM,Color.WHITE,1f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String tmpdir = System.getProperty("java.io.tmpdir");
        System.out.println("tmpdir = " + tmpdir);
    }
}