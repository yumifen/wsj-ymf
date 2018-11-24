<html>
    <head>
        <script type="text/javascript">
            $(function() {
                require(["test/js/index"], function(index) {
                    index.init();
                    index.form();
                })
            })
        </script>
    </head>
    <body class="gray-bg">
      <div  class="col-sm-12" class="wrapper wrapper-content animated fadeInRight">
          <div class="row" id="vueApp">
              <div class="ibox float-e-margins">
                   <div class="ibox-content">
                     <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper form-inline" role="grid">
                       <rows-template></rows-template>
                       <table class="table table-hover table-striped table-bordered table-sm">
                           <thead>
                           <tr role="row">
                               <th class="bs-checkbox " style="width: 36px; " data-field="state" tabindex="0">
                                   <div class="th-inner "><input name="btSelectAll" type="checkbox"></div>
                                   <div class="fht-cell" style="width: 36px;"></div>
                               </th>
                               <th class="sorting_asc" tabindex="0" aria-controls="editable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="渲染引擎：激活排序列升序" style="width: 260px;">ID</th>
                               <th class="sorting" tabindex="0" aria-controls="editable" rowspan="1" colspan="1" aria-label="浏览器：激活排序列升序" style="width: 459px;">名称</th>
                               <th class="sorting" tabindex="0" aria-controls="editable" rowspan="1" colspan="1" aria-label="平台：激活排序列升序" style="width: 433px;">权限</th>
                           </tr>
                           </thead>
                           <tbody>
                           <tr  class="gradeA odd" v-for="(menu,index) in dataArray">
                               <td><input type="checkbox"/></td>
                               <td>{{menu.id}}</td>
                               <td>{{menu.name}}</td>
                               <td>{{menu.permission}}</td>
                           <#--<td v-if="user.disenable == 0">正常</td>-->
                           <#--<td v-else>注销</td>-->
                           </tr>
                           </tbody>
                       </table>
                       <pagegation-template></pagegation-template>
                     </div>
                   </div>
              </div>
          </div>
      </div>
    </body>
</html>