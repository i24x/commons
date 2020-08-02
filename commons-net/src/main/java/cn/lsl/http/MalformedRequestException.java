package cn.lsl.http;
/**
 * 当HTTP请求无法正确解析时，抛出此异常
 *
 */
public class MalformedRequestException extends Exception {
	private static final long serialVersionUID = 1L;

	MalformedRequestException() { }

    MalformedRequestException(String msg) {
	super(msg);
    }

    MalformedRequestException(Exception x) {
	super(x);
    }
}
