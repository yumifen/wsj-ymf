// 创建一个闭包    
(function($) {

    toastr.options = {
        "closeButton": true,
        "debug": false,
        "progressBar": true,
        "positionClass": "toast-bottom-right",
        "onclick": null,
        "showDuration": "400",
        "hideDuration": "1000",
        "timeOut": "7000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };

    $.prototype.serializeObject=function(){
        var hasOwnProperty=Object.prototype.hasOwnProperty;
        return this.serializeArray().reduce(function(data,pair){
            if(!hasOwnProperty.call(data,pair.name)){
                data[pair.name]=pair.value;
            }
            return data;
        },{});
    };
    //重写form的方法
    $.fn.form.defaults.onSubmit = function(param) {
        var options = $(this).form('options');
        return options.onSubmitfn(param, $(this).validate({
            ignore: ""
        })) ? options.layer = layer.load(0,{  shade: [0.3, '#393D49'],  zIndex: 9999999999999 }) : false;
    };
    $.fn.form.defaults.success = function(data) {
        layer.close(this.layer);
        var obj = $.parseJSON(data);
        if(obj.bltnTip) {
            if(this.tip) toastr.success(obj.msg, "系统提示");
            this.successfn(obj.data, obj);
        } else {
            if(this.tip) toastr.error(obj.msg, "系统提示");
            this.failfn(obj.data, obj);
        }
        if(obj.flag==null){
            this.successfn(obj);
        }
        $('.popform').remove();
    };
    //自定义form的方法
    $.fn.form.defaults.tip = true;
    $.fn.form.defaults.question = true;
    $.fn.form.defaults.successfn = function(data, code) {};
    $.fn.form.defaults.failfn = function(data, code) {};
    $.fn.form.defaults.onSubmitfn = function(param, validate) {};

    var e = "<i class='fa fa-times-circle'></i> ";
    $.extend($.validator.messages, {
        required: e + "这是必填字段",
        remote: e + "请修正此字段",
        email: e + "请输入有效的电子邮件地址",
        url: e + "请输入有效的网址",
        date: e + "请输入有效的日期",
        dateISO: e + "请输入有效的日期 (YYYY-MM-DD)",
        number: e + "请输入有效的数字",
        digits: e + "只能输入数字",
        creditcard: e + "请输入有效的信用卡号码",
        equalTo: e + "你的输入不相同",
        extension: e + "请输入有效的后缀",
        maxlength: $.validator.format(e + "最多可以输入 {0} 个字符"),
        minlength: $.validator.format(e + "最少要输入 {0} 个字符"),
        rangelength: $.validator.format(e + "请输入长度在 {0} 到 {1} 之间的字符串"),
        range: $.validator.format(e + "请输入范围在 {0} 到 {1} 之间的数值"),
        max: $.validator.format(e + "请输入不大于 {0} 的数值"),
        min: $.validator.format(e + "请输入不小于 {0} 的数值")
    });
    /**
     初始化校验组件
     **/
    $.validator.setDefaults({
        highlight: function(e) {
            $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
        },
        success: function(e) {
            e.closest(".form-group").removeClass("has-error").addClass("has-success")
        },
        ignore:"",
        errorElement: "span",
        errorPlacement: function(e, r) {
            e.appendTo(r.closest(".error-info"))
        },
        errorClass: "help-block m-b-none",
        validClass: "help-block m-b-none"
    }),
	$.extend({
        ccAjax: function(options) {
            options=$.extend({
                url:"",
                queryParams:{},
                tip:false,
                onSubmitfn: function(param, validate) {
                    return true
                }
            },options);
            options.url = options.url || '';
            //去除首尾空白字符
            if(!(options.url.replace(/(^s*)|(s*$)/g, "").length == 0)) {
                $("<form method='post' ></form>").appendTo("body").form('submit', options);
            }
        },
        ajaxPost: function(url, options) {
            var opt = $.extend({}, {
                params: {},
                success: function(data) {},
                fail: function(data) {},
                tip: true
            }, options);
            $.post(url, opt.params,
                function(result) {
                    if(result) {
                        if(typeof opt.success == 'function') {
                            opt.success(result);
                        }
                        if(typeof opt.fail == 'function') {
                            opt.fail(result);
                        }
                        if(opt.tip) {
                            var tipStr;
                            if(result.bltnTip){
                                tipStr = result.msg;
                                toastr.success(tipStr,"系统提示");
                            }else{
                                tipStr = result.msg;
                                toastr.error(tipStr,"系统提示");
                            }
                            return;
                        }
                    }
                }, 'json').error(function() {
                //window.location.href="/zhongzhi/logout";
            })
        },
		ajaxGet: function(url, options) {
			var opt = $.extend({}, {
				params: {},
				success: function(data) {},
				fail: function(data) {},
				tip: true
			}, options);
			$.get(url, opt.params,function(result) {
					if(result) {
                        if(typeof opt.success == 'function') {
                            opt.success(result);
                        }
                        if(typeof opt.fail == 'function') {
                            opt.fail(result);
                        }
						if(opt.tip) {
						    var tipStr;
						    if(result.bltnTip){
                                tipStr = result.msg;
                                toastr.success(tipStr,"系统提示");
                            }else{
                                tipStr = result.msg;
                                toastr.error(tipStr,"系统提示");
                            }
							return;
						}

					}
				}, 'json').error(function() {
				//window.location.href="/zhongzhi/logout";
			})
		}
	});
})(jQuery);