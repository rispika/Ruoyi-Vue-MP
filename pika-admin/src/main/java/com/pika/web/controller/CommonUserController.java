package com.pika.web.controller;

import com.pika.common.constant.Constants;
import com.pika.common.core.controller.BaseController;
import com.pika.common.core.domain.AjaxResult;
import com.pika.common.core.domain.entity.SysUser;
import com.pika.common.core.domain.model.LoginBody;
import com.pika.common.core.domain.model.RegisterBody;
import com.pika.common.utils.SecurityUtils;
import com.pika.common.utils.StringUtils;
import com.pika.framework.web.service.SysLoginService;
import com.pika.framework.web.service.SysRegisterService;
import com.pika.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common/user")
public class CommonUserController extends BaseController {

    @Autowired
    private SysLoginService loginService;
    @Autowired
    private SysRegisterService registerService;
    @Autowired
    private ISysUserService userService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user)
    {
        String msg = registerService.registerIpUser(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
    
    @PostMapping("/me")
    public AjaxResult me()
    {
        Long userId = SecurityUtils.getUserId();
        SysUser sysUser = userService.selectUserById(userId);
        return AjaxResult.success(sysUser);
    }

}
