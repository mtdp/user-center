package me.wanx.usercenter.service;

import java.util.List;
import java.util.Map;

import me.wanx.common.core.persistence.BasePagination;
import me.wanx.common.core.utils.DateUtil;
import me.wanx.usercenter.UserCenterConstant;
import me.wanx.usercenter.bean.Resource;
import me.wanx.usercenter.dao.IResourceDao;
import me.wanx.usercenter.exception.UserCenterServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements IResourceService {
	private static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
	
	@Autowired
	private IResourceDao<Resource> resourceDao;

	@Override
	public boolean save(Resource res) {
		//如果状态为空则设置成功禁用
		if(null == res.getStatus() || res.getStatus().isEmpty()){
			res.setStatus(UserCenterConstant.STATUS_OFF);
		}
		if(null == res.getIsMenu() || res.getIsMenu().isEmpty()){
			res.setStatus(UserCenterConstant.MENU_OFF);
		}	
		res.setCreateTime(DateUtil.getCurrentTime());
		res.setUpdateTime(DateUtil.getCurrentTime());
		int c = resourceDao.add(res);
		if(c > 0 ){
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Resource res) {
		if(null == res || res.getResId() == null){
			return false;
		}
		//如果状态为空则设置成功禁用
		if(null == res.getStatus() || res.getStatus().isEmpty()){
			res.setStatus(UserCenterConstant.STATUS_OFF);
		}
		res.setUpdateTime(DateUtil.getCurrentTime());
		int c = resourceDao.update(res);
		if(c > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Resource res) {
		int c = resourceDao.del(res.getResId());
		if(c > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Integer resId) {
		int c = resourceDao.del(resId);
		if(c > 0){
			return true;
		}
		return false;
	}

	@Override
	public Resource getResource(Integer resId) {
		return resourceDao.get(resId);
	}

	@Override
	public List<Resource> getResources(String resName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void searchResPagination(BasePagination<Resource> resPage)throws UserCenterServiceException {
		logger.info("资源分页开始");
		//用户总数
		int totalRes = resourceDao.searchCountRes(resPage.getOtherQueryParams());
		//87行90位置不能互换因为getCurrent要用到总条数
		resPage.setTotalRecord(totalRes);
		//查询条件
		Map<String, Object> param = resPage.getQueryParams();
		param.put("sort", "updateTime");
		List<Resource> res = resourceDao.searchResPagination(param);
		resPage.setResults(res);
	}
	
	@Override
	public List<Resource> getMenuRes() {
		return resourceDao.getMenuResources();
	}
	
	@Override
	public List<Resource> getSubMenuRes(Integer resId) {
		return resourceDao.getSubMenuResources(resId);
	}
	
	@Override
	public List<Resource> getButtonMenuRes(Integer resId) {
		return resourceDao.getButtonMenuRes(resId);
	}
}
