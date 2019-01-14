package com.krzysztof.studio.boardroom;

import com.krzysztof.studio.model.db.DbBoardroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardroomRepository extends JpaRepository<DbBoardroom, String> { }
