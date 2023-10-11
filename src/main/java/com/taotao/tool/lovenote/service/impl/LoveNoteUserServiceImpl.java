package com.taotao.tool.lovenote.service.impl;

import com.taotao.tool.lovenote.model.LoveNoteUser;
import com.taotao.tool.lovenote.mapper.LoveNoteUserMapper;
import com.taotao.tool.lovenote.service.ILoveNoteUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taotao
 * @since 2023-10-11
 */
@Service
public class LoveNoteUserServiceImpl extends ServiceImpl<LoveNoteUserMapper, LoveNoteUser> implements ILoveNoteUserService {

    @Override
    public LoveNoteUser getUserByOpenid(String openid) {
        return query().eq("openid", openid).one();
    }
}
