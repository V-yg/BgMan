$(function(){
    var layer = layui.layer;
    var form = layui.form;
    var SlideVerifyPlug = window.slideVerifyPlug;
    var slideVerify = new SlideVerifyPlug('#verify-wrap',{});
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })
    })

    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(data){
        var check = true;
        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }
        if(check == true){
            if(slideVerify.slideFinishState){
                 var form = document.getElementById("myform")
                CoreUtil.sendAjax("/sys/user/login",form.type.value,form.username.value,form.password.value,slideVerify,
                    function (res) {
                        CoreUtil.setData("access_token",res.data.accessToken);
                        CoreUtil.setData("refresh_token",res.data.refreshToken);
                        window.location.href="/index/home";
                    });
            }else {
                top.layer.msg('请滑动滑块进行登录验证！',{icon: 7,time:1500});
            }
        }
        return false;
    });
    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
            hideValidate(this);
        });
    });
    function validate (input) {
        if($(input).val().trim() == ''){
            return false;
        }
    }
    function showValidate(input) {
        var thisAlert = $(input).parent();
        $(thisAlert).addClass('alert-validate');

    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();
        $(thisAlert).removeClass('alert-validate');
    }
})