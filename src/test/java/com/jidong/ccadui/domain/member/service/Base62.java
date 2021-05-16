package com.jidong.ccadui.domain.member.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by rocksea on 2016. 5. 12.
 */
public class Base62 {
//    /**
//     * Base62 Character Table
//     */
//    static final char[] BASE36 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
//
//    /**
//     * Base62 Encoding
//     *
//     * @return the base 62 string of an integer
//     */
//    public static String encode(long value) {
//        final StringBuilder sb = new StringBuilder();
//        do {
//            int i = (int) (value % 36);
//            sb.append(BASE36[i]);
//            value /= 36;
//        } while (value > 0);
//        return sb.toString();
//    }
//
//    public static String encodeToLong(long value) {
//        final StringBuilder sb = new StringBuilder();
//        do {
//            int i = (int) (value % 36);
//            sb.append(BASE36[i]);
//            value /= 36;
//        } while (value > 0);
//        return sb.toString();
//    }
//
//    /**
//     * Returns the base 62 value of a string.
//     *
//     * @return the base 62 value of a string.
//     */
//    public static int decode(String value) {
//        int result = 0;
//        int power = 1;
//        for (int i = 0; i < value.length(); i++) {
//            int digit = new String(BASE36).indexOf(value.charAt(i));
//            result += digit * power;
//            power *= 36;
//        }
//        return result;
//    }
//
//    public static long decodeToLong(String value) {
//        long result = 0;
//        long power = 1;
//        for (int i = 0; i < value.length(); i++) {
//            int digit = new String(BASE36).indexOf(value.charAt(i));
//            result += digit * power;
//            power *= 62;
//        }
//        return result;
//    }
//
//
//    public static void encodeTest() {
//        for(long i = 30000276; i < 30001276; i++){
//            byte[] bytes = String.valueOf(i << 10).getBytes();
//            String s64 = Base64.encodeBase64URLSafeString(bytes);
//            String s62 = Base62.encode(i);
//            String s32hex = BaseEncoding.base32Hex().omitPadding().encode(bytes);
//            String s32hex_2 = BaseEncoding.base32Hex().omitPadding().encode(String.valueOf(i).getBytes(StandardCharsets.UTF_8));
////            String s = new String(BaseEncoding.base32Hex().omitPadding().decode(s32hex_2));
//            Integer s = new Integer(Base62.decode(s62));
//            if(i != Long.valueOf(s)){
//                System.out.println("2222222222");
//            }
////            System.out.println(String.format("%d, %s, %s, %s", i, s62,s32hex, s32hex_2));
//            System.out.println(String.format("%d, %s", i, s62));
//
//        }
//    }
//    @Test
//    void test() {
//        long memberNo = 36750846;
//        byte[] bytes = String.valueOf(memberNo << 10).getBytes();
//
////
////        Base32 base32 = new Base32();
////        System.out.println("apache base32 : " + base32.encodeAsString(String.valueOf(memberNo).getBytes()));
////
////        String recommender = Base62.encode(memberNo);
////        System.out.println("encode : " + BaseEncoding.base32Hex().omitPadding().encode(String.valueOf(memberNo).getBytes(StandardCharsets.UTF_8)));
////        System.out.println("encode2 : " + BaseEncoding.base32Hex().encode(String.valueOf(memberNo).getBytes()));
////        System.out.println("encode base36 : " + recommender);
////        System.out.println("decode base36 : " + Base62.decode("4EZ5V"));
//
////        System.out.println("random : " + (char)((int)(Math.random()*26)+65) + (char)((int)(Math.random()*26)+65) + (char)((int)(Math.random()*26)+65));
//
//        encodeTest();
//
//        //3EZ5V
//    }


    public static void main(String[] args) {
        final List<String> referralCodeList = new ArrayList<>();
        for (int i = 60000005; i < 100000005; i++) {
            final String referralCode = getReferralCodeByMemberNo(i);
            referralCodeList.add(referralCode);
        }

        int number = 60000005;
        for (int j =0; j< referralCodeList.size(); j++) {
            if(number != getMemberByReferralCode(referralCodeList.get(j))) {
                System.out.println(String.format("%d, %s", number, referralCodeList.get(j)));
            }
            number++;
        }
    }

//    public static void main(String[] args) {
//        System.out.println(setLPad("0", 4, paddingText));;
//        System.out.println(encodePostfix("10009920"));
//
//    }

    private static final int fixedValue = 10000;
    private static final int fixedLength = 4;
    private static final String paddingText = "0";

    public static String getReferralCodeByMemberNo(long memberNo) {
        final String prefixAlphabet = encodePrefix(String.valueOf(memberNo));
        final String postfixNumber = encodePostfix(String.valueOf(memberNo));

        return prefixAlphabet + postfixNumber;
    }

    private static String encodePrefix(String memberNoStr) {
        return encode(Long.parseLong(memberNoStr.substring(0, memberNoStr.length() - fixedLength)));
    }

    private static String encodePostfix(String memberNoStr) {
        final int postNum = Integer.parseInt(memberNoStr.substring(memberNoStr.length() - fixedLength));
        return postNum == 0 ? "0000" : setLPad(Integer.toString(fixedValue - postNum), fixedLength, paddingText);
    }

    public static long getMemberByReferralCode(String referralCode) {
        final long preNumber = decodePrefix(referralCode);
        final String postNumber = decodePostfix(referralCode);

        return Long.parseLong(preNumber + setLPad(postNumber, fixedLength, paddingText));
    }

    private static long decodePrefix(String referralCode) {
        return decode(referralCode.substring(0, referralCode.length() - fixedLength));
    }

    private static String decodePostfix(String referralCode) {
        final String num = referralCode.substring(referralCode.length() - 4);
        return fixedValue - Integer.parseInt(num) == fixedValue ? "0000" : String.valueOf(fixedValue - Integer.parseInt(num));
    }

    /**
     * LeftPAD
     * 왼쪽부터 채운문자열 + 원래문자열
     *
     * @param originText
     * @param paddingLength
     * @param paddingText
     * @return
     */
    private static String setLPad(String originText, int paddingLength, String paddingText) {
        return IntStream.range(originText.length(), paddingLength)
                .mapToObj(i -> paddingText)
                .collect(Collectors.joining())
                .concat(originText);
    }


    private static final char[] BASE26 = "EFGHABCDIJKLMNWXYZSTUVOPQR".toCharArray();
    private static final int BASE26_INT = 26;

    /**
     * Base26 Encoding
     *
     * @return the base 26 string of an integer
     */
    public static String encode(final long value) {
        long quotient = value;
        final StringBuilder sb = new StringBuilder();
        do {
            final int i = (int) (quotient % 26);
            sb.append(BASE26[i]);
            quotient /= BASE26_INT;
        } while (quotient > 0);
        return sb.toString();
    }

    /**
     * Base26 Decoding
     *
     * @param value
     * @return
     */
    public static long decode(final String value) {
        long result = 0;
        long power = 1;
        for (int i = 0; i < value.length(); i++) {
            final int digit = new String(BASE26).indexOf(value.charAt(i));
            result += digit * power;
            power *= BASE26_INT;
        }
        return result;
    }
}
