package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.TypeList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeListMapper extends BaseMapper<TypeList> {

    List<TypeList> findByTypeModel(String typeModel);

    TypeList findByTypeId(Long typeId);
}
