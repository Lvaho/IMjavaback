package zjc.zhixun.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import zjc.zhixun.admin.annotation.RequiresPermissionsDesc;
import zjc.zhixun.core.util.ResponseUtil;
import zjc.zhixun.core.validator.Order;
import zjc.zhixun.db.domain.TMessages31WithBLOBs;
import zjc.zhixun.db.service.IMMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/message")
@Validated
public class AdminMessageController {
    private final Log logger = LogFactory.getLog(AdminMessageController.class);

    @Autowired
    private IMMessageService messageService;

    @RequiresPermissions("admin:message:list")
    @RequiresPermissionsDesc(menu = {"IM管理", "消息管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String id, String from, String target, String searchableKey,  @RequestParam(required =false ) Timestamp tStart,@RequestParam(required =false ) Timestamp tEnd,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<TMessages31WithBLOBs> messageList = messageService.querySelective(id, from, target, searchableKey,tStart,tEnd,page,limit, order);
        System.out.println("message查询！");
        return ResponseUtil.okList(messageList);
    }

    private Object validate(TMessages31WithBLOBs message) {
        Long mid = message.getMid();
        if (StringUtils.isEmpty(mid)) {
            return ResponseUtil.badArgument();
        }

        String  from=message.getFrom();
        if (StringUtils.isEmpty(from)) {
            return ResponseUtil.badArgument();
        }

        String  target=message.getTarget();
        if (StringUtils.isEmpty(target)) {
            return ResponseUtil.badArgument();
        }

        Integer  contentType=message.getContentType();
        if (StringUtils.isEmpty(contentType)) {
            return ResponseUtil.badArgument();
        }

        Timestamp dt=message.getDt();
        if (StringUtils.isEmpty(dt)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

//    @RequiresPermissions("admin:message:create")
////    @RequiresPermissionsDesc(menu = {"IM管理", "消息管理"}, button = "添加")
////    @PostMapping("/create")
////    public Object create(@RequestBody TMessages31WithBLOBs message) {
////        Object error = validate(message);
////        if (error != null) {
////            return error;
////        }
////        messageService.add(message);
////        return ResponseUtil.ok(message);
////    }

//    @RequiresPermissions("admin:message:read")
//    @RequiresPermissionsDesc(menu = {"IM管理", "消息管理"}, button = "详情")
//    @GetMapping("/read")
//    public Object read(@NotNull Integer id) {
//        TMessages31WithBLOBs brand = messageService.findById(id);
//        return ResponseUtil.ok(brand);
//    }

    @RequiresPermissions("admin:message:update")
    @RequiresPermissionsDesc(menu = {"IM管理", "消息管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody TMessages31WithBLOBs message) {
        Object error = validate(message);
        if (error != null) {
            return error;
        }
        if (messageService.updateById(message) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(message);
    }

    @RequiresPermissions("admin:message:delete")
    @RequiresPermissionsDesc(menu = {"IM管理", "消息管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody TMessages31WithBLOBs message) {
        Integer id = message.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        messageService.deleteById(id);
        return ResponseUtil.ok();
    }

}
