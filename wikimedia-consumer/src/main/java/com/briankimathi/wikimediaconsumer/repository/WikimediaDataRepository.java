package com.briankimathi.wikimediaconsumer.repository;

import com.briankimathi.wikimediaconsumer.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {

}
