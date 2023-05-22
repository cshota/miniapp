package jp.co.scsk.miniapp.sample.api.config;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 初始化 从配置文件加载mock数据
 *
 * @author : LiShuai
 * @since : 2022/2/16 9:50
 */
@Component
public class PacteraInitial {

    /**
     * mock数据加载后存放的缓存
     */
    private Map<String, Object> map;

    /**
     * 其他类引用缓存时的依赖注入对象
     */
    @Bean
    public Map<String, Object> cache () {
        return map;
    }

    /**
     * 初始化
     */
    @PostConstruct
    public void init () throws UnsupportedEncodingException {
        //读取mock文件
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("data.json");
        String data = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8")).lines().collect(Collectors.joining(System.lineSeparator()));
        JSONObject json = JSONUtil.parseObj(data);
        map = json.toBean(HashMap.class);

    }

}
