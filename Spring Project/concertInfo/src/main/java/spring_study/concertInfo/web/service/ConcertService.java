package spring_study.concertInfo.web.service;

import lombok.RequiredArgsConstructor;
import org.jdom2.JDOMException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_study.concertInfo.api.ConcertAPI;
import spring_study.concertInfo.domain.dto.ConcertResponseDTO;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertService {
    private final ConcertAPI concertAPI;

    @Transactional(readOnly = true)
    public List<ConcertResponseDTO> findByKeywordAndDate(String stDate, String edDate, String keyword) throws IOException, JDOMException {
        return concertAPI.requestConcert(stDate, edDate, keyword);
    }
}
