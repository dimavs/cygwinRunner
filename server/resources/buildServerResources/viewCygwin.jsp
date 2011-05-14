<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<jsp:useBean id="bean" class="cygwinRunner.server.CygwinRunnerBean"/>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>


<div class="parameter">
Cygwin Location: <strong><props:displayValue name="${bean.cygwinLocation}"</strong>
</div>

<props:viewWorkingDirectory />

<div class="parameter">
Script: <props:displayValue name="${bean.codeValue}"/>
</div>

