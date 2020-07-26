<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="/index" class="brand-link">
        <img src="img/uos-crest.png" alt="uos Logo" class="brand-image img-fluid elevation-3"
             style="opacity: .8">
        <span class="brand-text font-weight-light">TeamProject</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
                <img src="dist/img/avatar.png" class="img-circle elevation-2" alt="User Image">
            </div>
            <div class="info">
                <a class="d-block"><#if user??>${user.forename} ${user.surname}</#if></a>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu"
                data-accordion="false" id="menu"><!-- Add icons to the links using the .nav-icon class
                                                      with font-awesome or any other icon font library -->
            </ul>
        </nav><!-- /.sidebar-menu -->
    </div><!-- /.sidebar -->
</aside>
