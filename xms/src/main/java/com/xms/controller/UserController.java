package com.xms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xms.common.Result;
import com.xms.pojo.Menu;
import com.xms.pojo.User;
import com.xms.service.MenuService;
import com.xms.service.UserService;
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
 * @since 2023-06-26
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private MenuService menuService;

    //    获取用户数据
    @GetMapping("/getuser")
    public List<User> getuser() {
        return userService.list();
    }

    //    新增
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        return userService.save(user) ? Result.suc(user) : Result.fail();
    }

    //    修改
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        return userService.updateById(user) ? Result.suc(user) : Result.fail();
    }


    //    新增或修改
    @PostMapping("/saveorupdate")
    public boolean saveorupdate(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    //    删除
    @GetMapping("/delete")
    public Result delete(Integer id) {
        return userService.removeById(id) ? Result.suc() : Result.fail();
    }

    //    查询（模糊/匹配）
    @PostMapping("/getuserp")
    public Result getuserp(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(user.getName())) {
            lambdaQueryWrapper.like(User::getName, user.getName());
        } else {
        }
        return Result.suc(userService.list(lambdaQueryWrapper));
    }

    //分页
    @PostMapping("/listpage")
    public Result listpage(@RequestBody HashMap map) {
        String name = (String) map.get("name");
        String sex = String.valueOf(map.get("sex")) ;
        String roleId = String.valueOf(map.get("roleId")) ;
        Page<User> page = new Page();
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(User::getName, name);
        }
        if (StringUtils.isNotBlank(sex)) {
            lambdaQueryWrapper.like(User::getSex, sex);
        }
        if (StringUtils.isNotBlank(roleId)) {
            lambdaQueryWrapper.like(User::getRoleId, roleId);
        }
        IPage result = userService.page(page, lambdaQueryWrapper);
        return Result.suc(result.getRecords(), result.getTotal());
    }

    //根据no查询是否有此用户
    @GetMapping("/findByno")
    public Result findByno(@RequestParam String no) {
        List<User> list = userService.lambdaQuery().eq(User::getNo, no).list();
        return list.size() > 0 ? Result.suc(list) : Result.fail();
    }

    //    登录
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        List<User> list = userService.lambdaQuery().eq(User::getNo, user.getNo()).eq(User::getPassword, user.getPassword()).list();
        if(list.size()>0){
            User user1=list.get(0);
            List<Menu> meunlist = menuService.lambdaQuery().like(Menu::getMenuright,user1.getRoleId()).list();
            HashMap res=new HashMap();
            res.put("user",user1);
            res.put("menu",meunlist);
            return Result.suc(res);
        }
        return  Result.fail();
    }
}
