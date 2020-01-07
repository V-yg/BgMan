var layer = layui.layer;
var $ = jQuery = layui.jquery;
var form = layui.form;
var element = layui.element;
var uusername;
$(function () {
    FrameWH();
    //递归获取导航栏
    var getChild=function (item,ulHtml) {
        ulHtml += '<dl class="layui-nav-child">';
        $.each(item,function (index,child) {
            if(child.children !=null&& child.children.length>0){
                ulHtml +='<a>'+child.title+'</a>';
                ulHtml +=getChild(child.children,"");
            }else {
                ulHtml += '<dd><a href="javascript:;" data-url="'+child.url+'" data-title="'+child.title+'" data-id="'+child.id+'" class="menuNvaBar">';
                ulHtml += '<cite>'+child.title+'</cite></a></dd>';
            }
        });
        ulHtml += "</dl>"
        return ulHtml;
    };
    var uusername1;
    CoreUtil.sendAjax("/sys/home/",null,null,null,null,function (res) {
        $("#deptName").html("所属部门："+res.data.userInfo.deptName);
        $("#nickName").html(res.data.userInfo.realName);
        uusername1 = res.data.userInfo.username
        var data=res.data.menus;
        if(data!= "" && data.length>0){
            var ulHtml = '<ul class="layui-nav layui-nav-tree layui-left-nav">';
            if(data!= null&&data.length > 0){
                $.each(data,function(index,item){
                    if(index == 0){
                        ulHtml += '<li class="layui-nav-item layui-nav-itemed">';
                    }else{
                        ulHtml += '<li class="layui-nav-item">';
                    }
                    ulHtml += '<a href="javascript:;">';
                    if(item.icon != undefined && item.icon != ''){
                        ulHtml += '<i class="layui-icon '+item.icon+'" style="padding-right: 8px; font-size: 16px" "'+item.icon+'"></i>';
                    }
                    ulHtml += '<cite style="font-size: 16px">'+item.title+'</cite>';
                    ulHtml += '</a>'
                    if(item.children != null && item.children.length > 0){
                        ulHtml += '<dl class="layui-nav-child">';
                        $.each(item.children,function(index,child){
                            if(child.children !=null&& child.children.length>0){
                                ulHtml +='<a style="font-size: 16px">'+child.title+'</a>';
                                if(child.icon != undefined && child.icon != ''){
                                    ulHtml += '<i class="layui-icon '+child.icon+'" style="padding-right: 8px; font-size: 16px" "'+child.icon+'"></i>';
                                }
                                ulHtml +=getChild(child.children,"");
                            }else {
                                ulHtml += '<dd><a href="javascript:;" data-url="'+child.url+'" data-title="'+child.title+'" data-id="'+child.id+'" class="menuNvaBar">';
                                if(child.icon != undefined && child.icon != ''){
                                    ulHtml += '<i class="layui-icon '+child.icon+'" style="padding-right: 8px; font-size: 16px" "'+child.icon+'"></i>';
                                }
                                ulHtml += '<cite style="font-size: 16px">'+child.title+'</cite></a></dd>';
                            }
                        });
                        ulHtml += "</dl>"
                    }
                    ulHtml += '</li>'
                });
            }
            ulHtml += '</ul>';
            $(".navBar").html(ulHtml);
            element.init();  //初始化页面元素
        }else{
            $("#navBarId").empty();
        }
        top.layer.closeAll();
    },"GET",false);
    uusername = uusername1;
    $(document).on('click','.menuNvaBar',function () {
        var dataid = $(this);
        if ($(".layui-tab-title li[lay-id]").length <= 0) {
            active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"), dataid.attr("data-icon"));
        } else {
            var isData = false;
            $.each($(".layui-tab-title li[lay-id]"), function () {
                if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                    isData = true;
                }
            })
            if (isData == false) {
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"), dataid.attr("data-icon"));
            }
        }
        active.tabChange(dataid.attr("data-id"));
    });

    $(".hideMenu").click(function(){
        $(".layui-layout-admin").toggleClass("showMenu");
        //渲染顶部窗口
        //tab.tabMove();
    })
    //退出登录
    $("#logout").click(function () {
        logoutDialog();
    });

});
//删除前确认对话框
var logoutDialog=function () {
    layer.open({
        content: '确定要退出登录么？',
        yes: function(index, layero){
            layer.close(index); //如果设定了yes回调，需进行手工关闭
            CoreUtil.sendAjax("/sys/user/logout",null,function (res) {
                top.window.location.href="/index/login";
            },"GET",true);
        }
    });
}
//触发事件
var active={
    tabAdd : function (url,id,title,icon) {
        if(url!=""&&url!=null&&url!=undefined){
            element.tabAdd('tab',{
                title: title,
                icon:icon,
                content: '<iframe data-frameid="' + id + '" frameborder="0" name="content" width="100%" src="' + url + '"></iframe>',
                id: id
            })
        }
        FrameWH();//计算框架高度
    },
    tabChange: function (id) {
        //切换到指定Tab项
        element.tabChange('tab', id); //切换到：用户管理
        $("iframe[data-frameid='" + id + "']").attr("src", $("iframe[data-frameid='" + id + "']").attr("src"))//切换后刷新框架
    },
    tabDelete: function (id) {
        element.tabDelete("tab", id);//删除
    }
};
function FrameWH() {
    var h = $(window).height() - 41 - 10 - 35 - 10 - 44 - 10;
    $("iframe").css("height", h + "px");
};