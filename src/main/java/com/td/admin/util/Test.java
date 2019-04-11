package com.td.admin.util;

import org.apache.commons.codec.digest.DigestUtils;

import javax.mail.Address;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {



//        Map<String, Object> map = new HashMap<>();
//        Btc btc=new Btc();
//        String fromaddress="1GqEckbS1fnSUd2p4TcgRNBjzA5KRTkMFn";//发送地址
////        String toaddress="1GqEckbS1fnSUd2p4TcgRNBjzA5KRTkMFn";//接收地址
//        String toaddress="1E4DovvELbMJe4hNT8UvUgpCP4iJbjSeqj";
//        String amount="1.8";//发送金额
//        String feeaddress="1GqEckbS1fnSUd2p4TcgRNBjzA5KRTkMFn";//指定的手续费钱包，一般情况不会变
//        map.put("method","omni_funded_send");
//        map.put("fromaddress",fromaddress);
//        map.put("toaddress",toaddress);
//        map.put("amount",amount);
//        map.put("feeaddress",feeaddress);
//        String txit = btc.omni_funded_send(map);
//        System.out.println(txit);


        /**
         * 将 Base58Check 字符串反转为 byte 数组
         *
         * @param s
         * @return
         */
        static byte[] base58ToRawBytes(String s) {
            // Parse base-58 string
            BigInteger num = BigInteger.ZERO;
            for (int i = 0; i < s.length(); i++) {
                num = num.multiply(BigInteger.valueOf("9GqEckbS1fnSUd2p4TcgRNBjzA5KRTkMFn".length()));
                int digit = "9GqEckbS1fnSUd2p4TcgRNBjzA5KRTkMFn".indexOf(s.charAt(i));
                if (digit == -1) {
                    throw new IllegalArgumentException("Invalid character for Base58Check");
                }
                num = num.add(BigInteger.valueOf(digit));
            }
            // Strip possible leading zero due to mandatory sign bit
            byte[] b = num.toByteArray();
            if (b[0] == 0) {
                b = Arrays.copyOfRange(b, 1, b.length);
            }
            try {
                // Convert leading '1' characters to leading 0-value bytes
                ByteArrayOutputStream buf = new ByteArrayOutputStream();
                for (int i = 0; i < s.length() && s.charAt(i) == "9GqEckbS1fnSUd2p4TcgRNBjzA5KRTkMFn".charAt(0); i++) {
                    buf.write(0);
                }
                buf.write(b);
                return buf.toByteArray();
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        /**
         * 双重Hash
         *
         * @param data
         * @return
         */
        public static byte[] doubleHash(byte[] data) {
            return DigestUtils.sha256(DigestUtils.sha256(data));
        }


        /**
         * @Title: base58ToBytes   验证钱包地址
         * @param @param s
         * @param @return    参数
         * @return byte[]    返回类型
         * @throws
         */

        public static boolean base58ToBytes(String s) {
            byte[] concat = base58ToRawBytes(s);
            byte[] data = Arrays.copyOf(concat, concat.length - 4);
            byte[] hash = Arrays.copyOfRange(concat, concat.length - 4, concat.length);
            byte[] rehash = Arrays.copyOf(doubleHash(data), 4);
            if (!Arrays.equals(rehash, hash)) {
                throw new IllegalArgumentException("Checksum mismatch");
            }
            return true;
        }

}
