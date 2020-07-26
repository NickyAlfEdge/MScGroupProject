<!-- Content Header (Page header) -->
<div class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1 class="m-0 text-dark">Group</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a href="/">Home</a></li>
                    <li class="breadcrumb-item active">Group</li>
                </ol>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.container-fluid -->
</div>
<!-- /.content-header -->

<!-- My Grade Container -->
<#if user.userType == 1>
    <div class="studentGroup">
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Group List</h3>
                </div>
                <div class="card-body">
                    <div class="overflow-auto">
                        <table class="studentGroupList">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Forename</th>
                                <th>Surname</th>
                                <th>Email</th>
                            </tr>
                            </thead>
                            <tbody class="align-content-center">
                            <#list group.groupMembers as member>
                                <tr>
                                    <td>${member.userId}</td>
                                    <td>${member.forename}</td>
                                    <td>${member.surname}</td>
                                    <td><a href="mailto:${member.email}">${member.email}</a></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div><!-- /.card-container -->
        </div><!-- /.container-fluid -->
    </div><!-- /.grade -->
<#else>
    <div class="groupList">
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <div class="row">
                        <h3 class="card-title">Group List</h3>
                        <#if user.userType == 4>
                            <div class="col-md-2 m-2">
                                <button type="button" class="btn btn-info btn-sm" onclick="autoAllocation()">Auto
                                    Allocation
                                </button>
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="card-body">
                    <div class="overflow-auto">
                        <table class="studentGroupTable">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Group Name</th>
                                <th>Facilitator ID</th>
                                <th>Operation</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list groups as group>
                                <tr>
                                    <td>${group.groupId}</td>
                                    <td>${group.name}</td>
                                    <td>${group.facilitatorId!"No Facilitator"}</td>
                                    <td class="text-center">
                                        <button type="button" class="btn btn-info btn-sm details" value="${group.groupId}">
                                            Details
                                        </button>
                                        <#if (user.userType = 4)>
                                            <button type="button" class="btn btn-primary btn-sm edit" value="${group.groupId}">
                                                Edit
                                            </button>
                                            <button type="button" class="btn btn-danger btn-sm delete" value="${group.groupId}">
                                                <i class="fas fa-trash"></i>
                                                Delete
                                            </button>
                                        </#if>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th>ID</th>
                                <th>Group Name</th>
                                <th>Facilitator ID</th>
                                <th>Operation</th>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div><!-- /.card-container -->
        </div><!-- /.container-fluid -->
    </div><!-- /.grade -->
</#if>

<!-- Group details Modal -->
<div class="modal fade" id="group-detail">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Group Information</h5>
                <label type="hidden" id="getGroupId" value=""></label>
            </div>
            <div class="modal-body">
                <label for="groupMembers">Group Members</label>
                <table class="studentGroupList">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Forename</th>
                        <th>Surname</th>
                        <th>Email</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table><!--data list/-->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div><!--/ Edit Group Information Modal -->

<!-- new Group Modal -->
<div class="modal fade" id="groupExampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Enter group Information</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="new-group">
                    <div class="form-group">
                        <label for="groupId">Group ID</label>
                        <input type="text" class="form-control" id="groupId" placeholder="groupId">
                    </div>
                    <div class="form-group">
                        <label for="groupName">Group Name</label>
                        <input type="text" class="form-control" id="groupName" placeholder="groupName">
                    </div>
                    <div class="form-group">
                        <label for="facilitorId">Facilitator ID</label>
                        <input type="text" class="form-control" id="facilitorId" placeholder="facilitorId">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Create/Delete Group</button>
            </div>
        </div>
    </div>
</div>
<!--/.new Group Modal -->

<!--modal for deleting group prompt-->
<div class="modal fade" id="modal-sm-delete">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Are you sure?</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body text-center">
                <i class="icon fas fa-user-slash fa-2x" style="color:black"></i>
                <br><span class="prompt_message"></span>
            </div>
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" id="confirmDelete" data-dismiss="modal">Confirm</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Exit</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->

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
                <br><span class="prompt_message"></span>
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
                <br><span class="prompt_message"></span>
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

<!--modal for edit group detail-->
<div class="modal fade" id="modal_group_editGroup">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Group Detail</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="groupId">Group ID</label>
                        <input type="text" class="form-control" id="groupId" placeholder="groupId">
                    </div>
                    <div class="form-group">
                        <label for="groupName">Group Name</label>
                        <input type="text" class="form-control" id="groupName" placeholder="groupName">
                    </div>
                    <div class="form-group">
                        <label for="facilitatorId">Facilitator ID</label>
                        <input type="text" class="form-control" id="facilitatorId" placeholder="facilitatorId">
                    </div>
                </form>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!--./modal for group details-->

<!-- /.content -->
<script src="js/mycommon.js"></script>
<script src="js/groupManagement.js"></script>
