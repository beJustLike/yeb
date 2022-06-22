package com.xxxx.server.service.impl;

import com.xxxx.server.pojo.Oplog;
import com.xxxx.server.mapper.OplogMapper;
import com.xxxx.server.service.IOplogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liyongkang
 * @since 2021-11-30
 */
@Service
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
