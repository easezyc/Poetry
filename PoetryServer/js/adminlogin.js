        /**
 * Created by zhuyongchun on 2016/7/16.
 */
$("ducument").ready(function(){
    $(".btn-login").click(function(){
        $.ajax({
            url:"/PoetryServer/servlet/AdminLoginSvt",
            data:{adminid:$("#adminid").val(),pwd:$("#pwd").val()},
            type:"POST",
            dataType:"json",
            success:function(data){
                if(data.backnews=="wrong")alert("账号密码错误");
                else if(data.backnews=="suc")window.location="/PoetryServer/addpoetry.jsp";
            },
            error:function(){
                alert("请求失败");
            }
        });
        return false;
    });
});