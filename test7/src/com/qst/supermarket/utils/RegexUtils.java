package com.qst.supermarket.utils;

import org.apache.commons.lang3.StringUtils;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    public static void main(String[] args) {

         System.out.println("������Ӣ�������ַ�: "+RegexUtils.stringFilter("�й�~~!#$%%."));
         System.out.println("�Ƿ������Ӣ�������ַ�: "+RegexUtils.isContainsSpecialChar("12"));
         System.out.println("�ж������ַ�: "+RegexUtils.isChineseChar("�й���"));
         System.out.println("ƥ�人��: " + RegexUtils.isChinese("�й���"));
         System.out.println("�ж�Ӣ���ַ�: "+RegexUtils.isEnglish("abc!"));
         System.out.println("�жϺϷ��ַ�: "+RegexUtils.isRightfulString("abc_-11AAA"));
         System.out.println("����������֤: "+RegexUtils.isZipCode("162406"));
         System.out.println("���֤������֤: "+RegexUtils.isIdCardNo("35052419880210133e"));
         System.out.println("�ֻ�������֤: "+RegexUtils.isMobile("18918611111"));
         System.out.println("�绰������֤: "+RegexUtils.isPhone("8889333"));
         System.out.println("�绰������֤: "+RegexUtils.isNumeric("888.9333"));
         System.out.println("ƥ������: "+RegexUtils.isPwd("d888d_ddddd"));
         System.out.println("ƥ������: "+RegexUtils.isUrl("http://baidu.com"));
         System.out.println("��֤�ַ�: " + RegexUtils.stringCheck("����aabc001_-"));
         System.out.println(isEmail("416501600@qq.com"));

    }

       public final static boolean isNull(Object[] objs) {
                   if (objs == null || objs.length == 0)
               return true;
                   return false;
       }

       public final static boolean isNull(Integer integer) {
                   if (integer == null || integer == 0)
               return true;
                   return false;
       }

       public final static boolean isNull(Collection collection) {
                   if (collection == null || collection.size() == 0)
               return true;
                   return false;
       }

       public final static boolean isNull(Map map) {
                   if (map == null || map.size() == 0)
               return true;
                   return false;
       }

       public final static boolean isNull(String str) {
                   return str == null || "".equals(str.trim())
                         || "null".equals(str.toLowerCase());
       }

       public final static boolean isNull(Long longs) {
                   if (longs == null || longs == 0)
               return true;
                   return false;
       }

       public final static boolean isNotNull(Long longs) {
                   return !isNull(longs);
       }

       public final static boolean isNotNull(String str) {
                   return !isNull(str);
       }

       public final static boolean isNotNull(Collection collection) {
                   return !isNull(collection);
       }

       public final static boolean isNotNull(Map map) {
                   return !isNull(map);
       }

       public final static boolean isNotNull(Integer integer) {
                   return !isNull(integer);
       }

       public final static boolean isNotNull(Object[] objs) {
                   return !isNull(objs);
       }

       /**
         * ƥ��URL��ַ
         *
         * @param str
         * @return
         * @author jiqinlin
         */
                  public final static boolean isUrl(String str) {
                   return match(str, "^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$");
       }

       /**
         * ƥ�����룬����ĸ��ͷ��������6-12֮�䣬ֻ�ܰ����ַ������ֺ��»��ߡ�
         *
         * @param str
         * @return
         * @author jiqinlin
         */
                  public final static boolean isPwd(String str) {
                   return match(str, "\\w{6,12}$");
       }

       /**
         * ��֤�ַ���ֻ�ܰ������ġ�Ӣ�ġ����֡��»��ߵ��ַ���
         *
         * @param str
         * @return
         * @author jiqinlin
         */
                  public final static boolean stringCheck(String str) {
                   return match(str, "^[a-zA-Z0-9\u4e00-\u9fa5-_]+$");
       }

       /**
         * ƥ��Email��ַ
         *
         * @param str
         * @return
         * @author jiqinlin
         */
                  public final static boolean isEmail(String str) {
                   return match(str,
                                         "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
       }

       /**
         * ƥ��Ǹ�������������+0��
         *
         * @param str
         * @return
         * @author jiqinlin
         */
                  public final static boolean isInteger(String str) {
                   return match(str, "^[+]?\\d+$");
       }

       /**
         * �ж���ֵ���ͣ����������͸�����
         *
         * @param str
         * @return
         * @author jiqinlin
         */
                  public final static boolean isNumeric(String str) {
                   if (isFloat(str) || isInteger(str))
               return true;
                   return false;
       }

       /**
         * ֻ����������
         *
         * @param str
         * @return
         * @author jiqinlin
         */
                  public final static boolean isDigits(String str) {
                   return match(str, "^[0-9]*$");
       }

       /**
         * ƥ����������
         *
         * @param str
         * @return
         * @author jiqinlin
         */
                  public final static boolean isFloat(String str) {
                   return match(str, "^[-\\+]?\\d+(\\.\\d+)?$");
       }

       /**
         * ��ϵ�绰(�ֻ�/�绰�Կ�)��֤
         *
         * @param text
         * @return
         * @author jiqinlin
         */
                  public final static boolean isTel(String text) {
                   if (isMobile(text) || isPhone(text))
               return true;
                   return false;
       }

       /**
         * �绰������֤
         *
         * @param text
         * @return
         * @author jiqinlin
         */
                  public final static boolean isPhone(String text) {
                   return match(text, "^1[3456789]\\d{9}$");
       }

       /**
         * �ֻ�������֤
         *
         * @param text
         * @return
         * @author jiqinlin
         */
                  public final static boolean isMobile(String text) {
                   if (text.length() != 11)
               return false;
                   return match(text,
                                         "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$");
       }

       /**
         * ���֤������֤
         *
         * @param text
         * @return
         * @author jiqinlin
         */
                  public final static boolean isIdCardNo(String text) {
                   return match(text, "^(\\d{6})()?(\\d{4})(\\d{2})(\\d{2})(\\d{3})(\\w)$");
       }

       /**
         * ����������֤
         *
         * @param text
         * @return
         * @author jiqinlin
         */
                  public final static boolean isZipCode(String text) {
                   return match(text, "^[0-9]{6}$");
       }

       /**
         * �ж�����num�Ƿ����0
         *
         * @param num
         * @return
         * @author jiqinlin
         */
                  public final static boolean isIntEqZero(int num) {
                   return num == 0;
       }

       /**
         * �ж�����num�Ƿ����0
         *
         * @param num
         * @return
         * @author jiqinlin
         */
                  public final static boolean isIntGtZero(int num) {
                   return num > 0;
       }

       /**
         * �ж�����num�Ƿ���ڻ����0
         *
         * @param num
         * @return
         * @author jiqinlin
         */
                  public final static boolean isIntGteZero(int num) {
                   return num >= 0;
       }

       /**
         * �жϸ�����num�Ƿ����0
         *
         * @param num
         *??????????? ������
         * @return
         * @author jiqinlin
         */
                  public final static boolean isFloatEqZero(float num) {
                   return num == 0f;
       }

       /**
         * �жϸ�����num�Ƿ����0
         *
         * @param num
         *??????????? ������
         * @return
         * @author jiqinlin
         */
                  public final static boolean isFloatGtZero(float num) {
                   return num > 0f;
       }

       /**
         * �жϸ�����num�Ƿ���ڻ����0
         *
         * @param num
         *??????????? ������
         * @return
         * @author jiqinlin
         */
                  public final static boolean isFloatGteZero(float num) {
                   return num >= 0f;
       }

       /**
         * �ж��Ƿ�Ϊ�Ϸ��ַ�(a-zA-Z0-9-_)
         *
         * @param text
         * @return
         * @author jiqinlin
         */
                  public final static boolean isRightfulString(String text) {
                   return match(text, "^[A-Za-z0-9_-]+$");
       }

       /**
         * �ж�Ӣ���ַ�(a-zA-Z)
         *
         * @param text
         * @return
         * @author jiqinlin
         */
                  public final static boolean isEnglish(String text) {
                   return match(text, "^[A-Za-z]+$");
       }

       /**
         * �ж������ַ�(�������ֺͷ���)
         *
         * @param text
         * @return
         * @author jiqinlin
         */
                  public final static boolean isChineseChar(String text) {
                   return match(text, "^[\u0391-\uFFE5]+$");
       }

       /**
         * ƥ�人��
         *
         * @param text
         * @return
         * @author jiqinlin
         */
                  public final static boolean isChinese(String text) {
                   return match(text, "^[\u4e00-\u9fa5]+$");
       }

       /**
         * �Ƿ������Ӣ�������ַ�����Ӣ��"-_"�ַ���
         *
         * @param
         * @return
         */
                  public static boolean isContainsSpecialChar(String text) {
                   if (StringUtils.isBlank(text))
               return false;
                   String[] chars = { "[", "`", "~", "!", "@", "#", "$", "%", "^", "&",
                         "*", "(", ")", "+", "=", "|", "{", "}", "'", ":", ";", "'",
                         ",", "[", "]", ".", "<", ">", "/", "?", "~", "��", "@", "#",
                         "��", "%", "��", "&", "*", "��", "��", "��", "+", "|", "{", "}",
                         "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "]" };
                   for (String ch : chars) {
               if (text.contains(ch))
                         return true;
                   }
                   return false;
       }

       /**
         * ������Ӣ�������ַ�����Ӣ��"-_"�ַ���
         *
         * @param text
         * @return
         */
                  public static String stringFilter(String text) {
                   String regExpr = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~��@#��%����&*��������+|{}������������������������]";
                   Pattern p = Pattern.compile(regExpr);
                   Matcher m = p.matcher(text);
                   return m.replaceAll("").trim();
       }

       /**
         * ����html����
         *
         * @param inputString
         *??????????? ��html��ǩ���ַ���
         * @return
         */
                  public static String htmlFilter(String inputString) {
                   String htmlStr = inputString; // ��html��ǩ���ַ���
                   String textStr = "";
                   Pattern p_script;
                   Matcher m_script;
                   Pattern p_style;
                   Matcher m_style;
                   Pattern p_html;
                   Matcher m_html;
                   Pattern p_ba;
                   Matcher m_ba;

                   try {
               String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // ����script��������ʽ{��<script[^>]*?>[\\s\\S]*?<\\/script>
               // }
               String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // ����style��������ʽ{��<style[^>]*?>[\\s\\S]*?<\\/style>
               // }
               String regEx_html = "<[^>]+>"; // ����HTML��ǩ��������ʽ
               String patternStr = "\\s+";

               p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
               m_script = p_script.matcher(htmlStr);
               htmlStr = m_script.replaceAll(""); // ����script��ǩ

               p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
               m_style = p_style.matcher(htmlStr);
               htmlStr = m_style.replaceAll(""); // ����style��ǩ

               p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
               m_html = p_html.matcher(htmlStr);
               htmlStr = m_html.replaceAll(""); // ����html��ǩ

               p_ba = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
               m_ba = p_ba.matcher(htmlStr);
               htmlStr = m_ba.replaceAll(""); // ���˿ո�

               textStr = htmlStr;

                   } catch (Exception e) {
               System.err.println("Html2Text: " + e.getMessage());
                   }
                   return textStr;// �����ı��ַ���
       }

       /**
         * ������ʽƥ��
         *
         * @param text
         *??????????? ��ƥ����ı�
         * @param reg
         *??????????? ������ʽ
         * @return
         * @author jiqinlin
         */
                  private final static boolean match(String text, String reg) {
                   if (StringUtils.isBlank(text) || StringUtils.isBlank(reg))
               return false;
                   return Pattern.compile(reg).matcher(text).matches();
       }


}


	