package org.choongang.calendar.CalendarController;

import lombok.RequiredArgsConstructor;
import org.choongang.calendar.Calendar;
import org.choongang.commons.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final Calendar calendar;
    private final Utils utils;

    @GetMapping
    public String index(
            @RequestParam(name="year", required = false) Integer year,
            @RequestParam(name="month", required = false) Integer month,
            Model model) {

        Map<String, Object> data = calendar.getData(year, month);
        model.addAllAttributes(data);



        return utils.tpl("calendar/index");
    }
}
