package zjc.zhixun.db.service;

import com.github.pagehelper.PageHelper;
import zjc.zhixun.db.dao.TMessages31Mapper;
import zjc.zhixun.db.domain.IMUserExample;
import zjc.zhixun.db.domain.TMessages31Example;
import zjc.zhixun.db.domain.TMessages31WithBLOBs;
import zjc.zhixun.db.domain.TMessages31WithBLOBs.Column;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class IMMessageService {
    @Resource
    private TMessages31Mapper messageMapper;
    private  Column[] columns = new Column[]{Column.id, Column.mid, Column.from, Column.target, Column.searchableKey,Column.dt,Column.contentType};

    public List<TMessages31WithBLOBs> query(Integer page, Integer limit, String sort, String order) {
        TMessages31Example example = new TMessages31Example();
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, limit);
        return messageMapper.selectByExampleSelective(example, columns);
    }

    public List<TMessages31WithBLOBs> query(Integer page, Integer limit) {
        return query(page, limit, null, null);
    }

    public TMessages31WithBLOBs findById(Integer id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    public List<TMessages31WithBLOBs> querySelective(String id, String from, String target, String searchableKey, Timestamp tStart, Timestamp tEnd, Integer page, Integer size, String order) {
        TMessages31Example example = new TMessages31Example();
        TMessages31Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(id)) {
            criteria.andIdEqualTo(Integer.valueOf(id));
        }
        if (!StringUtils.isEmpty(from)) {
            criteria.andFromLike("%" + from + "%");
        }
        if (!StringUtils.isEmpty(target)) {
            criteria.andTargetLike("%" + target + "%");
        }
        if (!StringUtils.isEmpty(tStart)) {
            criteria.andDtGreaterThan(tStart);
        }
        if (!StringUtils.isEmpty(tEnd)) {
            criteria.andDtLessThan(tEnd);
        }
        if (!StringUtils.isEmpty(searchableKey)) {
            criteria.andSearchableKeyLike("%" +searchableKey + "%");
        }

        PageHelper.startPage(page, size);
        return messageMapper.selectByExample(example);
    }

    public int updateById(TMessages31WithBLOBs message) {
        return messageMapper.updateByPrimaryKeySelective(message);
    }

    public void deleteById(Integer id) {
        messageMapper.deleteByPrimaryKey(id);
    }

    public void add(TMessages31WithBLOBs message) {
        messageMapper.insertSelective(message);
    }

    public List<TMessages31WithBLOBs> all() {
        TMessages31Example example = new TMessages31Example();
        return messageMapper.selectByExample(example);
    }

    public int count() {
        TMessages31Example example = new TMessages31Example();
        return (int) messageMapper.countByExample(example);
    }
}
