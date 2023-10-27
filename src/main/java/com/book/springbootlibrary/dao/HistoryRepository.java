package com.book.springbootlibrary.dao;

import com.book.springbootlibrary.entity.History;
import com.book.springbootlibrary.projection.HistoryWithIdProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
//@RepositoryRestResource(excerptProjection = HistoryWithIdProjection.class)
public interface HistoryRepository extends JpaRepository<History,Long> {
    Page<History> findBooksByUserEmail(@RequestParam("email") String userEmail, Pageable pageable);
}
