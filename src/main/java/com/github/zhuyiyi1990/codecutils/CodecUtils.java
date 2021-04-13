package com.github.zhuyiyi1990.codecutils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

public class CodecUtils {

    public static void main(String[] args) throws Exception {
        String filePath = "D:\\mars\\[Android开发视频教学].01_14_Handler的使用（一）.mp4";
        File file = new File(filePath);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        String md5 = DigestUtils.md5Hex(in);
        System.out.println("MD5: " + md5.toUpperCase());
        in.close();
        in = new BufferedInputStream(new FileInputStream(file));
        String sha1 = DigestUtils.sha1Hex(in);
        System.out.println("SHA1: " + sha1.toUpperCase());
        in.close();
        System.out.println(System.currentTimeMillis());
        System.out.println("CRC32: " + Long.toHexString(getCRC32(filePath)).toUpperCase());
        System.out.println(System.currentTimeMillis());
        System.out.println("CRC32: " + Long.toHexString(checksumBufferedInputStream(filePath)).toUpperCase());
        System.out.println(System.currentTimeMillis());
    }

    /**
     * 采用BufferedInputStream的方式加载文件
     */
    public static long checksumBufferedInputStream(String filepath) throws Exception {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(filepath));
        CRC32 crc = new CRC32();
        byte[] bytes = new byte[1024];
        int cnt;
        while ((cnt = inputStream.read(bytes)) != -1) {
            crc.update(bytes, 0, cnt);
        }
        inputStream.close();
        return crc.getValue();
    }

    /**
     * 使用CheckedInputStream计算CRC
     */
    public static Long getCRC32(String filepath) throws Exception {
        CRC32 crc32 = new CRC32();
        InputStream in = new BufferedInputStream(new FileInputStream(new File(filepath)));
        CheckedInputStream checkedinputstream = new CheckedInputStream(in, crc32);
        while (checkedinputstream.read() != -1) {
        }
        checkedinputstream.close();
        return crc32.getValue();
    }

}
