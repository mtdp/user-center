package me.wanx.usercenter.action;

import me.wanx.common.core.persistence.BasePagination;
import me.wanx.usercenter.bean.Role;
import me.wanx.usercenter.exception.UserCenterServiceException;
import me.wanx.usercenter.service.IRoleService;
import me.wanx.usercenter.service.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String updateRole(Role role){
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
	
	

}
