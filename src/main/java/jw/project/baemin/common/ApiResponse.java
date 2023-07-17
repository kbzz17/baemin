package jw.project.baemin.common;

public record ApiResponse<T>(boolean success, T data, Error error) {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> fail(ErrorCode error) {
        return new ApiResponse<>(false, null, new Error(error.getCode(), error.getMsg()));
    }


    public record Error(String code, String msg) {

    }
}
