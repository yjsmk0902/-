package spring_study.concertInfo.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdom2.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring_study.concertInfo.domain.cond.cocert_search.ConcertSearchCond;
import spring_study.concertInfo.domain.cond.cocert_search.GenreCond;
import spring_study.concertInfo.domain.cond.cocert_search.SearchTypeCond;
import spring_study.concertInfo.domain.cond.cocert_search.StatusCond;
import spring_study.concertInfo.domain.dto.ConcertDetailsResponseDTO;
import spring_study.concertInfo.domain.dto.ConcertResponseDTO;
import spring_study.concertInfo.web.service.ConcertService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/search")
public class SearchController {

    private final ConcertService concertService;

    @ModelAttribute("searchTypes")
    public SearchTypeCond[] searchTypes() {
        return SearchTypeCond.values();
    }

    @ModelAttribute("genreTypes")
    public GenreCond[] genreTypes() {
        return GenreCond.values();
    }

    @ModelAttribute("statusTypes")
    public StatusCond[] statusTypes() {
        return StatusCond.values();
    }

    @GetMapping
    public String concert(@ModelAttribute("concertSearch") ConcertSearchCond concertSearch) {
        return "concerts";
    }

    @GetMapping("/list")
    public String concertList(@ModelAttribute("concertSearch") ConcertSearchCond concertSearch,
                              @RequestParam(defaultValue = "1") Integer page,
                              Model model) throws IOException, JDOMException {

        List<ConcertResponseDTO> result = concertService.findByKeywordAndDate(concertSearch, page);
        List<ConcertResponseDTO> resultBlock1 = new ArrayList<>();
        List<ConcertResponseDTO> resultBlock2 = new ArrayList<>();
        List<ConcertResponseDTO> resultBlock3 = new ArrayList<>();

        for (int i = 0; i < result.size() && i < 4; i++) {
            resultBlock1.add(result.get(i));
        }
        for (int i = 4; i < result.size() && i < 8; i++) {
            resultBlock2.add(result.get(i));
        }
        for (int i = 8; i < result.size() && i < 12; i++) {
            resultBlock3.add(result.get(i));
        }

        boolean resultEmpty = result.isEmpty();

        model.addAttribute("concertBlock1", resultBlock1);
        model.addAttribute("concertBlock2", resultBlock2);
        model.addAttribute("concertBlock3", resultBlock3);
        model.addAttribute("resultEmpty", resultEmpty);
        model.addAttribute("page", page);

        return "concerts";
    }

    @GetMapping("/info")
    public String concertDetails(@ModelAttribute("concertSearch") ConcertSearchCond concertSearch,
                                 @RequestParam(name = "show") String showId,
                                 Model model) throws IOException, JDOMException {
        ConcertDetailsResponseDTO info = concertService.findById(showId);
        log.info("info={}", info);
        model.addAttribute("info", info);
        return "concertDetail";
    }

}
