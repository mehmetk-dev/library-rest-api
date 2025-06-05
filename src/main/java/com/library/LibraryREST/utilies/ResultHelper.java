package com.library.LibraryREST.utilies;


import com.library.LibraryREST.core.result.Result;
import com.library.LibraryREST.core.result.ResultData;
import com.library.LibraryREST.dto.response.CursorResponse;
import org.springframework.data.domain.Page;

public class ResultHelper {

    public static <T> ResultData<T> created(T data){
        return new ResultData<>(true,MessageConstants.CREATED,"201",data);
    }

    public static <T> ResultData<T> validationError(T data){
        return new ResultData<>(false, MessageConstants.VALIDATE_ERROR,"400",data);
    }

    public static <T> ResultData<T> success(T data){
        return new ResultData<>(true, MessageConstants.SUCCESS,"200",data);
    }

    public static Result notFoundError(String msg){
        return new Result(false, msg,"404");
    }
    public static Result badRequestError(String msg){
        return new Result(false, msg,"409");
    }

    public static <T> ResultData<CursorResponse<T>> cursor(Page<T> pageData){
        CursorResponse<T> cursor = new CursorResponse<>();
        cursor.setItems(pageData.getContent());
        cursor.setPageNumber(pageData.getNumber());
        cursor.setPageSize(pageData.getSize());
        cursor.setTotalElement(pageData.getTotalElements());

        return ResultHelper.success(cursor);
    }
}
