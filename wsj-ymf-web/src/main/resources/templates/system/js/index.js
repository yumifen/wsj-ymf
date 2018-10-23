define(["utils"],function(utils) {

    return utils.extend({
        "mainV" : new Vue({
            el: '#vue_index',
            data: {
                "userName":"",
                "photoUrl":""
            },
            methods:{
                "getUserInfor":function(){
                    var _this = this;
                    var url = "/data/system/initUMenu";
                    $.ajaxPost(url,{
                        success:function(data){
                            _this.userName = data['data']['userName'];
                            _this.photoUrl = data['data']['photoUrl'];
                        },
                        tip:false
                    })
                }
            }
        })
    });
});