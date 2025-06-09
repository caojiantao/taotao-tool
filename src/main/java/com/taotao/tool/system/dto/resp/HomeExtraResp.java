package com.taotao.tool.system.dto.resp;

import lombok.Data;

@Data
public class HomeExtraResp {

    private UserInfo userInfo;

    @Data
    public static class UserInfo {
        private String nickname;
        private String avatar;
    }
}
