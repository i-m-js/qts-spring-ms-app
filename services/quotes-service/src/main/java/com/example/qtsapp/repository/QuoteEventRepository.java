package com.example.qtsapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.qtsapp.dto.stats.QuoteAggStatDto;
import com.example.qtsapp.entities.QuoteEvent;
import com.example.qtsapp.entities.QuoteEventId;

public interface QuoteEventRepository extends CrudRepository<QuoteEvent, QuoteEventId> {
	@Query("select  new com.example.qtsapp.dto.stats.QuoteAggStatDto(SUM(CAST(CASE WHEN qe.eventType = 0 THEN 1 ELSE 0 END AS LONG)),"
			+ " SUM(CAST(CASE WHEN qe.eventType = 1 THEN 1 ELSE 0 END AS LONG))) from QuoteEvent qe"
			+ " where qe.event.quote.id = :qid and qe.event.user.id = :uId group by qe.event.quote.id")
	@Transactional(readOnly = true)
	Optional<QuoteAggStatDto> loadUserLikeDislikeStatus(@Param("qid") Long qid, @Param("uId") Long uId);

	@Query("select  new com.example.qtsapp.dto.stats.QuoteAggStatDto(SUM(CAST(CASE WHEN qe.eventType = 0 THEN 1 ELSE 0 END AS LONG)),"
			+ " SUM(CAST(CASE WHEN qe.eventType = 1 THEN 1 ELSE 0 END AS LONG))) from QuoteEvent qe"
			+ " where qe.event.quote.id = :qid  group by qe.event.quote.id")
	Optional<QuoteAggStatDto> loadLikeDislikeStatus(@Param("qid") Long qid);

	@NativeQuery("insert into quote_events (user_id, quote_id, event_type) values (:uid, :qid, :type)")
	@Modifying
	@Transactional
	void insertUserQuoteEvent(@Param("qid") Long qId, @Param("uid") Long uid, @Param("type") int eventType);

	@NativeQuery("delete from quote_events where user_id = :uid and quote_id = :qid and  event_type = :type")
	@Modifying
	@Transactional
	void removeUserQuoteEvent(@Param("qid") Long qId, @Param("uid") Long uid, @Param("type") int eventType);

}
