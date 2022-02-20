package com.quantumtech.vido.movie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quantumtech.vido.model.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
//@SecurityRequirement(name = "vidoapi")
//@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "api/v1/movies")
public class MovieController {

    @Autowired
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AUDIENCE')")
//    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Response> getMovies() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("movies", movieService.list(30)))
                        .message("Movies retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AUDIENCE')")
//    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Response> getMovie(@PathVariable("id") Long id) {
        Movie movie = movieService.getMovie(id);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("movie", movie))
                        .message("Movie retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Response> saveMovie(@Valid @RequestParam ("movie") String movie, @Valid  @RequestParam("file") MultipartFile file) {
        movieService.storeImage(file);
        Movie movieJson = movieService.getJson(movie);
        String fileName = file.getOriginalFilename();

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("movie", movieService.createMovie(movieJson,fileName)))
                        .message("Movie created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
//    public ResponseEntity<Response> saveMovie( @ModelAttribute Movie movie) {
////        movieService.storeImage(movie.getImage());
//        movieService.createMovie(movie);
//
//        return ResponseEntity.ok(
//                Response.builder()
//                        .timeStamp(now())
//                        .data(of("movie", movie.getId()))
//                        .message("Movie created")
//                        .status(CREATED)
//                        .statusCode(CREATED.value())
//                        .build()
//        );
//    }
//    @PostMapping
//    public ResponseEntity<Response> saveMovie( @RequestBody @Valid Movie movie) {
////        movieService.storeImage(file);
////        movie.setImageUrl("http://localhost:8080/api/v1/movies/image/"+file.getName());
//        return ResponseEntity.ok(
//                Response.builder()
//                        .timeStamp(now())
//                        .data(of("movie", movieService.createMovie(movie)))
//                        .message("Movie created")
//                        .status(CREATED)
//                        .statusCode(CREATED.value())
//                        .build()
//        );
//    }

//    @PostMapping("/image")
//    public ResponseEntity<Response> uploadImage(@RequestParam("file") MultipartFile file) {
//        movieService.storeImage(file);
////        movie.setImageUrl("http://localhost:8080/api/v1/movies/image/"+file.getName());
//        return ResponseEntity.ok(
//                Response.builder()
//                        .timeStamp(now())
//                        .data(of("image", file.getOriginalFilename()))
//                        .message("Image created")
//                        .status(CREATED)
//                        .statusCode(CREATED.value())
//                        .build()
//        );
//    }

    @DeleteMapping(path = "{movieId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Response> deleteMovie(@PathVariable("movieId") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("movie", movieService.deleteMovie(id)))
                        .message("Movie deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PutMapping(path = "{movieId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Response> updateMovie(@RequestBody @Valid Movie movie) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("movie", movieService.updateMovie(movie)))
                        .message("Movie updated")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping(value = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AUDIENCE')")
//    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public byte[] getMoviesImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/images/" + fileName));
    }
}
