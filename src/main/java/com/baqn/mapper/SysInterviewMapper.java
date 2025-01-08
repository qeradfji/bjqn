package com.baqn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baqn.pojo.SysInterview;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 访谈记录表 Mapper 接口
 * </p>
 *
 * @author bao
 * @since 2025-01-04
 */
public interface SysInterviewMapper extends BaseMapper<SysInterview> {
  List<Long> selectStudentsWithoutInterview();
  List<Map<String, Object>> selectStudentsByNameAndIds(@Param("name") String name, @Param("studentIds") List<Long> studentIds);
  List<Map<String, Object>> selectStudentsByIds(@Param("studentIds") List<Long> studentIds);
}
