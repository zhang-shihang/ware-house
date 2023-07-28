package com.xms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xms.common.Result;
import com.xms.pojo.Goods;
import com.xms.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xms
 * @since 2023-07-02
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    //    新增
    @PostMapping("/save")
    public Result save(@RequestBody Goods goods) {
        System.out.println(goods);
        return goodsService.save(goods) ? Result.suc(goods) : Result.fail();
    }

    //    修改
    @PostMapping("/update")
    public Result update(@RequestBody Goods goods) {
        return goodsService.updateById(goods) ? Result.suc(goods) : Result.fail();
    }


    //    新增或修改
    @PostMapping("/saveorupdate")
    public boolean saveorupdate(@RequestBody Goods goods) {
        return goodsService.saveOrUpdate(goods);
    }

    //    删除
    @GetMapping("/delete")
    public Result delete(Integer id) {
        return goodsService.removeById(id) ? Result.suc() : Result.fail();
    }

    //分页
    @PostMapping("/listpage")
    public Result listpage(@RequestBody HashMap map) {
        System.out.println(map);
        String name = (String) map.get("name");
        String goodstype= String.valueOf(map.get("goodstype")) ;
        String storage= String.valueOf(map.get("storage")) ;
        Page<Goods> page = new Page();
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(Goods::getName, name);
        }
        if (StringUtils.isNotBlank(storage) && !"null".equals(storage)) {
            lambdaQueryWrapper.eq(Goods::getStorage, storage);
        }
        if (StringUtils.isNotBlank(goodstype) && !"null".equals(goodstype)) {
            lambdaQueryWrapper.eq(Goods::getGoodstype, goodstype);
        }
        IPage result = goodsService.page(page, lambdaQueryWrapper);
        return Result.suc(result.getRecords(), result.getTotal());
    }
}
