package cn.tedu.drug.service.exception;
/**
 * 药品条形码重复异常
 * @author PHP
 *
 */
public class BarCodeDuplicateException extends ServiceException{
	private static final long serialVersionUID = 1L;

	public BarCodeDuplicateException() {
		super();
	}

	public BarCodeDuplicateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BarCodeDuplicateException(String message, Throwable cause) {
		super(message, cause);
	}

	public BarCodeDuplicateException(String message) {
		super(message);
	}

	public BarCodeDuplicateException(Throwable cause) {
		super(cause);
	}
	
}
