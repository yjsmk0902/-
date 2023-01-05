package spring_study.concertInfo.web.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
import spring_study.concertInfo.domain.dto.ConcertResponseDTO;
import spring_study.concertInfo.web.service.ConcertService;

import java.io.IOException;
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
    public String concert(@ModelAttribute("concertSearch") ConcertSearchCond concertSearch,
                          Model model) {
        return "concerts";
    }

    @GetMapping("/list")
    public String concertList(@ModelAttribute("concertSearch") ConcertSearchCond concertSearch,
                              @RequestParam(defaultValue = "1") Integer page,
                              Model model) throws IOException, JDOMException {

        List<ConcertResponseDTO> result = concertService.findByKeywordAndDate(concertSearch, page);
        boolean resultEmpty = result.isEmpty();

        model.addAttribute("concerts", result);
        model.addAttribute("resultEmpty", resultEmpty);
        model.addAttribute("page", page);


        log.info("SearchController-concertList Active!!");
        log.info("concertSearch={}", concertSearch);
        log.info("result={}", result);
        log.info("page={}", page);

        return "concerts";
    }
}
