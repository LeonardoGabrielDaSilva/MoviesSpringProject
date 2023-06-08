package pt.com.leogds.domain.movie;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pt.com.leogds.domain.movie.dto.TmdbPageData;

@FeignClient(name = "tmdbClient", url = "${api.tmdb.url}")
public interface ClientTmdb {

	@RequestMapping(method = RequestMethod.GET, value = "/discover/movie?api_key=${api.tmdb.key}") 
	TmdbPageData discoverMovies();
}
