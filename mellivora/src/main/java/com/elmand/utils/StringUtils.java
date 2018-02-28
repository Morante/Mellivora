package com.elmand.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能：字符串相关的类
 * author: elmand6 on 2015/12/15 15:31
 * email：lysa8156@sina.com
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     *
     * @param s
     * @return 字符串是否为空
     */
    public static boolean isEmpty(String s) {
        boolean b = true;
        if (s != null && !s.trim().equals("")) {
            b = false;
        }
        return b;
    }

    /**
     * 判断是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 判断是否是邮箱
     *
     * @param strEmail
     * @return
     */
    public static boolean isEmail(String strEmail) {
        String strPattern = "^([a-z0-9A-Z_]+[-\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 判断密码匹配规则
     *
     * @param pwd
     * @return
     */
    public static boolean isPwdMatchRule(String pwd) {
        if (pwd == null)
            return false;
//		Pattern pattern = Pattern.compile("[A-Za-z0-9]{6,16}");
        Pattern pattern = Pattern.compile("^[\\x21-\\x7E]{6,16}$");//包含了键盘的所有特殊字符
        Matcher matcher = pattern.matcher(pwd);
        return matcher.matches();
    }

    /**
     * 判断名称匹配规则(建议使用isNickNameMatchRule)
     *
     * @param name
     * @return
     */
    public static boolean isNameMatchRule(String name) {
        if (name == null)
            return false;
        Pattern pattern = Pattern.compile("[A-Za-z0-9_]+");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    /**
     * 判断是否是中文
     *
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        return Pattern.matches("[\\u4e00-\\u9fa5]+", str);
    }

    /**
     * 得到中文数字个数
     *
     * @param str
     * @return
     */
    public static int getChineseCount(String str) {
        int count = 0;
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            for (int i = 0, size = m.groupCount(); i <= size; i++) {
                count = count + 1;
            }
        }
        return count;
    }

    /**
     * 判断 昵称匹配规则
     *
     * @param nickName
     * @return
     */
    public static boolean isNickNameMatchRule(String nickName) {
        int len = nickName.length();
        for (int i = 0; i < len; i++) {
            String str = nickName.substring(i, i + 1);
            if (isChinese(str) || isNameMatchRule(str)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否符合昵称的最小长度
     *
     * @param str
     * @param len 一个中文占2个字符
     * @return
     */
    public static boolean checkNickNameMinLen(String str, int len) {
        int chinaCount = getChineseCount(str);
        int otherCount = str.length() - chinaCount;
        if (otherCount + chinaCount * 2 < len)
            return false;
        return true;
    }

    /**
     * 判断是否符合昵称的最大长度
     *
     * @param str
     * @param len
     * @return
     */
    public static boolean checkNickNameMaxLen(String str, int len) {
        int chinaCount = getChineseCount(str);
        int otherCount = str.length() - chinaCount;
        if (otherCount + chinaCount * 2 > len)
            return false;
        return true;
    }

    /**
     * 检查是否是手机号
     *
     * @param phone
     * @return
     */
    public static boolean checkPhoneNumber(String phone) {
        String str = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
        Pattern p = Pattern.compile(str, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(phone);
        boolean isMatches = m.matches();
        return isMatches;
    }

    /**
     * md5 加密
     *
     * @param url
     * @return
     */
    public static String getMD5Str(String url) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] result = digest.digest(url.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : result) {
                int i = b & 0xff;// 将字节转为整数
                String hexString = Integer.toHexString(i);// 将整数转为16进制
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;// 如果长度等于1, 加0补位
                }
                sb.append(hexString);
            }
            return sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException ex) {
            return url;
        }
    }
}
