define(["utils"],function(utils) {

    // var vueApp = new Vue({
    //     el:"#vueApp",
    //     data:{
    //         userList:[]
    //     }
    // });
    // getUserByPage(0);
    // function getUserByPage(pageNow) {
    //     var datas = "{\"currNum\":1,\"currSize\":2,\"bltn\":false,\"totalCount\":23,\"pageCount\":12,\"grids\":[{\"id\":1,\"createId\":null,\"updateId\":null,\"createDate\":null,\"updateDate\":null,\"remark\":null,\"delFlag\":\"Y\",\"parentId\":0,\"name\":\"首页\",\"type\":\"MENU\",\"href\":\"/index\",\"target\":null,\"icon\":\"glyphicon glyphicon-home\",\"showFlag\":\"Y\",\"permission\":\"sys:index\",\"path\":\"1.\",\"sort\":1,\"sysCompanyId\":null},{\"id\":2,\"createId\":null,\"updateId\":null,\"createDate\":null,\"updateDate\":null,\"remark\":null,\"delFlag\":\"Y\",\"parentId\":0,\"name\":\"系统\",\"type\":\"MENU\",\"href\":\"/sys\",\"target\":null,\"icon\":\"glyphicon glyphicon-th-large\",\"showFlag\":\"Y\",\"permission\":\"sys:sys\",\"path\":\"2.\",\"sort\":2,\"sysCompanyId\":null}]}\n"
    //     var obj = JSON.parse(datas);
    //     vueApp.userList = obj['grids'];
    // }
    var grid = new Grid({
        href:'/data/testGrid',
        vueId:'vueApp',
        extentdVueCS:{
            opName(){
                return function (value){
                    return "ceshi____"+value;
                }
            }
        }
    });

    var  v1 =  new Vue({
        el:"#"+"dianjiwo",
        data:{
             "sdate":"2018-01-05",
             "edate":"2018-01-12",
        },
        methods:{
            "helloWorld":function(){
                console.log(grid.getCheckItems());
            },
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
            grid.rendGrid({});
        }
    });
});