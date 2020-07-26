<div class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1 class="m-0 text-dark">Group Preferences</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                    <li class="breadcrumb-item active">Group Preferences</li>
                </ol>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.container-fluid -->
</div><!-- /.content-header -->

<#if (activeUser.userType = 1)>
    <!-- My Grade Container -->
    <div class="studentPreference">
        <div class="container-fluid">
            <div class="card justify-content-center">
                <div class="card-header">
                    <h3 class="card-title">Choose Preference</h3>
                </div>
                <div class="card-body">
                    <div class="row justify-content-center">
                        <div class="alert alert-default-light" role="alert">ID of individual you wish to work with:</div>
                    </div>
                    <div class="row justify-content-center">
                        <input type="number" id="likePerson" name="vote.text2"  data-bv-notempty style="width:150px"
                               class="form-control" min="1">
                    </div>
                    <br>
                    <div class="row justify-content-center">
                        <div class="alert alert-default-light" role="alert">ID of individual #1 you don't wish to work with:</div>
                    </div>
                    <div class="row justify-content-center">
                        <input type="number" id="dislikePerson1" name="vote.text3"  data-bv-notempty style="width:150px"
                               class="form-control" min="1">
                    </div>
                    <br>
                    <div class="row justify-content-center">
                        <div class="alert alert-default-light" role="alert">ID of individual #2 you don't wish to work with:</div>
                    </div>
                    <div class="row justify-content-center">
                        <input type="number" id="dislikePerson2" name="vote.text4"  data-bv-notempty style="width:150px"
                               class="form-control" min="1">
                    </div>
                </div>
                <div class="row justify-content-center">
                    <input type="hidden" id="userID" value="${activeUser.userId}">
                    <button class="btn btn-info submitPref">Submit</button>
                </div>
                <div class="card-footer">
                </div><!-- /.card-footer -->
            </div><!-- /.card-body -->
        </div>
    </div>
</#if>

<#if (activeUser.userType = 4)>
    <!-- My Grade Container -->
    <div class="studentGrade">
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Student Preference List</h3>
                </div>
                <div class="card-body">
                    <div class="overflow-auto">
                        <table class="table table-bordered preferenceTable">
                            <thead>
                            <tr>
                                <th>StudentID</th>
                                <th>Preference</th>
                                <th>Dislike #1</th>
                                <th>Dislike #2</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list preferences as preference>
                                <tr>
                                    <td>${preference.studentId}</td>
                                    <td>${preference.likePersonId}</td>
                                    <td>${preference.dislikePersonId}</td>
                                    <td>${preference.dislikePersonTwoId}</td>
                                </tr>
                            </#list>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th>StudentID</th>
                                <th>Preference</th>
                                <th>Dislike #1</th>
                                <th>Dislike #2</th>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div><!-- /.card-container -->
        </div><!-- /.container-fluid -->
    </div><!-- /.grade -->
</#if>

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
<script src="js/mycommon.js"></script>
<script src="js/groupPreference.js"></script>