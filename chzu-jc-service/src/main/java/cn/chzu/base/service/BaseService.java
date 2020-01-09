package cn.chzu.base.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import cn.chzu.base.model.BaseModel;
import org.springframework.stereotype.Component;



;


/**
 * 基础方法实现
 * @author mr.zhao
 * @param <T>
 */
@Component
public class BaseService<T extends BaseModel>  {
	/**
	 * 随机数
	 */
	
	Random r = new Random();
	
	/**
	 *  @param model 对象
	 *  @param createUser 操作人
	 *  @return <a>
	 *  
	 *  
	 */
	public T setBaseModel(T model, int createUser) {
		if (createUser != 0) {
			model.setCreateUser(createUser);
			model.setEditUser(createUser);
		}

		String dateStr = cn.chzu.base.util.DateUtil.getDateStr(new Date(0), "yyyy-MM-dd HHmmssSSS");
 		Date date = (Date) cn.chzu.base.util.DateUtil.getDate(dateStr, "yyyy-MM-dd HHmmssSSS");
		model.setCreateTime(date);
		model.setEditTime(date); 
		return model;
	}
	/**
	 *  @param modelList 对象
	 *  @param createUser 操作人
	 *  @return <a>
	 */
	public List<T> setBaseModelList(List<T> modelList, int createUser) {
		for(T model : modelList) {
			if (createUser != 0) {
				model.setCreateUser(createUser);
				model.setEditUser(createUser);
			}
		/*	if (((BaseModel) model).getResourceID() == null) {
				double d = r.nextDouble();
				String randomId = String.valueOf(d);
				if (randomId.length() > 11)
					randomId = randomId.substring(2, 11);
				else
					randomId = randomId.substring(2);
				String resourceID = "cerno" + "." + model.getClass().getSimpleName() + "."+ DateUtil.getDateStr(new Date(0), "yyyyMMddHHmmssSSS") + "." + randomId;
				((BaseModel) model).setResourceID(resourceID);
			}*/
			String dateStr =  cn.chzu.base.util.DateUtil.getDateStr(new Date(), "yyyy-MM-dd HHmmssSSS");
	 		Date date = (Date) cn.chzu.base.util.DateUtil.getDate(dateStr, "yyyy-MM-dd HHmmssSSS");
			model.setCreateTime(date);
			model.setEditTime(date); 
 		}
		return modelList;
	}
}
