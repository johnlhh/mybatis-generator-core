package com.test.dao;

import com.test.entity.ServicePlus;
import java.util.List;
import java.util.Map;

public interface ServicePlusMapper {
    /**
     * 根据条件统计个数
     *
     * @param params
     */
    int countByMap(Map params);

    /**
     * 根据条件分页查询
     *
     * @param params
     */
    List<ServicePlus> findByMap(Map params);

    /**
     * 根据主键删除
     *
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     *
     * @param record
     */
    int insert(ServicePlus record);

    /**
     * 根据主键获取
     *
     * @param id
     */
    ServicePlus selectByPrimaryKey(Integer id);

    /**
     * 根据主键来更新部分信息
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ServicePlus record);
}