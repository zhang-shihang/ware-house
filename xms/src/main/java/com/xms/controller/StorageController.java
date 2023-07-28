package com.xms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xms.common.Result;
import com.xms.pojo.Menu;
import com.xms.pojo.Storage;
import com.xms.pojo.User;
import com.xms.service.StorageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xms
 * @since 2023-07-01
 */
@RestController
@RequestMapping("/storage")
public class StorageController {
    @Resource
    private StorageService storageService;

    //    新增
    @PostMapping("/save")
    public Result save(@RequestBody Storage storage) {
        return storageService.save(storage) ? Result.suc(storage) : Result.fail();
    }

    //    修改
    @PostMapping("/update")
    public Result update(@RequestBody Storage storage) {
        return storageService.updateById(storage) ? Result.suc(storage) : Result.fail();
    }


    //    新增或修改
    @PostMapping("/saveorupdate")
    public boolean saveorupdate(@RequestBody Storage storage) {
        return storageService.saveOrUpdate(storage);
    }

    //    删除
    @GetMapping("/delete")
    public Result delete(Integer id) {
        return storageService.removeById(id) ? Result.suc() : Result.fail();
    }

    //分页
    @PostMapping("/listpage")
    public Result listpage(@RequestBody HashMap map) {
        String name = (String) map.get("name");
        Page<Storage> page = new Page();
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(Storage::getName, name);
        }
        IPage result = storageService.page(page, lambdaQueryWrapper);
        return Result.suc(result.getRecords(), result.getTotal());
    }
    @GetMapping("/list")
    public Result getlist() {
        List<Storage> list = storageService.list();
        return Result.suc(list);
    }
}


