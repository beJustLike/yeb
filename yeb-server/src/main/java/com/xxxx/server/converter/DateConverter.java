package com.xxxx.server.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日期转换
 * @Author liyongkang
 * @Date 2021/12/13
 **/
@Component
public class DateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
