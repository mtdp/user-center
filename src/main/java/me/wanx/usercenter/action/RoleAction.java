package me.wanx.usercenter.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.wanx.common.core.persistence.BasePagination;
import me.wanx.usercenter.action.bean.ActionResultMessage;
import me.wanx.usercenter.action.bean.ZTreeBean;
import me.wanx.usercenter.bean.Resource;
import me.wanx.usercenter.bean.Role;
import me.wanx.usercenter.exception.UserCenterServiceException;
import me.wanx.usercenter.service.IResourceService;
import me.wanx.usercenter.service.IRoleService;
import me.wanx.usercenter.service.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
* @ClassName: RoleAction 
* @Description: TODO 
* @author gqwang
* @date 2015年6月23日 上午9:06:05 
*
 */
@Controller
@RequestMapping("/role")
public class RoleAction extends BaseAction {
	private static final Logger logger = LoggerFactory.getLogger(RoleAction.class);
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IResourceService resourceService;
	
	/**
	 * 角色列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/roles.do")
	public String list(ModelMap model ,BasePagination<Role> rolePage){
		//BasePagination<Role> rolePage = new BasePagination<Role>();
		try {
			roleService.searchRolePagination(rolePage);
			model.addAttribute("rolePage", rolePage);
		} catch (UserCenterServiceException e) {
			logger.error("角色分页查询出错",e);
		}
		return "pages/roles";
	}
	
	/**
	 * 展示角色添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/newRole.do")
	public String newRole(){
		return "pages/new-role";
	}
	
	/**
	 * 保存角色
	 * @return
	 */
	@RequestMapping("/saveRole.do")
	public String saveRole(Role role){
		roleService.save(role);
		return "redirect:roles.do";
	}
	
	/**
	 * 展示修改角色页面
	 * @param model
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/editRole.do")
	public String editRole(ModelMap model ,String roleId){
		try {
			Role r = roleService.getRole(Integer.parseInt(roleId));
			model.addAttribute("role", r);
		} catch (NumberFormatException e) {
			logger.error("编辑角色解析roleId出错",e);
		} 
		return "pages/edit-role";
	}
	
	/**
	 * 保存更新后的角色
	 * @param role
	 * @return
	 */
	@RequestMapping("/updateRole.do")
	public String updateRole(Role role,String chooseResIds){
		List<Resource> resList = new ArrayList<Resource>();
		if(chooseResIds != null && !chooseResIds.isEmpty()){
			List<String> resIds = Arrays.asList(chooseResIds.split(","));
			for(String str : resIds){
				Resource res = new Resource();
				res.setResId(Integer.parseInt(str));
				resList.add(res);
			}
		}
		role.setResources(resList);
		roleService.update(role);
		return "redirect:roles.do";
	}
	
	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/deleteRole.do")
	public String deleteRole(String roleId){
		try {
			roleService.delete(Integer.parseInt(roleId));
		}catch (NumberFormatException e) {
			logger.error("删除角色解析roleId出错",e);
		} 
		return "redirect:roles.do";
	}
	
	/**
	 * 查看角色详细
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/detailRole.do")
	public String detailRole(ModelMap model ,String roleId){
		try {
			Role r = roleService.getRole(Integer.parseInt(roleId));
			model.addAttribute("role", r);
		}catch (NumberFormatException e) {
			logger.error("查看角色详细解析roleId出错",e);
		} 
		return "pages/detail-role";
	}
	
	/**
	 * ajax 请求菜单
	 * @param resIds
	 * @return
	 */
	@RequestMapping("/ajaxMenu.do")
	@ResponseBody
	public List<ZTreeBean> ajaxGetMenu(ModelMap model ,String resIds,String roleId){
		logger.info("resIds="+resIds);
		List<Resource> res = resourceService.getMenuRes();
		Map<Integer,ZTreeBean> zTreeBeans = new HashMap<Integer,ZTreeBean>();
		List<String> checkedResIds = new ArrayList<String>();
		if(resIds == null || resIds.isEmpty()){
			//第一次编辑查询数据库中关联的资源
			List<Resource> resList = roleService.getRoleResource(Integer.parseInt(roleId));
			for(Resource r : resList){
				checkedResIds.add(String.valueOf(r.getResId()));
			}
		}else{
			//第二次编辑没有保存 获取修改后的资源id
			checkedResIds = Arrays.asList(resIds.split(","));
		}
		for(Resource r : res){
			ZTreeBean treeBean = new ZTreeBean();
			treeBean.setId(r.getResId());
			treeBean.setName(r.getResName());
			treeBean.setpId(r.getParentId());
			treeBean.setOpen(true); //默认打开
			//是否被选中
			if(checkedResIds.contains(String.valueOf(treeBean.getId()))){
				treeBean.setChecked(true);
			}
			List<Resource> rs = resourceService.getSubMenuRes(r.getResId());
			for(Resource rr : rs){
				ZTreeBean subTreeBean = new ZTreeBean();
				subTreeBean.setId(rr.getResId());
				subTreeBean.setpId(rr.getParentId());
				subTreeBean.setName(rr.getResName());
				if(checkedResIds.contains(String.valueOf(subTreeBean.getId()))){
					subTreeBean.setChecked(true);
				}
				zTreeBeans.put(subTreeBean.getId(), subTreeBean);
			}
			zTreeBeans.put(treeBean.getId(),treeBean);
		}
		return new ArrayList<ZTreeBean>(zTreeBeans.values());
	}
	
	/**
	 * ajax保存角色下资源
	 * @param resIds
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/ajaxSaveRoleRes.do")
	@ResponseBody
	public ActionResultMessage ajaxSaveRoleRes(String resIds,String roleId){
		ActionResultMessage msg = new ActionResultMessage();
		if(roleId == null || roleId.isEmpty()){
			msg.setCode("error");
			msg.setInfo("roleId不能为空");
			return msg;
		}
		if(resIds == null){
			resIds = "";
		}
		List<Resource> resources = new ArrayList<Resource>();
		String[] strArr = resIds.split(",");
		for(String str : strArr){
			Resource r = new Resource();
			r.setResId(Integer.parseInt(str));
			resources.add(r);
		}
		roleService.saveOrUpdateRoleResource(Integer.parseInt(roleId), resources);
		msg.setCode("success");
		msg.setInfo("变更权限成功");
		return msg;
	}
	
}
