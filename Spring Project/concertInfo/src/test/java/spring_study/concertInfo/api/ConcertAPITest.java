package spring_study.concertInfo.api;

import lombok.RequiredArgsConstructor;
import org.jdom2.JDOMException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring_study.concertInfo.domain.cond.cocert_search.ConcertSearchCond;
import spring_study.concertInfo.domain.dto.ConcertResponseDTO;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@RequiredArgsConstructor
class ConcertAPITest {

    @Autowired
    private final ConcertAPI concertAPI;
    @Test
    public void paging() throws IOException, JDOMException {
        List<ConcertResponseDTO> list = concertAPI.requestConcert(
                new ConcertSearchCond("", "2022-01-01~2023-01-01"));;

        for (ConcertResponseDTO responseDTO : list) {
            System.out.println("responseDTO = " + responseDTO);
        }
    }
}