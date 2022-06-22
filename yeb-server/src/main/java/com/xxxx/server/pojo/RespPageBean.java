package com.xxxx.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author liyongkang
 * @Date 2021/12/13
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {

    //总条数
    private Long total;

    //数据list
    private List<?> data;

}
