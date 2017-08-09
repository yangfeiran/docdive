package com.chiancloud.docdive.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinacloud.mir.common.dao.UserPrivDao;
import com.chinacloud.mir.common.dto.Tenant;
import com.chinacloud.mir.common.dto.User;
import com.chinacloud.mir.common.entity.UserRole;
import com.chinacloud.mir.common.service.OneAAService;
import com.google.common.collect.Sets;

@Service
public class PermissionService {
	
	@Autowired
	private OneAAService oneAAService;
	
	@Autowired
	private UserPrivDao userPrivDao;
	
	public User getUserById(String userId) {
		return oneAAService.getUserInfoForUserId(userId);
	}
	
	public List<User> getUsers() {
		List<User> list = new ArrayList<User>();
		Set<String> userIds = getUserIds();
		for (String userId : userIds) {
			list.add(getUserById(userId));
		}
		return list;
	}

	private Set<String> getUserIds() {
		Set<String> users = Sets.newHashSet();
		List<UserRole> userRoleList = userPrivDao.getUserRoleList();
		for (UserRole userRole : userRoleList) {
			users.add(userRole.getUserId());
		}
		return users;
	}
	
	/**
	 * 获取当前用户所属的租户列表,如果是管理员则获取该子系统所有租户列表
	 * @param userId
	 * @return
	 */
	public List<Tenant> geTenantsByUserId(String userId) {
		//oneAAService.getTenantsForUserId(userId); //该方法返回当前用户在所有所有子系统的租户列表，不符合要求
		List<Tenant> tenants = new ArrayList<Tenant>();
		List<String> list = userPrivDao.getTenantIdsForUserId(userId);
		if(isDataAdmin(userId)){//如果是管理员则获取该子系统所有租户列表
			for (String id : getUserIds()) {
				if(!id.equals(userId)){
					list.addAll(userPrivDao.getTenantIdsForUserId(id));
				}
			}
		}
		Set<String> tenantIds = new HashSet<String>(list);
		if(tenantIds != null){
			for (String tenantId : tenantIds) {
				tenants.add(oneAAService.getTenantInfoForTenantId(tenantId));
			}
		}
		return tenants;
	}
	
	public List<Map> getRoleIdsAndTenantIdsForUserId(String userId) {
		return userPrivDao.getRoleIdsAndTenantIdsForUserId(userId);
	}
	
	public List<String> getPrivilegeNamesForUserId(String userId) {
		return userPrivDao.getPrivilegeNamesForUserId(userId);
	}
	
	/**
	 * 判断用户是否是DataAdmin
	 * @param userId
	 * @return
	 */
	public boolean isDataAdmin(String userId) {
		if(userPrivDao.getPrivilegeNamesForUserId(userId).contains("DataAdmin")){
			return true;
		}
		return false;
	}
}
