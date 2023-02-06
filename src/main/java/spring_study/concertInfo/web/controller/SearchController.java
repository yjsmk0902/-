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
        return "search";
    }

    @GetMapping("/list")
    public String concertList(@ModelAttribute("concertSearch") ConcertSearchCond concertSearch,
                              @RequestParam(defaultValue = "1") Integer page,
                              Model model) throws IOException, JDOMException {

        List<ConcertResponseDTO> result = concertService.findByKeywordAndDate(concertSearch, page);

        boolean resultEmpty = result.isEmpty();

        model.addAttribute("shows",result);
        model.addAttribute("resultEmpty", resultEmpty);
        model.addAttribute("page", page);

        return "search";
    }

    @GetMapping("/info")
    public String concertDetails(@ModelAttribute("concertSearch") ConcertSearchCond concertSearch,
                                 @RequestParam(name = "show") String showId,
                                 Model model) throws IOException, JDOMException {
        ConcertDetailsResponseDTO info = concertService.findById(showId);
        log.info("info={}", info);
        model.addAttribute("info", info);
        return "searchDetails";
    }

}
