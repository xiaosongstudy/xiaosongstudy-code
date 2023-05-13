package com.gitee.xiaosongstudy.sourcetrack.test;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 测试解析业务预报表
 *
 * @author shiping.song
 * @date 2023/5/5 00:22
 */
public class SheetTest {

    public static void main(String[] args) {
        List<String> allCompanies = Arrays.asList("a", "b", "c", "d");
        // a,b  b,c  c,d
        // a b c d
        //b c d
        List<FdtcM> fdtcMList = new ArrayList<>();
        fdtcMList.add(new FdtcM(2, "企业A", 10));
        fdtcMList.add(new FdtcM(4, "企业B", 20));
        fdtcMList.add(new FdtcM(1, "企业C", 30));
        List<Result> resultList = new ArrayList<>();
        // 首先需要对解析到的企业信息依照nodeNo进行升序排序
        List<FdtcM> sortedFdtcMList = fdtcMList.stream().sorted(Comparator.comparingInt(FdtcM::getNodeNo)).collect(Collectors.toList());
        for (int i = 0; i < sortedFdtcMList.size() - 1; i++) {
            Result result = new Result();
            result.setSellerName(sortedFdtcMList.get(i).getName());
            result.setBuyerName(sortedFdtcMList.get(i + 1).getName());
            resultList.add(result);
        }
        resultList.forEach(System.out::println);
    }
}

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@ToString(callSuper = true)
class Result extends FdtcM {
    /**
     * 上有企业名称
     */
    private String sellerName;
    /**
     * 下游企业名称
     */
    private String buyerName;
}
