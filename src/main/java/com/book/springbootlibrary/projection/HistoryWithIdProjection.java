package com.book.springbootlibrary.projection;

import com.book.springbootlibrary.entity.History;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "withId", types = History.class)
public interface HistoryWithIdProjection {
    Long getId();
    String getUserEmail();
    String getCheckoutDate();
    String getReturnedDate();
    String getTitle();
    String getAuthor();
    String getDescription();
    String getImg();
}
