package dz.selma.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dz.selma.ipldashboard.model.Match;
import dz.selma.ipldashboard.model.Team;
import dz.selma.ipldashboard.repository.MatchRepository;
import dz.selma.ipldashboard.repository.TeamRepository;

@RestController
@CrossOrigin
public class TeamController {
    
    @Autowired
    private TeamRepository teamRepository;
    
    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        
        Team team = teamRepository.findByTeamName(teamName);

        team.setMatchs(matchRepository.findLatestMatchtsByTeam(teamName, 4));

        return team;
    }

     @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchsForTeam(@PathVariable String teamName, 
    @RequestParam int year){
        
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year+1, 1, 1);

        List<Match> matchs = matchRepository.
        getMatchesByTeamBetweenDates(
            teamName,
            startDate,
            endDate   
            );

        return matchs;
    }


}
