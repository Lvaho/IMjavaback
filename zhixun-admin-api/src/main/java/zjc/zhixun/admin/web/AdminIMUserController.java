package zjc.zhixun.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import zjc.zhixun.admin.annotation.RequiresPermissionsDesc;
import zjc.zhixun.core.util.ResponseUtil;
import zjc.zhixun.core.validator.Order;
import zjc.zhixun.db.domain.IMUser;
import zjc.zhixun.db.domain.TMessages31WithBLOBs;
import zjc.zhixun.db.service.IMUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/admin/imUser")
@Validated
public class AdminIMUserController {
    private final Log logger = LogFactory.getLog(AdminIMUserController.class);

    @Autowired
    private IMUserService userService;

    @RequiresPermissions("admin:imUser:list")
    @RequiresPermissionsDesc(menu = {"IM管理", "用户管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String uid,String displayName, String mobile,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<IMUser> userList = userService.querySelective(uid,displayName, mobile, page, limit, order);
        System.out.println("user查询!");
        return ResponseUtil.okList(userList);
    }

    @RequiresPermissions("admin:imUser:listDetail")
    @RequiresPermissionsDesc(menu = {"IM管理", "用户管理"}, button = "详情")
    @GetMapping("/detail")
    public Object userDetail(@NotNull Integer id) {
    	IMUser user=userService.findById(id);
        return ResponseUtil.ok(user);
    }

    @RequiresPermissions("admin:user:list")
    @RequiresPermissionsDesc(menu = {"IM管理", "用户管理"}, button = "编辑")
    @PostMapping("/update")
    public Object userUpdate(@RequestBody IMUser user) {
        return ResponseUtil.ok(userService.updateById(user));
    }

    @RequiresPermissions("admin:user:delete")
    @RequiresPermissionsDesc(menu = {"IM管理", "用户管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody IMUser user) {
        Integer id = user.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        userService.deleteById(id);
        return ResponseUtil.ok();
    }
}
