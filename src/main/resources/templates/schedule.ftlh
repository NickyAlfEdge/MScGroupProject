<!-- Content Header (Page header) -->
<div class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1 class="m-0 text-dark">Schedule</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a href="/">Home</a></li>
                    <li class="breadcrumb-item active">Schedule</li>
                </ol>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.container-fluid -->
</div>
<!-- /.content-header -->

<!-- Main content -->
<div class="container-fluid">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <div class="col-md-2 m-2">
                        <#if user.userType == 4>
                            <button type="button" class="btn btn-info btn-sm" id="update">Update</button>
                            <button class="btn btn-danger btn-sm" name="Auto"
                                    value="/schedule/generate" onclick="confirmGenerateSchedules(this)">
                                Auto
                            </button>
                        </#if>
                    </div>
                    <div class="overflow-auto">
                    <table id="schedule" class="table table-bordered table-responsive-md table-striped text-center">
                        <thead>
                        <tr>
                            <th rowspan="2"></th>
                            <th rowspan="2" Name="groupId">Team</th>
                            <th rowspan="2">Client</th>
                            <th rowspan="2">Facilitator</th>
                            <th rowspan="2" Name="day">Day</th>
                            <th colspan="2">Week 1/2</th>
                            <th colspan="2">Week 2/3</th>
                            <th colspan="2">Week 3/4</th>
                            <th colspan="2">Week 4/5</th>
                            <th colspan="2">Week 5/6</th>
                            <th>Week 6/7</th>
                            <th>Week 7/8</th>
                            <th>Week 8/9</th>
                            <th colspan="2">Week 9/10</th>
                            <th>Week 11</th>
                        </tr>
                        <tr>
                            <th>Client</th>
                            <th>Facilitator</th>
                            <th>Client</th>
                            <th>Facilitator</th>
                            <th>Client</th>
                            <th>Facilitator</th>
                            <th>Client</th>
                            <th>Facilitator</th>
                            <th>Client</th>
                            <th>Facilitator</th>
                            <th colspan="3">Facilitator</th>
                            <th>Client</th>
                            <th>Facilitator</th>
                            <th>Facilitator</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list schedules as schedule>
                        <tr>
                            <td>
                                <div class="icheck-blue " aria-checked="false" aria-disabled="false" style="position:relative;">
                                    <input  name="CheckBox" type="checkbox">
                                </div>
                            </td>
                            <#if (schedule.group.groupId)??>
                                <td> ${schedule.group.groupId}</td>
                            <#else>
                                <td>No Group</td>
                            </#if>
                            <#if (schedule.client)??>
                                <td> ${schedule.client}</td>
                            <#else>
                                <td>No Client</td>
                            </#if>
                            <#if (schedule.facilitator)??>
                                <td> ${schedule.facilitator}</td>
                            <#else>
                                <td>No Facilitator</td>
                            </#if>
                            <td EditType="DropDownList" DataItems="{text:'Monday',value: 1},{text:'Tuesday',value: 2},{text:'Wednesday',value: 3},{text:'Thursday',value: 4},{text:'Friday',value: 5}" Value= ${schedule.day}>
                                <#if schedule.day == 1>
                                    Monday
                                <#elseif schedule.day ==2>
                                    Tuesday
                                <#elseif schedule.day ==3>
                                    Wednesday
                                <#elseif schedule.day ==4>
                                    Thursday
                                <#else>
                                    Friday
                                </#if>
                            </td>
                            <td EditType="TimeBox">
                                <#list schedule.groupSchedules as time>
                                <#if time.weekNo == 1 && time.meetingType == 1>
                                ${time.meetingTime?string('HH:mm')}
                                </#if>
                            </#list>
                            </td>
                            <td EditType="TimeBox">
                                <#list schedule.groupSchedules as time>
                                <#if time.weekNo == 1 && time.meetingType == 2>
                                ${time.meetingTime?string('HH:mm')}
                                </#if>
                            </#list>
                            </td>
                            <td EditType="TimeBox">
                                <#list schedule.groupSchedules as time>
                                <#if time.weekNo == 2 && time.meetingType == 1>
                                ${time.meetingTime?string('HH:mm')}
                                </#if>
                            </#list>
                            </td>
                            <td EditType="TimeBox">
                                <#list schedule.groupSchedules as time>
                                <#if time.weekNo == 2 && time.meetingType == 2>
                                ${time.meetingTime?string('HH:mm')}
                                </#if>
                            </#list>
                            </td>
                            <td EditType="TimeBox">
                                <#list schedule.groupSchedules as time>
                                <#if time.weekNo == 3 && time.meetingType == 1>
                                ${time.meetingTime?string('HH:mm')}
                                </#if>
                            </#list>
                            </td>
                            <td EditType="TimeBox">
                                <#list schedule.groupSchedules as time>
                                <#if time.weekNo == 3 && time.meetingType == 2>
                                ${time.meetingTime?string('HH:mm')}
                                </#if>
                            </#list>
                            </td>
                            <td EditType="TimeBox">
                                <#list schedule.groupSchedules as time>
                                <#if time.weekNo == 4 && time.meetingType == 1>
                                ${time.meetingTime?string('HH:mm')}
                                </#if>
                            </#list>
                            </td>
                            <td EditType="TimeBox">
                                <#list schedule.groupSchedules as time>
                                <#if time.weekNo == 4 && time.meetingType == 2>
                                ${time.meetingTime?string('HH:mm')}
                                </#if>
                            </#list>
                            </td>
                            <td EditType="TimeBox">
                                <#list schedule.groupSchedules as time>
                                <#if time.weekNo == 5 && time.meetingType == 1>
                                ${time.meetingTime?string('HH:mm')}
                                </#if>
                            </#list>
                            </td>
                            <td EditType="TimeBox">
                                <#list schedule.groupSchedules as time>
                                <#if time.weekNo == 5 && time.meetingType == 2>
                                ${time.meetingTime?string('HH:mm')}
                                </#if>
                            </#list>
                            </td>
                            <td EditType="TimeBox">
                                <#list schedule.groupSchedules as time>
                                <#if time.weekNo == 6 && time.meetingType == 2>
                                ${time.meetingTime?string('HH:mm')}
                                </#if>
                            </#list>
                            </td>
                            <td EditType="TimeBox">
                                <#list schedule.groupSchedules as time>
                                <#if time.weekNo == 7 && time.meetingType == 2>
                                ${time.meetingTime?string('HH:mm')}
                                </#if>
                            </#list>
                            </td>
                            <td EditType="TimeBox">
                                <#list schedule.groupSchedules as time>
                                <#if time.weekNo == 8 && time.meetingType == 2>
                                ${time.meetingTime?string('HH:mm')}
                                </#if>
                            </#list>
                            </td>
                            <td EditType="TimeBox">
                                <#list schedule.groupSchedules as time>
                                <#if time.weekNo == 9 && time.meetingType == 1>
                                ${time.meetingTime?string('HH:mm')}
                                </#if>
                            </#list>
                            </td>
                            <td EditType="TimeBox">
                                <#list schedule.groupSchedules as time>
                                <#if time.weekNo == 9 && time.meetingType == 2>
                                ${time.meetingTime?string('HH:mm')}
                                </#if>
                            </#list>
                            </td>
                            <td EditType="TimeBox">
                                <#list schedule.groupSchedules as time>
                                <#if time.weekNo == 11 && time.meetingType == 2>
                                ${time.meetingTime?string('HH:mm')}
                                </#if>
                            </#list>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                    </div>
                </div>
                <!-- /.card-body -->
            </div>
            <!-- /.card -->
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</div><!-- /.content -->

<!-- modal for generate-->
<div class="modal" id="modal_generate_confirm">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Auto-shceduling?</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" value=""
                        onclick="generateSchedules(this)">Yes
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
<script src="js/schedule.js"></script>
<script>
    var tableId = document.getElementById("schedule");
    <#if user.userType == 4>
    SetTableCanEdit(tableId);
    </#if>
</script>
