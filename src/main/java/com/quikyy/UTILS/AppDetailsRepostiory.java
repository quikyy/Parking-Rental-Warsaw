package com.quikyy.UTILS;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppDetailsRepostiory extends JpaRepository<AppDetails, Integer> {
    public AppDetails findAppDetailsByTypeEquals(String keyword);
}
