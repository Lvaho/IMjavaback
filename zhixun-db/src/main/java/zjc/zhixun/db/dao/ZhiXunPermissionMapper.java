package zjc.zhixun.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zjc.zhixun.db.domain.ZhiXunPermission;
import zjc.zhixun.db.domain.ZhiXunPermissionExample;

public interface ZhiXunPermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    long countByExample(ZhiXunPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    int deleteByExample(ZhiXunPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    int insert(ZhiXunPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    int insertSelective(ZhiXunPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    ZhiXunPermission selectOneByExample(ZhiXunPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    ZhiXunPermission selectOneByExampleSelective(@Param("example") ZhiXunPermissionExample example, @Param("selective") ZhiXunPermission.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    List<ZhiXunPermission> selectByExampleSelective(@Param("example") ZhiXunPermissionExample example, @Param("selective") ZhiXunPermission.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    List<ZhiXunPermission> selectByExample(ZhiXunPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    ZhiXunPermission selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") ZhiXunPermission.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    ZhiXunPermission selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    ZhiXunPermission selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ZhiXunPermission record, @Param("example") ZhiXunPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ZhiXunPermission record, @Param("example") ZhiXunPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ZhiXunPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ZhiXunPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") ZhiXunPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_permission
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}