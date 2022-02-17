package com.quantumtech.vido.genre;

import com.quantumtech.vido.model.Response;
import com.quantumtech.vido.movie.Movie;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@SecurityRequirement(name = "vidoapi")
@RequestMapping(path = "api/v1/genres")
public class GenreController {

    @Autowired
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }


    @GetMapping
    public ResponseEntity<Response> getGenre() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("genre", genreService.list()))
                        .message("Genres retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    //    public List<Genre> getGenres(){
//        return genreService.getGenres();
//    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getMovie(@PathVariable("id") Long id) {
        Genre genre = genreService.getGenre(id);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("genre", genre))
                        .message("Genre retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<Response> saveGenre(@RequestBody @Valid Genre genre) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("genre", genreService.createGenre(genre)))
                        .message("Genre created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
//    public void addNewGenre(@RequestBody Genre genre) {
//        genreService.addNewGenre(genre);
//    }

    @DeleteMapping(path = "{genreId}")
    public ResponseEntity<Response> deleteGenre(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("genre", genreService.deleteGenre(id)))
                        .message("Genre deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
//    public void deleteGenre(@PathVariable("genreId") Long genreID) {
//        genreService.deleteGenre(genreID);
//    }

    @PutMapping(path = "{genreId}")
    public ResponseEntity<Response> updateGenre(@RequestBody @Valid Genre genre) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("genre", genreService.updateGenre(genre)))
                        .message("Genre updated")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
//    public void updateGenre(@PathVariable("genreId") Long genreId,
//                            @RequestParam(required = false) String title) {
//        genreService.updateGenre(genreId, title);
//    }
}
