package com.lc.forum.system.controller.user;

import com.fc.forum.system.model.User;
import com.lc.forum.system.controller.user.service.UserService;
import com.lc.forum.system.util.response.ActionResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author qiumin
 * @create 2018/10/28 21:31
 * @desc
 **/
@RestController
@RequestMapping("/user")
@Api(value = "userAPI",description = "用户controller")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation("注册用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "用户账号"),
            @ApiImplicitParam(paramType = "query", name = "password", dataType = "String", required = true, value = "用户密码"),
            @ApiImplicitParam(paramType = "query", name = "email", dataType = "String", required = true, value = "邮箱"),
            @ApiImplicitParam(paramType = "query", name = "nickname", dataType = "String", required = false, value = "用户昵称"),
    })
    @ResponseBody
    @RequestMapping(value = "/addUser",method = {RequestMethod.GET,RequestMethod.POST})
    public ActionResult addUser(@ApiIgnore User user){
        try {
            return userService.userRegister(user);
        }catch (Exception e){
            return ActionResult.failureServer("注册失败");
        }
    }

}
