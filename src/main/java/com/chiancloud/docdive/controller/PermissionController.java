package com.chiancloud.docdive.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chiancloud.docdive.service.PermissionService;
import com.chinacloud.mir.common.constant.Constant;
import com.chinacloud.mir.common.dto.Tenant;

@Controller
@RequestMapping("/v1/docdive") 
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;

	/**
	 * 获取当前用户所属的租户列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/tenants", method = RequestMethod.GET)
    @ResponseBody
    public List<Tenant> getTenants(HttpServletRequest request){
		String userId = (String) request
				.getAttribute(Constant.REQUEST_CONTEXT_USER_ID);
        return permissionService.geTenantsByUserId(userId);
    }
	
	@RequestMapping(value ="/roles", method = RequestMethod.GET)
    @ResponseBody
    public Object getRoleIdsAndTenantIdsForUserId(HttpServletRequest request){
		String userId = (String) request
				.getAttribute(Constant.REQUEST_CONTEXT_USER_ID);
		if(userId==null)
			userId="4191994b-66b4-4c28-8b0a-39f215a735b8";
        return permissionService.getRoleIdsAndTenantIdsForUserId(userId);
    }
	
	@RequestMapping(value ="/privnames", method = RequestMethod.GET)
	@ResponseBody
	public Object getPrivilegeNamesForUserId(HttpServletRequest request){
		String userId = (String) request
				.getAttribute(Constant.REQUEST_CONTEXT_USER_ID);
		if(userId==null)
			userId="4191994b-66b4-4c28-8b0a-39f215a735b8";
		return permissionService.getPrivilegeNamesForUserId(userId);
	}
}
