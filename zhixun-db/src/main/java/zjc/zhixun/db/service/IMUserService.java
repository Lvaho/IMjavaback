package zjc.zhixun.db.service;

import com.github.pagehelper.PageHelper;
import zjc.zhixun.db.dao.TUserMapper;
import zjc.zhixun.db.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IMUserService {
    @Resource
    private TUserMapper userMapper;

    public IMUser findById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public int updateById(IMUser user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public List<IMUser> querySelective(String uid,String displayName, String mobile, Integer page, Integer size, String order) {
        IMUserExample example = new IMUserExample();
        IMUserExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(uid)) {
            criteria.andUidLike("%" + uid + "%");
        }
        if (!StringUtils.isEmpty(displayName)) {
            criteria.andDisplayNameLike("%" + displayName + "%");
        }
        if (!StringUtils.isEmpty(mobile)) {
            criteria.andMobileLike("%" + mobile + "%");
        }
        criteria.andDeletedEqualTo(false);

        PageHelper.startPage(page, size);
        return userMapper.selectByExample(example);
    }

    public int count() {
        IMUserExample example = new IMUserExample();
        example.or().andDeletedEqualTo(false);
        return (int) userMapper.countByExample(example);
    }

    public List<IMUser> queryByDisplayName(String displayName) {
       IMUserExample example = new IMUserExample();
        example.or().andDisplayNameEqualTo(displayName).andDeletedEqualTo(false);
        return userMapper.selectByExample(example);
    }

    public boolean checkByDisplayName(String displayName) {
        IMUserExample example = new IMUserExample();
        example.or().andDisplayNameEqualTo(displayName).andDeletedEqualTo(false);
        return userMapper.countByExample(example) != 0;
    }

    public List<IMUser> queryByMobile(String mobile) {
        IMUserExample example = new IMUserExample();
        example.or().andMobileEqualTo(mobile).andDeletedEqualTo(false);
        return userMapper.selectByExample(example);
    }

    public void deleteById(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

}
