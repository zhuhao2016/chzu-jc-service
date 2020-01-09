package cn.chzu.conf.exception;


public class ErrorCode {

	protected String errCode;
	protected String errDesc;

	public String getErrCode() {
		return errCode;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public ErrorCode(String errCode, String errDesc) {
		super();
		this.errCode = errCode;
		this.errDesc = errDesc;
	}

	public cn.chzu.conf.exception.ZycException exception() {
		return new cn.chzu.conf.exception.ZycException(errCode, errDesc);
	}

	public cn.chzu.conf.exception.ZycException exception(Throwable cause) {
		return new cn.chzu.conf.exception.ZycException(errCode, errDesc, cause);
	}

}
