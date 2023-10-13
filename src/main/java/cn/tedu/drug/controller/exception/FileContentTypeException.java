package cn.tedu.drug.controller.exception;

/**
 * 文件类型异常
 * @author PHP
 *
 */
public class FileContentTypeException extends FileUploadException {

	private static final long serialVersionUID = 6935375018112733030L;

	public FileContentTypeException() {
		super();
	}

	public FileContentTypeException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public FileContentTypeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public FileContentTypeException(String arg0) {
		super(arg0);
	}

	public FileContentTypeException(Throwable arg0) {
		super(arg0);
	}

}
