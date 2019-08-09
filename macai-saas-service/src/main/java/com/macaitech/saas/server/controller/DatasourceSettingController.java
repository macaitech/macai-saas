package com.macaitech.saas.server.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.macaitech.saas.server.entity.DatasourceSetting;
import com.macaitech.saas.server.service.IDatasourceSettingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mht
 * @since 2019-08-09
 */
@Controller
@RequestMapping("/datasource")
public class DatasourceSettingController {
	@Autowired 
	private IDatasourceSettingService datasourceSettingService;
	
	@GetMapping("/list")
	@ResponseBody
	public String list() {
		List<DatasourceSetting> list = this.datasourceSettingService.findAll();
		return JSONObject.toJSONString(list);
	}

}
