package com.project.CovidTracker.controller;

import com.project.CovidTracker.models.LocationStats;
import com.project.CovidTracker.services.CoronaVirusDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataServices coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {

        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCases",totalReportedCases);
        return "home";
    }
}
