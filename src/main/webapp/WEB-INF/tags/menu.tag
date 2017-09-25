<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<nav>
	<ul>
		<li><a href="#">Artikels zoeken</a>
			<ul>
				<li><a href="<c:url value='/artikels/zoekopnummer.htm'/>">Zoeken op nummer</a></li>
				<li><a href="<c:url value='/artikels/zoekopnaam.htm'/>">Zoeken op naam</a></li>
				<li><a href="<c:url value='/artikels/perartikelgroep.htm'/>">Zoeken op naam</a></li>
			</ul></li>
		<li><a href="#">Admin</a>
			<ul>
				<li><a href="<c:url value='/artikels/toevoegen.htm'/>">Artikel toevoegen</a></li>
				<li><a href="<c:url value='/artikels/prijsverhoging.htm'/>">Prijs verhoging</a></li>
				<li><a href="<c:url value='/artikels/korting.htm'/>">Korting</a></li>
			</ul></li>
	</ul>
</nav>