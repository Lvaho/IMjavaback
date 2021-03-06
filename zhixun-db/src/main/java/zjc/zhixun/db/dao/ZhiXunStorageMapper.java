package zjc.zhixun.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zjc.zhixun.db.domain.ZhiXunStorage;
import zjc.zhixun.db.domain.ZhiXunStorageExample;

public interface ZhiXunStorageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    long countByExample(ZhiXunStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    int deleteByExample(ZhiXunStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    int insert(ZhiXunStorage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    int insertSelective(ZhiXunStorage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    ZhiXunStorage selectOneByExample(ZhiXunStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    ZhiXunStorage selectOneByExampleSelective(@Param("example") ZhiXunStorageExample example, @Param("selective") ZhiXunStorage.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    List<ZhiXunStorage> selectByExampleSelective(@Param("example") ZhiXunStorageExample example, @Param("selective") ZhiXunStorage.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    List<ZhiXunStorage> selectByExample(ZhiXunStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    ZhiXunStorage selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") ZhiXunStorage.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    ZhiXunStorage selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    ZhiXunStorage selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ZhiXunStorage record, @Param("example") ZhiXunStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ZhiXunStorage record, @Param("example") ZhiXunStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ZhiXunStorage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ZhiXunStorage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") ZhiXunStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZhiXun_storage
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}