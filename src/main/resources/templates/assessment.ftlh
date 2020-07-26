<!-- Content Header (Page header) -->
<div class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1 class="m-0 text-dark">My Mark</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a href="/index">Home</a></li>
                    <li class="breadcrumb-item active">My Mark</li>
                </ol>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.container-fluid -->
</div><!-- /.content-header -->

<!-- Marking Rubric Container  -->
<div class="rubric">
    <div class="container-fluid">
        <div class="card">
            <div class="card-header">
                <div class="float-left">
                    <h3 class="card-title">Grading Rubric</h3>
                </div>
                <#if (activeUser.userType = 4)>
                    <div class="float-right">
                        <label>Clear Rubric Content: </label>
                        <button class="btn" id="clearRubricContent"><i class="fas fa-trash"></i></button>
                    </div>
                </#if>
            </div>
            <div class="card-body">
                <div class="overflow-auto">
                    <table class="rubricTable">
                        <thead>
                            <tr>
                                <th><strong>Criteria</strong></th>
                                <th><strong>Superior (&gt;70)</strong></th>
                                <th><strong>Pass (50-70)</strong></th>
                                <th><strong>Marginal Fail (40-50)</strong></th>
                                <th><strong>Unacceptable (&lt;40)&nbsp;</strong></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="criterion">
                                    <div class="card-body d-flex flex-column criterionText" contenteditable="false">
                                        <#list criterion as criteria>
                                            <#if criteria.criterionId = 1>
                                                ${criteria.criterionName}
                                            </#if>
                                        </#list>
                                    </div>
                                    <#if (activeUser.userType = 4)>
                                        <div class="criterionButtons">
                                            <button class="btn postCriterion"><i class="fas fa-check"></i></button>
                                            <button class="btn closeCriterion"><i class="fas fa-times"></i></button>
                                            <button class="btn edit"><i class="fas fa-edit"></i></button>
                                            <input type="hidden" class="criterionID" value="1">
                                        </div>
                                    </#if>
                                </td>
                                <td class="criteria">
                                    <div class="card-body d-flex flex-column criterionText" contenteditable="false">
                                        <#list criterion as criteria>
                                            <#if criteria.criterionId = 2>
                                                ${criteria.criterionName}
                                            </#if>
                                        </#list>
                                    </div>
                                    <#if (activeUser.userType = 4)>
                                        <div class="criterionButtons">
                                            <button class="btn postCriterion"><i class="fas fa-check"></i></button>
                                            <button class="btn closeCriterion"><i class="fas fa-times"></i></button>
                                            <button class="btn edit"><i class="fas fa-edit"></i></button>
                                            <input type="hidden" class="criterionID" value="2">
                                        </div>
                                    </#if>
                                </td>
                                <td class="criteria">
                                    <div class="card-body d-flex flex-column criterionText" contenteditable="false">
                                        <#list criterion as criteria>
                                            <#if criteria.criterionId = 3>
                                                ${criteria.criterionName}
                                            </#if>
                                        </#list>
                                    </div>
                                    <#if (activeUser.userType = 4)>
                                        <div class="criterionButtons">
                                            <button class="btn postCriterion"><i class="fas fa-check"></i></button>
                                            <button class="btn closeCriterion"><i class="fas fa-times"></i></button>
                                            <button class="btn edit"><i class="fas fa-edit"></i></button>
                                            <input type="hidden" class="criterionID" value="3">
                                        </div>
                                    </#if>
                                </td>
                                <td class="criteria">
                                    <div class="card-body d-flex flex-column criterionText" contenteditable="false">
                                        <#list criterion as criteria>
                                            <#if criteria.criterionId = 4>
                                                ${criteria.criterionName}
                                            </#if>
                                        </#list>
                                    </div>
                                    <#if (activeUser.userType = 4)>
                                        <div class="criterionButtons">
                                            <button class="btn postCriterion"><i class="fas fa-check"></i></button>
                                            <button class="btn closeCriterion"><i class="fas fa-times"></i></button>
                                            <button class="btn edit"><i class="fas fa-edit"></i></button>
                                            <input type="hidden" class="criterionID" value="4">
                                        </div>
                                    </#if>
                                </td>
                                <td class="criteria">
                                    <div class="card-body d-flex flex-column criterionText" contenteditable="false">
                                        <#list criterion as criteria>
                                            <#if criteria.criterionId = 5>
                                                ${criteria.criterionName}
                                            </#if>
                                        </#list>
                                    </div>
                                    <#if (activeUser.userType = 4)>
                                        <div class="criterionButtons">
                                            <button class="btn postCriterion"><i class="fas fa-check"></i></button>
                                            <button class="btn closeCriterion"><i class="fas fa-times"></i></button>
                                            <button class="btn edit"><i class="fas fa-edit"></i></button>
                                            <input type="hidden" class="criterionID" value="5">
                                        </div>
                                    </#if>
                                </td>
                            </tr>
                            <tr>
                                <td class="criterion">
                                    <div class="card-body d-flex flex-column criterionText" contenteditable="false">
                                        <#list criterion as criteria>
                                            <#if criteria.criterionId = 6>
                                                ${criteria.criterionName}
                                            </#if>
                                        </#list>
                                    </div>
                                    <#if (activeUser.userType = 4)>
                                        <div class="criterionButtons">
                                            <button class="btn postCriterion"><i class="fas fa-check"></i></button>
                                            <button class="btn closeCriterion"><i class="fas fa-times"></i></button>
                                            <button class="btn edit"><i class="fas fa-edit"></i></button>
                                            <input type="hidden" class="criterionID" value="6">
                                        </div>
                                    </#if>
                                </td>
                                <td class="criteria">
                                    <div class="card-body d-flex flex-column criterionText" contenteditable="false">
                                        <#list criterion as criteria>
                                            <#if criteria.criterionId = 7>
                                                ${criteria.criterionName}
                                            </#if>
                                        </#list>
                                    </div>
                                    <#if (activeUser.userType = 4)>
                                        <div class="criterionButtons">
                                            <button class="btn postCriterion"><i class="fas fa-check"></i></button>
                                            <button class="btn closeCriterion"><i class="fas fa-times"></i></button>
                                            <button class="btn edit"><i class="fas fa-edit"></i></button>
                                            <input type="hidden" class="criterionID" value="7">
                                        </div>
                                    </#if>
                                </td>
                                <td class="criteria">
                                    <div class="card-body d-flex flex-column criterionText" contenteditable="false">
                                        <#list criterion as criteria>
                                            <#if criteria.criterionId = 8>
                                                ${criteria.criterionName}
                                            </#if>
                                        </#list>
                                    </div>
                                    <#if (activeUser.userType = 4)>
                                        <div class="criterionButtons">
                                            <button class="btn postCriterion"><i class="fas fa-check"></i></button>
                                            <button class="btn closeCriterion"><i class="fas fa-times"></i></button>
                                            <button class="btn edit"><i class="fas fa-edit"></i></button>
                                            <input type="hidden" class="criterionID" value="8">
                                        </div>
                                    </#if>
                                </td>
                                <td class="criteria">
                                    <div class="card-body d-flex flex-column criterionText" contenteditable="false">
                                        <#list criterion as criteria>
                                            <#if criteria.criterionId = 9>
                                                ${criteria.criterionName}
                                            </#if>
                                        </#list>
                                    </div>
                                    <#if (activeUser.userType = 4)>
                                        <div class="criterionButtons">
                                            <button class="btn postCriterion"><i class="fas fa-check"></i></button>
                                            <button class="btn closeCriterion"><i class="fas fa-times"></i></button>
                                            <button class="btn edit"><i class="fas fa-edit"></i></button>
                                            <input type="hidden" class="criterionID" value="9">
                                        </div>
                                    </#if>
                                </td>
                                <td class="criteria">
                                    <div class="card-body d-flex flex-column criterionText" contenteditable="false">
                                        <#list criterion as criteria>
                                            <#if criteria.criterionId = 10>
                                                ${criteria.criterionName}
                                            </#if>
                                        </#list>
                                    </div>
                                    <#if (activeUser.userType = 4)>
                                        <div class="criterionButtons">
                                            <button class="btn postCriterion"><i class="fas fa-check"></i></button>
                                            <button class="btn closeCriterion"><i class="fas fa-times"></i></button>
                                            <button class="btn edit"><i class="fas fa-edit"></i></button>
                                            <input type="hidden" class="criterionID" value="10">
                                        </div>
                                    </#if>
                                </td>
                            </tr>
                            <tr>
                                <td class="criterion">
                                    <div class="card-body d-flex flex-column criterionText" contenteditable="false">
                                        <#list criterion as criteria>
                                            <#if criteria.criterionId = 11>
                                                ${criteria.criterionName}
                                            </#if>
                                        </#list>
                                    </div>
                                    <#if (activeUser.userType = 4)>
                                        <div class="criterionButtons">
                                            <button class="btn postCriterion"><i class="fas fa-check"></i></button>
                                            <button class="btn closeCriterion"><i class="fas fa-times"></i></button>
                                            <button class="btn edit"><i class="fas fa-edit"></i></button>
                                            <input type="hidden" class="criterionID" value="11">
                                        </div>
                                    </#if>
                                </td>
                                <td class="criteria">
                                    <div class="card-body d-flex flex-column criterionText" contenteditable="false">
                                        <#list criterion as criteria>
                                            <#if criteria.criterionId = 12>
                                                ${criteria.criterionName}
                                            </#if>
                                        </#list>
                                    </div>
                                    <#if (activeUser.userType = 4)>
                                        <div class="criterionButtons">
                                            <button class="btn postCriterion"><i class="fas fa-check"></i></button>
                                            <button class="btn closeCriterion"><i class="fas fa-times"></i></button>
                                            <button class="btn edit"><i class="fas fa-edit"></i></button>
                                            <input type="hidden" class="criterionID" value="12">
                                        </div>
                                    </#if>
                                </td>
                                <td class="criteria">
                                    <div class="card-body d-flex flex-column criterionText" contenteditable="false">
                                        <#list criterion as criteria>
                                            <#if criteria.criterionId = 13>
                                                ${criteria.criterionName}
                                            </#if>
                                        </#list>
                                    </div>
                                    <#if (activeUser.userType = 4)>
                                        <div class="criterionButtons">
                                            <button class="btn postCriterion"><i class="fas fa-check"></i></button>
                                            <button class="btn closeCriterion"><i class="fas fa-times"></i></button>
                                            <button class="btn edit"><i class="fas fa-edit"></i></button>
                                            <input type="hidden" class="criterionID" value="13">
                                        </div>
                                    </#if>
                                </td>
                                <td class="criteria">
                                    <div class="card-body d-flex flex-column criterionText" contenteditable="false">
                                        <#list criterion as criteria>
                                            <#if criteria.criterionId = 14>
                                                ${criteria.criterionName}
                                            </#if>
                                        </#list>
                                    </div>
                                    <#if (activeUser.userType = 4)>
                                        <div class="criterionButtons">
                                            <button class="btn postCriterion"><i class="fas fa-check"></i></button>
                                            <button class="btn closeCriterion"><i class="fas fa-times"></i></button>
                                            <button class="btn edit"><i class="fas fa-edit"></i></button>
                                            <input type="hidden" class="criterionID" value="14">
                                        </div>
                                    </#if>
                                </td>
                                <td class="criteria">
                                    <div class="card-body d-flex flex-column criterionText" contenteditable="false">
                                        <#list criterion as criteria>
                                            <#if criteria.criterionId = 15>
                                                ${criteria.criterionName}
                                            </#if>
                                        </#list>
                                    </div>
                                    <#if (activeUser.userType = 4)>
                                        <div class="criterionButtons">
                                            <button class="btn postCriterion"><i class="fas fa-check"></i></button>
                                            <button class="btn closeCriterion"><i class="fas fa-times"></i></button>
                                            <button class="btn edit"><i class="fas fa-edit"></i></button>
                                            <input type="hidden" class="criterionID" value="15">
                                        </div>
                                    </#if>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div><!-- /.overflow -->
            </div><!-- /.card-body -->
        </div><!-- /.card-container -->
    </div><!-- /.container-fluid -->
</div><!-- /.rubric -->

<#if (activeUser.userType = 4)>
    <!-- Student Mark Container -->
    <div class="studentMark">
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <div class="float-left">
                        <h3 class="card-title">Students</h3>
                    </div>
                    <div class="float-right">
                        <label>Clear Student Grades: </label>
                        <button class="btn" id="clearStudentMarks"><i class="fas fa-trash"></i></button>
                    </div>
                </div><!-- /.card-header -->
                <div class="card-body">
                    <div class="overflow-auto">
                        <table class="studentTable">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Forename</th>
                                    <th>Surname</th>
                                    <th>Email Address</th>
                                    <th>Group</th>
                                    <th>Evaluation</th>
                                    <th>Grade</th>
                                </tr>
                            </thead>
                            <tbody>
                                <#list students as student>
                                    <tr>
                                        <#if (student.userId = 0)>
                                            <td class="studentID">No ID</td>
                                        <#else>
                                            <td class="studentID">${student.userId}</td>
                                        </#if>
                                        <td class="studentFirstName">${student.forename}</td>
                                        <td class="studentLastName">${student.surname}</td>
                                        <#if (student.email = "No Email")>
                                            <td class="studentEmail">${student.email}</td>
                                        <#else>
                                            <td class="studentEmail"><a href="mailto:${student.email}">${student.email}</a></td>
                                        </#if>
                                        <#if (student.studentGroupId = 0)>
                                            <td class="studentGroupID">No Group ID</td>
                                        <#else>
                                            <td class="studentGroupID">${student.studentGroupId}</td>
                                        </#if>
                                        <td class="studentEvaluation"><a href="#">Evaluation Link</a></td>
                                        <#if (student.grade = 0)>
                                            <td class="studentMark">
                                                <div class="gradeField">
                                                    <input type="number" name="grade" id="studentGrade" class="form-control"
                                                           placeholder="Grade" required min="0" max="100" step="1">
                                                    <input type="hidden" id="studentID" value="${student.userId}">
                                                    <button class="btn"><i class="fas fa-plus"></i></button>
                                                </div>
                                            </td>
                                        <#else>
                                            <td class="studentMark">
                                                <div class="markField">
                                                    <input class="grade" value="${student.grade}" disabled>
                                                        <div class="editGrade">
                                                            <input type="number" name="grade" id="studentGrade" class="form-control"
                                                            placeholder="Grade" required min="0" max="100" step="1">
                                                            <button class="btn"><i class="fas fa-plus"></i></button>
                                                            <input type="hidden" id="studentID" value="${student.userId}">
                                                            <input type="hidden" id="markID" value="${student.markId}">
                                                        </div>
                                                    <button class="btn edit"><i class="fas fa-edit"></i></button>
                                                </div>
                                            </td>
                                        </#if>
                                    </tr>
                                </#list>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>ID</th>
                                    <th>Surname</th>
                                    <th>Forename</th>
                                    <th>Email Address</th>
                                    <th>Group</th>
                                    <th>Evaluation</th>
                                    <th>Grade</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div><!-- /.overflow -->
                </div><!-- /.card.body -->
            </div><!-- /.card -->
        </div><!-- /.container-fluid -->
    </div><!-- /.student_mark -->
</#if>

<#if (activeUser.userType = 1)>
    <!-- My Grade Container -->
    <div class="studentGrade">
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">My Grade</h3>
                </div>
                <div class="card-body">
                    <div class="overflow-auto">
                        <table class="studentGradeTable">
                            <thead>
                                <tr>
                                    <th>Project</th>
                                    <th>Client</th>
                                    <th>Group</th>
                                    <th>Marked</th>
                                    <th>Evaluation Result</th>
                                    <th>Grade</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${userProject}</td>
                                    <td>${clientName}</td>
                                    <#if (activeUser.studentGroupId)?has_content>
                                        <td>${activeUser.studentGroupId}</td>
                                    <#else>
                                        <td>No Group</td>
                                    </#if>
                                    <#if (userMark = 0)>
                                        <td><i class="fas fa-times"></i></td>
                                    <#else>
                                        <td><i class="fas fa-check"></i></td>
                                    </#if>
                                    <td class="studentEvaluation"><a href="#">Evaluation Link</a></td>
                                    <#if (userMark = 0)>
                                        <td>No Mark Awarded</td>
                                    <#else>
                                        <#if (userMark >= 70)>
                                            <td><span class="badge badge-success">${userMark}/100</span></td>
                                        <#elseif (userMark >= 50 && userMark < 70)>
                                            <td><span class="badge badge-warning">${userMark}/100</span></td>
                                        <#else>
                                            <td><span class="badge badge-danger">${userMark}/100</span></td>
                                        </#if>
                                    </#if>
                                </tr>
                            </tbody>
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

<!--modal for clear marks/clear rubric content prompt-->
<div class="modal fade" id="modal-sm-clear">
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
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!--modal for group Eval-->
<div class="modal fade" id="modal-lg-groupEval">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Group Eval Form</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body text-center">
                <br><span class="prompt_message"></span>
                <br>
                <br>
                <form id="groupEval">
                    <div class="form-group">
                        <label>You</label>
                        <input type="number" min="0" max="100" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Group Member #2</label>
                        <input type="number" min="0" max="100" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Group Member #3</label>
                        <input type="number" min="0" max="100" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Group Member #4</label>
                        <input type="number" min="0" max="100" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Group Member #5</label>
                        <input type="number" min="0" max="100" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Group Member #6</label>
                        <input type="number" min="0" max="100" class="form-control">
                    </div>
                </form>
            </div>
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Submit</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="js/markingPage.js"></script>