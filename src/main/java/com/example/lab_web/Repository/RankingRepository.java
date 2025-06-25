package com.example.lab_web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab_web.Model.Ranking;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
    
}
