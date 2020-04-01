package pl.edu.pwsztar.service.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.converter.Converter;
import pl.edu.pwsztar.domain.dto.CreateMovieDto;
import pl.edu.pwsztar.domain.dto.MovieDto;
import pl.edu.pwsztar.domain.entity.Movie;
import pl.edu.pwsztar.domain.repository.MovieRepository;
import pl.edu.pwsztar.service.MovieService;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final MovieRepository movieRepository;
    private final Converter<CreateMovieDto, Movie> movieConverter;
    private final Converter<List<Movie>, List<MovieDto>> movieListConverter;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository,
                            Converter<CreateMovieDto, Movie> movieConverter,
                            Converter<List<Movie>, List<MovieDto>> movieListConverter) {

        this.movieRepository = movieRepository;
        this.movieConverter = movieConverter;
        this.movieListConverter = movieListConverter;
    }

    @Override
    public List<MovieDto> findAll() {
        List<Movie> movies = movieRepository.findAll();
        return movieListConverter.convert(movies);
    }

    @Override
    public void creatMovie(CreateMovieDto createMovieDto) {
        Movie movie = movieConverter.convert(createMovieDto);
        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long movieId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        movieOptional.ifPresent(movieRepository::delete);
    }

    @Override
    public long countMovies() {
        return movieRepository.count();
    }
}