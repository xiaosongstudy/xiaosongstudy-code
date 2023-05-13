package com.gitee.xiaosongstudy.sourcetrack.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class FdtcM {

    /**
     * 节点编号，保证解析到的企业的顺序
     */
    private Integer nodeNo;
    /**
     * 企业名称
     */
    private String name;

    /**
     * 企业流水号
     */
    private Integer refcode;
}