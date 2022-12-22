package spring_study.concertInfo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring_study.concertInfo.domain.dto.ConcertResponseDTO;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConcertAPI {
    private final RestTemplate restTemplate;
    private final String SERVICE_KEY = "935d9414e551433598a19a622d2c0660";
    private final String API_URL = "http://kopis.or.kr/openApi/restful/pblprfr";
    public List<ConcertResponseDTO> requestConcert(String stDate, String edDate, String keyword) throws IOException, JDOMException {
        StringBuilder OpenConcertApi = new StringBuilder();
        OpenConcertApi
                .append(API_URL)
                .append("?service=" + SERVICE_KEY)
                .append("&stdate=" + stDate)
                .append("&ddate=" + edDate)
                .append("&cpage=1")
                .append("&rows=10")
                .append("&shprfnm=" + keyword);

        log.info("URL={}", OpenConcertApi.toString());

        URL url = new URL(OpenConcertApi.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("Content-Type", "application/xml");
        conn.connect();

        List<Element> result = getXML(conn.getInputStream());

        return getDtoList(result);
    }

    private static List<ConcertResponseDTO> getDtoList(List<Element> result) {
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

    private List<Element> getXML(InputStream inputStream) throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();

        Document document = builder.build(inputStream);

        Element dbs = document.getRootElement();
        return dbs.getChildren();
    }
}
