package com.taotao.tool.lovenote.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.tool.system.dto.resp.LoveNoteLoginResp;
import com.taotao.tool.lovenote.entity.LoveNoteUserRegisterRequest;
import com.taotao.tool.lovenote.model.LoveNoteUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taotao
 * @since 2023-10-11
 */
public interface ILoveNoteUserService extends IService<LoveNoteUser> {

    LoveNoteUser getUserByOpenid(String openid);

    LoveNoteLoginResp login(String code);

    LoveNoteLoginResp register(LoveNoteUserRegisterRequest request);

    String getToken(String openid);

    LoveNoteUser verifyToken(String token, String openid);
}
