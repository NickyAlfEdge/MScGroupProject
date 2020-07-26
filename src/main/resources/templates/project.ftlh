<!-- Content Header (Page header) -->
<div class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1 class="m-0 text-dark">Project</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a href="/">Home</a></li>
                    <li class="breadcrumb-item active">Project</li>
                </ol>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.container-fluid -->
</div>
<!-- /.content-header -->

<!-- Main content -->
<div class="content">
    <div class="row align-items-center justify-content-between">
        <div class="col-md-2 m-2">
            <#if user.userType != 1>
            <button class="btn btn-primary" id="create_project">Create Project</button>
            </#if>
        </div>
        <div class="col-md-3">
            <div class="input-group input-group-md">
                <input type="text" id="table_search" class="form-control float-right"
                       placeholder="Project Name">
                <div class="input-group-append">
                    <button type="submit" class="btn btn-default" id="search_btn"><i
                                class="fas fa-search"></i></button>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div id="project_list" class="col-md-12">
            <#list projects as project>
                <div class="card">
                    <div class="card-header">
                        <div>
                            <h3 class="card-title">
                                <a class="text-body" data-project-detail="/project/detail/${project.projectId}"
                                   href="javascript:void(0)"
                                   onclick="modalProjectDetail(this)">${project.name}</a>
                                <#if project.status == 1>
                                    <span class="badge badge-success">Suitable</span>
                                <#elseif project.status == 2>
                                    <span class="badge badge-info">Pending</span>
                                <#else>
                                    <span class="badge badge-warning">Unsuitable</span>
                                </#if>
                            </h3>
                        </div>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body">
                        <dl>
                            <dt>Description</dt>
                            <dd>${project.brief}</dd>
                            <dt>Tags</dt>
                            <dd><#list project.projectTags as tag>
                                    <span class="badge badge-primary">${tag.tagName}</span>
                                </#list>
                            </dd>
                            <dt>Client</dt>
                            <dd>${project.user.forename} ${project.user.surname}:
                                <a href="mailto:${project.user.email}">${project.user.email}</a>
                            </dd>
                        </dl>
                    </div>
                    <div class="card-footer">
                        <!-- only student can select project-->
                        <#if user.userType==1>
                            <button class="btn btn-primary btn-sm" name="Choose"
                                    value="/project/choose/${project.projectId}" onclick="chooseProjectConfirm(this)">
                                <i class="fas fa-pencil-alt">
                                </i>
                                Choose
                            </button>
                        </#if>
                        <#if user.userType=4 || (user.userType==2&&user.userId==project.clientId)>
                            <button class="btn btn-info btn-sm" name="Edit"
                                    value="/project/detail/query/${project.projectId}" onclick="editProject(this)">
                                <i class="fas fa-pencil-alt">
                                </i>
                                Edit
                            </button>
                        </#if>
                        <#if user.userType==4>
                            <button class="btn btn-danger btn-sm" name="Delete"
                                    value="/project/delete/${project.projectId}" onclick="confirmDeleteProject(this)">
                                <i class="fas fa-trash">
                                </i>
                                Delete
                            </button>
                        </#if>
                    </div>
                    <!-- /.card-body -->
                </div>
                <!-- /.card -->
            </#list>
        </div>
    </div>
    <div class="row p-3">
        <div class="col-sm-12 col-md-5 text-left">
            <p>
                Showing <span
                        id="pageStartIndex"><#if pageInfo.total==0> 0 <#else> ${(pageInfo.pageNum - 1) * pageInfo.pageSize + 1} </#if>
                </span> to
                <span id="pageEndIndex">
                <#if (pageInfo.pageNum * pageInfo.pageSize) <= pageInfo.total>
                    ${pageInfo.pageNum * pageInfo.pageSize}
                <#else>
                    ${pageInfo.total}
                </#if></span>
                of ${pageInfo.total}
                entries
            </p>
        </div>
        <div class="col-sm-12 col-md-7">
            <ul class="pagination justify-content-end">
                <#if (pageInfo.pageSize>1) >
                    <li class="page-item <#if pageInfo.pageNum == 1>disabled</#if>"><a class="page-link" tabindex="-1">Previous</a>
                    </li>
                </#if>
                <!--when the number of total page is larger than 0, show pagination normally; if there is 0 page, only show 1 page.-->
                <#if (pageInfo.totalPage>0) >
                    <#list 1..pageInfo.totalPage as i>
                        <li class="page-item <#if i == pageInfo.pageNum>active</#if>"><a class="page-link">${i}</a></li>
                    </#list>
                <#else>
                    <li class="page-item active"><a class="page-link">1</a></li>
                </#if>
                <#if (pageInfo.pageSize>1) >
                    <li class="page-item <#if pageInfo.pageNum == pageInfo.totalPage>disabled</#if>"><a
                                class="page-link">Next</a></li>
                </#if>
            </ul>
        </div>
    </div>
</div>
<!-- /.content -->
<!--modal for update-->
<div class="modal fade" id="modal-lg-editProject">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Edit Project</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="edit_project">
                    <input id="projectId" type="text" class="form-control" name="projectId" hidden>
                    <div class="form-group">
                        <label for="name">Project Name</label>
                        <input type="text" id="name" class="form-control" name="name">
                    </div>
                    <div class="form-group">
                        <label for="description">Project Description</label>
                        <textarea id="description" class="form-control" rows="6" name="description"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="tagId">Tags</label>
                        <select class="form-control custom-select" id="tagId" name="tagId"></select>
                    </div>
                    <#if user.userType == 4>
                        <div class="form-group">
                            <label for="status">Status</label>
                            <select class="form-control custom-select" id="status" name="status">
                                <option value="2">Pending</option>
                                <option value="1">Suitable</option>
                                <option value="3">Unsuitable</option>
                            </select>
                        </div>
                    </#if>
                </form>
            </div>
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="save_project">Save changes</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- modal for delete-->
<div class="modal" id="modal_delete_confirm">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Do You want to delete this project?</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" value=""
                        onclick="deleteProject(this)">Yes
                </button>
            </div>
        </div>
    </div>
</div>
<!-- /.modal-->

<!-- modal for choose project-->
<div class="modal" id="modal_choose_confirm">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Do You want to choose this project?</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body text-left">
                <h5><i class="icon fas fa-info"></i>  Notice!</h5>
                <p>One group can only choose one project. After you chose this project, you <b>cannot switch</b> to others.</p>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" value=""
                        onclick="chooseProject(this)">Yes
                </button>
            </div>
        </div>
    </div>
</div>
<!-- /.modal-->

<!--modal for success/failure prompt-->
<div class="modal fade" id="modal-sm-success">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Success</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body text-center">
                <i class="icon fas fa-check fa-2x" style="color:green"></i>
                <span class="prompt_message"></span>
            </div>
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!--modal for success/failure prompt-->
<div class="modal fade" id="modal-sm-failure">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Failure</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body text-center">
                <i class="icon fas fa-exclamation-triangle fa-2x" style="color:red"></i>
                <span class="prompt_message"></span>
            </div>
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--modal for project detail-->
<div class="modal" id="modal_project_detail">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Project Detail</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!--./modal-->
<script src="js/mycommon.js"></script>
<script src="js/project.js"></script>
