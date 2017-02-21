package jinhe.lt;

/**
 * <p>
 * TestApache.java
 * </p>
 * 
 * @author Jon.King
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.CharSet;
import org.apache.commons.lang.CharSetUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.StopWatch;

public class TestApacheUtils {
    public void charSetDemo() {
        System.out.println("**CharSetDemo**");
        CharSet charSet = CharSet.getInstance("aeiou");
        String demoStr = "The quick brown fox jumps over the lazy dog.";
        int count = 0;
        for (int i = 0, len = demoStr.length(); i < len; i++) {
            if (charSet.contains(demoStr.charAt(i))) {
                count++;
            }
        }
        System.out.println("count: " + count);
    }

    public void charSetUtilsDemo() {
        System.out.println("**CharSetUtilsDemo**");
        System.out.println("计算字符串中包含某字符数.");
        System.out.println(CharSetUtils.count(
                "The quick brown fox jumps over the lazy dog.", "aeiou"));

        System.out.println("删除字符串中某字符.");
        System.out.println(CharSetUtils.delete(
                "The quick brown fox jumps over the lazy dog.", "aeiou"));

        System.out.println("保留字符串中某字符.");
        System.out.println(CharSetUtils.keep(
                "The quick brown fox jumps over the lazy dog.", "aeiou"));

        System.out.println("合并重复的字符.");
        System.out.println(CharSetUtils.squeeze("a  bbbbbb     c dd", "b d"));
    }

    public void objectUtilsDemo() {
        System.out.println("**ObjectUtilsDemo**");
        System.out.println("Object为null时，默认打印某字符.");
        Object obj = null;
        System.out.println(ObjectUtils.defaultIfNull(obj, "空"));

        System.out.println("验证两个引用是否指向的Object是否相等,取决于Object的equals()方法.");
        Object a = new Object();
        Object b = a;
        Object c = new Object();
        System.out.println(ObjectUtils.equals(a, b));
        System.out.println(ObjectUtils.equals(a, c));

        System.out.println("用父类Object的toString()方法返回对象信息.");
        Date date = new Date();
        System.out.println(ObjectUtils.identityToString(date));
        System.out.println(date);

        System.out.println("返回类本身的toString()方法结果,对象为null时，返回0长度字符串.");
        System.out.println(ObjectUtils.toString(date));
        System.out.println(ObjectUtils.toString(null));
        System.out.println(date);
    }

    public void serializationUtilsDemo() {
        System.out.println("*SerializationUtils**");
        Date date = new Date();
        byte[] bytes = SerializationUtils.serialize(date);
        System.out.println(ArrayUtils.toString(bytes));
        System.out.println(date);

        Date reDate = (Date) SerializationUtils.deserialize(bytes);
        System.out.println(reDate);
        System.out.println(ObjectUtils.equals(date, reDate));
        System.out.println(date == reDate);

        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            fos = new FileOutputStream(new File("d:/test.txt"));
            fis = new FileInputStream(new File("d:/test.txt"));
            SerializationUtils.serialize(date, fos);
            Date reDate2 = (Date) SerializationUtils.deserialize(fis);

            System.out.println(date.equals(reDate2));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void randomStringUtilsDemo() {
        System.out.println("**RandomStringUtilsDemo**");
        System.out.println("生成指定长度的随机字符串,好像没什么用.");
        System.out.println(RandomStringUtils.random(500));

        System.out.println("在指定字符串中生成长度为n的随机字符串.");
        System.out.println(RandomStringUtils.random(5, "abcdefghijk"));

        System.out.println("指定从字符或数字中生成随机字符串.");
        System.out.println(RandomStringUtils.random(5, true, false));
        System.out.println(RandomStringUtils.random(5, false, true));

    }

    public void stringUtilsDemo() {
        System.out.println("**StringUtilsDemo**");
        System.out.println("将字符串重复n次，将文字按某宽度居中，将字符串数组用某字符串连接.");
        String[] header = new String[3];
        header[0] = StringUtils.repeat("*", 50);
        header[1] = StringUtils.center("  StringUtilsDemo  ", 50, "^O^");
        header[2] = header[0];
        String head = StringUtils.join(header, "\n");
        System.out.println(head);

        System.out.println("缩短到某长度,用...结尾.");
        System.out.println(StringUtils.abbreviate(
                "The quick brown fox jumps over the lazy dog.", 10));
        System.out.println(StringUtils.abbreviate(
                "The quick brown fox jumps over the lazy dog.", 15, 10));

        System.out.println("返回两字符串不同处索引号.");
        System.out.println(StringUtils.indexOfDifference("aaabc", "aaacc"));

        System.out.println("返回两字符串不同处开始至结束.");
        System.out.println(StringUtils.difference("aaabcde", "aaaccde"));

        System.out.println("截去字符串为以指定字符串结尾的部分.");
        System.out.println(StringUtils.chomp("aaabcde", "de"));

        System.out.println("检查一字符串是否为另一字符串的子集.");
        System.out.println(StringUtils.containsOnly("aad", "aadd"));

        System.out.println("检查一字符串是否不是另一字符串的子集.");
        System.out.println(StringUtils.containsNone("defg", "aadd"));

        System.out.println("检查一字符串是否包含另一字符串.");
        System.out.println(StringUtils.contains("defg", "ef"));
        System.out.println(StringUtils.containsOnly("ef", "defg"));

        System.out.println("返回可以处理null的toString().");
        System.out.println(StringUtils.defaultString("aaaa"));
        System.out.println("?" + StringUtils.defaultString(null) + "!");

        System.out.println("去除字符中的空格.");
        System.out.println(StringUtils.deleteWhitespace("aa  bb  cc"));

        System.out.println("判断是否是某类字符.");
        System.out.println(StringUtils.isAlpha("ab"));
        System.out.println(StringUtils.isAlphanumeric("12"));
        System.out.println(StringUtils.isBlank(""));
        System.out.println(StringUtils.isNumeric("123"));
    }

    public void systemUtilsDemo() {
        System.out.println(genHeader("SystemUtilsDemo"));
        System.out.println("获得系统文件分隔符.");
        System.out.println(SystemUtils.FILE_SEPARATOR);

        System.out.println("获得源文件编码.");
        System.out.println(SystemUtils.FILE_ENCODING);

        System.out.println("获得ext目录.");
        System.out.println(SystemUtils.JAVA_EXT_DIRS);

        System.out.println("获得java版本.");
        System.out.println(SystemUtils.JAVA_VM_VERSION);

        System.out.println("获得java厂商.");
        System.out.println(SystemUtils.JAVA_VENDOR);
    }

    public void classUtilsDemo() {
        System.out.println(genHeader("ClassUtilsDemo"));
        System.out.println("获取类实现的所有接口.");
        System.out.println(ClassUtils.getAllInterfaces(Date.class));

        System.out.println("获取类所有父类.");
        System.out.println(ClassUtils.getAllSuperclasses(Date.class));

        System.out.println("获取简单类名.");
        System.out.println(ClassUtils.getShortClassName(Date.class));

        System.out.println("获取包名.");
        System.out.println(ClassUtils.getPackageName(Date.class));

        System.out.println("判断是否可以转型.");
        System.out.println(ClassUtils.isAssignable(Date.class, Object.class));
        System.out.println(ClassUtils.isAssignable(Object.class, Date.class));
    }

    public void stringEscapeUtilsDemo() {
        System.out.println(genHeader("StringEcsapeUtils"));
        System.out.println("转换特殊字符.");
        System.out.println("html:" + StringEscapeUtils.escapeHtml(" "));
        System.out.println("html:" + StringEscapeUtils.unescapeHtml("<p>"));
    }

    private final class BuildDemo {
        String name;

        int age;

        public BuildDemo(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            ToStringBuilder tsb = new ToStringBuilder(this,
                    ToStringStyle.MULTI_LINE_STYLE);
            tsb.append("Name", name);
            tsb.append("Age", age);
            return tsb.toString();
        }

        public int hashCode() {
            HashCodeBuilder hcb = new HashCodeBuilder();
            hcb.append(name);
            hcb.append(age);
            return hcb.hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof BuildDemo)) {
                return false;
            }
            BuildDemo bd = (BuildDemo) obj;
            EqualsBuilder eb = new EqualsBuilder();
            eb.append(name, bd.name);
            eb.append(age, bd.age);
            return eb.isEquals();
        }
    }

    public void builderDemo() {
        System.out.println(genHeader("BuilderDemo"));
        BuildDemo obj1 = new BuildDemo("a", 1);
        BuildDemo obj2 = new BuildDemo("b", 2);
        BuildDemo obj3 = new BuildDemo("a", 1);

        System.out.println("toString()");
        System.out.println(obj1);
        System.out.println(obj2);
        System.out.println(obj3);

        System.out.println("hashCode()");
        System.out.println(obj1.hashCode());
        System.out.println(obj2.hashCode());
        System.out.println(obj3.hashCode());

        System.out.println("equals()");
        System.out.println(obj1.equals(obj2));
        System.out.println(obj1.equals(obj3));
    }

    public void numberUtils() {
        System.out.println(genHeader("NumberUtils"));
//        System.out.println("字符串转为数字(不知道有什么用).");
//        System.out.println(NumberUtils.stringToInt("ba", 33));

        System.out.println("从数组中选出最大值.");
        System.out.println(NumberUtils.max(new int[] { 1, 2, 3, 4 }));

        System.out.println("判断字符串是否全是整数.");
        System.out.println(NumberUtils.isDigits("123.1"));

        System.out.println("判断字符串是否是有效数字.");
        System.out.println(NumberUtils.isNumber("0123.1"));
    }

    @SuppressWarnings("unchecked")
	public void dateFormatUtilsDemo() {
        System.out.println(genHeader("DateFormatUtilsDemo"));
        System.out.println("格式化日期输出.");
        System.out.println(DateFormatUtils.format(System.currentTimeMillis(),
                "yyyy-MM-dd HH:mm:ss"));

        System.out.println("秒表.");
        StopWatch sw = new StopWatch();
        sw.start();

        for (Iterator iterator = DateUtils.iterator(new Date(),
                DateUtils.RANGE_WEEK_CENTER); iterator.hasNext();) {
            Calendar cal = (Calendar) iterator.next();
            System.out.println(DateFormatUtils.format(cal.getTime(),
                    "yy-MM-dd HH:mm"));
        }

        sw.stop();
        System.out.println("秒表计时:" + sw.getTime());

    }

    private String genHeader(String head) {
        String[] header = new String[3];
        header[0] = StringUtils.repeat("*", 50);
        header[1] = StringUtils.center("  " + head + "  ", 50, "^O^");
        header[2] = header[0];
        return StringUtils.join(header, "\n");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TestApacheUtils langDemo = new TestApacheUtils();

        langDemo.charSetDemo();
        langDemo.charSetUtilsDemo();
        langDemo.objectUtilsDemo();
        langDemo.serializationUtilsDemo();
        langDemo.randomStringUtilsDemo();
        langDemo.stringUtilsDemo();
        langDemo.systemUtilsDemo();
        langDemo.classUtilsDemo();
        langDemo.stringEscapeUtilsDemo();
        langDemo.builderDemo();
        langDemo.numberUtils();
        langDemo.dateFormatUtilsDemo();

    }

}
