package com.jincou.mapper;
import org.apache.ibatis.annotations.Param;


import com.jincou.entity.User;

public interface UserMapper {
    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return 更新条目数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入一条记录
     *
     * @param record 实体对象
     * @return 更新条目数
     */
    int insert(User record);

    /**
     * 动态插入一条记录
     *
     * @param record 实体对象
     * @return 更新条目数
     */
    int insertSelective(User record);

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 实体对象
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 根据主键动态更新记录
     *
     * @param record 实体对象
     * @return 更新条目数
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据主键更新记录
     *
     * @param record 实体对象
     * @return 更新条目数
     */
    int updateByPrimaryKey(User record);

    User findOneByUsername(@Param("username")String username);

}