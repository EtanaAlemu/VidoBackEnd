package com.quantumtech.vido.genre;

import com.quantumtech.vido.enumeration.GenreEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/genres")
public class GenreController {

//    @Autowired
//    private final GenreService genreService;

//    public GenreController(GenreService genreService) {
//        this.genreService = genreService;
//    }

    GenreEnum genreEnum;

    @GetMapping
    public List<GenreEnum> getGenres(){
        return genreEnum.;
    }


//    @GetMapping
//    public List<GenreEnum> getGenres(){
//        return genreService.getGenres();
//    }
//
//    @PostMapping
//    public void addNewGenre(@RequestBody Genre genre){
//        genreService.addNewGenre(genre);
//    }
//
//    @DeleteMapping(path =  "{genreId}")
//    public void deleteGenre(@PathVariable("genreId") Long genreID){
//        genreService.deleteGenre(genreID);
//    }
//
//    @PutMapping (path =  "{genreId}")
//    public void updateGenre(@PathVariable("genreId") Long genreId,
//                            @RequestParam(required = false) String title){
//        genreService.updateGenre(genreId, title);
//    }
}
