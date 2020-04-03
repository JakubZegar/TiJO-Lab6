package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.converter.Converter;
import pl.edu.pwsztar.domain.dto.MovieCounterDto;

@Component
public class CounterMapper implements Converter<Long, MovieCounterDto> {

    @Override
    public MovieCounterDto convert(Long numberOfMovies) {
        MovieCounterDto movieCounterDto = new MovieCounterDto();
        movieCounterDto.setCounter(numberOfMovies);

        return movieCounterDto;
    }
}
