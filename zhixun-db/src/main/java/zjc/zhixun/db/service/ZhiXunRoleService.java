package zjc.zhixun.db.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import zjc.zhixun.db.domain.ZhiXunRole;
import zjc.zhixun.db.domain.ZhiXunRoleExample;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ZhiXunRoleService {
    @Resource
    private zjc.zhixun.db.dao.ZhiXunRoleMapper roleMapper;


    public Set<String> queryByIds(Integer[] roleIds) {
        Set<String> roles = new HashSet<String>();
        if(roleIds.length == 0){
            return roles;
        }

        ZhiXunRoleExample example = new ZhiXunRoleExample();
        example.or().andIdIn(Arrays.asList(roleIds)).andEnabledEqualTo(true).andDeletedEqualTo(false);
        List<ZhiXunRole> roleList = roleMapper.selectByExample(example);

        for(ZhiXunRole role : roleList){
            roles.add(role.getName());
        }

        return roles;

    }

    public List<ZhiXunRole> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        ZhiXunRoleExample example = new ZhiXunRoleExample();
        ZhiXunRoleExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return roleMapper.selectByExample(example);
    }

    public ZhiXunRole findById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public void add(ZhiXunRole role) {
        role.setAddTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.insertSelective(role);
    }

    public void deleteById(Integer id) {
        roleMapper.logicalDeleteByPrimaryKey(id);
    }

    public void updateById(ZhiXunRole role) {
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.updateByPrimaryKeySelective(role);
    }

    public boolean checkExist(String name) {
        ZhiXunRoleExample example = new ZhiXunRoleExample();
        example.or().andNameEqualTo(name).andDeletedEqualTo(false);
        return roleMapper.countByExample(example) != 0;
    }

    public List<ZhiXunRole> queryAll() {
        ZhiXunRoleExample example = new ZhiXunRoleExample();
        example.or().andDeletedEqualTo(false);
        return roleMapper.selectByExample(example);
    }
}
