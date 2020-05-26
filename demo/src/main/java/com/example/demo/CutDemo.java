package com.example.demo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yb
 * @description
 * @data 2020/5/22
 */
public class CutDemo {
        private static String fileName = "C:\\Users\\86178\\Desktop\\test.txt";
        /**
         *@Description: 按行分割文件
         *@Author: annecheng,2019/7/11
         */
        public static void splitField(long totalLine, int splitRow) {
            try {
                FileReader read = new FileReader(fileName);
                BufferedReader br = new BufferedReader(read);
                List<FileWriter> subFileList = new ArrayList<FileWriter>();
                long subFileSize = totalLine % splitRow == 0 ? totalLine / splitRow : totalLine / splitRow + 1;
                for (int i = 0; i < subFileSize; i++) {
                    subFileList.add(new FileWriter(fileName.substring(0, fileName.length() - 4) + "_" + (i + 1) + ".txt"));
                }

                for (Integer rowNum = 1; rowNum <= totalLine; ++rowNum) {
                    String row = br.readLine();
                    int subFieldIndex = rowNum % splitRow == 0 ? rowNum / splitRow - 1 : rowNum / splitRow;
                    subFileList.get(subFieldIndex).append(row + "\r\n");
                }

                for (int i = 0; i < subFileList.size(); i++) {
                    subFileList.get(i).close();
                }
                br.close();
            } catch (Exception e) {
                System.out.println("将大文件拆分成小文件异常，异常：" + e);
            }
        }

        public static void main(String[] args) throws IOException {
            long fileLineCount = Files.lines(Paths.get(fileName)).count();
            System.out.println("行数："+fileLineCount);
            splitField(fileLineCount-1,10);
        }
}
