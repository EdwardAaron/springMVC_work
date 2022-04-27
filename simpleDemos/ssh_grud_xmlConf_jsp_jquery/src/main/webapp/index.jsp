<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工列表</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <!--引入样式-->
    <script src="${APP_PATH}/static/js/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.4.1-dist/css/bootstrap.min.css">
    <script src="${APP_PATH}/static/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
</head>
<body>
<!--员工添加模态框-->
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">员工添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">empName</label>
                        <div class="col-sm-10">
                            <input type="text" name="empName" class="form-control" id="empName_add_input"
                                   placeholder="empName">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">email</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="email_add_input"
                                   placeholder="email@123.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender1_add_input" value="M" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender2_add_input" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">deptName</label>
                        <div class="col-sm-4">
                            <!--提交部门id-->
                            <select class="form-control" name="dId" id="dept_select">
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
            </div>
        </div>
    </div>
</div>

<!--员工修改模态框-->
<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">员工修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">empName</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="empName_update_input"></p>
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">email</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="email_update_input"
                                   placeholder="email@123.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender1_update_input" value="M" checked="checked">
                                男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender2_update_input" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">deptName</label>
                        <div class="col-sm-4">
                            <!--提交部门id-->
                            <select class="form-control" name="dId" id="dept_update_select">
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
            </div>
        </div>
    </div>
</div>

<!--员工信息列表-->
<div class="container">
    <!--标题-->
    <div class="row">
        <div class="col-md-12">
            <h2>员工信息列表</h2>
        </div>
    </div>
    <!--新增，删除按钮-->
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
            <button class="btn btn-danger"id="emp_delete_all">删除</button>
        </div>
    </div>
    <!--显示内容-->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="emps_table">
                <thead>
                <tr>
                    <th>
                        <input type="checkbox" id="check_all"></input>
                    </th>
                    <th>#</th>
                    <th>empName</th>
                    <th>gender</th>
                    <th>email</th>
                    <th>deptName</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
    <!--分页-->
    <div class="row">
        <!--分页文字信息-->
        <div class="col-md-6" id="page_info_area">

        </div>
        <!--分页条信息-->
        <div class="col-md-6" id="page_nav_area">

        </div>
    </div>
</div>
<script type="text/javascript">
    let totalRecord, currentPageNumber;

    /**
     * 回显指定元素的校验结果，结果显示指定元素的相对位置上
     * @param selector 校验元素的选择器
     * @param status 元素是正确的 success ，错误的 error
     * @param msg 回显的信息
     */
    function show_validate_msg(selector, status, msg) {
        let $ele = $(selector);
        $ele.parent().removeClass("has-success has-error");
        $ele.next("span").text("");
        if ("success" === status) {
            $ele.parent().addClass("has-success");
        } else {
            $ele.parent().addClass("has-error");
        }
        $ele.next("span").text(msg);
    }

    /**
     *  校验用户名格式
     *  @param selector 校验元素的选择器
     */
    function validate_empName_format(selector) {
        let $empName = $(selector);
        let empName = $empName.val();
        //如果不包含中文，则至少6个字符，含有中文的话至少两个字符
        let regName = /^([a-zA-Z0-9_-]{5,16})|([\u2E80-\u9FFF]{2,16})$/;
        if (!regName.test(empName)) {
            show_validate_msg(selector, "error", "用户名可以是2-16位中文，或6-16英文数字!");
            return false;
        } else {
            show_validate_msg(selector, "success", "");
            return true;
        }
    }

    /**
     *  校验邮箱格式
     *  @param selector 校验元素的选择器
     */
    function validate_email_format(selector) {
        let $email = $(selector);
        let email = $email.val();
        let regEmail = /^([a-z0-9_\.-])+@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        if (!regEmail.test(email)) {
            show_validate_msg(selector, "error", "邮箱格式不正确!");
            return false;
        } else {
            show_validate_msg(selector, "success", "");
            return true;
        }
    }

    /**
     * 新增表单校验
     */
    function validate_add_form() {
        if (!validate_empName_format("#empName_add_input")) {
            return false;
        }
        if (!validate_email_format("#email_add_input")) {
            return false;
        }
        return true;
    }

    /**
     * 清空表单数据和样式
     */
    function reset_form(selector) {
        $(selector)[0].reset();
        $(selector).find("*").removeClass("has-error has-success");
        $(selector).find(".help-block").text("");
    }

    /**
     * 查询部门，结果显示在对话框下拉列表中
     * @param selector 下拉列表选择器
     */
    function getDeptsAndShow(selector) {
        $dept_select = $(selector).empty();
        $.ajax({
            url: "${APP_PATH}/depts",
            type: "GET",
            success: function (result) {
                $.each(result.extend.deps, function () {
                    let optionEle = $("<option></option>").append(this.deptName).attr("value", this.deptId);
                    $dept_select.append(optionEle);
                });
            }
        });
    }

    /**
     * 填充emp编辑页面
     * @param  empId
     */
    function getEmpAndShow(empId) {
        $.ajax({
            url: "${APP_PATH}/emp/" + empId,
            type: "GET",
            success: function (result) {
                let empData = result.extend.emp;
                $("#empName_update_input").text(empData.empName);
                $("#email_update_input").val(empData.email);
                $("#empUpdateModal input:radio").val([empData.gender]);
                $("#empUpdateModal select").val([empData.dId]);
            }
        });
    }

    //页面完成后，发送ajax数据请求
    $(function () {
        to_page(1);
    });

    function to_page(pn) {
        //清空checkALL
        $("#check_all").prop("checked", false);
        $.ajax({
            url: "${APP_PATH}/emps",
            data: "pn=" + pn,
            type: "get",
            success: function (result) {
                // console.log(result);
                // 1、解析员工数据
                build_emps_table(result);
                // 2、解析并显示分页信息
                build_page_info(result);
                //3、显示分页条
                build_page_nav(result);
            }
        });
    }

    function build_emps_table(result) {
        $("#emps_table tbody").empty();
        let emps = result.extend.pageInfo.list;
        $.each(emps, function (index, item) {
            let $checkBox=$("<td><input type='checkbox' class='check_item'/></td>")
            let $empIdTd = $("<td></td>").append(item.empId);
            let $empNameTd = $("<td></td>").append(item.empName);
            let $genderTd = $("<td></td>").append(item.gender == 'M' ? "男" : "女");
            let $emailTd = $("<td></td>").append(item.email);
            let $deptName = $("<td></td>").append(item.department.deptName);

            let $editBtn = $("<button></button>")
                .addClass("btn btn-primary btn-sm edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
                .append("編輯");
            $editBtn.attr("edit_id", item.empId)

            let $delBtn = $("<button></button>")
                .addClass("btn btn-danger btn-sm delete_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash"))
                .append("删除");
            $delBtn.attr("delete_id", item.empId)

            let $btnTd = $("<td></td>").append($editBtn).append(" ").append($delBtn);
            $("<tr></tr>")
                .append($checkBox)
                .append($empIdTd)
                .append($empNameTd)
                .append($genderTd)
                .append($emailTd)
                .append($deptName)
                .append($btnTd)
                .appendTo("#emps_table tbody");
        });
    }

    //解析分页信息
    function build_page_info(result) {
        $("#page_info_area").empty().append("当前第" + result.extend.pageInfo.pageNum + "页，总共有" + result.extend.pageInfo.pages + "页," +
            "总共有" + result.extend.pageInfo.total + "条记录")
        totalRecord = result.extend.pageInfo.total;
        currentPageNumber = result.extend.pageInfo.pageNum;
    }

    //解析显示分页条
    function build_page_nav(result) {
        let ul = $("<ul></ul").addClass("pagination");
        //首页
        let fistPageLi = $("<li></li").append($("<a></a>").append("首页"));
        //前一页
        let prePageLi = $("<li></li").append($("<a></a>").append("&laquo;"));
        if (result.extend.pageInfo.hasPreviousPage === false) {
            fistPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        } else {
            fistPageLi.click(function () {
                to_page(1);
            });
            prePageLi.click(function () {
                to_page(result.extend.pageInfo.pageNum - 1);
            });
        }

        //后一页
        let nextPageLi = $("<li></li").append($("<a></a>").append("&raquo;").attr("href", "#"));
        //末页
        let lastPageLi = $("<li></li").append($("<a></a>").append("末页").attr("href", "#"));
        if (result.extend.pageInfo.hasNextPage === false) {
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        } else {
            nextPageLi.click(function () {
                to_page(result.extend.pageInfo.pageNum + 1);
            });
            lastPageLi.click(function () {
                to_page(result.extend.pageInfo.pages);
            });
        }

        ul.append(fistPageLi).append(prePageLi);
        $.each(result.extend.pageInfo.navigatepageNums, function (index, item) {
            let numLi = $("<li></li>").append($("<a></a>").append(item).attr("href", "#"));
            if (result.extend.pageInfo.pageNum == item) {
                numLi.addClass("active");
            }
            numLi.click(function () {
                to_page(item);
            });
            ul.append(numLi);
        });
        ul.append(nextPageLi).append(lastPageLi);
        let navElm = $("<nav></nav>").append(ul)
        $("#page_nav_area").empty().append(navElm);
    }

    //点击新增按钮，弹出对话框
    $("#emp_add_modal_btn").click(function () {
        reset_form("#empAddModal form");
        getDeptsAndShow("#dept_select");
        $("#empAddModal").modal({
            backdrop: "static"
        });
    });

    //编辑按钮，弹出编辑对话框，后来新添加的也会被绑定上
    $(document).on("click", ".edit_btn", function () {
        //form清空前一次数据
        reset_form("#empUpdateModal form");
        //存储员工的id
        $("#emp_update_btn").attr("edit_id", $(this).attr("edit_id"));
        //填充部门列表
        getDeptsAndShow("#dept_update_select");
        //填充员工信息填充
        getEmpAndShow($(this).attr("edit_id"));
        //显示编辑页面
        $("#empUpdateModal").modal({
            backdrop: "static"
        });
    });

    //单个删除
    $(document).on("click", ".delete_btn", function () {
        //弹出确认删除
        if (confirm("确认删除 " + $(this).parents("tr").find("td:eq(2)").text() + " 吗？")) {
            //发送请求删除
            let empId = $(this).attr("delete_id");
            $.ajax({
                url: "${APP_PATH}/emp/" + empId,
                type: "DELETE",
                success: function (result) {
                    alert(result.msg);
                    to_page(currentPageNumber);
                }
            });
        }
    });

    //后台校验用户名是否可用
    $("#empName_add_input").change(function () {
        let empName = this.value;
        $.ajax({
            url: "${APP_PATH}/checkempname",
            type: "GET",
            data: "empName=" + empName,
            success: function (result) {
                if (result.code === 100) {
                    show_validate_msg("#empName_add_input", "success", "用户名可用");
                    $("#emp_save_btn").attr("ajax_va", "success");
                } else {
                    show_validate_msg("#empName_add_input", "error", result.extend.val_msg);
                    $("#emp_save_btn").attr("ajax_va", "fail");
                }
            }

        })
    });

    //新增保存员工
    $("#emp_save_btn").click(function () {
        //判断用户是否可用
        if ("fail" === $("#emp_save_btn").attr("ajax_va")) {
            return;
        }
        //校验数据
        if (validate_add_form() === false) {
            return;
        }
        //发送请求
        $.ajax({
            url: "${APP_PATH}/emp",
            type: "post",
            data: $("#empAddModal form").serialize(),
            success: function (result) {
                if (result.code === 100) {
                    $("#empAddModal").modal("hide");
                    to_page(totalRecord);
                } else {
                    if (result.extend.errorFields.empName) {
                        show_validate_msg("#empName_add_input", "error",
                            result.extend.errorFields.empName);
                    }
                    if (result.extend.errorFields.email) {
                        show_validate_msg("#email_add_input", "error",
                            result.extend.errorFields.email);
                    }
                }

            }
        });
    });
    //更新保存员工
    $("#emp_update_btn").click(function () {
        if (!validate_email_format("#email_update_input")) {
            return false;
        }
        let empId = $(this).attr("edit_id");
        $.ajax({
            url: "${APP_PATH}/emp/" + empId,
            // type: "post",
            // data: $("#empUpdateModal form").serialize()+"&_method=PUT",
            type: "put",
            data: $("#empUpdateModal form").serialize(),
            success: function (result) {
                if (result.code === 100) {
                    $("#empUpdateModal").modal("hide");
                    to_page(currentPageNumber);
                } else {
                    if (result.extend.errorFields.email) {
                        show_validate_msg("#email_update_input", "error",
                            result.extend.errorFields.email);
                    }
                }
            }
        });
    });

    //全选按钮
    $("#check_all").click(function () {
        $(".check_item").prop("checked", $(this).prop("checked"));
    });
    //同步全选按钮
    $(document).on('click', ".check_item", function () {
        let checkAll = $(".check_item").length === $(".check_item:checked").length;
        $("#check_all").prop("checked", checkAll);
    });

    $("#emp_delete_all").click(function () {
        let $check_items = $(".check_item");
        if ($check_items.length === 0) {
            return;
        }
        let empNames = "";
        let ids = "";
        $.each($check_items, function (item) {
            empNames += $(this).parents("tr").find("td:eq(2)").text() + ",";
            ids+=$(this).parents("tr").find("td:eq(1)").text() + ",";
        });
        ids = ids.substr(0, ids.length - 1);
        if (confirm("确认删除 " + empNames.substr(0, empNames.length - 1) + "?")) {
            //发送请求删除
            $.ajax({
                url: "${APP_PATH}/emp/" + ids,
                type: "DELETE",
                success: function (result) {
                    alert(result.msg);
                    to_page(currentPageNumber);
                }
            });
        }
    });
</script>
</body>
</html>
