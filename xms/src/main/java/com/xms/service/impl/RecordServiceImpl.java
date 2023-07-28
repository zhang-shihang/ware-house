package com.xms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xms.pojo.Record;
import com.xms.mapper.RecordMapper;
import com.xms.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xms
 * @since 2023-07-02
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
@Resource
private  RecordMapper recordMapper;
    @Override
    public IPage pagecc(IPage<Record> page, Wrapper wrapper) {
        return recordMapper.pagecc(page,wrapper);
    }
}
