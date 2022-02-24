package zjc.zhixun.db.service;

import zjc.zhixun.db.dao.*;
import com.github.pagehelper.PageHelper;
import zjc.zhixun.db.domain.ZhiXunStorage;
import zjc.zhixun.db.domain.ZhiXunStorageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ZhiXunStorageService {
    @Autowired
    private ZhiXunStorageMapper storageMapper;

    public void deleteByKey(String key) {
        ZhiXunStorageExample example = new ZhiXunStorageExample();
        example.or().andKeyEqualTo(key);
        storageMapper.logicalDeleteByExample(example);
    }

    public void add(ZhiXunStorage storageInfo) {
        storageInfo.setAddTime(LocalDateTime.now());
        storageInfo.setUpdateTime(LocalDateTime.now());
        storageMapper.insertSelective(storageInfo);
    }

    public ZhiXunStorage findByKey(String key) {
        ZhiXunStorageExample example = new ZhiXunStorageExample();
        example.or().andKeyEqualTo(key).andDeletedEqualTo(false);
        return storageMapper.selectOneByExample(example);
    }

    public int update(ZhiXunStorage storageInfo) {
        storageInfo.setUpdateTime(LocalDateTime.now());
        return storageMapper.updateByPrimaryKeySelective(storageInfo);
    }

    public ZhiXunStorage findById(Integer id) {
        return storageMapper.selectByPrimaryKey(id);
    }

    public List<ZhiXunStorage> querySelective(String key, String name, Integer page, Integer limit, String sort, String order) {
        ZhiXunStorageExample example = new ZhiXunStorageExample();
        ZhiXunStorageExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(key)) {
            criteria.andKeyEqualTo(key);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return storageMapper.selectByExample(example);
    }
}
