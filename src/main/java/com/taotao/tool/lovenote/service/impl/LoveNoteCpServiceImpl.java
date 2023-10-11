package com.taotao.tool.lovenote.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.lovenote.mapper.LoveNoteCpMapper;
import com.taotao.tool.lovenote.model.LoveNoteCp;
import com.taotao.tool.lovenote.model.LoveNoteUser;
import com.taotao.tool.lovenote.service.ILoveNoteCpService;
import com.taotao.tool.lovenote.service.ILoveNoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author taotao
 * @since 2023-10-11
 */
@Service
public class LoveNoteCpServiceImpl extends ServiceImpl<LoveNoteCpMapper, LoveNoteCp> implements ILoveNoteCpService {

    @Autowired
    private ILoveNoteUserService userService;

    @Override
    public LoveNoteCp getCpByOpenid(String openid) {
        LoveNoteCp cp = query().eq("inviter", openid).one();
        if (Objects.nonNull(cp)) {
            return cp;
        }
        cp = query().eq("invitee", openid).one();
        return cp;
    }

    @Override
    public void addCp(String inviter, String invitee) {
        LoveNoteCp cp = new LoveNoteCp();
        cp.setInviter(inviter);
        cp.setInvitee(invitee);
        LoveNoteUser inviterUser = userService.getUserByOpenid(inviter);
        LoveNoteUser inviteeUser = userService.getUserByOpenid(invitee);
        String name = inviterUser.getNickname() + "❤" + inviteeUser.getNickname();
        cp.setCpName(name);
        cp.setCpDescription(name);
        save(cp);
    }
}
