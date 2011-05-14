<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<jsp:useBean id="bean" class="cygwinRunner.server.CygwinRunnerBean"/>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>

<tr id="cygwin_location">
    <th><label for="${bean.cygwinLocation}">Cygwin Location:</label></th>
    <td>
        <props:textProperty name="${bean.cygwinLocation}" />
        <span class="smallNote">Enter path to Cygwin location.</span>
        <span class="error" id="error_${bean.cygwinLocation}"></span>
    </td>
</tr>

<forms:workingDirectory/>

<tr id="cygwin_script_code">
    <th><label for="${bean.codeValue}">Bash script:</label></th>
    <td>
        <props:multilineProperty name="${bean.codeValue}"
                                 linkTitle="Enter bash script content" 
                                 cols="58" rows="10" 
                                 expanded="${true}" />
        <span class="smallNote">TeamCity references will be replaced in the code</span>
        <span class="error" id="error_${bean.codeValue}"></span>
    </td>
</tr>                                                                                                             </tr>



