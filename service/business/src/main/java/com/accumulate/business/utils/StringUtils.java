/**
 * Drore Confidential
 * 
 * (c) Copyright Zhejiang Drore Technology Co.,Ltd. 2018
 * 
 * All rights reserved.
 */

package com.accumulate.business.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;


/**
 * 
 */
public class StringUtils {

    private static final char[] symbols = new char[36];
    private static final char[] digitSymbols = new char[10];
    private static final Random random = new Random(System.currentTimeMillis());
    private static final Pattern REMOVE_CTRL_CHARS_PATTERN = Pattern.compile("[\\p{Cntrl}&&[^\\r\\n\\t\\x7F]]");
    public static final String EMPTY_STRING = "";

    static {
        for (int idx = 0; idx < 10; ++idx) {
            symbols[idx] = (char) ('0' + idx);
            digitSymbols[idx] = (char) ('0' + idx);
        }
        for (int idx = 10; idx < 36; ++idx) {
            symbols[idx] = (char) ('a' + idx - 10);
        }
    }

    /**
     * 
     */
    private StringUtils() {
    }

    /**
     * @param value
     * @return - Integer value or null if the string cannot be converted to an
     *         Integer
     */
    public static Integer toInt(String value) {
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            // Eaten on purpose
        }
        return null;
    }

    /**
     * @param value
     * @return - Long value or null if the string cannot be converted to a Long
     */
    public static Long toLong(String value) {
        try {
            return Long.valueOf(value);
        } catch (Exception e) {
            // Eaten on purpose
        }
        return null;
    }

    /**
     * @param value
     * @return - Float value or null if the string cannot be converted to a Float
     */
    public static Float toFloat(String value) {
        try {
            return Float.valueOf(value);
        } catch (Exception e) {
            // Eaten on purpose
        }
        return null;
    }

    /**
     * @param value
     * @return - Double value or null if the string cannot be converted to a Double
     */
    public static Double toDouble(String value) {
        try {
            return Double.valueOf(value);
        } catch (Exception e) {
            // Eaten on purpose
        }
        return null;
    }

    /**
     * @param value
     * @return Boolean value or null if the string cannot be converted to a Boolean
     */
    public static Boolean toBoolean(String value) {
        try {
            return Boolean.valueOf(value);
        } catch (Exception e) {
            // Eaten on purpose
        }
        return null;
    }

    /**
     * @param value
     * @param dflt
     * @return Integer
     */
    public static int toInt(String value, int dflt) {
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            return dflt;
        }
    }

    /**
     * @param value
     * @param dflt
     * @return long
     */
    public static long toLong(String value, long dflt) {
        try {
            return Long.valueOf(value);
        } catch (Exception e) {
            return dflt;
        }
    }

    /**
     * @param value
     * @param dflt
     * @return float
     */
    public static float toFloat(String value, float dflt) {
        try {
            return Float.valueOf(value);
        } catch (Exception e) {
            return dflt;
        }
    }

    /**
     * @param value
     * @param dflt
     * @return double
     */
    public static double toDouble(String value, double dflt) {
        try {
            return Double.valueOf(value);
        } catch (Exception e) {
            return dflt;
        }
    }

    /**
     * @param value
     * @param dflt
     * @return boolean
     */
    public static boolean toBoolean(String value, boolean dflt) {
        try {
            return Boolean.valueOf(value);
        } catch (Exception e) {
            return dflt;
        }
    }

    /**
     * Splits a string value by ',', ';', ':' and returns a String[]
     * 
     * @param value
     * @return - Double value or null if the string cannot be converted to a Double
     */
    public static String[] toStringArray(String value) {
        int len = value.length();
        boolean quote = false;
        List<String> fields = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char c = value.charAt(i);
            switch (c) {
                case '\t':
                case ' ':
                    if (quote) {
                        token.append(c);
                    }
                    break;
                case '\'':
                case '\"':
                    quote = !quote;
                    break;
                case ',':
                case ';':
                case ':':
                case '|':
                case '/':
                case '\\':
                    if (!quote) {
                        fields.add(token.toString());
                        token = new StringBuilder();
                    } else {
                        token.append(c);
                    }
                    break;
                default:
                    token.append(c);
            }
        }
        if (token.length() > 0) {
            fields.add(token.toString());
        }
        return fields.toArray(new String[0]);
    }

    /**
     * @param values
     * @return String
     */
    public static String stringsToCommaSeparated(Collection<String> values) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String string : values) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(string);
            i++;
        }
        return sb.toString();
    }

    /**
     * @param length
     * @return String
     */
    public static String randomString(int length) {
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(symbols[rnd.nextInt(symbols.length)]);
        }
        return sb.toString();
    }

    /**
     * @param length
     * @return String
     */
    public static String randomNumber(int length) {
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(digitSymbols[rnd.nextInt(digitSymbols.length)]);
        }
        return sb.toString();
    }

    /**
     * @param value
     * @return String
     */
    public static String unquote(String value) {
        if (value == null) {
            return null;
        }
        int start = 0, end = value.length();
        if (start == end) {
            return value;
        }

        if (value.charAt(0) == '"') {
            start = 1;
        }
        if (start == 1 && value.charAt(value.length() - 1) == '"') {
            end = value.length() - 1;
            return value.substring(start, end);
        }
        return value;
    }

    /**
     * @param value
     * @return
     */
    public static String singleQuote(String value) {
        return quote(value, '\'');
    }

    /**
     * @param value
     * @return
     */
    public static String quote(String value) {
        return quote(value, '\"');
    }

    /**
     * @param value
     * @param c
     * @return
     */
    public static String quote(String value, char c) {
        if (value == null) {
            return value;
        }

        StringBuilder builder = new StringBuilder();

        if (value.charAt(0) != c) {
            builder.append(c);
        }
        builder.append(value);

        if (value.charAt(value.length() - 1) != c) {
            builder.append(c);
        }

        return builder.toString();
    }

    /**
     * @param arrayOne
     * @param arrayTwo
     * @return return String[]
     */
    public static String[] arrayConcat(String[] arrayOne, String[] arrayTwo) {
        String[] retVal = new String[arrayOne.length + arrayTwo.length];
        System.arraycopy(arrayOne, 0, retVal, 0, arrayOne.length);
        System.arraycopy(arrayTwo, 0, retVal, arrayOne.length, arrayTwo.length);
        return retVal;
    }

//    /**
//     * @param seq
//     * @param f
//     * @return
//     */
//    public static String removeFirstChars(String seq, Function1<Character, Boolean> f) {
//        StringBuilder sb = new StringBuilder();
//
//        int len = seq.length();
//        boolean remove = true;
//        for (int i = 0; i < len; i++) {
//            char ch = seq.charAt(i);
//            if (remove) {
//                remove = f.apply(ch);
//            }
//            if (!remove) {
//                sb.append(ch);
//            }
//        }
//
//        return sb.toString();
//    }

    /**
     * @param value
     */
    public static String escapeChars(String value) {
        StringBuilder sb = new StringBuilder();
        int length = value.length();
        for (int i = 0; i < length; i++) {
            sb.append(escapeChar(value.charAt(i)));
        }
        return sb.toString();
    }

    /**
     * @param c
     * @return escaped character as String
     */
    public static String escapeChar(Character c) {
        StringBuilder sb = new StringBuilder();
        switch (c) {
            case '\"':
                sb.append("\\\"");
                break;
            case '\'':
                sb.append("\\\'");
                break;
            case '\\':
                sb.append("\\\\");
                break;
            default:
                sb.append(c);
        }
        return sb.toString();
    }

    /**
     * @return String
     */
    public static String generateGUID() {
        StringBuilder sb = new StringBuilder();

        long rnd = random.nextLong();
        long tmp = System.currentTimeMillis();

        sb.append(Long.toHexString(tmp)).append("-").append(Long.toHexString(rnd));
        return sb.toString();
    }

    /**
     * @param value
     * @return
     */
    public static String ltrim(String value) {
        if (value == null) {
            return null;
        }
        int index = 0;
        while (index < value.length() && value.charAt(index) <= ' ') {
            index++;
        }
        return value.substring(index);
    }

    /**
     * @param value
     * @return
     */
    public static String rtrim(String value) {
        if (value == null) {
            return null;
        }
        int index = value.length() - 1;
        while (index > 0 && value.charAt(index) <= ' ') {
            index--;
        }
        return value.substring(0, index + 1);
    }

    /**
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    /**
     * @param value
     * @return
     */
    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    /**
     * @param value
     * @return
     */
    public static String capitalize(String value) {
        if (isEmpty(value)) {
            return value;
        } else {
            StringBuilder stringBuilder = new StringBuilder(value);
            stringBuilder.setCharAt(0, Character.toUpperCase(stringBuilder.charAt(0)));
            return stringBuilder.toString();
        }
    }

    /**
     * @param separator
     * @param parts
     * @return
     */
    public static String concat(String separator, String... parts) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String s : parts) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(s);
            i++;
        }
        return sb.toString();
    }

    /**
     * @param first
     * @param separator
     * @param last
     * @param parts
     * @return
     */
    public static String concatAndWrap(String first, String separator, String last, String... parts) {
        StringBuilder sb = new StringBuilder();
        sb.append(first);
        int i = 0;
        for (String s : parts) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(s);
            i++;
        }
        sb.append(last);
        return sb.toString();
    }

    /**
     * @param text
     * @param separator
     * @return
     */
    public static String removeLastPart(String text, String separator) {
        StringBuilder sb = new StringBuilder();
        String[] split = text.split(separator);

        if (split.length == 0) {
            return null;
        }

        for (int i = 0; i < split.length - 1; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(split[i]);
        }
        return sb.toString();

    }

    /**
     * @param text
     * @param separator
     * @param pos
     * @return
     */
    public static String removePart(String text, String separator, int pos) {
        StringBuilder sb = new StringBuilder();
        String[] split = text.split(separator);
        if (pos < 0 || pos >= split.length) {
            return null;
        }
        for (int i = 0, k = 0; i < split.length; i++) {
            if (i != pos) {
                if (k > 0) {
                    sb.append(separator);
                }
                sb.append(split[i]);
                k++;
            }
        }
        return sb.toString();
    }

    /**
     * @param text
     * @return
     */
    public static boolean containsCtrlChar(String text) {
        for (int ix = 0, length = text.length(); ix < length; ++ix) {
            char c = text.charAt(ix);
            if (c < 0x20 && c != 0x09 && c != 0x0A && c != 0x0D) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param text
     * @return
     */
    public static String removeCtrlChars(String text) {
        return REMOVE_CTRL_CHARS_PATTERN.matcher(text).replaceAll(EMPTY_STRING);
    }

    /**
     * @param array
     * @param delimiter
     * @return
     */
    public static <T> String arrayToString(T[] array, String delimiter) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(delimiter);
            }
            sb.append(array[i].toString());
        }
        return sb.toString();
    }

    /**
     * @param col
     * @param delimiter
     * @return
     */
    public static <T> String collectionToString(Collection<T> col, String delimiter) {

        StringBuilder sb = new StringBuilder();

        int i = 0;
        for (T t : col) {
            if (i++ > 0) {
                sb.append(delimiter);
            }
            sb.append(t.toString());
        }
        return sb.toString();
    }

    /**
     * <p>
     * Removes a substring only if it is at the end of a source string, otherwise
     * returns the source string.
     * </p>
     * <p>
     * A {@code null} source string will return {@code null}. An empty ("") source
     * string will return the empty string. A {@code null} search string will return
     * the source string.
     * </p>
     *
     * <pre>
     * StringUtils.removeEnd(null, *)      = null
     * StringUtils.removeEnd("", *)        = ""
     * StringUtils.removeEnd(*, null)      = *
     * StringUtils.removeEnd("www.domain.com", ".com.")  = "www.domain.com"
     * StringUtils.removeEnd("www.domain.com", ".com")   = "www.domain"
     * StringUtils.removeEnd("www.domain.com", "domain") = "www.domain.com"
     * StringUtils.removeEnd("abc", "")    = "abc"
     * </pre>
     *
     * @param str the source String to search, may be null
     * @param remove the String to search for and remove, may be null
     * @return the substring with the string removed if found, {@code null} if null
     *         String input
     */
    public static String removeEnd(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.endsWith(remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }

}
