import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 一个生成 README.md 的脚本
 */
public class CreateREADME {

    // TODO 在此处输入你项目的根路径
    static final String 根路径 = "C:\\Users\\86132\\Desktop\\Android-Interview-QA";
    // TODO 在此处输入你要输出的文件名
    static final String 输出文件名 = "README.md";

    public static void main(String[] args) throws Exception {
        输出 = new PrintWriter(根路径 + '/' + 输出文件名);
        乱序输出 = new PrintWriter(根路径 + "/QA-shuffle.md");
        写介绍();
        写("重要");
        写("记忆");
        写("源码");
        写("普通");
        写("其次");
        写("汇总");
        写("待整理");
        写乱序();
        输出.close();
        乱序输出.close();
        System.out.println("完成");
    }

    static final String 换行符 = System.lineSeparator();
    static Writer 输出;
    static Writer 乱序输出;
    static List<String> 乱序集合 = new ArrayList<>();

    static void 写介绍() throws Exception {
        BufferedReader 输入 = new BufferedReader(new FileReader(new File(根路径 + "/script/介绍.md")));
        String 一行;
        while ((一行 = 输入.readLine()) != null) {
            输出.append(一行);
            输出.append(换行符);
        }
        输出.append(换行符);
        输出.flush();
    }

    static void 写(String 文件夹名) throws Exception {
        File 文件夹 = new File(根路径 + '/' + 文件夹名);
        输出.append("## ")
                .append(文件夹名)
                .append(换行符)
                .append(换行符);
        List<File> 子文件集合 = Arrays.asList(文件夹.listFiles());
        for (File 子文件 : 子文件集合) {
            StringBuilder 字符串 = new StringBuilder();
            字符串.append('[')
                    .append(子文件.getName().replace(".md", ""))
                    .append(']')
                    .append('(')
                    .append(文件夹名)
                    .append('/')
                    .append(子文件.getName())
                    .append(')')
                    .append(换行符)
                    .append(换行符);
            输出.append(字符串.toString());
            if (!文件夹名.equals("待整理")) {
                乱序集合.add(字符串.toString());
            }
        }
        输出.flush();
    }

    static void 写乱序() throws Exception {
        Collections.shuffle(乱序集合);
        for (String 字符串 : 乱序集合) {
            乱序输出.append(字符串);
        }
        乱序输出.flush();
    }

}
