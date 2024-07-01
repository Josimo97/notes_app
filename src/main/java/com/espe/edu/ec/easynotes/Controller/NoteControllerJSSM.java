package com.espe.edu.ec.easynotes.Controller;

import com.espe.edu.ec.easynotes.Exceptions.ResourceNotFoundExceptionJSSM;
import com.espe.edu.ec.easynotes.Model.NoteJSSM;
import com.espe.edu.ec.easynotes.Repository.NoteRepositoryJSSM;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteControllerJSSM {

    @Autowired
    NoteRepositoryJSSM noteRepository;

    // Get All Notes
    @GetMapping("/notes")
    public List<NoteJSSM> getAllNotes() {
        return noteRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/notes")
    public NoteJSSM createNote(@Valid @RequestBody NoteJSSM note) {
        return noteRepository.save(note);
    }

    // Get a Single Note
    @GetMapping("/notes/{id}")
    public NoteJSSM getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundExceptionJSSM("Note", "id", noteId));
    }

    // Update a Note
    @PutMapping("/notes/{id}")
    public NoteJSSM updateNote(@PathVariable(value = "id") Long noteId,
                               @Valid @RequestBody NoteJSSM noteDetails) {
        NoteJSSM note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundExceptionJSSM("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        NoteJSSM updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    // Delete a Note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        NoteJSSM note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundExceptionJSSM("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}