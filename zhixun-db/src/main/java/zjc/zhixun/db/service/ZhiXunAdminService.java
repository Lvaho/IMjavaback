package zjc.zhixun.db.service;

import com.github.pagehelper.PageHelper;
import zjc.zhixun.db.domain.ZhiXunAdmin;
import zjc.zhixun.db.domain.ZhiXunAdmin.Column;
import zjc.zhixun.db.domain.ZhiXunAdminExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ZhiXunAdminService {
    private final Column[] result = new Column[]{Column.id, Column.username, Column.avatar, Column.roleIds};
    @Resource
    private zjc.zhixun.db.dao.ZhiXunAdminMapper adminMapper;

    public List<ZhiXunAdmin> findAdmin(String username) {
        ZhiXunAdminExample example = new ZhiXunAdminExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }

    public ZhiXunAdmin findAdmin(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    public List<ZhiXunAdmin> querySelective(String username, Integer page, Integer limit, String sort, String order) {
        ZhiXunAdminExample example = new ZhiXunAdminExample();
        ZhiXunAdminExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return adminMapper.selectByExampleSelective(example, result);
    }

    public int updateById(ZhiXunAdmin admin) {
        admin.setUpdateTime(LocalDateTime.now());
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    public void deleteById(Integer id) {
        adminMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(ZhiXunAdmin admin) {
        admin.setAddTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.insertSelective(admin);
    }

    public ZhiXunAdmin findById(Integer id) {
        return adminMapper.selectByPrimaryKeySelective(id, result);
    }

    public List<ZhiXunAdmin> all() {
        ZhiXunAdminExample example = new ZhiXunAdminExample();
        example.or().andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }
}
