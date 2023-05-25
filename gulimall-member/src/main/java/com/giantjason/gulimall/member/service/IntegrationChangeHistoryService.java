package com.giantjason.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.giantjason.common.utils.PageUtils;
import com.giantjason.gulimall.member.entity.IntegrationChangeHistoryEntity;

import java.util.Map;

/**
 * 积分变化历史记录
 *
 * @author GiantJason
 * @email 1720003053@qq.com
 * @date 2023-05-02 19:01:36
 */
public interface IntegrationChangeHistoryService extends IService<IntegrationChangeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

