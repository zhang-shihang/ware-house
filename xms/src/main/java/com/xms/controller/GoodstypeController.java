package com.xms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xms.common.Result;
import com.xms.pojo.Goodstype;
import com.xms.pojo.Storage;
import com.xms.service.GoodstypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xms
 * @since 2023-07-02
 */
@RestController
@RequestMapping("/goodstype")
public class GoodstypeController {
    @Resource
    private GoodstypeService goodstypeService;

    //    新增
    @PostMapping("/save")
    public Result save(@RequestBody Goodstype goodstype) {
        return goodstypeService.save(goodstype) ? Result.suc(goodstype) : Result.fail();
    }

    //    修改
    @PostMapping("/update")
    public Result update(@RequestBody Goodstype goodstype) {
        return goodstypeService.updateById(goodstype) ? Result.suc(goodstype) : Result.fail();
    }


    //    新增或修改
    @PostMapping("/saveorupdate")
    public boolean saveorupdate(@RequestBody Goodstype goodstype) {
        return goodstypeService.saveOrUpdate(goodstype);
    }

    //    删除
    @GetMapping("/delete")
    public Result delete(Integer id) {
        return goodstypeService.removeById(id) ? Result.suc() : Result.fail();
    }

    //分页
    @PostMapping("/listpage")
    public Result listpage(@RequestBody HashMap map) {
        System.out.println(map);
        String name = (String) map.get("name");
        Page<Goodstype> page = new Page();
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        LambdaQueryWrapper<Goodstype> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(Goodstype::getName, name);
        }
        IPage result = goodstypeService.page(page, lambdaQueryWrapper);
        return Result.suc(result.getRecords(), result.getTotal());
    }
    @GetMapping("/list")
    public Result getlist() {
        List<Goodstype> list = goodstypeService.list();
        return Result.suc(list);
    }
}
