package zjc.zhixun.admin.web;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import zjc.zhixun.admin.annotation.RequiresPermissionsDesc;
import zjc.zhixun.core.util.ResponseUtil;
import zjc.zhixun.core.validator.Order;
import zjc.zhixun.db.domain.IMUser;
import zjc.zhixun.db.service.IMPromoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/imPromoter")
@Validated
public class AdminIMPromoterController {
    private final Log logger = LogFactory.getLog(AdminIMPromoterController.class);

    @Autowired
    private IMPromoterService imPromoterService;

    @RequiresPermissions("admin:imPromoter:list")
    @RequiresPermissionsDesc(menu = {"IM管理", "推广者查询"}, button = "查询")
    @GetMapping("/list")
    public Object list(String uid,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        Boolean flag=imPromoterService.judgeCustom(uid);
        if(flag){
            List<IMUser> userList = imPromoterService.querySelective(uid,page,limit,order);
            System.out.println("推广者查询!");
            return ResponseUtil.okList(userList);
        }else{
            return ResponseUtil.noCustom();
        }
    }
}
