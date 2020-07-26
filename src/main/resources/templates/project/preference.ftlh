<div class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1 class="m-0 text-dark">Group Project</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                    <li class="breadcrumb-item active">Group Project</li>
                </ol>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.container-fluid -->
</div>
<!-- /.content-header -->
<!-- Main content -->
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <!--students can only see their own project-->
                <#if user.userType == 1>
                    <#if myProject?? == false>
                        <div class="text-center"><h3>You have not chose group.</h3></div>
                    <#elseif (myProject?size == 0 || myProject[0].projectId?? == false) >
                        <div class="text-center"><h3>You have not chose project.</h3></div>
                    <#else>
                        <div>
                            <h3 class="m-3">You have been allocated a project!</h3>
                            <!--project detail-->
                            <div class="card">
                                <div class="card-header">
                                    <h3 class="card-title">
                                        <i class="fas fa-atom my-auto"></i>
                                        ${project.name}
                                    </h3>
                                    <#if project.status == 1>
                                        <span class="float-right badge badge-success"><h4>Published</h4></span>
                                    <#elseif project.status == 2>
                                        <span class="float-right badge badge-info"><h4>Pending</h4></span>
                                    <#else>
                                        <span class="float-right badge badge-warning"><h4>Unsuitable</h4></span>
                                    </#if>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <dl>
                                        <dt>Description</dt>
                                        <dd><p style="white-space: pre-wrap;">${project.description?no_esc}</p></dd>
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
                                <!-- /.card-body -->
                            </div>
                        </div>
                    </#if>
                <#else>
                    <div class="card">
                        <div class="card-header">
                            <div class="card-tools">
                                <div class="input-group input-group-sm" style="width: 150px;">
                                    <input type="text" id="table_search" class="form-control float-right"
                                           placeholder="Group Name">
                                    <div class="input-group-append">
                                        <button type="submit" class="btn btn-default" id="search_btn"><i
                                                    class="fas fa-search"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <table class="table table-bordered projects">
                                <thead>
                                <tr>
                                    <th style="width: 3%">
                                        GroupID
                                    </th>
                                    <th style="width: 27%">
                                        Group Name
                                    </th>
                                    <th style="width: 62%">
                                        Project Name
                                    </th>
                                    <#if user.userType==4>
                                        <th style="width: 7%">
                                        </th>
                                    </#if>
                                </tr>
                                </thead>
                                <tbody>
                                <#list pageInfo.list as projectPreference>
                                    <tr>
                                        <td>
                                            ${projectPreference.groupId}
                                        </td>
                                        <td>
                                            ${projectPreference.groupName}
                                        </td>
                                        <td>
                                            ${projectPreference.projectName!"<b>Hasn't chosen project!</b>"?no_esc}
                                        </td>
                                        <!--only admin can edit-->
                                        <#if user.userType==4>
                                            <td class="project-actions text-right">
                                                <a class="btn btn-info btn-sm" href="javascript:void(0)"
                                                   onclick="modalEditGroupProject(this)"
                                                   data-groupId="${projectPreference.groupId}"
                                                   data-groupName="${projectPreference.groupName}"
                                                   data-projectId="${projectPreference.projectId!}">
                                                    <i class="fas fa-pencil-alt">
                                                    </i>
                                                    Edit
                                                </a>
                                            </td>
                                        </#if>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.card-body -->
                        <div class="card-footer clearfix">
                            <ul class="pagination pagination-sm m-0 float-right">
                                <#if (pageInfo.pageSize>1) >
                                    <li class="page-item <#if pageInfo.pageNum == 1>disabled</#if>"><a class="page-link"
                                                                                                       tabindex="-1">«</a>
                                    </li>
                                </#if>
                                <#list 1..pageInfo.totalPage as i>
                                    <li class="page-item <#if i == pageInfo.pageNum>active</#if>"><a
                                                class="page-link">${i}</a></li>
                                </#list>
                                <#if (pageInfo.pageSize>1) >
                                    <li class="page-item <#if pageInfo.pageNum == pageInfo.totalPage>disabled</#if>"><a
                                                class="page-link">»</a></li>
                                </#if>
                            </ul>
                        </div>
                    </div>
                </#if>
            </div>
        </div>
        <!-- /.row -->
    </div><!-- /.container-fluid -->
</div>
<!-- /.content -->

<!--modal for update-->
<div class="modal fade" id="modal_editGroupProject">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Edit Project</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="edit_group_project">
                    <input type="text" id="groupId" class="form-control" name="groupId" hidden>
                    <div class="form-group">
                        <label for="name">Group Name</label>
                        <input type="text" id="groupName" class="form-control" name="groupName" disabled>
                    </div>
                    <div class="form-group">
                        <label for="projectId">Project Name</label>
                        <select class="form-control custom-select" style="width: 100%" id="projectId" name="projectId">
                            <!--only admin can modify project preference-->
                            <#if user.userType == 4>
                                <#list projects as project>
                                    <option value="${project.projectId}">${project.name}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="save_group_project">Save changes</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

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
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
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
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script src="js/mycommon.js"></script>
<script src="js/projectPreference.js"></script>
