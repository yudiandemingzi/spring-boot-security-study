package com.jincou.imgcheck.mapper;


import com.jincou.imgcheck.entity.RolesUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolesUserMapper {
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
    int insert(RolesUser record);

    /**
     * 动态插入一条记录
     *
     * @param record 实体对象
     * @return 更新条目数
     */
    int insertSelective(RolesUser record);

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 实体对象
     */
    RolesUser selectByPrimaryKey(Integer id);

    /**
     * 根据主键动态更新记录
     *
     * @param record 实体对象
     * @return 更新条目数
     */
    int updateByPrimaryKeySelective(RolesUser record);

    /**
     * 根据主键更新记录
     *
     * @param record 实体对象
     * @return 更新条目数
     */
    int updateByPrimaryKey(RolesUser record);

    List<RolesUser> findAllByUid(@Param("uid") Integer uid);


}