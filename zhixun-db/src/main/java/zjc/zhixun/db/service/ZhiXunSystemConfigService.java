package zjc.zhixun.db.service;

import zjc.zhixun.db.dao.*;
import zjc.zhixun.db.domain.ZhiXunSystem;
import zjc.zhixun.db.domain.ZhiXunSystemExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZhiXunSystemConfigService {
    @Resource
    private ZhiXunSystemMapper systemMapper;

    public Map<String, String> queryAll() {
        ZhiXunSystemExample example = new ZhiXunSystemExample();
        example.or().andDeletedEqualTo(false);

        List<ZhiXunSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> systemConfigs = new HashMap<>();
        for (ZhiXunSystem item : systemList) {
            systemConfigs.put(item.getKeyName(), item.getKeyValue());
        }

        return systemConfigs;
    }

    public Map<String, String> listMail() {
        ZhiXunSystemExample example = new ZhiXunSystemExample();
        example.or().andKeyNameLike("ZhiXun_mall_%").andDeletedEqualTo(false);
        List<ZhiXunSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(ZhiXunSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listWx() {
        ZhiXunSystemExample example = new ZhiXunSystemExample();
        example.or().andKeyNameLike("ZhiXun_wx_%").andDeletedEqualTo(false);
        List<ZhiXunSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(ZhiXunSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listOrder() {
        ZhiXunSystemExample example = new ZhiXunSystemExample();
        example.or().andKeyNameLike("ZhiXun_order_%").andDeletedEqualTo(false);
        List<ZhiXunSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(ZhiXunSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listExpress() {
        ZhiXunSystemExample example = new ZhiXunSystemExample();
        example.or().andKeyNameLike("ZhiXun_express_%").andDeletedEqualTo(false);
        List<ZhiXunSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(ZhiXunSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public void updateConfig(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            ZhiXunSystemExample example = new ZhiXunSystemExample();
            example.or().andKeyNameEqualTo(entry.getKey()).andDeletedEqualTo(false);

            ZhiXunSystem system = new ZhiXunSystem();
            system.setKeyName(entry.getKey());
            system.setKeyValue(entry.getValue());
            system.setUpdateTime(LocalDateTime.now());
            systemMapper.updateByExampleSelective(system, example);
        }

    }

    public void addConfig(String key, String value) {
        ZhiXunSystem system = new ZhiXunSystem();
        system.setKeyName(key);
        system.setKeyValue(value);
        system.setAddTime(LocalDateTime.now());
        system.setUpdateTime(LocalDateTime.now());
        systemMapper.insertSelective(system);
    }
}
