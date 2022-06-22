package com.xxxx.server.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 自定义Authority解析器
 * @Author liyongkang
 * @Date 2021/12/16
 **/
@Data
public class CustomAuthorityDeserializer extends JsonDeserializer {
    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode jsonNode = mapper.readTree(p);
        List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
        Iterator<JsonNode> elements = jsonNode.elements();
        while (elements.hasNext()){
            JsonNode next = elements.next();
            //找到authority字段
            JsonNode authority = next.get("authority");
            //序列号，让json可以解析
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.asText()));
        }
        return grantedAuthorities;
    }
}
