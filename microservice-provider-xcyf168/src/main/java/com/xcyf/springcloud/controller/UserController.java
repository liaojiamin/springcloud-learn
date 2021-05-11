package com.xcyf.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xcyf.springcloud.entity.XcyfUser;
import com.xcyf.springcloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 代码生成器，参考源码测试用例：
 * <p>
 * /mybatis-plus/src/test/java/com/baomidou/mybatisplus/test/generator/MysqlGenerator.java
 */
@RestController
@RequestMapping("/user")
public class UserController extends ApiController {

    @Autowired
    private IUserService userService;


    /**
     * http://localhost:8080/user/test
     */
    @GetMapping("/test")
    public IPage<XcyfUser> test() {
        return userService.page(new Page<XcyfUser>(0, 12), null);
    }

    /**
     * AR 部分测试
     * http://localhost:8080/user/test1
     */
    @GetMapping("/test1")
    public IPage<XcyfUser> test1() {
        XcyfUser user = new XcyfUser();
        System.err.println("删除所有：" + user.delete(null));
        user.setStatus(1);
        user.setCreateTime(new Date());
        user.setPhone("18594208136");
        user.insert();
        System.err.println("查询插入结果：" + user.selectById(user.getUserID()).toString());
        user.setNickName("mybatis-plus-ar");
        System.err.println("更新：" + user.updateById());
        return user.selectPage(new Page<XcyfUser>(0, 12), null);
    }

    /**
     * 增删改查 CRUD
     * http://localhost:8080/user/test2
     */
    @GetMapping("/test2")
    public XcyfUser test2() {
        System.err.println("删除一条数据：" + userService.removeById(33L));
        System.err.println("插入一条数据：" + userService.save(buildUserEntity("ljmadmin1")));
        XcyfUser user = buildUserEntity("ljmadmin2");
        boolean result = userService.save(user);
        // 自动回写的ID
        Long id = user.getUserID();
        System.err.println("插入一条数据：" + result + ", 插入信息：" + user.toString());
        System.err.println("查询：" + userService.getById(id).toString());
        System.err.println("更新一条数据：" + userService.updateById(buildUserEntity("ljmadmin5")));
        for (int i = 0; i < 5; ++i) {
            userService.save(buildUserEntity("ljmadmin4"));
        }
        IPage<XcyfUser> userListPage = userService.page(new Page<XcyfUser>(1, 5), new QueryWrapper<XcyfUser>());
        System.err.println("total=" + userListPage.getTotal() + ", current list size=" + userListPage.getRecords().size());

        userListPage = userService.page(new Page<XcyfUser>(1, 5), new QueryWrapper<XcyfUser>().orderByDesc("nickName"));
        System.err.println("total=" + userListPage.getTotal() + ", current list size=" + userListPage.getRecords().size());
        return userService.getById(1L);
    }

    /**
     * 插入 OR 修改
     * http://localhost:8080/user/test3
     */
    @GetMapping("/test3")
    public XcyfUser test3() {
        XcyfUser user = buildUserEntity("ljmadmin6");
        user.setPhone("6666666");
        userService.saveOrUpdate(user);
        return userService.getById(1L);
    }

    /**
     * http://localhost:8080/user/add
     */
    @GetMapping("/add")
    public Object addUser() {
        XcyfUser user = buildUserEntity("ljmadmin7");
        user.setPhone("7777777");
        return userService.save(user);
    }


    /**
     * http://localhost:8080/user/select_wrapper
     */
    @GetMapping("/select_wrapper")
    public Object getUserByWrapper() {
        return userService.selectListByWrapper();
    }

    /**
     * <p>
     * 参数模式分页
     * </p>
     * <p>
     * 7、分页 size 一页显示数量  current 当前页码
     * 方式一：http://localhost:8080/user/page?size=1&current=1<br>
     * <p>
     * 集合模式，不进行分页直接返回所有结果集：
     * http://localhost:8080/user/page?listMode=true
     */
    @GetMapping("/page")
    public IPage page(Page page, boolean listMode) {
        if (listMode) {
            // size 小于 0 不在查询 total 及分页，自动调整为列表模式。
            // 注意！！这个地方自己控制好！！
            page.setSize(-1);
        }
        return userService.page(page, null);
    }

    /**
     * 测试事物
     * http://localhost:8080/user/test_transactional<br>
     * 访问如下并未发现插入数据说明事物可靠！！<br>
     * http://localhost:8080/user/test<br>
     * <br>
     * 启动  Application 加上 @EnableTransactionManagement 注解其实可无默认貌似就开启了<br>
     * 需要事物的方法加上 @Transactional 必须的哦！！
     */
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/test_transactional")
    public void testTransactional() {
        XcyfUser user = buildUserEntity("ljmadmin8888");
        userService.save(user);
        System.out.println(" 这里手动抛出异常，自动回滚数据");
        throw new RuntimeException();
    }

    public XcyfUser buildUserEntity(String nickName){
        Date date = new Date();
        XcyfUser user = new XcyfUser();
        user.setAddress("1111");
        user.setBirthday(date);
        user.setCreateTime(date);
        user.setEmail("645121107@qq.com");
        user.setGender(1);
        user.setLastRegisterTime(date);
        user.setNickName(nickName);
        user.setPhone("18594208134");
        user.setUserType(1);
        user.setStatus(1);
        return user;
    }
}
