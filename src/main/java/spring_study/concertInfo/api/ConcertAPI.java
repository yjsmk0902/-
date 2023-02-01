package spring_study.concertInfo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Service;
import spring_study.concertInfo.domain.cond.cocert_search.ConcertSearchCond;
import spring_study.concertInfo.domain.cond.cocert_search.GenreCond;
import spring_study.concertInfo.domain.cond.cocert_search.StatusCond;
import spring_study.concertInfo.domain.dto.ConcertDetailsResponseDTO;
import spring_study.concertInfo.domain.dto.ConcertResponseDTO;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConcertAPI {
    private final String SERVICE_KEY = "935d9414e551433598a19a622d2c0660";
    private final String API_URL = "http://kopis.or.kr/openApi/restful/pblprfr";
    private final String API_DETAIL_URL = "http://kopis.or.kr/openApi/restful/pblprfr";
    public List<ConcertResponseDTO> requestConcert(ConcertSearchCond cond, Integer page)
            throws IOException, JDOMException {

        String stDate, edDate;
        String genre = cond.getGenreCond() == null ? "" : getGenre(cond.getGenreCond());
        String status = cond.getStatusCond() == null ? "" : getStatus(cond.getStatusCond());
        String showName = cond.getShowName() == null ? "" : cond.getShowName();
        String showPlace = cond.getShowPlace() == null ? "" : cond.getShowPlace();

        if (cond.getStartEndDate() != null) {
            String[] date = cond.getStartEndDate()
                    .replaceAll("-", "").split("~");
            stDate = date[0];
            edDate = date[1];
        }else{
            stDate = LocalDate.now().toString();
            edDate = stDate;
        }


        StringBuilder OpenConcertApi = new StringBuilder();
        OpenConcertApi
                .append(API_URL)
                .append("?service=" + SERVICE_KEY)
                .append("&stdate=" + stDate)
                .append("&eddate=" + edDate)
                .append("&cpage=" + page)
                .append("&rows=" + 12)
                .append("&prfstate=" + status)
                .append("&shcate=" + genre);

        if (showName != "")     OpenConcertApi.append("&shprfnm=" + showName.replace(" ","+"));
        if (showPlace != "")    OpenConcertApi.append("&shprfnmfct=" + showPlace.replace(" ","+"));

        HttpURLConnection conn = getHttpURLConnection(OpenConcertApi);

        List<Element> result = getXMLs(conn.getInputStream());

        return getConcertResponseDtoList(result);
    }

    public ConcertDetailsResponseDTO requestConcertDetails(String showId) throws IOException, JDOMException {

        StringBuilder OpenConcertApi = new StringBuilder();
        OpenConcertApi
                .append(API_DETAIL_URL)
                .append("/" + showId)
                .append("?service=" + SERVICE_KEY);

        HttpURLConnection conn = getHttpURLConnection(OpenConcertApi);

        Element result = getXML(conn.getInputStream());
        return getConcertDetailsResponseDto(result);
    }

    private static HttpURLConnection getHttpURLConnection(StringBuilder OpenConcertApi) throws IOException {
        URL url = new URL(OpenConcertApi.toString());
        log.info("URL={}", OpenConcertApi.toString());

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "application/xml");
        conn.connect();
        return conn;
    }

    private static List<ConcertResponseDTO> getConcertResponseDtoList(List<Element> result) {
        List<ConcertResponseDTO> concertList = new ArrayList<>();
        for (Element response : result) {
            concertList.add(new ConcertResponseDTO(
                            response.getChildText("mt20id"),
                            response.getChildText("prfnm"),
                            response.getChildText("prfpdfrom"),
                            response.getChildText("prfpdto"),
                            response.getChildText("fcltynm"),
                            response.getChildText("poster"),
                            response.getChildText("genrenm"),
                            response.getChildText("prfstate"),
                            response.getChildText("openrun")
                    )
            );
        }
        return concertList;
    }

    private static ConcertDetailsResponseDTO getConcertDetailsResponseDto(Element response) {
        List<Element> styurls = response.getChild("styurls").getChildren();
        List<String> styImg = new ArrayList<>();
        for (Element styurl : styurls) {
            styImg.add(styurl.getText());
        }
        log.info("styImg={}", styImg);
        return new ConcertDetailsResponseDTO(
                response.getChildText("mt20id"),
                response.getChildText("mt10id"),
                response.getChildText("prfnm"),
                response.getChildText("prfpdfrom"),
                response.getChildText("prfpdto"),
                response.getChildText("fcltynm"),
                response.getChildText("prfcast"),
                response.getChildText("prfcrew"),
                response.getChildText("prfruntime"),
                response.getChildText("prfage"),
                response.getChildText("entrpsnm"),
                response.getChildText("pcseguidance"),
                response.getChildText("poster"),
                response.getChildText("sty"),
                response.getChildText("genrenm"),
                response.getChildText("prfstate"),
                response.getChildText("openrun"),
                styImg,
                response.getChildText("dtguidance"));
    }

    private List<Element> getXMLs(InputStream inputStream) throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();

        Document document = builder.build(inputStream);
        Element dbs = document.getRootElement();
        return dbs.getChildren();
    }

    private Element getXML(InputStream inputStream) throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(inputStream);
        Element dbs = document.getRootElement();
        return dbs.getChild("db");
    }

    private String getGenre(GenreCond genreCond) {
        switch (genreCond) {
            case PLAY:              return "AAAA";
            case MUSICAL:           return "GGGA";
            case DANCE:             return "BBBC";
            case CLASSIC:           return "CCCA";
            case CIRCUS:            return "EEEB";
            case GUKAK:             return "CCCC";
            case MUSIC:             return "CCCD";
            case COMPLEX:           return "EEEA";
            case DANCE_POPULAR:     return "BBBE";
            default:                return "";
        }
    }

    private String getStatus(StatusCond statusCond) {
        switch (statusCond) {
            case EXPECTED:          return "01";
            case PLAYING:           return "02";
            case COMPLETED:         return "03";
            default:                return null;
        }
    }
}
