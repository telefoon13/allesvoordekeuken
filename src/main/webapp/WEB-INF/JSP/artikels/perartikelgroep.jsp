<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v' %>
<!doctype html>
<html lang='nl'>
<head>
    <v:head title='${empty artikelgroep ? "Per artikelgroep zoeken" : "Artikelgroep : ".concat(artikelgroep.naam)}'/>
</head>
<body>
<v:menu/>
<h1>${empty artikelgroep ? "Per artikelgroep zoeken" : "Artikelgroep : ".concat(artikelgroep.naam)}</h1>
<ul class='zonderbolletjes'>
    <c:forEach items='${artikelgroepen}' var='artikelgroep'>
        <c:url value='' var='url'>
            <c:param name='id' value='${artikelgroep.id}'/>
        </c:url>
        <li><a href='${url}'>${artikelgroep.naam}</a></li>
    </c:forEach>
</ul>
<c:if test='${not empty artikelgroep}'>
    <h2>${artikelgroep.naam} </h2>
    <ul class="zonderbolletjes">
        <c:forEach items='${artikelgroep.artikelsEntities}' var='artikel'>
            <li>${artikel.id}</li>
            <li>${artikel.naam}</li>
            <li>&euro; <fmt:formatNumber value='${artikel.verkoopprijs}' maxFractionDigits='2' minFractionDigits='2'/></li>
            <li style="display: block">&emsp;</li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>