export enum ContentTypeEnum {
    // json
    JSON = 'application/json;charset=UTF-8',
    // form-data   上传资源（图片，视频）
    FORM_DATA = 'multipart/form-data;charset=UTF-8'
}

export enum RequestMethodsEnum {
    GET = 'GET',
    POST = 'POST'
}

export enum RequestCodeEnum {
    SUCCESS = 200, //成功
    FAILED = 300, // 失败
    PARAMS_VALID_ERROR = 310, //参数校验错误
    PARAMS_TYPE_ERROR = 311, //参数类型错误
    REQUEST_METHOD_ERROR = 312, //请求方法错误
    ASSERT_ARGUMENT_ERROR = 313, //断言参数错误
    ASSERT_MYBATIS_ERROR = 314, //断言mybatis错误
    LOGIN_ACCOUNT_ERROR = 330, //登陆账号或密码错误
    LOGIN_DISABLE_ERROR = 331, //登陆账号已被禁用
    TOKEN_EMPTY = 332, // TOKEN参数为空
    TOKEN_INVALID = 333, // TOKEN参数无效
    VERIFICATION_CODE_ERROR = 334, // 验证码错误
    NO_PERMISSTION = 403, //无相关权限
    REQUEST_404_ERROR = 404, //请求接口不存在
    SYSTEM_ERROR = 500 //系统错误
}
