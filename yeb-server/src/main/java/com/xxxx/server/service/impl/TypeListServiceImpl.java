package com.xxxx.server.service.impl;

import com.xxxx.server.pojo.TypeList;
import com.xxxx.server.mapper.TypeListMapper;
import com.xxxx.server.service.ITypeListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liyongkang
 * @since 2022-01-07
 */
@Service
public class TypeListServiceImpl extends ServiceImpl<TypeListMapper, TypeList> implements ITypeListService {

    @Resource
    TypeListMapper typeListMapper;

    @Override
    public List<TypeList> findByTypeModel(String typeModel) {
        return typeListMapper.findByTypeModel(typeModel);
    }
}
