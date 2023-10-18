package com.taotao.tool.lovenote.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.admin.service.WorkWxService;
import com.taotao.tool.common.util.ApiAssertUtils;
import com.taotao.tool.lovenote.mapper.LoveNoteCpMapper;
import com.taotao.tool.lovenote.model.LoveNoteCp;
import com.taotao.tool.lovenote.model.LoveNoteUser;
import com.taotao.tool.lovenote.service.ILoveNoteCpService;
import com.taotao.tool.lovenote.service.ILoveNoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Properties;

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
    @Autowired
    private WorkWxService workWxService;

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
    public void addCp(LoveNoteUser inviter, LoveNoteUser invitee) {
        LoveNoteCp cp1 = getCpByOpenid(inviter.getOpenid());
        LoveNoteCp cp2 = getCpByOpenid(invitee.getOpenid());
        ApiAssertUtils.isTrue(Objects.isNull(cp1) && Objects.isNull(cp2), "你或TA已经有组过CP");
        LoveNoteCp cp = new LoveNoteCp();
        cp.setInviter(inviter.getOpenid());
        cp.setInvitee(invitee.getOpenid());
        String name = inviter.getNickname() + "❤" + invitee.getNickname();
        cp.setCpName(name);
        cp.setCpDescription(name);
        save(cp);
        sendWxNotice(inviter, invitee, cp);
    }

    private void sendWxNotice(LoveNoteUser inviter, LoveNoteUser invitee, LoveNoteCp cp) {
        Properties properties = new Properties();
        properties.setProperty("inviter", inviter.getNickname());
        properties.setProperty("invitee", invitee.getNickname());
        properties.setProperty("cpName", cp.getCpName());
        workWxService.sendMessage("love_note_notice_addcp", properties);
    }
}
