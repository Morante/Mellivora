package com.elmand.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.math.BigDecimal;

/**
 * SD卡相关的辅助类 ，内存大小单位格式化
 * author: elmand6 on 2015/12/15 11:04
 * email：lysa8156@sina.com
 */
public class SDCardUtils {
    private SDCardUtils() {
         /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * 获取SD卡的所有容量，单位byte
     *
     * @return
     */

    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs statFs = new StatFs(getSDCardPath());
            //获取所有的数据块的数量
            long availableBlocks = statFs.getBlockCountLong();
            //获取单个数据块的大小（byte)
            long freeBlocks = statFs.getBlockSizeLong();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * @return 获取SD卡的剩余可用容量，单位byte
     */
    public static long getSDCardFreeSize() {
        if (isSDCardEnable()) {
            StatFs statFs = new StatFs(getSDCardPath());
            //获取空闲的数据块的数量
            long availableBlocksLong = statFs.getAvailableBlocksLong();
            //获取单个数据块的大小（byte)
            long freeBlocks = statFs.getBlockSizeLong();
            return freeBlocks * availableBlocksLong;
        }
        return 0;
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

    /**
     * 获取系统存储路径
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }
}
