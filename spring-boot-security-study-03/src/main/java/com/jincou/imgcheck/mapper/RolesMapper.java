package com.jincou.imgcheck.mapper;



import com.jincou.imgcheck.entity.Roles;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface RolesMapper {
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
    int insert(Roles record);

    /**
     * 动态插入一条记录
     *
     * @param record 实体对象
     * @return 更新条目数
     */
    int insertSelective(Roles record);

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 实体对象
     */
    Roles selectByPrimaryKey(Integer id);

    /**
     * 根据主键动态更新记录
     *
     * @param record 实体对象
     * @return 更新条目数
     */
    int updateByPrimaryKeySelective(Roles record);

    /**
     * 根据主键更新记录
     *
     * @param record 实体对象
     * @return 更新条目数
     */
    int updateByPrimaryKey(Roles record);


    List<Roles> findByIdIn(@Param("idCollection") Collection<Integer> idCollection);


}