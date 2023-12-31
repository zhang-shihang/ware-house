package com.xms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.xms.pojo.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xms
 * @since 2023-07-02
 */
@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    IPage pagecc(IPage<Record> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
