package be.vdab.servlets.artikels;

import be.vdab.entities.ArtikelsEntity;
import be.vdab.entities.FoodArtikels;
import be.vdab.entities.NonFoodArtikels;
import be.vdab.services.ArtikelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@WebServlet(name = "ToevoegenServlet", urlPatterns = "/artikels/toevoegen.htm")
public class ToevoegenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/toevoegen.jsp";
	private static final String REDIRECT_URL = "%s/artikels/zoekopnummer.htm?id=%d";
	private final transient ArtikelService artikelService = new ArtikelService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map<String, String> fouten = new HashMap<>();
		String naam = request.getParameter("naam");
		BigDecimal aankoopprijs = null;
		BigDecimal verkoopprijs = null;
		String soort = request.getParameter("soort");
		ArtikelsEntity artikel = null;

		if (!ArtikelsEntity.isNaamValid(naam)){
			fouten.put("naam", "Naam is verplicht");
		}

		try{
			aankoopprijs = new BigDecimal(request.getParameter("aankoopprijs"));
			if (!ArtikelsEntity.isPrijsValid(aankoopprijs)){
				fouten.put("aankoopprijs", "Aankoopprijs is niet correct (min 0.01)");
			}
		} catch (NumberFormatException ex){
			fouten.put("aankoopprijs", "Aankoopprijs is niet correct (min 0.01)");
		}

		try{
			verkoopprijs = new BigDecimal(request.getParameter("verkoopprijs"));
			if (!ArtikelsEntity.isPrijsValid(verkoopprijs)){
				fouten.put("verkoopprijs", "verkoopprijs is niet correct (min 0.01)");
			} else if (aankoopprijs.compareTo(verkoopprijs) > 0){
				fouten.put("verkoopprijs", "verkoopprijs mag niet kleiner dan de aankopprijs zijn");
			}
		} catch (NumberFormatException ex){
			fouten.put("verkoopprijs", "verkoopprijs is niet correct (min 0.01)");
		}



		if (soort.equals("F")){
			int houdbaarheid = Integer.parseInt(request.getParameter("houdbaarheid"));
			if (houdbaarheid < 1 || houdbaarheid > 365){
				fouten.put("houdbaarheid", "Houdbaarheid tussen 1 en 356 dagen.");
			} else {
				artikel = new FoodArtikels(naam,aankoopprijs,verkoopprijs, new HashSet<>(), houdbaarheid);
			}
		} else if (soort.equals("NF")){
			int garantie = Integer.parseInt(request.getParameter("garantie"));
			if (garantie < 1 || garantie > 48){
				fouten.put("garantie", "Garantie tussen 1 en 48 maand.");
			} else {
				artikel = new NonFoodArtikels(naam,aankoopprijs,verkoopprijs, new HashSet<>(), garantie);
			}
		} else {
			fouten.put("soort", "kies een soort");
		}


		if (fouten.isEmpty()) {
			artikelService.create(artikel);
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath(), artikel.getId())));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher(VIEW).forward(request, response);

	}
}
