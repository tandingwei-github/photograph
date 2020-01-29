/*package com.tdw.photograph.config;

import com.tdw.photograph.bean.MessageTips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

*//**
 * @author 谭锭伟
 * @date 2020/1/3-10:29
 *//*
@Configuration
public class MyRedisConfig{
    @Bean
    public RedisTemplate<String , List<MessageTips>> redisMessageListTemplate(RedisConnectionFactory factory){
        RedisTemplate<String ,List<MessageTips>>template=new RedisTemplate<>();

        //关联
        template.setConnectionFactory(factory);
        //设置key的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化器
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(MessageTips.class));

        return template;
    }
    @Bean
    public RedisTemplate<String , MessageTips> redisMessageTemplate(RedisConnectionFactory factory){
        RedisTemplate<String ,MessageTips>template=new RedisTemplate<>();

        //关联
        template.setConnectionFactory(factory);
        //设置key的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化器
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(MessageTips.class));

        return template;
    }
}*/

   /* //获取所有用户账号
    List<String > username = userSelectService.getAllUsername();
    //获取所有已发信息的id
    List<Integer> messageId = userSelectService.getAllMessageId();

    //创建信息标记列表
    List<MessageTips> messageTipsList = new ArrayList<>();
//发送信息未读标记给所有用户
        for(int i=0;i<messageId.size();i++){
        MessageTips messageTips = new MessageTips(messageId.get(i),false);
        messageTipsList.add(messageTips);
        }

        this.redisTemplateList.opsForList().leftPush("username_2",messageTipsList);
        this.redisTemplateList.opsForList().rightPush("username_3",messageTipsList);

        //获取最新信息的id
        Integer message = userSelectService.getNewMessageId();
        MessageTips messageTips = new MessageTips(message,false);
        this.redisTemplate.opsForValue().set("username_1",messageTips);


        if(this.redisTemplate.hasKey("username_1")){
        System.out.println("username已经存在了");
        }
        else{
        System.out.println("username不存在，需要写入");
        }


        //发送及标记操作完成
        System.out.println("发送及标记操作完成");*/
/*

    @Autowired
    private UserSelectService userSelectService;

    @Autowired
    private RedisTemplate<String,List<MessageTips>> redisTemplateList;
    @Autowired
    private RedisTemplate<String,MessageTips> redisTemplate;*/
