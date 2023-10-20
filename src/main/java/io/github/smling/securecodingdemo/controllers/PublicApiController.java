package io.github.smling.securecodingdemo.controllers;

import io.github.smling.securecodingdemo.models.EntryCollection;
import io.github.smling.securecodingdemo.services.PublicApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class PublicApiController {
    final PublicApiService publicApiService;

    @Autowired
    public PublicApiController(PublicApiService publicApiService) {
        this.publicApiService = publicApiService;
    }

    @GetMapping("/entities")
    public Mono<EntryCollection> getEntries(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "auth", required = false) String auth,
            @RequestParam(value = "https", required = false, defaultValue = "true") boolean https,
            @RequestParam(value = "cors", required = false) String cors,
            @RequestParam(value = "category", required = false) String category
    ) {
        return publicApiService.getEntries(title, description, auth, https, cors, category);
    }
}
