package com.xms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xms.pojo.Record;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xms
 * @since 2023-07-02
 */
public interface RecordService extends IService<Record> {
    IPage pagecc(IPage<Record> page, Wrapper wrapper);
}

