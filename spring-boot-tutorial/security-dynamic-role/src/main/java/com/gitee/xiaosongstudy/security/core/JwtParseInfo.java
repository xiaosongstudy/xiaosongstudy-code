package com.gitee.xiaosongstudy.security.core;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.Setter;


/**
 * jwt解析信息类<br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/10/2 19:48
 */
@Getter
@Setter
public class JwtParseInfo {
    private SignatureStatus signatureStatus;
    private Claims claims;
}
