define(["utils"],function(utils) {

    var  v1 =  new Vue({
        el:"#"+"dianjiwo",
        data:{
             "sdate":"2018-01-05",
             "edate":"2018-01-12",
        },
        methods:{
            "openDetail":function(){
                $("#myModal_body").panel('refresh','/web/detail');
            }
        },
        computed:{

        },
        watch:{

        },
        mounted: function() {
            //把动态获取ID的操作放到this.$nextTick的回调中执行即可
            this.$nextTick( function () {

             });
            $('#myModal').on('shown.bs.modal', function () {
                $("#myModal_body").css("width","100%");
            });
        }

    });

    return utils.extend({
        'form': function() {

        },
        'init': function() {

        }
    });
});