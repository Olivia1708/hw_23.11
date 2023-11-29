package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {
    private final List<String> messages = new ArrayList<>();
    // curl localhost:8080/messages
    @GetMapping("messages")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messages);
    }
    // curl -X POST localhost:8080/messages -d "data"
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }
    // curl -X DELETE localhost:8080/messages/0
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer
                                                   index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }
    // curl -X PUT localhost:8080/messages/0 -d "data"
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }
    // curl localhost:8080/messages/search/data
    @GetMapping("/messages/search/{text}")
    public ResponseEntity<Integer> searchMessage(@PathVariable("text") String text) {
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).contains(text)) {
                return ResponseEntity.ok(i);
            }
        }
        return ResponseEntity.ok(-1);
    }
    // curl localhost:8080/messages/count
    @GetMapping("/messages/count")
    public ResponseEntity<Integer> countMessage() {
        return ResponseEntity.ok(messages.size());
    }
    // curl -X POST localhost:8080/messages/1/create -d "data"
    @PostMapping("/messages/{index}/create")
    public ResponseEntity<Void> createMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }
    // curl -X DELETE localhost:8080/messages/search/data
    @DeleteMapping("/messages/search/{text}")
    public ResponseEntity<Void> searchAndDeleteMessage(@PathVariable("text") String text) {
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).contains(text)) {
                messages.remove(i);
                i--;
            }
        }
        return ResponseEntity.accepted().build();
    }
}
