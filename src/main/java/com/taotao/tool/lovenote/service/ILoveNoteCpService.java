package com.taotao.tool.lovenote.service;

import com.taotao.tool.lovenote.model.LoveNoteCp;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.tool.lovenote.model.LoveNoteUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taotao
 * @since 2023-10-11
 */
public interface ILoveNoteCpService extends IService<LoveNoteCp> {

    LoveNoteCp getCpByOpenid(String openid);

    void addCp(LoveNoteUser inviter, LoveNoteUser invitee);
}
