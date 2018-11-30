define(function() {
	var name_="utils";
	var module_="modules";
	var model_="model";  
	//生成一个实例
	var newC  = function(model_f,modelId) {
		var this_=this;
		var model = $.extend({ "modelId": modelId  },  model_f);
		if(!model || !model.modelId || model.modelId=="")  throw new Error("modelId can not be empty") ;
		if(typeof model.modelId != 'string') throw new Error('modelId must be a string'); 
		var ns=namespace(name_+'.'+module_+'.'+model.modelId,this_); 
		ns[model_]=model;  
		return　ns;　
	};　　
	//打印日志
	var log=function(obj){
		if(debug)console.log(obj) ;
	}
	
	var print=function(obj){
		 console.log(obj) ;
	}
	//打印所有模块
	var logModules=function(){
		log(modulesfn()) ;
	}
	//打印指定模块
	var logModule=function(model_f){
		var this_=this; 
		if(!model_f){
		    log(getModule(this_[model_])) ;
		} else if(model_f[model_] ){
			log(model_f);
		}else{ 
			log(getModule(model_f)) 
		}
	}
	//返回指定模块
	//model_f 可为空,可为字符串
	var c=function(model_f){
		var this_=this;
		if(!model_f){
		     return  getModule(this_[model_])  ;
		}else if(model_f[model_] ){
			return model_f ;
		}else{ 
			return getModule(model_f)  
		}
	}
	var logM=function(model_f){
		var this_=this;
		if(!model_f){
			model_f=this_[model_]
		} 
		log(m(model_f)) 
	}
	var m=function(model_f){
		var this_=this;
		if(!model_f){
			model_f=this_[model_]
		}else if(model_f[model_] ){
			return model_f[model_];
		} 
		return getModule(model_f)[model_];
	}
 
	function getModule(model_f){
		
		return (typeof model_f != 'string')? gloabVars(model_f.modelId):gloabVars(model_f);
	}
	function modulesfn(){
		if(!window[name_] || !window[name_][module_]) throw new Error('vars name_.models not exits'); 
		return window[name_][module_];
	}
	function gloabVars(ns){
		if(typeof ns != 'string')  throw new Error('ns must be a string'); 
		
		var modules=modulesfn();
		var ns_arr = ns.split('.');
		var index = null;
		for(var i in ns_arr) {
			index = modules[ns_arr[i]] ; 
			modules=index;
		}
		return index;
	}
	function namespace(ns,this_){
		if(typeof ns != 'string')  throw new Error('namespace must be a string'); 
		var ns_arr = ns.split('.');
		var parent = window;
		for(var i in ns_arr) {
			if(i==ns_arr.length-1 && this_){
				parent[ns_arr[i]]=$.extend({},this_);
				parent[ns_arr[i]][module_]=null;
			}
			parent[ns_arr[i]] = parent[ns_arr[i]] || {};
			parent = parent[ns_arr[i]];
		}
		return parent;
	}
	var extend=function()　{
		var fn={ 
			'newC': newC,
			'extend':extend　,
			'log':log　　,
			'logModules':logModules　　,
			'logModule':logModule,
			'logM':logM　,
			'm':m ,
			'c':c　,
			'print':print
		};
		for(i=0;i<arguments.length;i++){
			fn=$.extend(fn,arguments[i]);
		} 
		
		return fn;
	}　
	var fn_=extend();
	window['u']=window['u']||  namespace(name_+'.'+module_,fn_);
	window[name_]=window['u'];
	//log("loadind base ...");
	return fn_;
});