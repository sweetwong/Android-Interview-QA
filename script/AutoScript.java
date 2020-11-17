import javas.concurrent.ThreadUtils;

import java.io.*;
import java.util.*;

/**
 * 一个生成 README.md 的脚本
 */
public class AutoScript {

    // TODO 在此处输入你项目的根路径
    static final String rootPath = "C:\\Users\\Administrator\\Desktop\\Android-Interview-QA";

    public static void main(String[] args) throws Exception {
        new CreateREADME().create(rootPath);
        new CreateTag().create(rootPath);
    }

    static class CreateREADME {

        public void create(String rootPath) throws Exception {
            mWriter = new PrintWriter(rootPath + "/README.md");
            mShuffleWriter = new PrintWriter(rootPath + "/QA-shuffle.md");
            writeHeader();
            writeFolder("重要");
            writeFolder("记忆");
            writeFolder("源码");
            writeFolder("普通");
            writeFolder("其次");
            writeFolder("汇总");
            writeFolder("待整理");
            writeTail();
            writeShuffle();
            mWriter.close();
            mShuffleWriter.close();
            System.out.println("CreateREADME 完成");
        }

        private static final String LINE_SEPARATOR = System.lineSeparator();
        private Writer mWriter;
        private Writer mShuffleWriter;
        private List<String> mShuffleList = new ArrayList<>();

        private void writeHeader() throws Exception {
            BufferedReader reader = new BufferedReader(new FileReader(new File(rootPath + "/script/header.md")));
            String line;
            while ((line = reader.readLine()) != null) {
                mWriter.append(line);
                mWriter.append(LINE_SEPARATOR);
            }
            mWriter.append(LINE_SEPARATOR);
            mWriter.flush();
        }

        private void writeTail() throws Exception {
            BufferedReader reader = new BufferedReader(new FileReader(new File(rootPath + "/script/tail.md")));
            String line;
            while ((line = reader.readLine()) != null) {
                mWriter.append(line);
                mWriter.append(LINE_SEPARATOR);
            }
            mWriter.append(LINE_SEPARATOR);
            mWriter.flush();
        }


        private void writeFolder(String folderPath) throws Exception {
            File folder = new File(rootPath + '/' + folderPath);
            mWriter.append("## ")
                    .append(folderPath)
                    .append(LINE_SEPARATOR)
                    .append(LINE_SEPARATOR);
            List<File> files = Arrays.asList(folder.listFiles());
            for (File f : files) {
                StringBuilder builder = new StringBuilder();
                builder.append('[')
                        .append(f.getName().replace(".md", ""))
                        .append(']')
                        .append('(')
                        .append(folderPath)
                        .append('/')
                        .append(f.getName())
                        .append(')')
                        .append(LINE_SEPARATOR)
                        .append(LINE_SEPARATOR);
                mWriter.append(builder.toString());
                if (!folderPath.equals("待整理") && !folderPath.equals("汇总")) {
                    mShuffleList.add(builder.toString());
                }
            }
            mWriter.flush();
        }

        private void writeShuffle() throws Exception {
            Collections.shuffle(mShuffleList);
            for (String s : mShuffleList) {
                mShuffleWriter.append(s);
            }
            mShuffleWriter.flush();
        }
    }

    static class CreateTag {

        private Map mMap = new LinkedHashMap();

        {
            Map Android = createMap(
                    "四大组件",
                    "Fragment",
                    "View",
                    "动画",
                    "存储",
                    "网络",
                    "图片",
                    "Handler",
                    "Binder",
                    "WebView",
                    "Framework 源码",
                    "性能优化",
                    "组件化/插件化/热修复",
                    "第三方库",
                    "MVC/MVP/MVVM",
                    "AOP",
                    "Android 其他"
            );
            Map Java = createMap(
                    "Java 基础",
                    "Java 并发",
                    "Java 集合",
                    "Java 泛型",
                    "Java 注解",
                    "Java 反射",
                    "Java IO",
                    "Java 虚拟机"
            );
            mMap.put("Android", Android);
            mMap.put("Java", Java);
            mMap.put("计算机网络", new ArrayList<>());
            mMap.put("数据结构与算法", new ArrayList<>());
            mMap.put("设计模式", new ArrayList<>());
        }


        public void create(String rootPath) throws Exception {
            readFiles(rootPath);
            writeFiles(rootPath);
            writeDirectory(rootPath);
            System.out.println("CreateTAG 完成");
        }

        private void readFiles(String rootPath) throws Exception {
            readFilesRecursive(new File(rootPath), rootPath);
        }

        private void readFilesRecursive(File file, String rootPath) throws Exception {
            File[] fs = file.listFiles();
            for (File f : fs) {
                String name = f.getName();
                if (f.isDirectory()) {
                    if (name.equals(".git")) {
                        continue;
                    }
                    readFilesRecursive(f, rootPath);
                } else {
                    if (!name.endsWith(".md")) {
                        continue;
                    }
                    BufferedReader reader = new BufferedReader(new FileReader(f));
                    String firstLine = reader.readLine();
                    reader.close();
                    if (firstLine == null || firstLine.isEmpty() || !firstLine.contains("`")
                            || firstLine.length() >= 50) {
                        continue;
                    }
                    String[] split = firstLine.split("、");
                    List<String> tags = new ArrayList<>();
                    for (String s : split) {
                        tags.add(s.replace("`", ""));
                    }
                    String relativePath = f.getAbsolutePath().substring(rootPath.length()).replace("\\", "/");
                    String fileName = f.getName().replace(".md", "");

                    boolean[] hasTag = new boolean[1];
                    for (String tag : tags) {
                        hasTag[0] = false;
                        addToMapRecursive(tag, "[" + fileName + "](" + relativePath + ")", mMap, hasTag);
                        if (!hasTag[0]) {
                            throw new RuntimeException("不是正确的TAG, tag: " + tag + ", relativePath: " + relativePath);
                        }
                    }
                }
            }
        }

        private void addToMapRecursive(String tag, String s, Map map, boolean[] hasTag) {
            map.forEach((key, value) -> {
                if (Objects.equals(tag, key)) {
                    ((ArrayList) value).add(s);
                    hasTag[0] = true;
                }
                if (value instanceof Map) {
                    addToMapRecursive(tag, s, (Map) value, hasTag);
                }
            });
        }

        private Map createMap(String... keys) {
            Map map = new LinkedHashMap<>();
            for (String key : keys) {
                map.put(key, new ArrayList<>());
            }
            return map;
        }

        private void writeFiles(String rootPath) throws Exception {
            PrintWriter writer = new PrintWriter(new File(rootPath + "/TAG.md"));
            writeFilesRecursive(writer, mMap, 1);
            writer.close();
        }

        private void writeFilesRecursive(PrintWriter writer, Map map, int level) {
            map.forEach((key, value) -> {
                if (value instanceof Map) {
                    writer.println(getTitleByLevel(level) + key);
                    writeFilesRecursive(writer, (Map) value, level + 1);
                } else if (value instanceof List) {
                    List list = (List) value;
                    if (list.isEmpty()) {
                        return;
                    }
                    writer.println(getTitleByLevel(level) + key);
                    for (Object s : list) {
                        writer.println(s);
                        writer.println();
                    }
                }
            });
        }

        private String getTitleByLevel(int level) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < level; i++) {
                builder.append("#");
            }
            builder.append(" ");
            return builder.toString();
        }

        private void writeDirectory(String rootPath) throws Exception {
            BufferedReader reader = new BufferedReader(new FileReader(rootPath + "/TAG.md"));
            StringBuilder main = new StringBuilder();
            StringBuilder directory = new StringBuilder();
            directory.append("<!-- GFM-TOC -->")
                    .append(System.lineSeparator());
            String line;
            while ((line = reader.readLine()) != null) {
                main.append(line).append(System.lineSeparator());
                if (line.startsWith("# ")) {
                    directory.append("* [" + line.substring(2) + "](#" + line.substring(2) + ")" + System.lineSeparator());
                } else if (line.startsWith("## ")) {
                    directory.append("    * [" + line.substring(3) + "](#" + line.substring(3) + ")" + System.lineSeparator());
                } else if (line.startsWith("### ")) {
                    directory.append("        * [" + line.substring(4) + "](#" + line.substring(4) + ")" + System.lineSeparator());
                }
            }
            directory.append("<!-- GFM-TOC -->").
                    append(System.lineSeparator());

            PrintWriter writer = new PrintWriter(new File(rootPath + "/TAG.md"));
            writer.write("");
            writer.flush();

            writer.println(directory.toString());
            writer.println(main.toString());
            writer.flush();

            reader.close();
            writer.close();
        }

    }

}
