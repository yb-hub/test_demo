package com.yb.demo;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: Yang
 * @Date: 2020/05/23 8:50
 * @Description: 文件分割工具类
 */
public class FileCutUtil {

    //private static RandomAccessFile randomAccessFile;
    private static Set<StartEndPair> startEndPairs = new HashSet<StartEndPair>();
    private static File file;

    public static void fileCut(String fileSource,String destFilePath, int threadNumber, int fileNumber,int batchId) throws IOException {
//        if(batchId > 1){
//            return ;
//        }
        //创建一个可读的randomAccessFile
         file = new File(fileSource);
//        try {
//            randomAccessFile = new RandomAccessFile(file, "r");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        //创建固定个数的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(threadNumber);
        //对文件进行分片
        long everyLength = file.length() / fileNumber;
        String pairId = "0";
        System.out.println("====================开始分片========================");
        calculateStartEnd(0, everyLength, file.length(),pairId,batchId);
        //使用多线程对不同分区进行分割
        System.out.println("====================分片成功，开始切割==============");
        for (final StartEndPair startEndPair : startEndPairs) {
            System.out.println("分配分片：" + startEndPair);
            threadPool.execute(() -> {
                RandomAccessFile randomAccessFile = null;
                try {
                    randomAccessFile = new RandomAccessFile(file, "r");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                /**
                 * 起始位置
                 */
                long start = startEndPair.start;
                /**
                 * 分片长度
                 */
                long cutSize = startEndPair.end - startEndPair.start + 1;
                try {
                    randomAccessFile.seek(start);
                   //System.out.println("start:" + start);
                    long limit = cutSize;
                    while (limit > 0) {
                        //此处要是固定50m的话，会重复读取一部分数据
                        long shouldRead = 1024*1024*5 > limit ? limit : 1024*1024*5;
                        byte[] bytes = new byte[(int) shouldRead];
                        randomAccessFile.read(bytes);
                        //将数据流转为字符串，并写入到特定文件中
                        //String line = new String(bytes, "utf-8");
                        //System.out.println(line);
                        //可以使用分片名
                        File destFile = new File(destFilePath+ startEndPair.id+ ".txt");
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(destFile, true);
                            try {
                                fileOutputStream.write(bytes);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        limit = limit - 1024*1024*5;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                try {
//                    FileCutUtil.fileCut("D:\\cutfile\\" +"20200520-"+ startEndPair.id+ ".txt","D:\\cutfile1\\" +"20200520-",10, 300,batchId+1);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            });
        }

    }

    private static void calculateStartEnd(long start, long size, long fileLength,String pairId,int batchId) throws IOException {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ;
        }
        long tmpStart = start;
        int fileId = 1;
        while(tmpStart < fileLength - 1){
            //System.out.println("tmpStart:"+tmpStart);
            StartEndPair pair = new StartEndPair();
            pair.start = tmpStart;
            pair.id = String.valueOf(fileId++);
            randomAccessFile.seek(tmpStart);
            //byte startTmp = (byte) randomAccessFile.read();
            //System.out.println("starttamp:" + startTmp);
            long endPosition = tmpStart + size - 1;
            if (endPosition >= fileLength - 1) {
                pair.end = fileLength - 1;
                startEndPairs.add(pair);
                return;
            }
            randomAccessFile.seek(endPosition);
            byte tmp = (byte) randomAccessFile.read();
            //换行\n和回车\r
            while (tmp != '\n') {
                endPosition++;
                if (endPosition >= fileLength - 1) {
                    endPosition = fileLength - 1;
                    break;
                }
                randomAccessFile.seek(endPosition);
                tmp = (byte) randomAccessFile.read();
            }
            pair.end = endPosition;
            startEndPairs.add(pair);
            tmpStart = endPosition + 1;
        }
       // calculateStartEnd(endPosition + 1, size, fileLength,String.valueOf(Integer.parseInt(pairId)+1),batchId);
    }

//    private static void calculateStartEnd(long start, long size, long fileLength,String pairId,int batchId) throws IOException {
//        RandomAccessFile randomAccessFile = null;
//        try {
//            randomAccessFile = new RandomAccessFile(file, "r");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (start > fileLength - 1) {
//            return;
//        }
//        StartEndPair pair = new StartEndPair();
//        pair.id =  batchId+"-"+pairId;
//        pair.start = start;
//        randomAccessFile.seek(start);
//        byte startTmp = (byte) randomAccessFile.read();
//        System.out.println("starttamp:" + startTmp);
//        long endPosition = start + size - 1;
//        if (endPosition >= fileLength - 1) {
//            pair.end = fileLength - 1;
//            startEndPairs.add(pair);
//            return;
//        }
//
//        randomAccessFile.seek(endPosition);
//        byte tmp = (byte) randomAccessFile.read();
//        //换行\n和回车\r
//        while (tmp != '\n') {
//            endPosition++;
//            if (endPosition >= fileLength - 1) {
//                endPosition = fileLength - 1;
//                break;
//            }
//            randomAccessFile.seek(endPosition);
//            tmp = (byte) randomAccessFile.read();
//        }
//        pair.end = endPosition;
//        startEndPairs.add(pair);
//
//        calculateStartEnd(endPosition + 1, size, fileLength,String.valueOf(Integer.parseInt(pairId)+1),batchId);
//
//    }



    private static class StartEndPair {
        public String id;
        public long start;
        public long end;

        @Override
        public String toString() {
            return "star=" + start + ";end=" + end + ";id" + id;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (end ^ (end >>> 32));
            result = prime * result + (int) (start ^ (start >>> 32));
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            StartEndPair other = (StartEndPair) obj;
            if (end != other.end)
                return false;
            if (start != other.start)
                return false;
            return true;
        }

    }


    public static void main(String[] args) {
        try {
            System.out.println("====================开始切割文件====================");
            String separator = File.separator;
            //FileCutUtil.fileCut(separator+"root"+ separator +"cutfile"+ separator +"20200520.txt",separator+"root"+separator+"cutfile"+separator+"smallfile"+separator+"20200520-", 100, 200*2,0);
            FileCutUtil.fileCut("C:\\Users\\86178\\Desktop\\20200520-1.txt","C:\\Users\\86178\\Desktop\\20200520-", 10, 5,0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("====================完成文件切割====================");
    }
}
