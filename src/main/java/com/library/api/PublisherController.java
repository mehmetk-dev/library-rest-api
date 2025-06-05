package com.library.api;

import com.library.business.abstracts.IPublisherService;
import com.library.core.config.modelMapper.IModelMapperService;
import com.library.core.result.Result;
import com.library.core.result.ResultData;
import com.library.dto.request.publisher.PublisherSaveRequest;
import com.library.dto.request.publisher.PublisherUpdateRequest;
import com.library.dto.response.CursorResponse;
import com.library.dto.response.publisher.PublisherResponse;
import com.library.entities.Publisher;
import com.library.utilies.MessageConstants;
import com.library.utilies.ResultHelper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {

    private final IPublisherService publisherService;
    private final IModelMapperService modelMapperService;

    public PublisherController(IPublisherService publisherService, IModelMapperService modelMapperService) {
        this.publisherService = publisherService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<PublisherResponse> save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest){
        Publisher publisher = this.modelMapperService.forRequest().map(publisherSaveRequest,Publisher.class);
        this.publisherService.save(publisher);
        return ResultHelper.created(this.modelMapperService.forResponse().map(publisher, PublisherResponse.class));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> update(@PathVariable("id")int id){
        Publisher publisher = this.publisherService.get(id);
        return ResultHelper.success(this.modelMapperService.forResponse().map(publisher, PublisherResponse.class));
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<PublisherResponse>> cursor(
            @RequestParam(name = "page", required = false,defaultValue = "0")int page,
            @RequestParam(name = "pageSize",required = false, defaultValue = "3")int pageSize
    ){
        Page<Publisher> publisherPage = this.publisherService.cursor(page,pageSize);
        Page<PublisherResponse> publisherResponcePage = publisherPage
                .map(publisher -> this.modelMapperService.forResponse().map(publisher, PublisherResponse.class));

        return ResultHelper.cursor(publisherResponcePage);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> update(@Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest){
        Publisher publisher = this.modelMapperService.forRequest().map(publisherUpdateRequest,Publisher.class);
        this.publisherService.update(publisher);
        return ResultHelper.success(this.modelMapperService.forResponse().map(publisher, PublisherResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.publisherService.delete(id);
        return new Result(true, MessageConstants.DELETED,"200");
    }

}
