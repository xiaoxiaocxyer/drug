$(document).ready(function() {
    $('#form-reg').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: '用户名不能为空',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 15,
                        message: '用户名在(2~15)位之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9\u4E00-\u9FA5]+$/, 
                        message: '用户名只能包含大写、小写、数字、用户名'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 16,
                        message: '密码在(6~16)位之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: '密码包含大写、小写、数字'
                    }
                }
            },
            newpassword: {
                validators: {
                    notEmpty: {
                        message: '确认密码不能为空'
                    },
                    identical: {
                        field: 'password',
                        message: '俩次输入密码必须一样'
                    }
                }
            },
            email: {
                message: '邮箱不能为空',
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空,方便发送邮件'
                    },
                    regexp: {
                        regexp: /^\w+@[a-zA-Z0-9]+(\.[a-zA-Z]+)+$/,
                        message: '请输入邮箱的正确格式'
                    }
                }
            },
            phone: {
                message: '联系方式不能为空',
                validators: {
                    notEmpty: {
                        message: '联系方式不能为空'
                    },
                    regexp: {
                        regexp: /^\d{11}$/,
                        message: '联系方式只能是11位数'
                    }
                }
            },
        }
    });
});