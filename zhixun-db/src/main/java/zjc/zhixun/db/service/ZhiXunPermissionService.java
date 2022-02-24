package zjc.zhixun.db.service;

import zjc.zhixun.db.domain.ZhiXunPermission;
import zjc.zhixun.db.domain.ZhiXunPermissionExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ZhiXunPermissionService {
    @Resource
    private zjc.zhixun.db.dao.ZhiXunPermissionMapper permissionMapper;

    public Set<String> queryByRoleIds(Integer[] roleIds) {
        Set<String> permissions = new HashSet<String>();
        if(roleIds.length == 0){
            return permissions;
        }

        ZhiXunPermissionExample example = new ZhiXunPermissionExample();
        example.or().andRoleIdIn(Arrays.asList(roleIds)).andDeletedEqualTo(false);
        List<ZhiXunPermission> permissionList = permissionMapper.selectByExample(example);

        for(ZhiXunPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }


    public Set<String> queryByRoleId(Integer roleId) {
        Set<String> permissions = new HashSet<String>();
        if(roleId == null){
            return permissions;
        }

        ZhiXunPermissionExample example = new ZhiXunPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        List<ZhiXunPermission> permissionList = permissionMapper.selectByExample(example);

        for(ZhiXunPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }

    public boolean checkSuperPermission(Integer roleId) {
        if(roleId == null){
            return false;
        }

        ZhiXunPermissionExample example = new ZhiXunPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andPermissionEqualTo("*").andDeletedEqualTo(false);
        return permissionMapper.countByExample(example) != 0;
    }

    public void deleteByRoleId(Integer roleId) {
        ZhiXunPermissionExample example = new ZhiXunPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        permissionMapper.logicalDeleteByExample(example);
    }

    public void add(ZhiXunPermission ZhiXunPermission) {
        ZhiXunPermission.setAddTime(LocalDateTime.now());
        ZhiXunPermission.setUpdateTime(LocalDateTime.now());
        permissionMapper.insertSelective(ZhiXunPermission);
    }
}
