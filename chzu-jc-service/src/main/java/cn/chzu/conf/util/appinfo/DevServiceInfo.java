package cn.chzu.conf.util.appinfo;

import java.util.List;

/**
 * 接口信息
 * @author zyn
 *
 */
public class DevServiceInfo 
{
	//UUID
	private String uuid;
	
	//应用UUID
	private String appUuid;
	
	//接口编号
	private String txnName;
	
	//接口类型
	private String methodType;
	
	//接口描述
	private String funcDesc;
	
	private List<FieldInfo> input;
	private List<FieldInfo> output;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAppUuid() {
		return appUuid;
	}

	public void setAppUuid(String appUuid) {
		this.appUuid = appUuid;
	}
	
	public String getTxnName() {
		return txnName;
	}

	public void setTxnName(String txnName) {
		this.txnName = txnName;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getFuncDesc() {
		return funcDesc;
	}

	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}

	public List<FieldInfo> getInput() {
		return input;
	}

	public void setInput(List<FieldInfo> input) {
		this.input = input;
	}

	public List<FieldInfo> getOutput() {
		return output;
	}

	public void setOutput(List<FieldInfo> output) {
		this.output = output;
	}

	@Override
	public String toString() {
		return "TxnInfoMongoDB [uuid=" + uuid + ", txnName=" + txnName + ", funcDesc=" + funcDesc + ", input="
				+ input + ", output=" + output + "]";
	}
}
