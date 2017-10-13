<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v' %>
<!doctype html>
<html lang='nl'>
<head>
    <v:head title='Artikel toevoegen'/>
</head>
<body>
<v:menu/>
<h1>Artikel toevoegen</h1>
<form method='post' id='toevoegform'>
    <label>Naam:<span>${fouten.naam}</span><input name='naam' value='${param.naam}' autofocus required></label>
    <label>Aankoopprijs:<span>${fouten.aankoopprijs}</span><input name='aankoopprijs' value='${param.aankoopprijs}' required type='number' min='0' step='0.01'></label>
    <label>Verkoopprijs:<span>${fouten.verkoopprijs}</span><input name='verkoopprijs' value='${param.verkoopprijs}' required type='number' min='0' step='0.01'></label>

    <label><span>${fouten.soort}</span>Food<input type="radio" name="soort" id="food" value="F" checked></label><br>
    <label>Houdbaarheid:<span>${fouten.houdbaarheid}</span><input name="houdbaarheid" id="houdbaarheid" value="${param.houdbaarheid}" type="number" step="1" max="365"></label>
    <label>Non Food<input type="radio" name="soort" id="nonfood" value="NF"></label><br>
    <label>Garantie:<span>${fouten.garantie}</span><input name="garantie" id="garantie" value="${param.garantie}" type="number" step="1" max="48"></label>

    <label>Artikel groep :<span>${fouten.artikelgroep}</span><select name="artikelgroepen" size="${artikelgroepen.size()}" required>
        <c:forEach items="${artikelgroepen}" var="artikelgroep">
            <option value="${artikelgroep.id}" ${artikelgroep.id == param.artikelgroepen ? 'selected' : ''}>
                    ${artikelgroep.naam}
            </option>
        </c:forEach>
    </select> </label>

    <input type='submit' value='Toevoegen' id='toevoegknop'>
</form>
<script>
    document.getElementById('toevoegform').onsubmit = function() {
        document.getElementById('toevoegknop').disabled = true;
    };

    document.getElementById('food').onclick = enableDisableInputs;
    document.getElementById('nonfood').onclick = enableDisableInputs;
    enableDisableInputs();
    function enableDisableInputs() {
        document.getElementById('houdbaarheid').disabled = !document.getElementById('food').checked;
        document.getElementById('garantie').disabled = !document.getElementById('nonfood').checked;
    }
</script>
</body>
</html>
