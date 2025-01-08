package com.baqn.mapper;

import com.baqn.pojo.SysStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学员表 Mapper 接口
 * </p>
 *
 * @author bao
 * @since 2025-01-03
 */
public interface SysStudentMapper extends BaseMapper<SysStudent> {

  @Select("SELECT student_id, name FROM sys_student WHERE student_id IN (#{studentIds})")
  List<Map<String, Object>> selectStudentNamesByIds(@Param("studentIds") List<Long> studentIds);
}
