package com.devteria.identityservice.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {
    public static final String SYSTEM_ACCOUNT = "system";
    public static final String SECURITY_SCHEME_KEY = "bearerAuth";

    public static class ResponseCode {

        ResponseCode() {
        }

        public static final String CREATED = "201";
        public static final String SUCCESS = "200";
        public static final String ERROR = "500";
        public static final String NOT_FOUND = "404";
        public static final String BAD_REQUEST = "400";
        public static final String UNAUTHORIZED = "401";
        public static final String FORBIDDEN = "403";
        public static final String CONFLICT = "409";
    }


    public static class ResponseMessage {


        ResponseMessage() {
        }

        public static final String CREATED = "Tài nguyên đã tạo thành công";
        public static final String S = "%s";
        public static final String INVALID_EMAIL_PASSWORD = "Mật khẩu ứng dụng không chính xác";
        public static final String SUCCESS = "Thành công";
        public static final String ERROR = "Lỗi hệ thống %s";
        public static final String NOT_FOUND = "Tài nguyên không tồn tại, %s";
        public static final String COMMAND_NOT_FOUND = "Lệnh tìm kiếm không phù hợp";
        public static final String FIELD_REQUIRED = "Thiếu trường dữ liệu, %s";
        public static final String BAD_REQUEST = "Lỗi người dùng: %s";
        public static final String UNDEFINED_ERROR = "Lỗi không xác định, %s";
        public static final String INVALID_INPUT = "Dữ liệu đầu vào không hợp lệ, %s";
        public static final String ALREADY_EXIST = "Tài nguyên đã tồn tại, %s";
        public static final String BAD_CREDENTIALS = "Mật khẩu không đúng.";
        public static final String FORBIDDEN = "Tài khoản không có quyền phù hợp";
        public static final String INCORRECT_PASSWORD = "Mật khẩu không chính xác";
        public static final String UNAUTHORIZED = "Người dùng cần đăng nhập";
        public static final String UNREGISTERED_USER = "Người dùng chưa được đăng ký";
        public static final String CONFLICT = "Lỗi xung đột: %s";
        public static final String PASSWORD_NOT_MATCH = "Mật khẩu cũ không chính xác";
    }

    public static class DataField {
        private DataField() {
        }

        public static final String MENU = "menu";
        public static final String USER = "user";
    }
}
