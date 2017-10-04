<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v' %>
<!doctype html>
<html lang='nl'>
<head>
    <v:head title='Artikel zoeken op naam'/>
</head>
<body>
<v:menu/>
<h1>Artikel zoeken op naam</h1>
<form>
    <label>(Gedeeltelijke) Naam:<span>${fouten.naam}</span>
        <input name='naam' value='${param.naam}' required autofocus type="search" minlength="3"></label>
    <input type='submit' value='Zoeken'>
</form>
<c:if test='${not empty param and empty fouten and empty artikelen}'>
    Geen artikel(en) gevonden.
</c:if>

<c:if test="${not empty artikelen}">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Naam</th>
            <th>Aankoopprijs</th>
            <th>Verkoopprijs</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items='${artikelen}' var='artikel'>
            <tr>
                <td>${artikel.id}</td>
                <td>${artikel.naam}</td>
                <td><fmt:formatNumber value='${artikel.aankoopprijs}' minFractionDigits='2' maxFractionDigits='2'/></td>
                <td><fmt:formatNumber value='${artikel.verkoopprijs}' minFractionDigits='2' maxFractionDigits='2'/></td>
            </tr>
        </c:forEach>
        </tbody>
        <c:if test='${vanafRij != 0}'>
            <c:url value='' var='vorigePaginaURL'>
                <c:param name='van' value='${param.van}'/>
                <c:param name='tot' value='${param.tot}'/>
                <c:param name='vanafRij' value='${vanafRij - aantalRijen}'/>
            </c:url>
            <a href="<c:out value='${vorigePaginaURL}'/>" title='vorige pagina'
               class='pagineren'>&larr;</a>
        </c:if>
        <c:if test='${empty laatstePagina}'>
            <c:url value='' var='volgendePaginaURL'>
                <c:param name='van' value='${param.van}'/>
                <c:param name='tot' value='${param.tot}'/>
                <c:param name='vanafRij' value='${vanafRij + aantalRijen}'/>
            </c:url>
            <a href="<c:out value='${volgendePaginaURL}'/>" title='volgende pagina'
               class='pagineren'>&rarr;</a>
        </c:if>
    </table>
</c:if>

</body>
</html>