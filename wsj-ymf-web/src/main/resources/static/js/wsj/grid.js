function Grid(options){                    //js 调用生成grid实例，初始化grid配置
    var this_ = this;
    this_.gridOptions= {             //grid默认配置
        href:'',                      //grid请求地址
        pagegation:true,             //是否分页
        params:{},                    //请求参数
        currentPage : 1,             //当前页，初始化/默认为1
        pageSize :10,                //一页显示的条数
        totalSize : 0,              //当前grid列表所要展示的总共数量
        leftFlag : true,            //是否显示左移页符号
        rightFlag : true,          //是否显示右移页符号
        content:[],                 //用来存储index的元素<< 1 2 3 4 5 >>
        stepLenth:5,               //定义存储index的大小
        vueId:'',                  //与VUE进行绑定的id
        sizeArray:[10,15,20,25],
        callBack:function(dataArray,data){           //grid加载后的回调函数

        },
        extendVueMS:{},              //扩展vue的methods
        extentdVueCS:{}              //扩展vue的computed
    };

    this_.gridOptions = $.extend({},this_.gridOptions,options); //重新加载配置

    var opt =  this_.gridOptions;
    opt.content = new Array(opt.stepLenth).fill(0);

    //定义vue的模型
    this_.vueDataOptions = {
        dataArray:[],
        pageSize :0,
        totalSize :0,
        maxPage :0,
        currentPage: opt.currentPage,
        leftTag : opt.leftFlag,
        rightTag : opt.rightFlag,
        number:[],
        sizeArray:opt.sizeArray,
        srow:0,
        erow:0,
        lastNum:0,
        nextNum:0
    }

    this_.vueMethodOptions = {
        goPage : function(pageNumber){      //输入页码跳转到指定页
            rload(this_,pageNumber);
        },
        fristPage : function(){             //跳转到首页
            rload(this_,1);
        },
        lastPage : function(){             //跳转到最后一页
            var maxPage =  parseInt(this.totalSize / this.pageSize);
            rload(this_,maxPage);
        },
        sizeLoad:function(pageSize){
            console.log(pageSize);
            this_.gridOptions.pageSize = pageSize;
            rload(this_,1);
        }
    }
    //this_.vueMethodOptions = $.extend({},this_.gridOptions.extendVueMS,options); //重新加载配置

    console.log("v-grid的model当前为："+this_.gridOptions.vueId);

    this_.gvue = null;

    this_.ptemplate = {
                        props:['currentPage','leftTag','rightTag','maxPage','number','totalSize','srow','erow'],
                        template:'<div class="row">' +
                        '<div class="col-sm-6">' +
                        '<div class="dataTables_info"   role="alert" >显示 {{this.$parent.srow}} 到 {{this.$parent.erow}} 项，共 {{this.$parent.totalSize}} 项</div>' +
                        '</div>' +
                        '<div class="col-sm-6">' +
                        '<div class="dataTables_paginate paging_simple_numbers" >' +
                        '<ul class="pagination">' +
                        '<li class="paginate_button previous " v-bind:class="{disabled: this.$parent.currentPage==1?true:false}"  @click="goPage(($parent.currentPage)-1)"><a href="#">上一页</a></li>' +
                        '<li class="paginate_button"  v-bind:class="{active: num==currentPage?true:false}" @click="goPage(num)" v-for="num in this.$parent.number" v-if = "num != 0"><a href="#">{{num}}</a></li>' +
                        '<li class="paginate_button next" v-bind:class="{disabled: this.$parent.currentPage==this.$parent.maxPage?true:false}" @click="goPage(($parent.currentPage)+1)"><a href="#">下一页</a></li>' +
                        '</ul>' +
                        '</div>' +
                        '</div>' +
                        '</div>',
                        // data:function(){
                        //     return {
                        //         currentPage: this_.vueDataOptions.currentPage,
                        //         leftTag:     this_.vueDataOptions.leftTag,
                        //         rightTag:    this_.vueDataOptions.rightTag,
                        //         maxPage:     this_.vueDataOptions.maxPage,
                        //         number:      this_.vueDataOptions.number,
                        //         totalSize:  this_.vueDataOptions.totalSize,
                        //         srow:        this_.vueDataOptions.srow,
                        //         erow:        this_.vueDataOptions.erow
                        //     }
                        // },
                        methods:this_.vueMethodOptions,
                        watch: {
                            number: {
                                handler(newValue, oldValue) {
                                    for (var i = 0; i < newValue.length; i++) {
                                        if (oldValue[i] != newValue[i]) {
                                            console.log(newValue)
                                        }
                                    }
                                },
                                deep: true
                            },
                            srow(newValue, oldValue) {
                                console.log(newValue)
                            },
                            erow(newValue, oldValue) {
                                console.log(newValue)
                            },
                        }

                    };
    this_.rtemplate = {
                        //props:['sizeArray'],
                        template:'<div class="row">' +
                        '<div class="col-sm-6">' +
                        '<div class="dataTables_length" id="DataTables_Table_0_length"><label>每页 ' +
                        '<select name="DataTables_Table_0_length" aria-controls="DataTables_Table_0" v-model="sizeVal"  class="form-control input-sm">' +
                        '<option :value="size" v-for="size in this.$parent.sizeArray"  :key="size">{{size}}</option>' +
                        '</select> 条记录</label>' +
                        '</div>' +
                        '</div>' +
                        '</div>',
                         methods:this_.vueMethodOptions,
                        data:function(){
                            return {
                                "sizeVal":''
                            }
                        },
                        watch:{
                            sizeVal(val,oladVal){
                                 this.sizeLoad(val);
                            }
                        }
    };
}

var rbing = function(this_){

    this_.gvue =  new Vue({
        el:"#"+this_.gridOptions.vueId,
        data:this_.vueDataOptions,
        methods:this_.gridOptions.extendVueMS,
        //methods:this_.vueMethodOptions,
        computed:this_.gridOptions.extentdVueCS,
        components:{                                                                                       //在组件中定义分页toolbar
            "pagegation-template": this_.ptemplate,
            "rows-template":this_.rtemplate
        },
        watch:{
            "currentPage":{
                handler:function(val,oldVal){
                    console.log("监听到了currentPage数据变化--------》将变化为第"+val+"页");
                },
                //deep:true,
            },
            "pageSize":{
                handler:function(val,oldVal){
                    console.log("监听到了pageSize数据变化--------》将变化为一页为："+val);
                },
                //deep:true,
            },
        }
    });
}

var rbuild = function(this_){
    var data=this_.gridOptions;
    var maxPage =  parseInt(data.totalSize / data.pageSize)+((data.totalSize % data.pageSize)>0?1:0);
    if(maxPage <= data.content.length){//当最大页码小于数组长度
        for(var i =1 ;i<= data.content.length;i++){
            if(i<= maxPage)
                data.content[i-1] = i;
            else
                data.content[i-1] = 0;
        }
        data.leftFlag = false;
        data.rightFlag = false;

    }else{//当最大页码大于数组长
        var pagePoint=0
        var diff=parseInt(data.content.length /2);
        var mo=data.content.length %2;
        if(data.currentPage+data.content.length>maxPage){
            data.leftFlag=true;
            pagePoint=data.currentPage+ diff+mo;
            if(pagePoint>=maxPage){
                pagePoint=maxPage-data.content.length+mo;
                data.rightFlag = false;
            }else{
                data.rightFlag = true;
                pagePoint=pagePoint-data.content.length;
            }

        }else{
            data.rightFlag=true;
            pagePoint=data.currentPage- diff;
        }
        //修正一下值，如果最小值<=0
        if(pagePoint<=0){
            pagePoint=1;
            data.leftFlag = false;
        }else{
            data.leftFlag=true;
        }
        //将计算的结果写回数组
        for(var i =1;i<= data.content.length;i++,pagePoint++){
            data.content[i-1] = pagePoint;
        }
    }
}

var dbind = function(this_) {

    // Vue.set(this_.gvue,"data",this_.vueDataOptions) //这样就能被vue监控到，更新视图
    // Vue.set(this_.gvue,"methods",this_.vueMethodOptions) //这样就能被vue监控到，更新视图
    // Vue.set(this_.gvue,"computed",this_.gridOptions.extentdVueCS) //这样就能被vue监控到，更新视图
    // Vue.set(this_.gvue,"pagegation-template",this_.ptemplate) //这样就能被vue监控到，更新视图
    // Vue.set(this_.gvue,"rows-template",this_.rtemplate) //这样就能被vue监控到，更新视图

    // this_.gvue.data=this_.vueDataOptions;
    // this_.gvue.methods=this_.vueMethodOptions;
    // this_.gvue.computed=this_.gridOptions.extentdVueCS;
    // this_.gvue["pagegation-template"]=this_.ptemplate;
    // this_.gvue["rows-template"]= this_.rtemplate;
}


var rload = function(this_,pageNumber){

    var gridOptions = this_.gridOptions;
    var url = gridOptions.href;              //当前访问路径为

    pageNumber=pageNumber?pageNumber:gridOptions.currentPage;

    $.ajaxPost(url,{
        success:function(result){
            //清空历史，渲染表格
            this_.vueDataOptions.dataArray=[];
            if(result.grids){
                $.each(result.grids, function(i, value) {               //重新配置数据
                    this_.vueDataOptions.dataArray.push(value);
                });
            }
            if(typeof this_.gridOptions.callBack =='function'  ){
                this_.gridOptions.callBack(this_.vueDataOptions.dataArray,result.grids);
            }
            //处理分页区
            if(!this_.gridOptions.pagegation) {
                return ;
            }
            gridOptions.pageSize=result.currSize ;
            gridOptions.totalSize=result.totalCount ;
            gridOptions.currentPage=result.currNum;
            rbuild(this_);    //重新配置<< 1 2 3 4 5 >>

            this_.vueDataOptions.number=[];
            $.each(this_.gridOptions.content, function(i, value) {
                this_.vueDataOptions.number.push(value);
            })
            this_.vueDataOptions.currentPage=gridOptions.currentPage;
            this_.vueDataOptions.leftTag=gridOptions.leftFlag;
            this_.vueDataOptions.rightTag=gridOptions.rightFlag;
            this_.vueDataOptions.pageSize= gridOptions.pageSize;
            this_.vueDataOptions.totalSize=gridOptions.totalSize;
            this_.vueDataOptions.maxPage=parseInt(result.totalCount / result.currSize)+((result.totalCount % result.currSize)>0?1:0);
            if(result.grids!=null){
                this_.vueDataOptions.srow = result.currNum>1?(result.currNum)*(result.currSize)+1:1;
                var length = result.grids.length;
                this_.vueDataOptions.erow = result.currNum>1?(result.currNum)*(result.currSize)+length:length;
            }
            if(this_.gvue!=null){
                dbind(this_);
            }else{
                rbing(this_);
            }
        },
        params:$.extend({},{
            "pageNum":pageNumber,
            "pageSize":gridOptions.pageSize
        },gridOptions.params),
        tip:false
    })

}

//渲染表格
Grid.prototype.rendGrid=function(options){              //options为url参数
    //取json数据
    var this_=this;
    this_.gridOptions.params=$.extend({},options);
    var opts =this_.gridOptions.params
    if(this_.gridOptions.href && this_.gridOptions.href!=''){
        rload(this_,1); //1 为初始化页码为第一页
    }
}

Grid.prototype.refresh=function(){
    var this_=this;
    //取json数据
    return this_.rendGrid(this_.gridOptions.params );
}

Grid.prototype.getParams=function(){
    var this_=this;
    return this_.gridOptions.params;
}

