package zjc.zhixun.db.service;

import com.github.pagehelper.PageHelper;
import zjc.zhixun.db.dao.TMessages31Mapper;
import zjc.zhixun.db.dao.TUserMapper;
import zjc.zhixun.db.domain.IMUser;
import zjc.zhixun.db.domain.IMUserExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class IMPromoterService {
    @Resource
    private TUserMapper userMapper;
    @Resource
    private TMessages31Mapper messages31Mapper;

    public List<IMUser> querySelective(String uid,Integer page, Integer size, String order) {
        List<String> list=removeDuplicate(messages31Mapper.selectFromByTarget(uid));
        List<IMUser> userList=new ArrayList<>();
        for (String from:list) {
            IMUserExample example2 = new IMUserExample();
            IMUserExample.Criteria criteria2 = example2.createCriteria();
            if (!StringUtils.isEmpty(from)) {
                criteria2.andUidLike("%" + from + "%");
            }
            IMUser imUser=userMapper.selectOneByExample(example2);
            if(imUser.getType()==0){
                userList.add(imUser);
            }
        }

        PageHelper.startPage(page, size);
        return userList;
    }

    public Boolean judgeCustom(String uid){
        IMUserExample example = new IMUserExample();
        IMUserExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(uid)){
            criteria.andUidEqualTo(uid);
        }
        IMUser imUser=userMapper.selectOneByExample(example);
        if(imUser.getType()==3){
            return true;
        }else{
            return false;
        }
    }
    public static List<String> removeDuplicate(List<String> list) {
        HashSet<String> h = new HashSet<String>(list);
        list.clear();
        list.addAll(h);
        return list;
    }

}
