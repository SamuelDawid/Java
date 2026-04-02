package com.YellowFlash.Library.Manager.repository;

import com.YellowFlash.Library.Manager.model.BorrowLog;
import com.YellowFlash.Library.Manager.model.Member;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowLogRepository extends JpaRepository<@NonNull BorrowLog,@NonNull Long> {
    List<BorrowLog> findByBook_Id(Long bookId);
    List<BorrowLog> findAllByOrderByTimeStampDesc();
    List<BorrowLog> findByAction(BorrowLog.BorrowAction action);
    List<BorrowLog> findByMemberAndAction(Member member,BorrowLog.BorrowAction action);
}
