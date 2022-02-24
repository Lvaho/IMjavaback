package zjc.zhixun.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import zjc.zhixun.db.dao.TMessages31Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IMPromoterTest {
    @Autowired
    private TMessages31Mapper messages31Mapper;

    @Test
    public void test() {
        List<String> list=removeDuplicate(messages31Mapper.selectFromByTarget("L5LDLDyy"));
        System.out.println(list);
    }

    public static List<String> removeDuplicate(List<String> list) {
        HashSet<String> h = new HashSet<String>(list);
        list.clear();
        list.addAll(h);
        return list;
    }
}
