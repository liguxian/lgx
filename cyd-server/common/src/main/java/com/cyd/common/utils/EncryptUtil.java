package com.cyd.common.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@UtilityClass
public class EncryptUtil {

    private byte[] Keys = {0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};
    public String KeyDES = "87654321";

    /// <summary>
    /// DES加密字符串
    /// </summary>
    /// <param name="encryptString">待加密的字符串</param>
    /// <param name="encryptKey">加密密钥,要求为8位</param>
    /// <returns>加密成功返回加密后的字符串，失败返回源串</returns>
//    public String EncryptDES(String encryptString, String encryptKey) {
//        try {
//            byte[] rgbKey = Encoding.UTF8.GetBytes(encryptKey.Substring(0, 8));
//            byte[] rgbIV = Keys;
//            byte[] inputByteArray = Encoding.UTF8.GetBytes(encryptString);
//            DESCryptoServiceProvider dCSP = new DESCryptoServiceProvider();
//            MemoryStream mStream = new MemoryStream();
//            CryptoStream cStream = new CryptoStream(mStream, dCSP.CreateEncryptor(rgbKey, rgbIV), CryptoStreamMode.Write);
//            cStream.Write(inputByteArray, 0, inputByteArray.Length);
//            cStream.FlushFinalBlock();
//            return Convert.ToBase64String(mStream.ToArray());
//        } catch
//        {
//            return encryptString;
//        }
//    }
//
//    /// <summary>
//    /// DES解密字符串
//    /// </summary>
//    /// <param name="decryptString">待解密的字符串</param>
//    /// <param name="decryptKey">解密密钥,要求为8位,和加密密钥相同</param>
//    /// <returns>解密成功返回解密后的字符串，失败返源串</returns>
//    public String DecryptDES(String decryptString, String decryptKey) {
//        try {
//            byte[] rgbKey = Encoding.UTF8.GetBytes(decryptKey.Substring(0, 8));
//            byte[] rgbIV = Keys;
//            byte[] inputByteArray = Convert.FromBase64String(decryptString);
//            DESCryptoServiceProvider DCSP = new DESCryptoServiceProvider();
//            MemoryStream mStream = new MemoryStream();
//            CryptoStream cStream = new CryptoStream(mStream, DCSP.CreateDecryptor(rgbKey, rgbIV), CryptoStreamMode.Write);
//            cStream.Write(inputByteArray, 0, inputByteArray.Length);
//            cStream.FlushFinalBlock();
//            return Encoding.UTF8.GetString(mStream.ToArray());
//        } catch
//        {
//            return decryptString;
//        }
//    }
//
//    /// <summary>
//    /// Sha1加密
//    /// </summary>
//    /// <param name="value">value</param>
//    /// <returns></returns>
//    public String Sha1(String value) {
//        if (String.IsNullOrEmpty(value)) {
//            return null;
//        }
//
//        System.Security.Cryptography.SHA1 sha1 = new System.Security.Cryptography.SHA1CryptoServiceProvider();
//        byte[] bytResult = sha1.ComputeHash(System.Text.Encoding.Default.GetBytes(value));
//        String strResult = BitConverter.ToString(bytResult);
//        strResult = strResult.Replace("-", "");
//        return strResult;
//
//    }

//    / <summary>
//    / Md5加密
//    / </summary>
//    / <param name="value">value</param>
//    / <returns></returns>
    public String Md5(String str) {
        // 加密后的16进制字符串
        String hexStr = "";
        try {

            // 此 MessageDigest 类为应用程序提供信息摘要算法的功能
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 转换为MD5码
            byte[] digest = md5.digest(str.getBytes(StandardCharsets.UTF_8));
            hexStr = EncryptUtil.toHexString(digest);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return hexStr;
    }

    // 将字节数组转换为十六进制字符串
    public static String toHexString(byte[] bytes) {
        return Hex.encodeHexString(bytes);
    }

    // 将十六进制字符串转换为字节数组
    public static byte[] fromHexString(String hexString) {
        try {
            return Hex.decodeHex(hexString.toCharArray());
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert hex string to byte array", e);
        }
    }

    /// <summary>
    /// Md5加密
    /// </summary>
    /// <param name="value">value</param>
    /// <param name="Is16">是否16位密码</param>
    /// <returns></returns>
//    public String Md5(String value, Boolean Is16) {
//        if (StrUtil.isBlank(value)) {
//            return null;
//        }
//        if (Is16) {
//
//            return value.Md5().Substring(8, 16);
//        } else {
//            return value.Md5();
//        }
//    }
}
