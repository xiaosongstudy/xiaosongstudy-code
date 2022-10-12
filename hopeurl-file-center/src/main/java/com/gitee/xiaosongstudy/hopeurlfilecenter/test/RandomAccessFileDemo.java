package com.gitee.xiaosongstudy.hopeurlfilecenter.test;

import com.gitee.xiaosongstudy.hopeurlfilecenter.constant.Globals;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

/**
 * RandomAccessFile类测试
 * RandomAcessFile类是jdk原生的类，可以辅助我们随心所欲的操作【文件】
 *
 * @author hopeurl
 * @email myhopeurlife@foxmail
 * @since 2022/10/12 9:28
 */
public class RandomAccessFileDemo {
    private static final String FILE = "E:\\MyCode\\xiaosongstudy-code\\hopeurl-file-center\\src\\main\\java\\com\\gitee\\xiaosongstudy\\hopeurlfilecenter\\temp\\temp.txt";

    public static void main(String[] args) {
        rwAndSkipR();
    }

    /**
     * 读写测试/跳读
     */
    private static void rwAndSkipR() {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(FILE, Globals.File.MODE_RW); RandomAccessFile rFile = new RandomAccessFile(FILE,Globals.File.MODE_R)) {
            Emp emp1012 = Emp.builder().empId(1012).idNo("2018310140126").build();
            Emp emp1013 = Emp.builder().empId(1013).idNo("2018310140127").build();
            byte[] bytes1012 = emp1012.toString().getBytes(Charset.defaultCharset());
            randomAccessFile.write(bytes1012);
            randomAccessFile.write(emp1013.toString().getBytes(Charset.defaultCharset()));

            // 跳过第一个职工信息
            rFile.skipBytes(bytes1012.length);
            int len;
            byte[] buffer = new byte[1024 * 2];
            while ((len = rFile.read(buffer)) != -1) {
                System.out.println(new String(buffer,0,len));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 只读测试
     */
    private static void readOnly() {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(FILE, Globals.File.MODE_R)) {
            randomAccessFile.write(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
