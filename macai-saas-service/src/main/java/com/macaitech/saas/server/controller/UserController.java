package com.macaitech.saas.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.macaitech.saas.server.entity.User;
import com.macaitech.saas.server.service.IUserService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Mht
 * @since 2019-08-07
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping(value = "/show")
	@ResponseBody
	public String testEnum() {
		User user = new User();
		user.setId(1);
		Wrapper<User> queryWrapper = new QueryWrapper<User>(user);
		List<User> users = null;// userService.list(queryWrapper);
		 users = userService.findDeptUsers();
		JSONObject result = new JSONObject();
		result.put("users", users);
		return JSONObject.toJSONString(result);
	}

}
