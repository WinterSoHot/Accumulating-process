package com.springboot.charpter3.web;


import com.springboot.charpter3.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());


    @ApiOperation(value = "获取所有用户")
    @GetMapping("")
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>(users.values());
        return userList;
    }

    @ApiOperation(value = "创建用户", notes = "根据User用户创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体", required = true, dataType = "User")
    @PostMapping("")
    public String postUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return "success";
    }
}
