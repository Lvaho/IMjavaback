package zjc.zhixun.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zjc.zhixun.db.domain.ZhiXunSystem;
import zjc.zhixun.db.domain.ZhiXunSystemExample;

public interface ZhiXunSystemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    long countByExample(ZhiXunSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    int deleteByExample(ZhiXunSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    int insert(ZhiXunSystem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    int insertSelective(ZhiXunSystem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    ZhiXunSystem selectOneByExample(ZhiXunSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    ZhiXunSystem selectOneByExampleSelective(@Param("example") ZhiXunSystemExample example, @Param("selective") ZhiXunSystem.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    List<ZhiXunSystem> selectByExampleSelective(@Param("example") ZhiXunSystemExample example, @Param("selective") ZhiXunSystem.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    List<ZhiXunSystem> selectByExample(ZhiXunSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    ZhiXunSystem selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") ZhiXunSystem.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    ZhiXunSystem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    ZhiXunSystem selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ZhiXunSystem record, @Param("example") ZhiXunSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ZhiXunSystem record, @Param("example") ZhiXunSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ZhiXunSystem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ZhiXunSystem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") ZhiXunSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_system
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}