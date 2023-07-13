package jw.project.baemin.common;

public enum ErrorCode {
    ERROR_EXAMPLE("400","에러 예제입니다.");
    private final String code;
    private final String msg;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
