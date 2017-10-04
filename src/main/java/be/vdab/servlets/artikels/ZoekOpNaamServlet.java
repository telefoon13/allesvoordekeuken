package be.vdab.servlets.artikels;

import be.vdab.entities.ArtikelsEntity;
import be.vdab.services.ArtikelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@WebServlet(name = "ZoekOpNaamServlet", urlPatterns = "/artikels/zoekopnaam.htm")
public class ZoekOpNaamServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final transient ArtikelService artikelService = new ArtikelService();
	private static final String VIEW = "/WEB-INF/JSP/artikels/zoekopnaam.jsp";
	private static final int AANTAL_RIJEN = 20;
	private static final String NAAM_PATTERN = "^[a-zA-Z-]{3,40}$";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getQueryString() != null) {
			Map<String, String> fouten = new HashMap<>();
			String naam = request.getParameter("naam");
			int vanafRij = request.getParameter("vanafRij") == null ? 0 : Integer.parseInt(request.getParameter("vanafRij"));
			request.setAttribute("vanafRij", vanafRij);
			request.setAttribute("aantalRijen", AANTAL_RIJEN);
			Pattern pattern = Pattern.compile(NAAM_PATTERN);

			if (naam == null || naam.isEmpty()){
				fouten.put("naam", "Gelieven een naam in te vullen.");
			} else if (!pattern.matcher(naam).matches()){
				fouten.put("naam", "naam voldoet niet aan de voorwaarden (a-Z -) min 3 max 40 tekens.");
			} else {
				List<ArtikelsEntity> artikelen = artikelService.findByNaam(naam, vanafRij, AANTAL_RIJEN + 1);
				if (artikelen.size() <= AANTAL_RIJEN) {
					request.setAttribute("laatstePagina", true);
				} else {
					artikelen.remove(AANTAL_RIJEN);
				}
				request.setAttribute("artikelen", artikelen);
			}
			request.setAttribute("fouten", fouten);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
