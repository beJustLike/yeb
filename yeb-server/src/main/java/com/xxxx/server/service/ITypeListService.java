package com.xxxx.server.service;

import com.xxxx.server.pojo.TypeList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liyongkang
 * @since 2022-01-07
 */
public interface ITypeListService extends IService<TypeList> {

    List<TypeList> findByTypeModel(String typeModel);

}
