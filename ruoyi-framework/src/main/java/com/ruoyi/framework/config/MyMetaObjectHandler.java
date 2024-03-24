package com.ruoyi.framework.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        String operator = null;
        try {
            operator = SecurityUtils.getUsername();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        this.setFieldValByName("createBy", operator, metaObject);
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", operator, metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String operator = null;
        try {
            operator = SecurityUtils.getUsername();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        this.setFieldValByName("updateBy", operator, metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}