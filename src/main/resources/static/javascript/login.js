$(document).ready(function() {
    $('#form-login').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
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
            phone: {
                message: '电话不能为空',
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