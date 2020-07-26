<!-- Content Header (Page header) -->
<div class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1 class="m-0 text-dark">User Management</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                    <li class="breadcrumb-item active">User Management</li>
                </ol>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.container-fluid -->
</div>
<!-- /.content-header -->

<!-- Main content -->
<div class="content">
    <div class="container-fluid">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">User List</h3>
            </div>
            <!-- /.card-header -->
            <div class="card-body">
                <div class="col-md-auto">
                    <button type="button" class="btn btn-primary" data-toggle="modal" id="createUser">
                        Create User
                    </button>
                </div>
                <div class="overflow-auto">
                    <table class="userTable">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Forename</th>
                                <th>Surname</th>
                                <th>Email Address</th>
                                <th>User Type</th>
                                <th>Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                        <#list users as user>
                            <tr>
                                <td>${user.userId}</td>
                                <td>${user.forename}</td>
                                <td>${user.surname}</td>
                                <td>${user.email}</td>
                                <td>
                                    <#if user.userType == 1>student</#if>
                                    <#if user.userType == 2>client</#if>
                                    <#if user.userType == 3>facilitator</#if>
                                    <#if user.userType == 4>admin</#if>
                                </td>
                                <td>
                                    <div class="d-inline-block">
                                        <button class="btn btn-info btn-sm" name="Edit" value="usermanagement/edit/${user.userId}" onclick="editUser(this)">
                                            <i class="fas fa-pencil-alt"></i>Edit</button>
                                        <button class="btn btn-danger btn-sm" name="Delete" value="usermanagement/delete/${user.userId}" onclick="confirmDeleteUser(this)">
                                            <i class="fas fa-trash"></i>Delete</button>
                                    </div>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                            <tfoot>
                                <tr>
                                    <th>ID</th>
                                    <th>Forename</th>
                                    <th>Surname</th>
                                    <th>Email Address</th>
                                    <th>User Type</th>
                                    <th>Operation</th>
                                </tr>
                            </tfoot>
                    </table>
                </div>
            </div>
        </div><!-- /.row -->
    </div><!-- /.container-fluid -->
</div>

<!-- Create User Modal -->
<div class="modal fade" id="createUserModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Enter User Information</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="createUserForm">
                    <div class="form-group">
                        <label for="userId">UserId</label>
                        <input type="text" class="form-control" id="userId" placeholder="userId">
                    </div>
                    <div class="form-group">
                        <label for="surname">Surname</label>
                        <input type="text" class="form-control" id="surname" placeholder="surname">
                    </div>
                    <div class="form-group">
                        <label for="forename">Forename</label>
                        <input type="text" class="form-control" id="forename" placeholder="forename">
                    </div>
                    <div class="form-group">
                        <label for="email">Email address</label>
                        <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email">
                        <small id="emailHelp" class="form-text text-muted">We will never share your email with anyone else.</small>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" placeholder="Password">
                    </div>
                    <div class="form-group">
                        <label for="userType">UserType</label>
                        <select id="userType">
                            <option value="1">student</option>
                            <option value="2">client</option>
                            <option value="3">facilitator</option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="createUserButton">Update</button>
            </div>
        </div>
    </div>
</div>
<!-- /.modal -->

<!-- modal for delete-->
<div class="modal" id="modal_delete_confirm">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Do You want to delete this user?</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" value=""
                        onclick="deleteUser(this)">Yes
                </button>
            </div>
        </div>
    </div>
</div><!-- /.modal-->

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
</div><!-- /.modal -->

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

<!-- /.content -->
<script src="js/mycommon.js"></script>
<script src="js/usermanagement.js"></script>
