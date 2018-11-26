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
    <body class="gray-bg fixed-sidebar full-height-layout "   style="overflow: scroll;">
      <div  class="col-sm-12" class="wrapper wrapper-content animated fadeInRight">
          <div class="row" id="vueApp">
              <div class="ibox float-e-margins">
                   <div class="ibox-content">
                     <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper form-inline" role="grid">
                       <rows-template></rows-template>
                       <table class="table table-striped table-bordered table-hover">
                           <thead>
                           <tr role="row">
                               <th class="bs-checkbox " style="width: 36px;text-align: center" data-field="state" >
                                   <div class="th-inner "><input name="btSelectAll" type="checkbox"></div>
                                   <div class="fht-cell" style="width: 36px;"></div>
                               </th>
                               <th class="sorting_asc" style="width: 260px;text-align: center">ID</th>
                               <th class="sorting" style="width: 459px;text-align: center">名称</th>
                               <th class="sorting" style="width: 433px;text-align: center">权限</th>
                           </tr>
                           </thead>
                           <tbody>
                           <tr  class="gradeA odd" v-for="(menu,index) in dataArray">
                               <td style="text-align: center"><input type="checkbox"/></td>
                               <td style="text-align: center">{{menu.id}}</td>
                               <td style="text-align: center" >{{opName(menu.name)}}</td>
                               <td style="text-align: center">{{menu.permission}}</td>
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