package me.wanx.usercenter.action;

import me.wanx.common.core.persistence.BasePagination;
import me.wanx.usercenter.bean.Resource;
import me.wanx.usercenter.exception.UserCenterServiceException;
import me.wanx.usercenter.service.IResourceService;
import me.wanx.usercenter.service.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
* @ClassName: ResourceAction 
* @Description: TODO 
* @author gqwang
* @date 2015年6月23日 上午9:06:05 
*
 */
@Controller
@RequestMapping("/res")
public class ResourceAction extends BaseAction {
	private static final Logger logger = LoggerFactory.getLogger(ResourceAction.class);
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IResourceService resourceService;
	
	/**
	 * 资源列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/res.do")
	public String list(ModelMap model){
		BasePagination<Resource> resPage = new BasePagination<Resource>();
		try {
			resourceService.searchResPagination(resPage);
			model.addAttribute("resPage", resPage);
		} catch (UserCenterServiceException e) {
			logger.error("资源分页查询出错",e);
		}
		return "pages/res";
	}
	
	/**
	 * 展示资源添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/newRes.do")
	public String newRes(){
		return "pages/new-res";
	}
	
	/**
	 * 保存资源
	 * @return
	 */
	@RequestMapping("/saveRes.do")
	public String saveRole(Resource res){
		resourceService.save(res);
		return "redirect:res.do";
	}
	
	/**
	 * 展示修改角色页面
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping("/editRes.do")
	public String editRes(ModelMap model ,String resId){
		try {
			Resource r = resourceService.getResource(Integer.parseInt(resId));
			model.addAttribute("res", r);
		} catch (NumberFormatException e) {
			logger.error("编辑资源解析resId出错",e);
		} 
		return "pages/edit-res";
	}
	
	/**
	 * 保存更新后的资源
	 * @param res
	 * @return
	 */
	@RequestMapping("/updateRes.do")
	public String updateRole(Resource res){
		resourceService.update(res);
		return "redirect:res.do";
	}
	
	/**
	 * 删除资源
	 * @param resId
	 * @return
	 */
	@RequestMapping("/deleteRole.do")
	public String deleteRes(String resId){
		try {
			resourceService.delete(Integer.parseInt(resId));
		}catch (NumberFormatException e) {
			logger.error("删除资源解析resId出错",e);
		} 
		return "redirect:res.do";
	}
	
	/**
	 * 查看资源详细
	 * @param resId
	 * @return
	 */
	@RequestMapping("/detailRes.do")
	public String detailRole(ModelMap model ,String resId){
		try {
			Resource r = resourceService.getResource(Integer.parseInt(resId));
			model.addAttribute("res", r);
		}catch (NumberFormatException e) {
			logger.error("查看角色详细解析resId出错",e);
		} 
		return "pages/detail-res";
	}
	
	

}
