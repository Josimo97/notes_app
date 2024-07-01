package com.espe.edu.ec.easynotes.Repository;

import com.espe.edu.ec.easynotes.Model.NoteJSSM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepositoryJSSM extends JpaRepository<NoteJSSM, Long> {
}