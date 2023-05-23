package com.giantjason.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.giantjason.common.utils.PageUtils;
import com.giantjason.gulimall.member.entity.MemberLevelEntity;

import java.util.Map;

/**
 * 会员等级
 *
 * @author GiantJason
 * @email 1720003053@qq.com
 * @date 2023-05-02 19:01:36
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

