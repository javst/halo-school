package run.halo.app.service;

import org.springframework.lang.NonNull;
import javax.validation.constraints.NotNull;

public interface PostPriceService {


    @NonNull
    Float getPrice(@NotNull Integer postId);

}
