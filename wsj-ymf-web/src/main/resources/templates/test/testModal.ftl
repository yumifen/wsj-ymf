<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript">
            $(function() {
                require(["test/js/testModal"], function(index) {

                 })
            })
        </script>
        <style>
            .modal-backdrop {
                position: fixed;
                top: 0;
                right: 0;
                bottom: 0;
                left: 0;
                z-index: 1040;
                background-color: #000;
            }
            .modal-backdrop.in {
                filter: alpha(opacity=50);
                opacity: .5;
            }
        </style>
    </head>
    <body  class="gray-bg"   style="overflow: scroll;">
      <div id="dianjiwo">
          <#--<pre  v-text="$data"></pre>-->
          <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" v-on:click="openDetail">
              打开示例窗口
          </button>
      </div>

      <div class="ibox-content">
              <div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
                  <div class="modal-dialog" style="width: 90%">
                      <div class="modal-content animated bounceInRight">
                          <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                              </button>
                              <h4 class="modal-title">模态框测试案例</h4>
                              <small class="font-bold">详情页。
                          </div>
                          <div class="modal-body">
                              <div class="wrapper">
                                  <div id="myModal_body" class="easyui-panel" > </div>
                              </div>
                          </div>
                          <div class="modal-footer">
                              <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                              <button type="button" class="btn btn-primary">保存</button>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
    </body>
</html>