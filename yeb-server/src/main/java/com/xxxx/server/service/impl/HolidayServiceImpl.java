package com.xxxx.server.service.impl;

import com.xxxx.server.pojo.Holiday;
import com.xxxx.server.mapper.HolidayMapper;
import com.xxxx.server.service.IHolidayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liyongkang
 * @since 2022-01-07
 */
@Service
public class HolidayServiceImpl extends ServiceImpl<HolidayMapper, Holiday> implements IHolidayService {

}
