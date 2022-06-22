package com.xxxx.server.controller;

import com.xxxx.server.pojo.TypeList;
import com.xxxx.server.service.ITypeListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author liyongkang
 * @Date 2022/4/8
 **/
@RestController
@RequestMapping("/type-list")
public class TypeListController {

    @Resource
    private ITypeListService typeListService;

    @GetMapping("/findByTypeModel")
    public List<TypeList> findByTypeModel(@RequestParam("typeModel") String typeModel) {
        return typeListService.findByTypeModel(typeModel);
    }
}
