package com.td.admin.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

//import com.quqian.framework.config.Envionment;
//import com.quqian.util.filter.AbstractFilter;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.regex.Pattern;

public class StringHelper {
    private static final char[] HEXES = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final Pattern REPLACE_VARIABLE_PATTERN = Pattern.compile("\\$\\{\\s*(\\w|\\.|-|_|\\$)+\\s*\\}", 2);
    public static final String EMPTY_STRING = "";
    private static String[] SAFELESS = new String[]{"<script", "</script", "<iframe", "</iframe", "<frame", "</frame", "set-cookie", "%3cscript", "%3c/script", "%3ciframe", "%3c/iframe", "%3cframe", "%3c/frame", "src=\"javascript:", "<body", "</body", "%3cbody", "%3c/body"};
    static final char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final String UTF8 = "utf-8";

    public StringHelper() {
    }

    public static String trim(String value) {
        return value == null ? null : value.trim();
    }

    public static boolean isEmpty(String value) {
        int length;
        if (value != null && (length = value.length()) != 0) {
            for(int index = 0; index < length; ++index) {
                char ch = value.charAt(index);
                if (ch != ' ' && ch != 160 && !Character.isISOControl(ch)) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

//    public static void filter(AbstractFilter writer, String str) throws IOException {
//        if (!isEmpty(str)) {
//            writer.append(str);
//        }
//    }

    public static void filterHTML(Appendable writer, String str) throws IOException {
        if (!isEmpty(str)) {
            for(int i = 0; i < str.length(); ++i) {
                char ch = str.charAt(i);
                if (!Character.isISOControl(ch)) {
                    switch(ch) {
                        case '"':
                        case '&':
                        case '\'':
                        case '<':
                        case '>':
                            writer.append("&#");
                            writer.append(Integer.toString(ch));
                            writer.append(';');
                            break;
                        default:
                            writer.append(ch);
                    }
                }
            }

        }
    }

    public static void filterQuoter(Appendable writer, String str) throws IOException {
        if (!isEmpty(str)) {
            for(int i = 0; i < str.length(); ++i) {
                char ch = str.charAt(i);
                if (ch == '"') {
                    writer.append('\\');
                }

                writer.append(ch);
            }

        }
    }

    public static void filterSingleQuoter(Appendable writer, String str) throws IOException {
        if (!isEmpty(str)) {
            for(int i = 0; i < str.length(); ++i) {
                char ch = str.charAt(i);
                if (ch == '\'') {
                    writer.append('\\');
                }

                writer.append(ch);
            }

        }
    }

    public static String digest(String content) throws Throwable {
        if (isEmpty(content)) {
            return content;
        } else {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] ciphertext = digest.digest(content.getBytes());
            char[] chars = new char[ciphertext.length + ciphertext.length];
            int i = 0;
            byte[] var5 = ciphertext;
            int var6 = ciphertext.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                byte element = var5[var7];
                chars[i++] = HEXES[element & 15];
                chars[i++] = HEXES[element >> 4 & 15];
            }

            return new String(chars);
        }
    }

    public static boolean formatChikcZr(String content) {
        if (null != content && content.length() > 0) {
            String[] var1 = SAFELESS;
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                String s = var1[var3];
                if (content.toLowerCase().contains(s)) {
                    return false;
                }
            }
        }

        return true;
    }

//    public static String format(String pattern, Envionment envionment) {
//        if (envionment != null && !isEmpty(pattern)) {
//            StringBuilder builder = new StringBuilder();
//
//            try {
//                format(builder, pattern, envionment);
//            } catch (IOException var4) {
//                ;
//            }
//
//            return builder.toString();
//        } else {
//            return pattern;
//        }
//    }

//    public static void format(Appendable out, String pattern, Envionment envionment) throws IOException {
//        if (out != null && envionment != null && !isEmpty(pattern)) {
//            format(out, pattern, envionment, (Set)null);
//        }
//    }

//    private static final void format(Appendable out, String pattern, Envionment envionment, Set<String> loadedKeys) throws IOException {
//        Matcher matcher = REPLACE_VARIABLE_PATTERN.matcher(pattern);
//        int startIndex = 0;
//
//        int endIndex;
//        for(boolean var6 = false; matcher.find(); startIndex = matcher.end()) {
//            endIndex = matcher.start();
//            if (endIndex != startIndex) {
//                out.append(pattern, startIndex, endIndex);
//            }
//
//            String key = matcher.group();
//            key = key.substring(2, key.length() - 1).trim();
//            if (loadedKeys == null) {
//                loadedKeys = new HashSet();
//            }
//
//            if (!((Set)loadedKeys).contains(key)) {
//                String value = envionment.get(key);
//                if (value != null) {
//                    Set<String> set = new HashSet((Collection)loadedKeys);
//                    set.add(key);
//                    format(out, value, envionment, set);
//                }
//            }
//        }
//
//        endIndex = pattern.length();
//        if (startIndex < endIndex) {
//            out.append(pattern, startIndex, endIndex);
//        }
//
//    }

    public static String truncation(String string, int maxLength) {
        if (isEmpty(string)) {
            return "";
        } else {
            try {
                StringBuilder out = new StringBuilder();
                truncation(out, string, maxLength, (String)null);
                return out.toString();
            } catch (IOException var3) {
                return "";
            }
        }
    }

    public static String truncation(String string, int maxLength, String replace) {
        if (isEmpty(string)) {
            return "";
        } else {
            try {
                StringBuilder out = new StringBuilder();
                truncation(out, string, maxLength, replace);
                return out.toString();
            } catch (IOException var4) {
                return "";
            }
        }
    }

    public static void truncation(Appendable out, String string, int maxLength) throws IOException {
        truncation(out, string, maxLength, (String)null);
    }

    public static void truncation(Appendable out, String string, int maxLength, String replace) throws IOException {
        if (!isEmpty(string) && maxLength > 0) {
            if (isEmpty(replace)) {
                replace = "...";
            }

            int index = 0;

            for(int end = Math.min(string.length(), maxLength); index < end; ++index) {
                out.append(string.charAt(index));
            }

            if (string.length() > maxLength) {
                out.append(replace);
            }

        }
    }

    public static void truncationMpno(Appendable out, String string) throws IOException {
        if (!isEmpty(string)) {
            String replace;
            if (string.length() == 11 && string.indexOf(64) == -1) {
                replace = "****";
                int index = 0;

                for(int end = Math.min(string.length(), 3); index < end; ++index) {
                    out.append(string.charAt(index));
                }

                out.append(replace);
                out.append(string.substring(7, 11));
            } else {
                replace = string.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
                out.append(replace);
            }

        }
    }

    public static String truncation(String string) throws IOException {
        StringBuilder s = new StringBuilder();
        if (!isEmpty(string) && string.length() == 11) {
            String replace = "****";
            int index = 0;

            for(int end = Math.min(string.length(), 3); index < end; ++index) {
                s.append(string.charAt(index));
            }

            s.append(replace);
            s.append(string.substring(7, 11));
            return s.toString();
        } else {
            return "";
        }
    }

    public static String arabia2CN(String num) {
        for(int i = num.length() - 1; i >= 0; --i) {
            num = num.replaceAll(",", "");
            num = num.replaceAll(" ", "");
        }

        num = num.replaceAll("￥", "");

        try {
            Double.parseDouble(num);
        } catch (Exception var6) {
            return "请检查小写金额是否正确";
        }

        String[] part = num.split("\\.");
        String newchar = "";

        int i;
        String tmpnewchar;
        char perchar;
        for(i = part[0].length() - 1; i >= 0; --i) {
            if (part[0].length() > 10) {
                return "位数过大，无法计算";
            }

            tmpnewchar = "";
            perchar = part[0].charAt(i);
            switch(perchar) {
                case '0':
                    tmpnewchar = "零" + tmpnewchar;
                    break;
                case '1':
                    tmpnewchar = "壹" + tmpnewchar;
                    break;
                case '2':
                    tmpnewchar = "贰" + tmpnewchar;
                    break;
                case '3':
                    tmpnewchar = "叁" + tmpnewchar;
                    break;
                case '4':
                    tmpnewchar = "肆" + tmpnewchar;
                    break;
                case '5':
                    tmpnewchar = "伍" + tmpnewchar;
                    break;
                case '6':
                    tmpnewchar = "陆" + tmpnewchar;
                    break;
                case '7':
                    tmpnewchar = "柒" + tmpnewchar;
                    break;
                case '8':
                    tmpnewchar = "捌" + tmpnewchar;
                    break;
                case '9':
                    tmpnewchar = "玖" + tmpnewchar;
            }

            switch(part[0].length() - i - 1) {
                case 0:
                    tmpnewchar = tmpnewchar + "元";
                    break;
                case 1:
                    if (perchar != 0) {
                        tmpnewchar = tmpnewchar + "拾";
                    }
                    break;
                case 2:
                    if (perchar != 0) {
                        tmpnewchar = tmpnewchar + "佰";
                    }
                    break;
                case 3:
                    if (perchar != 0) {
                        tmpnewchar = tmpnewchar + "仟";
                    }
                    break;
                case 4:
                    tmpnewchar = tmpnewchar + "万";
                    break;
                case 5:
                    if (perchar != 0) {
                        tmpnewchar = tmpnewchar + "拾";
                    }
                    break;
                case 6:
                    if (perchar != 0) {
                        tmpnewchar = tmpnewchar + "佰";
                    }
                    break;
                case 7:
                    if (perchar != 0) {
                        tmpnewchar = tmpnewchar + "仟";
                    }
                    break;
                case 8:
                    tmpnewchar = tmpnewchar + "亿";
                    break;
                case 9:
                    tmpnewchar = tmpnewchar + "拾";
            }

            newchar = tmpnewchar + newchar;
        }

        if (num.indexOf(".") != -1) {
            if (part[1].length() > 2) {
                part[1] = part[1].substring(0, 2);
            }

            for(i = 0; i < part[1].length(); ++i) {
                tmpnewchar = "";
                perchar = part[1].charAt(i);
                switch(perchar) {
                    case '0':
                        tmpnewchar = "零" + tmpnewchar;
                        break;
                    case '1':
                        tmpnewchar = "壹" + tmpnewchar;
                        break;
                    case '2':
                        tmpnewchar = "贰" + tmpnewchar;
                        break;
                    case '3':
                        tmpnewchar = "叁" + tmpnewchar;
                        break;
                    case '4':
                        tmpnewchar = "肆" + tmpnewchar;
                        break;
                    case '5':
                        tmpnewchar = "伍" + tmpnewchar;
                        break;
                    case '6':
                        tmpnewchar = "陆" + tmpnewchar;
                        break;
                    case '7':
                        tmpnewchar = "柒" + tmpnewchar;
                        break;
                    case '8':
                        tmpnewchar = "捌" + tmpnewchar;
                        break;
                    case '9':
                        tmpnewchar = "玖" + tmpnewchar;
                }

                if (i == 0) {
                    tmpnewchar = tmpnewchar + "角";
                }

                if (i == 1) {
                    tmpnewchar = tmpnewchar + "分";
                }

                newchar = newchar + tmpnewchar;
            }
        }

        while(newchar.indexOf("零零") != -1) {
            newchar = newchar.replaceAll("零零", "零");
        }

        newchar = newchar.replaceAll("零亿", "亿");
        newchar = newchar.replaceAll("亿万", "亿");
        newchar = newchar.replaceAll("零万", "万");
        newchar = newchar.replaceAll("零佰", "");
        newchar = newchar.replaceAll("零元", "元");
        newchar = newchar.replaceAll("零角", "");
        newchar = newchar.replaceAll("零分", "");
        newchar = newchar.replaceAll("零拾", "零");
        newchar = newchar.replaceAll("零元", "元");
        if (newchar.charAt(newchar.length() - 1) == 20803 || newchar.charAt(newchar.length() - 1) == '角') {
            newchar = newchar + "整";
        }

        return newchar;
    }

    public static String toUnsignedString(BigDecimal bigDecimal, int shift) {
        BigDecimal divisor = new BigDecimal(shift);
        StringBuilder builder = new StringBuilder();

        do {
            BigDecimal[] ba = bigDecimal.divideAndRemainder(divisor);
            bigDecimal = ba[0];
            builder.append(digits[ba[1].intValue()]);
        } while(bigDecimal.compareTo(BigDecimal.ZERO) > 0);

        return builder.reverse().toString();
    }

}

