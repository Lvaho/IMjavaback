package zjc.zhixun.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import zjc.zhixun.db.domain.*;

@Mapper
public interface TMessages31Mapper {

    long countByExample(TMessages31Example example);


    int deleteByExample(TMessages31Example example);

    int deleteByPrimaryKey(Integer id);



    int insert(TMessages31WithBLOBs record);

    int insertSelective(TMessages31WithBLOBs record);



    TMessages31 selectOneByExample(TMessages31Example example);

    TMessages31WithBLOBs selectOneByExampleSelective(@Param("example") TMessages31Example example, @Param("selective") TMessages31WithBLOBs.Column... selective);

    TMessages31WithBLOBs selectOneByExampleWithBLOBs(TMessages31Example example);

    List<TMessages31WithBLOBs> selectByExampleSelective(@Param("example") TMessages31Example example, @Param("selective") TMessages31WithBLOBs.Column... selective);

    List<TMessages31WithBLOBs> selectByExampleWithBLOBs(TMessages31Example example);

    List<TMessages31WithBLOBs> selectByExample(TMessages31Example example);

    TMessages31WithBLOBs selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") TMessages31WithBLOBs.Column... selective);

    TMessages31WithBLOBs selectByPrimaryKey(Integer id);

//    @Select("select _from from t_messages_33 where _target=#{target}")
    List<String> selectFromByTarget(String target);




    int updateByExampleSelective(@Param("record") TMessages31WithBLOBs record, @Param("example") TMessages31Example example);

    int updateByExampleWithBLOBs(@Param("record") TMessages31WithBLOBs record, @Param("example") TMessages31Example example);

    int updateByExample(@Param("record") TMessages31 record, @Param("example") TMessages31Example example);

    int updateByPrimaryKeySelective(TMessages31WithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TMessages31WithBLOBs record);

    int updateByPrimaryKey(TMessages31 record);
}