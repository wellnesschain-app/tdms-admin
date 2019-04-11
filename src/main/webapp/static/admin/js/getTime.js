    $(function(){
        function nowDate(){
            var date = new Date();
            Y = date.getFullYear() + '年';
            M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '月';
            D = (date.getDate() < 10 ? '0'+date.getDate() : date.getDate()) + '日 ';
            h = (date.getHours() < 10 ? '0'+date.getHours() : date.getHours()) + ':';
            m = (date.getMinutes() < 10 ? '0'+date.getMinutes() : date.getMinutes()) + ':';
            s = (date.getSeconds() < 10 ? '0'+date.getSeconds() : date.getSeconds());


            var YMDHMS = Y+M+D+h+m+s;

            // var d=new Date();
            // var YMDHMS = d.getFullYear() + "年" +(d.getMonth()+1) + "月" + d.getDate() + "日   " +
            //     d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
            return YMDHMS;
        }
        function getTime(){
            var time = nowDate();
            var now=new Date().getHours();
            if(now<6){
                $(".span_right").text("Hello  凌晨好！");
            }else if(now<12&&now>=6){
                $(".span_right").text("Hello  上午好！");
            }else if(now>=11&&now<=13){
                $(".span_right").text("Hello  中午好！");
            }else if(now>13&&now<=18){
                $(".span_right").text("Hello  下午好！");
            }else if(now>18&&now<=23){
                $(".span_right").text("Hello  晚上好！");
            }
            $(".span_right2").text(time);
        }
        getTime();
        setInterval(getTime,1000);
    })