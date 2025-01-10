package com.baqn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysInterview;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 访谈记录表 Mapper 接口
 * </p>
 *
 * @author bao
 * @since 2025-01-04
 */
public interface SysInterviewMapper extends BaseMapper<SysInterview> {


  Page<SysInterview> getInterviewsByStudentNameAndTeacherId(@Param("page") Page<SysInterview> page, @Param("studentName") String studentName, @Param("teacherId") Long teacherId);
}
