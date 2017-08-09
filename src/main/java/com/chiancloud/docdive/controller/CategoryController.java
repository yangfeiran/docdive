package com.chiancloud.docdive.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chiancloud.docdive.service.CategoryService;
import com.chinacloud.mir.common.constant.Constant;

@Controller
@RequestMapping("/v1/docdive")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCategories(HttpServletRequest request, @PathVariable("id") String categoryId) {
		String userId = (String) request.getAttribute(Constant.REQUEST_CONTEXT_USER_ID);
		return categoryService.getCategories(userId, categoryId);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	@ResponseBody
	public Object addCategory(HttpServletRequest request, @RequestBody String name) {
		String userId = (String) request.getAttribute(Constant.REQUEST_CONTEXT_USER_ID);
		JSONObject obj = JSON.parseObject(name);
		return categoryService.addCategory(userId, obj.getString("name"));
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateCategory(HttpServletRequest request, @PathVariable("id") String categoryId,
			@RequestBody String name) {
		String userId = (String) request.getAttribute(Constant.REQUEST_CONTEXT_USER_ID);
		JSONObject obj = JSON.parseObject(name);
		return categoryService.updateCategory(userId, categoryId, obj.getString("name"));
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteCategory(HttpServletRequest request, @PathVariable("id") String categoryId) {
		String userId = (String) request.getAttribute(Constant.REQUEST_CONTEXT_USER_ID);
		return categoryService.deleteCategory(userId, categoryId);
	}

}
