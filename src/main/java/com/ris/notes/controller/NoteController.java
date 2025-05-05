package com.ris.notes.controller;

import com.ris.notes.model.Note;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:3000")
public class NoteController {

    private final Map<Long, Note> notes = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @GetMapping
    public List<Note> getNotes() {
        return new ArrayList<>(notes.values());
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        long id = idGenerator.incrementAndGet();
        note.setId(id);
        notes.put(id, note);
        return note;
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        notes.remove(id);
    }
}
