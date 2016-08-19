<!--% 
    Document   : categories.jsp
    Created on : Aug 18, 2016, 4:50:35 PM
    Author     : roberto.gatti
%-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"        prefix="c"    %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Categories Informations</h2>
<ul>
    <c:forEach var="cat" items="${categories}">
        <li>
            <p>${cat.title} -- ${cat.description}</p>
        </li>
    </c:forEach>    
</ul>
<form:form method="post" action="categories" modelAttribute="category">
   <table>
    <tr>
        <td><label>Titolo</label></td>
        <td><form:input path="title"/></td>
    </tr>
    <tr>
        <td><label>Descrizione</label></td>
        <td><form:input path="description" /></td>
    </tr>
    <tr>
        <td><label>Colore</label></td>
        <td><form:input path="color" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>