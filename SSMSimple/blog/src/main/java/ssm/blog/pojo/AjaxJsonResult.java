package ssm.blog.pojo;

public class AjaxJsonResult {
	private int error;
	private String message;
	private Object data;

	public AjaxJsonResult() {
		
	}
	
	public AjaxJsonResult(int error, String message) {
		this.error = error;
		this.message = message;
	}
	
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
