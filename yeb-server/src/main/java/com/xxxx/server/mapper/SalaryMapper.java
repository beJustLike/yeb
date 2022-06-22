package com.xxxx.server.mapper;

import com.xxxx.server.pojo.Salary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liyongkang
 * @since 2021-11-30
 */
public interface SalaryMapper extends BaseMapper<Salary> {

    Salary getSalaryById(Integer salaryId);
}
