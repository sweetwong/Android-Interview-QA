import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreateREADME {

    // TODO 在此处输入你项目的根路径
    static final String 根路径 = "C:\\Users\\wangsw\\Desktop\\其他\\Android-Interview-QA";
    // TODO 在此处输入你要输出的文件名
    static final String 输出文件名 = "README.md";
    // TODO 是否随机排序
    static final boolean 是否随机排序 = false;

    public static void main(String[] args) throws Exception {
        输出 = new PrintWriter(根路径 + '/' + 输出文件名);
        写("重要");
        写("记忆");
        写("源码");
        写("普通");
        写("其次");
        写("待整理");
        输出.close();
    }

    static final String 换行符 = System.lineSeparator();
    static Writer 输出;

    static void 写(String 文件夹名) throws Exception {
        File 文件夹 = new File(根路径 + '/' + 文件夹名);
        输出.append("## ")
                .append(文件夹名)
                .append(换行符)
                .append(换行符);
        List<File> 子文件集合 = Arrays.asList(文件夹.listFiles());
        if (是否随机排序) {
            Collections.shuffle(子文件集合);
        }
        for (File 子文件 : 子文件集合) {
            输出.append('[')
                    .append(子文件.getName().replace(".md", ""))
                    .append(']')
                    .append('(')
                    .append(文件夹名)
                    .append('/')
                    .append(子文件.getName())
                    .append(')')
                    .append(换行符)
                    .append(换行符);
        }
        输出.flush();
    }

}
