package me.wanx.usercenter.action;

import java.util.List;

import me.wanx.common.core.persistence.BasePagination;
import me.wanx.usercenter.bean.Role;
import me.wanx.usercenter.bean.User;
import me.wanx.usercenter.exception.UserCenterServiceException;
import me.wanx.usercenter.service.IRoleService;
import me.wanx.usercenter.service.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
* @ClassName: UserAction 
* @Description: TODO 
* @author gqwang
* @date 2015年6月23日 上午9:06:05 
*
 */
@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {
	private static final Logger logger = LoggerFactory.getLogger(UserAction.class);
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value="/login.do",method = RequestMethod.POST)
	public String login(String account, String password){
		logger.info("welcome to shiro {}","哈哈");
		try {
			userService.loginAction(account, password);
		} catch (UserCenterServiceException e) {
			logger.error("登录失败",e);
			return "login";
		}
        return "home";
	}
	
	/**
	 * 用户列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/users.do")
	public String list(ModelMap model){
		BasePagination<User> userPage = new BasePagination<User>();
		try {
			userService.searchUserPagination(userPage);
			model.addAttribute("userPage", userPage);
		} catch (UserCenterServiceException e) {
			logger.error("用户分页查询出错",e);
		}
		return "pages/users";
	}
	
	/**
	 * 展示用户添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/newUser.do")
	public String newUser(ModelMap model){
		//获取所有角色
		List<Role> roles = roleService.getAllRole();
		model.addAttribute("roles", roles);
		return "pages/new-user";
	}
	
	/**
	 * 保存用户
	 * @return
	 */
	@RequestMapping("/saveUser.do")
	public String saveUser(User user){
		try {
			userService.save(user);
			userService.saveOrUpdateUserRoles(user.getUserId(), user.getRoles());
		} catch (UserCenterServiceException e) {
			logger.error("保存用户出错",e);
		}
		return "redirect:users.do";
	}
	
	/**
	 * 展示修改用户页面
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping("/editUser.do")
	public String editUser(ModelMap model ,String userId){
		try {
			User u = userService.getUser(Integer.parseInt(userId));
			model.addAttribute("user", u);
			//查询出所有角色
			List<Role> roles = roleService.getAllRole();
			model.addAttribute("roles", roles);
		} catch (NumberFormatException e) {
			logger.error("编辑用户解析userId出错",e);
		} catch (UserCenterServiceException e) {
			logger.error("编辑用户查询出错",e);
		}
		return "pages/edit-user";
	}
	
	/**
	 * 保存更新后的用户
	 * @param user
	 * @return
	 */
	@RequestMapping("/updateUser.do")
	public String updateUser(User user){
		try {
			userService.update(user);
		} catch (UserCenterServiceException e) {
			logger.error("更新用户信息出错",e);
		}
		return "redirect:users.do";
	}
	
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	@RequestMapping("/deleteUser.do")
	public String deleteUser(String userId){
		try {
			userService.delete(Integer.parseInt(userId));
		}catch (NumberFormatException e) {
			logger.error("删除用户解析userId出错",e);
		} catch (UserCenterServiceException e) {
			logger.error("删除用户信息出错",e);
		}
		return "redirect:users.do";
	}
	
	/**
	 * 查看用户详细
	 * @param userId
	 * @return
	 */
	@RequestMapping("/detailUser.do")
	public String detailUser(ModelMap model ,String userId){
		try {
			User u = userService.getUser(Integer.parseInt(userId));
			model.addAttribute("user", u);
		}catch (NumberFormatException e) {
			logger.error("查看用户详细解析userId出错",e);
		} catch (UserCenterServiceException e) {
			logger.error("删除用户信息出错",e);
		}
		return "pages/detail-user";
	}
	
	

}
