package cn.chzu.common.constant;


import cn.chzu.conf.exception.ErrorCode;

/**
 * 
 * Description : 错误提示相关常量类
 *
 * Author：        yuyjd     
 * Create Date：   2019年2月21日 下午3:31:53
 *
 *
 */
public class ErrMsgConstant {
	
	private ErrMsgConstant() {
		
	}
	
	/**
	 * --------------------------------------------通用提示相关常量--------------------------------------------------------
	 */
	
	public static final ErrorCode FILTER_IS_NULL = new ErrorCode("00001", "参数为空");
	public static final ErrorCode FILTER_IS_ERROR = new ErrorCode("00002", "参数有误");
	public static final ErrorCode SYSTEM_ERROR = new ErrorCode("999999", "系统错误");
	public static final ErrorCode NO_LOGIN_ERROR = new ErrorCode("AUTH09", "您还没有登录，请登录后再试！");
	/**
	 * ---------------------------------------------用户相关常量--------------------------------------------------------
	 */
	public static final ErrorCode ID_NOT_NULL = new ErrorCode("10010", "主键id不能为空");
	public static final ErrorCode MESSAGE_CONTENT_NOT_NULL = new ErrorCode("10011", "消息内容不能为空");
	public static final ErrorCode RECEIVER_USER_NOT_NULL = new ErrorCode("10012", "接受者不能为空");
	public static final ErrorCode SENDER_USER_NOT_NULL = new ErrorCode("100013", "发送者不能为空");
	
	/**
	 * ---------------------------------------------资讯相关常量--------------------------------------------------------
	 */
	public static final ErrorCode DATA_OVERSTEP_ERROR = new ErrorCode("VLD002", "数据过大，请分页");
	public static final ErrorCode SYSTEM_THROW_ERROR = new ErrorCode("999998", "系统异常请重新输入");
	public static final ErrorCode TITLE_CONTENT_NOT_NULL = new ErrorCode("20011", "标题内容不能为空");
	public static final ErrorCode TYPE_CONTENT_NOT_NULL = new ErrorCode("20012", "类别内容不能为空");
	public static final ErrorCode CONTENT_NOT_NULL = new ErrorCode("20013", "正文内容不能为空");
	public static final ErrorCode TOP_CONTENT_NOT_NULL = new ErrorCode("20014", "是否置顶不能为空");
	public static final ErrorCode TITLE_CONTENT_NOT_REPEAT = new ErrorCode("20015", "标题内容不能为空");
	/**
	 * ---------------------------------------------用户相关常量--------------------------------------------------------
	 */
	public static final ErrorCode USER_NAME_IS_NULL = new ErrorCode("10001", "用户名不能为空");
	public static final ErrorCode PHONE_IS_NULL = new ErrorCode("10002", "手机号不能为空");
	public static final ErrorCode PWD_IS_NULL = new ErrorCode("10003", "密码不能为空");
	public static final ErrorCode CONFIRMPWD_IS_NULL = new ErrorCode("10004", "确认密码不能为空");
	public static final ErrorCode AGREE_IS_NULL = new ErrorCode("10005", "请勾选用户服务协议");
	public static final ErrorCode USER_NAME_CHECK = new ErrorCode("10006", "用户名为字母/数字/字母和数字的组合，长度6-16位");
	public static final ErrorCode USER_NAME_IS_ERROR = new ErrorCode("10007","用户名错误，请重新输入");
	public static final ErrorCode PWD_IS_ERROR = new ErrorCode("10008","密码错误，请重新输入");
	public static final ErrorCode GET_TOKEN_IS_ERROR = new ErrorCode("10009","获取token异常，请稍后重试");
	public static final ErrorCode BLANK_SPACE_CHECK = new ErrorCode("10010", "空格不可输入");
	public static final ErrorCode CONFIRMPWD_CHECK = new ErrorCode("10011", "两次密码输入不一致，请重新输入");
	public static final ErrorCode USER_REPEAT = new ErrorCode("10012", "该用户已存在，请重新输入");
	public static final ErrorCode USER_SOURCE_IS_NULL = new ErrorCode("10013", "来源不能为空");
	public static final ErrorCode PHONE_IS_ERROR = new ErrorCode("10014", "手机号有误");
	public static final ErrorCode VC_ERROR_PHONE_IS_REGISTER = new ErrorCode("10015", "验证码发送失败，该手机号已注册");
	public static final ErrorCode VC_ERROR_PHONE_IS_NOT_REGISTER = new ErrorCode("10016", "验证码发送失败，该手机号未注册");
	public static final ErrorCode VC_SEND_ERROR = new ErrorCode("10017", "验证码发送失败");
	public static final ErrorCode VC_IS_NULL = new ErrorCode("10018", "验证码不能为空");
	public static final ErrorCode PHONE_REPEAT = new ErrorCode("10019", "该手机号已注册，请重新输入");
	public static final ErrorCode VC_CHECK_ERROR = new ErrorCode("10020", "验证码校验失败");
	public static final ErrorCode OLD_PWD_IS_NULL = new ErrorCode("10021","原密码不能为空");
	public static final ErrorCode NEW_PWD_IS_NULL = new ErrorCode("10022","新密码不能为空");
	public static final ErrorCode OLD_NEW_PWD_IS_ERROR = new ErrorCode("10023","新密码和原密码不能相同");
	public static final ErrorCode OLD_PWD_IS_ERROR = new ErrorCode("10024","原密码错误，请重新输入");
	public static final ErrorCode USER_IS_NULL = new ErrorCode("10025","无此用户信息");
	public static final ErrorCode LOGIN_PHONE_IS_ERROR = new ErrorCode("10026","手机号错误，请重新输入");
	
    

}
