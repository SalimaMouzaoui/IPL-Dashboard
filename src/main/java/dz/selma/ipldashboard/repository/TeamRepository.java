package dz.selma.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import dz.selma.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
    
    Team findByTeamName(String teamName);
}
