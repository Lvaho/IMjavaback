package zjc.zhixun.db.service;

import com.github.pagehelper.PageHelper;
import zjc.zhixun.db.domain.ZhiXunLog;
import zjc.zhixun.db.domain.ZhiXunLogExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ZhiXunLogService {
    @Resource
    private zjc.zhixun.db.dao.ZhiXunLogMapper logMapper;

    public void deleteById(Integer id) {
        logMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(ZhiXunLog log) {
        log.setAddTime(LocalDateTime.now());
        log.setUpdateTime(LocalDateTime.now());
        logMapper.insertSelective(log);
    }

    public List<ZhiXunLog> querySelective(String name, Integer page, Integer size, String sort, String order) {
        ZhiXunLogExample example = new ZhiXunLogExample();
        ZhiXunLogExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andAdminLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return logMapper.selectByExample(example);
    }
}
