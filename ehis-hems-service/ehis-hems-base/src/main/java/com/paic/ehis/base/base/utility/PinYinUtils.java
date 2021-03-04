package com.paic.ehis.base.base.utility;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtils {

    public static HanyuPinyinOutputFormat PINYIN_FORMAT;
    static {

        PINYIN_FORMAT = new HanyuPinyinOutputFormat();
        /**
         * 大小写设置
         *      LOWERCASE:小写
         *      UPPERCASE:大写
         */
        PINYIN_FORMAT.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        /**
         * 输出音标设置
         *
         * WITH_TONE_MARK:直接用音标符（VCharType必须设置WITH_U_UNICODE，否则会抛出异常）
         * WITH_TONE_NUMBER：1-4数字表示音标
         * WITHOUT_TONE：没有音标
         */
        PINYIN_FORMAT.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        /**
         * 特殊音符ü的设置
         * WITH_U_AND_COLON:用u表示(没有设置默认用u表示)
         * WITH_V:用v表示
         * WITH_U_UNICODE:用ü表示
         */
        PINYIN_FORMAT.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
    }



    /**
     *  汉字转拼音小写
     * @param chinese
     * @return
     */
    public static String toPinYin(String chinese){
        //创建返回对象
        StringBuilder result = new StringBuilder();//将字符串转成字符数组
        char[] chars = chinese.toCharArray();
        try {
            for (char c : chars) {
                //是中文则进行转换
                if(String.valueOf(c).matches("[\u4e00-\u9fa5]+")){
                    String[] pinyinStr = PinyinHelper.toHanyuPinyinStringArray(c, PINYIN_FORMAT);
//                    result.append(pinyinStr[0].charAt(0));//取每个中文的第一个拼音字母
                    result.append(pinyinStr[0]);
                }else {
                    result.append(c);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        return result.toString();
    }
}