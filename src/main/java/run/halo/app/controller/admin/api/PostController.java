package run.halo.app.controller.admin.api;

import static org.springframework.data.domain.Sort.Direction.DESC;

import io.swagger.annotations.ApiOperation;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.validation.Valid;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.cache.AbstractStringCacheStore;
import run.halo.app.model.dto.post.BasePostDetailDTO;
import run.halo.app.model.dto.post.BasePostMinimalDTO;
import run.halo.app.model.dto.post.BasePostSimpleDTO;
import run.halo.app.model.entity.Category;
import run.halo.app.model.entity.Post;
import run.halo.app.model.entity.Stock;
import run.halo.app.model.enums.PostPermalinkType;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.params.PostContentParam;
import run.halo.app.model.params.PostParam;
import run.halo.app.model.params.PostQuery;
import run.halo.app.model.vo.PostDetailVO;
import run.halo.app.service.CategoryService;
import run.halo.app.service.OptionService;
import run.halo.app.service.PostService;
import run.halo.app.service.impl.CategoryServiceImpl;
import run.halo.app.service.impl.PostCategoryServiceImpl;
import run.halo.app.service.impl.StockServiceImpl;
import run.halo.app.utils.HaloUtils;

/**
 * Post controller.
 *
 * @author johnniang
 * @author ryanwang
 * @author guqing
 * @date 2019-03-19
 */
@RestController
@RequestMapping("/api/admin/posts")
public class PostController {

    private final PostService postService;

    private final AbstractStringCacheStore cacheStore;

    private final OptionService optionService;

    private final CategoryServiceImpl categoryService;

    private final StockServiceImpl stockService;


    public PostController(PostService postService,
        AbstractStringCacheStore cacheStore,
        OptionService optionService,
        StockServiceImpl stockService,
        CategoryServiceImpl categoryService) {
        this.postService = postService;
        this.cacheStore = cacheStore;
        this.optionService = optionService;
        this.categoryService = categoryService;
        this.stockService = stockService;
    }

    @GetMapping
    @ApiOperation("Lists posts")
    public Page<? extends BasePostSimpleDTO> pageBy(
        @PageableDefault(sort = {"topPriority", "createTime"}, direction = DESC) Pageable pageable,
        PostQuery postQuery,
        @RequestParam(value = "more", defaultValue = "true") Boolean more) {
        Page<Post> postPage = postService.pageBy(postQuery, pageable);
        if (more) {
            return postService.convertToListVo(postPage, true);
        }

        return postService.convertToSimple(postPage);
    }

    @GetMapping("latest")
    @ApiOperation("Pages latest post")
    public List<BasePostMinimalDTO> pageLatest(
        @RequestParam(name = "top", defaultValue = "10") int top) {
        return postService.convertToMinimal(postService.pageLatest(top).getContent());
    }

    @GetMapping("status/{status}")
    @ApiOperation("Gets a page of post by post status")
    public Page<? extends BasePostSimpleDTO> pageByStatus(
        @PathVariable(name = "status") PostStatus status,
        @RequestParam(value = "more", required = false, defaultValue = "false") Boolean more,
        @PageableDefault(sort = "createTime", direction = DESC) Pageable pageable) {
        Page<Post> posts = postService.pageBy(status, pageable);

        if (more) {
            return postService.convertToListVo(posts, true);
        }

        return postService.convertToSimple(posts);
    }

    @GetMapping("{postId:\\d+}")
    @ApiOperation("Gets a post")
    public PostDetailVO getBy(@PathVariable("postId") Integer postId) {
        Post post = postService.getById(postId);
        System.out.println(postService.convertToDetailVo(post, true));

        return postService.convertToDetailVo(post, true);
    }

    @PutMapping("{postId:\\d+}/likes")
    @ApiOperation("Likes a post")
    public void likes(@PathVariable("postId") Integer postId) {
        postService.increaseLike(postId);
    }

    @PostMapping
    @ApiOperation("Creates a post")
    public PostDetailVO createBy(@Valid @RequestBody PostParam postParam,
        @RequestParam(value = "autoSave", required = false, defaultValue = "false") Boolean autoSave
    ) {
        // Convert to
        Post post = postParam.convertTo();
        return postService.createBy(post, postParam.getTagIds(), postParam.getCategoryIds(),
            postParam.getPostMetas(), autoSave);
    }

    @PutMapping("{postId:\\d+}")
    @ApiOperation("Updates a post")
    public PostDetailVO updateBy(@Valid @RequestBody PostParam postParam,
        @PathVariable("postId") Integer postId,
        @RequestParam(value = "autoSave", required = false, defaultValue = "false") Boolean autoSave
    ) {
        // Get the post info
        Post postToUpdate = postService.getById(postId);
        System.out.println(postParam);

        System.out.println(postToUpdate);

        postParam.update(postToUpdate);

        return postService.updateBy(postToUpdate, postParam.getTagIds(), postParam.getCategoryIds(),
            postParam.getPostMetas(), autoSave);
    }

    @PutMapping("{postId:\\d+}/status/{status}")
    @ApiOperation("Updates post status")
    public BasePostMinimalDTO updateStatusBy(
        @PathVariable("postId") Integer postId,
        @PathVariable("status") PostStatus status) {
        Post post = postService.updateStatus(status, postId);

        return new BasePostMinimalDTO().convertFrom(post);
    }

    @PutMapping("status/{status}")
    @ApiOperation("Updates post status in batch")
    public List<Post> updateStatusInBatch(@PathVariable(name = "status") PostStatus status,
        @RequestBody List<Integer> ids) {
        return postService.updateStatusByIds(ids, status);
    }

    @PutMapping("{postId:\\d+}/status/draft/content")
    @ApiOperation("Updates draft")
    public BasePostDetailDTO updateDraftBy(
        @PathVariable("postId") Integer postId,
        @RequestBody PostContentParam contentParam) {
        // Update draft content
        Post post = postService.updateDraftContent(contentParam.getContent(), postId);

        return new BasePostDetailDTO().convertFrom(post);
    }

    @DeleteMapping("{postId:\\d+}")
    @ApiOperation("Deletes a photo permanently")
    public void deletePermanently(@PathVariable("postId") Integer postId) {
        postService.removeById(postId);
    }

    @DeleteMapping
    @ApiOperation("Deletes posts permanently in batch by id array")
    public List<Post> deletePermanentlyInBatch(@RequestBody List<Integer> ids) {
        return postService.removeByIds(ids);
    }

    @GetMapping(value = {"preview/{postId:\\d+}", "{postId:\\d+}/preview"})
    @ApiOperation("Gets a post preview link")
    public String preview(@PathVariable("postId") Integer postId)
        throws UnsupportedEncodingException, URISyntaxException {
        Post post = postService.getById(postId);

        post.setSlug(URLEncoder.encode(post.getSlug(), StandardCharsets.UTF_8.name()));

        BasePostMinimalDTO postMinimalDTO = postService.convertToMinimal(post);

        String token = HaloUtils.simpleUUID();

        // cache preview token
        cacheStore.putAny(token, token, 10, TimeUnit.MINUTES);

        StringBuilder previewUrl = new StringBuilder();

        if (!optionService.isEnabledAbsolutePath()) {
            previewUrl.append(optionService.getBlogBaseUrl());
        }

        previewUrl.append(postMinimalDTO.getFullPath());

        // build preview post url and return
        return new URIBuilder(previewUrl.toString())
            .addParameter("token", token)
            .build().toString();
    }

    @PostMapping("inStock")
    @ApiOperation("import stock")
    public String inStock(@Valid @RequestBody PostParam postParam,
        @RequestParam("categories") String categoriesSlug) {
        Category category = new Category();
        System.out.println(categoriesSlug);
        try {
            category.setSlug(categoriesSlug);
            category.setName(postParam.getCategoryCreate());
            category = categoryService.create(category);

        } catch (Exception e) {
            category = categoryService.getBySlug(categoriesSlug);
            System.out.println(category);
            System.out.println(e.getMessage().toString());
        }
        Set<Integer> Ids = new HashSet<>();
        Ids.add(category.getId());
        postParam.setThumbnail("/themes/device.jpeg");
        postParam.setCategoryIds(Ids);
        Post post = postParam.convertTo();
        Post postById = new Post();
        try {
            postById = postService.getByDeviceNum(post.getDeviceNum());
            postById.setStock(post.getStock() + postById.getStock());
            postById.setPrice(post.getPrice());
            postById.setNorms(post.getNorms());
            postById.setDeviceType(post.getDeviceType());
            postById.setImportPeople(post.getImportPeople());
            post = postService.update(postById);
        } catch (Exception e) {
            post.setSlug(post.getSlug() + post.getDeviceNum());
            post.setStatus(PostStatus.PUBLISHED);
            postService.createBy(post, postParam.getTagIds(), postParam.getCategoryIds(),
                postParam.getPostMetas(), false);
            post = postService.getBySlug(post.getSlug());
        } finally {
            Stock stock = new Stock();
            stock.postConvert(post);
            stock.setCategories(postParam.getCategoryCreate());
            stockService.create(stock);
        }
        if (post != null) {
            return "1";
        }
        return "0";
    }

    @PostMapping("inStockAll")
    @ApiOperation("import stock")
    public String inStock(@Valid @RequestBody List<PostParam> postParamList) {

        Set<Integer> Ids = new HashSet<>();
        Post postById = new Post();
        for (PostParam item : postParamList) {
            Ids.clear();
            Category category = new Category();
            try {
                System.out.println(item.getCategorySlug());
                category.setSlug(item.getCategorySlug());
                category.setName(item.getCategoryCreate());
                category = categoryService.create(category);
            } catch (Exception e) {
                category = categoryService.getBySlug(item.getCategorySlug());
                System.out.println(category);
                System.out.println(e.getMessage().toString());
            }
            Ids.add(category.getId());
            item.setThumbnail("/themes/device.jpeg");
            item.setCategoryIds(Ids);
            Post post = item.convertTo();
            try {
                postById = postService.getBySlug(post.getSlug() + "-" + post.getDeviceNum());
                postById.setStock(post.getStock()==null?0:post.getStock() + postById.getStock());
                postById.setPrice(post.getPrice()==null?0:post.getPrice());
                postById.setNorms(post.getNorms()==null?"":post.getNorms());
                postById.setDeviceType(post.getDeviceType()==null?"???":post.getDeviceType());
                postById.setImportPeople(post.getImportPeople());
                post = postService.update(postById);
            } catch (Exception e) {
                post.setSlug(post.getSlug() + "-" + post.getDeviceNum());
                post.setStatus(PostStatus.PUBLISHED);
                postService.createBy(post, item.getTagIds(), item.getCategoryIds(),
                        item.getPostMetas(), false);
                post = postService.getBySlug(post.getSlug());
            } finally {
                Stock stock = new Stock();
                stock.postConvert(post);
                stock.setCategories(item.getCategoryCreate());
                stockService.create(stock);
            }
        }
        return "1";
    }


}
