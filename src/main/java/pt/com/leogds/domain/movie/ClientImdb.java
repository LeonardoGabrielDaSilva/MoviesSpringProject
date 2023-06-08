package pt.com.leogds.domain.movie;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pt.com.leogds.domain.movie.dto.ImdbListData;

@FeignClient(name = "ImdbClient", url = "${api.imdb.url}")
public interface ClientImdb {

	@RequestMapping(method = RequestMethod.GET, value = "/Top250Movies/${api.imdb.key}")
	ImdbListData findTop250Movies();
}
