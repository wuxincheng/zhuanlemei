package lihu.zhuanlemei.service;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 7979821016072855413L;

	private String code = null;

	public ServiceException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "服务异常：" + code + " - " + this.getMessage();
	}

}
