<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-sm-12">
    <div class="alert fade alert-simple alert-danger alert-dismissible text-left font__family-montserrat font__size-16 font__weight-light brk-library-rendered rendered show" role="alert" data-brk-library="component__alert">
        <i class="start-icon far fa-times-circle faa-pulse animated"></i>
        <strong class="font__weight-semibold">Oh snap!</strong> <%= request.getAttribute("error") %>
    </div>
</div>
