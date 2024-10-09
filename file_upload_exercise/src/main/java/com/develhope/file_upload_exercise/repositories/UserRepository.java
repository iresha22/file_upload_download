package com.develhope.file_upload_exercise.repositories;

import com.develhope.file_upload_exercise.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
