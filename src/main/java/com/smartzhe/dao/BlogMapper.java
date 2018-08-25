package com.smartzhe.dao;

import com.smartzhe.entity.Blog;
import java.util.List;
import java.util.Map;

public interface BlogMapper {
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
    List<Blog> findByMap(Map params);

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
    int insert(Blog record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(Blog record);

    /**
     * 根据主键获取
     *
     * @param id
     */
    Blog selectByPrimaryKey(Integer id);

    /**
     * 根据主键来更新部分信息
     *
     * @param record
     */
    int updateByPrimaryKeySelective(Blog record);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeyWithBLOBs(Blog record);

    /**
     * 根据主键更新
     *
     * @param record
     */
    int updateByPrimaryKey(Blog record);
}