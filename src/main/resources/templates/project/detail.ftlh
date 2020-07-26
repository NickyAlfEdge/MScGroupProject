<!-- Main content -->
<div class="content">
    <div class="row">
        <div class="col-md-12">
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
                        <dt>Groups</dt>
                    </dl>
                    <div class="row m-0">
                        <div class="col-5 m-0">
                            <table class="table table-bordered">
                                <tbody>
                                <#list groups as group>
                                    <tr>
                                        <td>${group.name}</td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- /.card-body -->
            </div>
            <!-- /.card -->
        </div>
    </div>
</div>
<!-- /.content -->
