package com.lc.forum.system.controller.user;

import com.fc.forum.system.model.User;
import com.lc.forum.system.logFactory.LogFactory;
import com.lc.forum.system.user.service.UserService;
import com.lc.forum.system.email.util.config.EmailConfig;
import com.lc.forum.system.util.response.ActionResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * @author qiumin
 * @create 2018/10/28 21:31
 * @desc
 **/
@RestController
@RequestMapping("/user")
@Api(value = "userAPI",description = "用户controller")
public class UserController {

    private static final Logger BUSINESS = LogFactory.getBusinessLog();

    @Autowired
    private UserService userService;

    @Autowired
    private EmailConfig mc;

    @ApiOperation("注册用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "用户账号"),
            @ApiImplicitParam(paramType = "query", name = "password", dataType = "String", required = true, value = "用户密码"),
            @ApiImplicitParam(paramType = "query", name = "email", dataType = "String", required = false, value = "邮箱"),
            @ApiImplicitParam(paramType = "query", name = "nickname", dataType = "String", required = false, value = "用户昵称"),
    })
    @ResponseBody
    @RequestMapping(value = "/addUser",method = {RequestMethod.GET,RequestMethod.POST})
    public ActionResult addUser(@ApiIgnore @Valid User user, BindingResult result){
        try {
            if (result.hasErrors()){
                return ActionResult.failureParamter(result.getFieldError().getDefaultMessage());
            }
            return userService.userRegister(user);
        }catch (Exception e){
            BUSINESS.info(UserController.class.getName(),e);
            return ActionResult.failureServer("注册失败");
        }
    }


}
